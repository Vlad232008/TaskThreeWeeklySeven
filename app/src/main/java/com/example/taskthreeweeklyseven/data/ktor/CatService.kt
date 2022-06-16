package com.example.taskthreeweeklyseven.data.ktor

import com.example.taskthreeweeklyseven.data.dto.ImageResponce
import com.example.taskthreeweeklyseven.data.dto.MessageResponce
import com.example.taskthreeweeklyseven.data.dto.VoteRequest
import com.example.taskthreeweeklyseven.data.dto.VoteResponce
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface CatService {

    suspend fun getRandomImage(): List<ImageResponce>
    suspend fun getImageById(id: String): ImageResponce
    suspend fun createVote(postRequest: VoteRequest): MessageResponce
    suspend fun getCatList(): List<VoteResponce>

    companion object {
        fun create(): CatService {
            return CatServiceImpl(
                HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                            ignoreUnknownKeys = true
                            isLenient = true
                        })
                    }
                }
            )
        }
    }
}
