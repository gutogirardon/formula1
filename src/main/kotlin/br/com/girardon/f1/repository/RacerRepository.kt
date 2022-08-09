package br.com.girardon.f1.repository

import br.com.girardon.f1.model.RacerModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RacerRepository : MongoRepository<RacerModel, String>