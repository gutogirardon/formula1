package br.com.girardon.f1.controller

import br.com.girardon.f1.service.RacerService
import br.com.girardon.f1.service.ScraperService
import br.com.girardon.f1.service.request.RacerRequest
import br.com.girardon.f1.service.response.RacerResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
    private val scraperService: ScraperService
) {
    var logger: Logger = LoggerFactory.getLogger(RacerController::class.java)


//    @PostMapping
//    fun createRacer(@RequestBody request: RacerRequest) : ResponseEntity<RacerResponse> {
//        val createdRacer = racerService.createRacer(request)
//
//        return ResponseEntity.ok(
//            RacerResponse.fromEntity(createdRacer)
//        )
//    }

    @GetMapping
    fun findAllRacers(): ResponseEntity<List<RacerResponse>> {
        logger.info("findAllRacers Invoked!")

        val racers = racerService.findAll()

        return ResponseEntity.ok(
            racers.map { RacerResponse.fromEntity(it) }
        )
    }
}