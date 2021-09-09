package com.example.problemas.model

import java.net.URL

class ConexionClima {

    public fun conseguirRespuestaDeAPI(lat: Double, lon: Double): String? {

        val apiKey: String = "99ccac116e6924f62f669458a754299c"
        var response: String?

//        val urlStr: String = "https://api.openweathermap.org/data/2.5/hourly?lat=$lat&lon=$lon" +
//                            "&appid=$apiKey&units=metric&lang=es"
        //
        val urlStr = "https://api.openweathermap.org/data/2.5/onecall?lat=2.0&lon=1.0&appid=99ccac116e6924f62f669458a754299c"

        try{
            response = URL(urlStr).readText(Charsets.UTF_8)
        } catch (e: Exception) {
            response = null
        }
        return response
    }
}