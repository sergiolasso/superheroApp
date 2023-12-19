package com.example.proyectoyoutube

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("name") val name : String,
    @SerializedName("powerstats") val powerstats : PowerStatsResponse,
    @SerializedName("image") val image : ImageSuperhero,
    @SerializedName("biography") val biografyDetail: biografyDetail
)

data class PowerStatsResponse(
    @SerializedName("intelligence") val intelligence : String,
    @SerializedName("strength") val strength : String,
    @SerializedName("speed") val speed : String,
    @SerializedName("durability") val duravility : String,
    @SerializedName("power") val power : String,
    @SerializedName("combat") val combat : String
)

data class ImageSuperhero(
    @SerializedName("url") val url : String
)

data class biografyDetail(
    @SerializedName("full-name") val fullName : String,
    @SerializedName("publisher") val published : String
)