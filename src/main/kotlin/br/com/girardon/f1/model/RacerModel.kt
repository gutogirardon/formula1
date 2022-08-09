package br.com.girardon.f1.model;

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("racer")
data class RacerModel(
    @Id
    val id: String? = null,
    var name: String,
    var points: String
)
