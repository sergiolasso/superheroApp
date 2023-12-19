package com.example.proyectoyoutube


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("api.php/763650698931372/search/{name}")
    suspend fun getSuperheroes(@Path("name") superheroName : String) : Response<SuperHeroDataResponse>

    @GET("/api.php/763650698931372/{id}")
    suspend fun getSuperheroesDetail(@Path("id") superheroId: String) : Response<SuperHeroDetailResponse>

}