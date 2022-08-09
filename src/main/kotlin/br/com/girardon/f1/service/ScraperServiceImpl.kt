package br.com.girardon.f1.service

import br.com.girardon.f1.model.RacerModel
import br.com.girardon.f1.repository.RacerRepository
import org.jsoup.Jsoup
import org.springframework.stereotype.Service

@Service
class ScraperServiceImpl(
    private val racerRepository: RacerRepository
) {

    fun getDataFromWebSite() : List<RacerModel> {
        val racerList = ArrayList<RacerModel>()

        val webPage = Jsoup
            .connect("https://motorsport.uol.com.br/f1/standings/2022/?type=Driver&class=")
            .get()

        val tbody = webPage
            .getElementsByClass("ms-table ms-table--result")[0]
            .getElementsByTag("tbody")[0]

        val rows = tbody
            .children()
            //.drop(2)

        for (row in rows) {
            val name = row
                .getElementsByTag("a")[0]
                .text()

            val points = row
                .getElementsByClass("ms-table_cell ms-table_field--total_points")[0]
                .text()

//            racerRepository.save(
//                RacerModel(
//                    id = null,
//                    name,
//                    points
//                )
//            )

            racerList.add(
                RacerModel(id = null, name, points)
            )
        }
        return racerList
    }
}