package br.com.girardon.f1.service.response

import br.com.girardon.f1.model.RacerModel

class RacerResponse(
    val id: String,
    val name: String,
    val points: String
) {
    companion object {
        fun fromEntity(racer: RacerModel) : RacerResponse =
            RacerResponse(
                id = racer.id!!,
                name = racer.name,
                points = racer.points
            )
    }
}