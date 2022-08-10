package br.com.girardon.f1.service

import br.com.girardon.f1.model.RacerModel
import br.com.girardon.f1.repository.RacerRepository
import org.jsoup.Jsoup
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class ScraperService(
    private val racerRepository: RacerRepository,
    @Value("\${f1.data.racers.url}") var racersURL: String
) {
    var logger: Logger = LoggerFactory.getLogger(ScraperService::class.java)

    @PostConstruct
    fun init() : Unit {
        if (racerRepository.findAll().isEmpty()) {
            logger.info("Initializing Database Populate")
            getDataFromWebSite()
            logger.info("Success Database Populate")
        }
    }

    fun getDataFromWebSite() : List<RacerModel> {
        val racerList = ArrayList<RacerModel>()

        val webPage = Jsoup
            .connect(racersURL)
            .get()

        val tbody = webPage
            .getElementsByClass("ms-table ms-table--result")[0]
            .getElementsByTag("tbody")[0]

        val rows = tbody
            .children()

        for (row in rows) {
            val name = row
                .getElementsByTag("a")[0]
                .text()

            val points = row
                .getElementsByClass("ms-table_cell ms-table_field--total_points")[0]
                .text()

            racerRepository.save(
                RacerModel(
                    id = null,
                    name,
                    points
                )
            )
            racerList.add(
                RacerModel(id = null, name, points)
            )
        }
        return racerList
    }
}