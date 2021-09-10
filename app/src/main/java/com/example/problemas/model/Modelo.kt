package com.example.problemas.model

import com.google.gson.annotations.SerializedName

data class Modelo (

    @SerializedName("lat") val lat : Int,
    @SerializedName("lon") val lon : Int,
    @SerializedName("timezone") val timezone : String,
    @SerializedName("timezone_offset") val timezone_offset : Int,
    @SerializedName("hourly") val hourly : List<Hourly>
)

data class Hourly (

    @SerializedName("dt") val dt : Int,
    @SerializedName("temp") val temp : Double,
    @SerializedName("feels_like") val feels_like : Double,
    @SerializedName("pressure") val pressure : Int,
    @SerializedName("humidity") val humidity : Int,
    @SerializedName("dew_point") val dew_point : Double,
    @SerializedName("uvi") val uvi : Double,
    @SerializedName("clouds") val clouds : Int,
    @SerializedName("visibility") val visibility : Int,
    @SerializedName("wind_speed") val wind_speed : Double,
    @SerializedName("wind_deg") val wind_deg : Int,
    @SerializedName("wind_gust") val wind_gust : Double,
    @SerializedName("weather") val weather : List<Weather>,
    @SerializedName("pop") val pop : Double

)

data class Weather (

    @SerializedName("id") val id : Int,
    @SerializedName("main") val main : String,
    @SerializedName("description") val description : String,
    @SerializedName("icon") val icon : String
)




