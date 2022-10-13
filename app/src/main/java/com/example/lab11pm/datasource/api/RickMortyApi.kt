package com.example.lab11pm.datasource.api

import com.example.lab11pm.datasource.model.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.lab11pm.datasource.model.Character

interface RickMortyApi {

    @GET("/api/character")
    fun getCharacters(): Call<CharactersResponse>

    @GET("/api/character/{id}")
    fun getCharacter(
        @Path("id") id: Int
    ): Call<Character>

}