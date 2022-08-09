package br.com.girardon.f1.controller

import br.com.girardon.f1.service.RacerService
import br.com.girardon.f1.service.ScraperServiceImpl
import br.com.girardon.f1.service.request.RacerRequest
import br.com.girardon.f1.service.response.RacerResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/racer")
class RacerController(
    private val racerService: RacerService,
    private val scraperService: ScraperServiceImpl
) {

    @PostMapping
    fun createRacer(@RequestBody request: RacerRequest) : ResponseEntity<RacerResponse> {
        val createdRacer = racerService.createRacer(request)

        return ResponseEntity.ok(
            RacerResponse.fromEntity(createdRacer)
        )
    }

    @GetMapping
    fun findAllRacers(): ResponseEntity<List<RacerResponse>> {
        val racers = racerService.findAll()

        //call this method when spring is up
        //scraperService.getDataFromWebSite()

        return ResponseEntity.ok(
            racers.map { RacerResponse.fromEntity(it) }
        )
    }
}