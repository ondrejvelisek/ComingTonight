package com.muni.comingtonight.service

import android.annotation.TargetApi
import com.muni.comingtonight.model.Activity
import com.muni.comingtonight.model.Category
import com.muni.comingtonight.service.ShowRatingServiceOmdb
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gildor.coroutines.retrofit.await
import java.text.SimpleDateFormat
import java.util.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList

import javax.xml.parsers.DocumentBuilderFactory


/**
 * Implementation based on Ct service http://www.ceskatelevize.cz/xml/tv-program/
 */
@TargetApi(24)
class TvProgramServiceCt : TvProgramService
{
    private val showRatingService = ShowRatingServiceOmdb()
    var name = String()

    val ctApi = Retrofit.Builder()
            .baseUrl("http://www.ceskatelevize.cz/services/programme/xml/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(com.muni.comingtonight.api.CTApi::class.java)


    override suspend fun getTodaysMovies(): List<Activity> {

        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        val cal = Date()
        val channel = "ct1"
        val tvProgram = ctApi.todaysProgram(dateFormat.format(cal), channel).await()

        val xmlDoc: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(tvProgram)
        xmlDoc.documentElement.normalize()

        var movieArray = ArrayList<Activity> (200)

        //Parsing complexType element "porad"
        val movieList: NodeList = xmlDoc.getElementsByTagName("porad")

        for(i in 0 until movieList.length - 1)
        {
            var movieNode: Node = movieList.item(i)

            if (movieNode.getNodeType() === Node.ELEMENT_NODE)
            {
                val elem = movieNode as Element

                //Parsing simple element "porad/cas"
                val time = elem.getAttribute("cas")

                //Parsing complexType element "nazvy"
                val nameList: NodeList = movieNode.getElementsByTagName("nazvy")
                var nameNode: Node = nameList.item(0)

                if (nameNode.getNodeType() === Node.ELEMENT_NODE)
                {
                    val elem2 = nameNode as Element

                    //Parsing simple element "porad/nazvy/nazev"
                    name = elem2.getAttribute("nazev")

                    //Filtering shows by time
                    if ((time >= "19:00") && (time < "23:59"))
                    {
                        val movieInfo = showRatingService.getRating(name)
                        val tmp = Activity(name, movieInfo.first, Category.HOME, movieInfo.second)
                        movieArray.add(tmp)
                    }
                }
            }
        }

        return movieArray.toList()
    }
}