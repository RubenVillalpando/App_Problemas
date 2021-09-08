package com.example.problemas.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Year
import java.util.*
import kotlin.collections.HashMap

class Resolvedor {

    fun resolverPrimerProblema(latitud: Double, longitud: Double){

    }


    fun resolverSegundoProblema(fechaInicio: Date, fechaFin: Date){

    }


    fun resolverTercerProblema(anioInicio: Year, anioFin: Year){

    }



    fun resolverCuartoProblema(n: Int, rotaciones: IntArray,
                               coordenada: Pair<Int, Int>): Int{
        val x: Int = coordenada.first
        val y: Int = coordenada.second
        var numRotaciones: Int = 0
        for(i in rotaciones){
            numRotaciones += i.compareTo(0)
        }
        if (numRotaciones < 0){
            numRotaciones = modCorrecto(numRotaciones)
        }else if (numRotaciones > 0){
            numRotaciones %= 4
        }
        var resultado: Int
        when (numRotaciones){
            0 -> {           //fórmula
                resultado = (n * y) + (x + 1)
            }
            1 -> {          //x = y              y = (n - (x + 1)
                resultado = (n * (n - (x + 1))) + (y + 1)
            }
            2 -> {          //x = (n - (x + 1))  y = (n - (y + 1))
                resultado = (n * (n - (y + 1)) + ((n - (x + 1) + 1)))
            }
            else -> {       //x = (n - (y + 1))  y = x
                resultado = (n * x) + (n - (y + 1) + 1)
            }
        }
        return resultado
    }

    //comportamiento extraño en kotlin con % y números negativos
    private fun modCorrecto(n: Int): Int {
        var i: Int = n
        while (i < 0){
            i += 4
        }
        return i
    }


    fun resolverQuintoProblema(texto: String): HashMap<Char, Int>{
        var hm: HashMap<Char, Int> = HashMap<Char, Int>()
        var repeticiones: Int = 0
        var caracter: Char
        for(i in texto.indices){
            caracter = texto[i]
            try {
                repeticiones = hm.getValue(caracter)
                hm.put(caracter, repeticiones + 1)
            } catch(e: Exception){
                if (caracter.isLetter()) hm.put(caracter, 1)
            }
        }
        return hm
    }


    fun resolverSextoProblema(ruta: String){

    }


    fun resolverSeptimoProblema(tamanioGrid: Int){

    }
}