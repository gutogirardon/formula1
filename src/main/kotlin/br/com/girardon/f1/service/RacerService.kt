package br.com.girardon.f1.service

import br.com.girardon.f1.model.RacerModel
import br.com.girardon.f1.service.request.RacerRequest
import br.com.girardon.f1.repository.RacerRepository
import org.springframework.stereotype.Service

@Service
class RacerService(
    private val racerRepository: RacerRepository
) {
    fun createRacer(request: RacerRequest) : RacerModel =
        racerRepository.save(
            RacerModel(
                name = request.name,
                points = request.points?: "0"
            )
        )

    fun findAll(): List<RacerModel> = racerRepository.findAll()

}