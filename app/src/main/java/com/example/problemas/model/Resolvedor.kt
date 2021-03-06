package com.example.problemas.model

import android.icu.util.Calendar
import android.os.AsyncTask
import android.widget.Toast
import com.example.problemas.view.MainActivity
import com.google.gson.Gson
import java.io.File
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.roundToLong

class Resolvedor {

    lateinit var main: MainActivity

    var lat: Double = 0.0
    var lon: Double = 0.0
    var datosClima: String? = ""

    fun resolverPrimerProblema(latitud: Double, longitud: Double, mainActivity: MainActivity): ArrayList<String>{
        lat = latitud
        lon = longitud
        main = mainActivity
        val datosHora : List<Hourly>
        try {
            ConexionClima().execute()
            val json = datosClima
            val modelo = Gson().fromJson(json, Modelo::class.java)
            datosHora = modelo.hourly
        } catch(e: Exception){
            throw(e)
        }


        return darFormato(datosHora.subList(0,6))
    }

    private fun darFormato(datosHora: List<Hourly>): ArrayList<String> {
        val listaClima = ArrayList<String>()

        // Formato de hora
        val sdf = java.text.SimpleDateFormat("hh:mm")
        var fecha : Date
        var strBuilder = StringBuilder()


        for(dato in datosHora){
            fecha = Date(dato.dt.toLong()*1000)
            strBuilder.append(sdf.format(fecha).toString())
                .append(": ")
                .append(dato.temp.toString())
                .append(" C°---")
                .append(dato.weather.get(0).description)

            listaClima.add(strBuilder.toString())

            strBuilder.clear()
        }
        return listaClima
    }

    inner class ConexionClima: AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            Toast.makeText(main, "Cargando datos...", Toast.LENGTH_LONG).show()
        }

        override fun doInBackground(vararg params: String?): String? {
            val apiKey = "99ccac116e6924f62f669458a754299c"
            var respuesta: String?

            val urlStr = "https://api.openweathermap.org/data/2.5/onecall?" +
                    "lat=$lat&lon=$lon&exclude=current,daily,alerts,minutely" +
                    "&units=metric&lang=es&appid=$apiKey"

            try{
                respuesta = URL(urlStr).readText(Charsets.UTF_8)
            } catch (e: Exception) {
                respuesta = null
            }
            datosClima = respuesta
            return respuesta
        }
    }


    fun resolverSegundoProblema(fechaInicio: Date, fechaFin: Date): Int{
        val varCal = Calendar.getInstance()
        val cFin = Calendar.getInstance()
        var mesesDomingo: Int = 0


        varCal.setTime(fechaInicio)
        cFin.setTime(fechaFin)

        // Hacer el poner el día del mes como el último
        varCal.set(Calendar.DAY_OF_MONTH, varCal.getActualMaximum(Calendar.DAY_OF_MONTH))
        while(!(varCal.after(cFin))){
            if (Calendar.SUNDAY == varCal.get(Calendar.DAY_OF_WEEK)) mesesDomingo++

            varCal.set(Calendar.MONTH, varCal.get(Calendar.MONTH) + 1)
            varCal.set(Calendar.DAY_OF_MONTH, varCal.getActualMaximum(Calendar.DAY_OF_MONTH))
        }

        return mesesDomingo
    }


    fun resolverTercerProblema(anioInicio: Int, anioFin: Int): String{
        // Año bisiesto es el divisible entre 4, salvo que sea año
        // secular -último de cada siglo, terminado en «00»-,
        // en cuyo caso también ha de ser divisible entre 400.
        var aInicio = anioInicio
        var aFin = anioFin

        // sacar bisiestos más cercanos
        while (aInicio % 4 != 0){
            aInicio ++
        }
        while (aFin % 4 != 0){
            aFin --
        }

        if (aInicio > aFin){
            return ""
        } else {
            return construirCadenaBisiestos(aInicio, aFin)
        }

    }

    private fun construirCadenaBisiestos(aInicio: Int, aFin: Int): String {
        val strBuild = StringBuilder()

        for(a in aInicio..aFin step 4){
            if(a %100 == 0){
                if(a % 400 == 0){
                    strBuild.append(a.toString())
                    strBuild.append("@")
                }
            } else {
                strBuild.append(a.toString())
                strBuild.append("@")
            }
        }

        return strBuild.toString().substring(0, strBuild.length-1)
    }


    fun resolverCuartoProblema(n: Int, rotaciones: List<Int>,
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
    //por lo que se tuvo que implementar una función de mod para números negativos
    private fun modCorrecto(n: Int): Int {
        var i: Int = n
        while (i < 0){
            i += 4
        }
        return i
    }


    fun resolverQuintoProblema(texto: String): HashMap<Char, Int> {
        var hm: HashMap<Char, Int> = HashMap<Char, Int>()
        var repeticiones: Int = 0
        var caracter: Char
        for (i in texto.indices) {
            caracter = texto[i]
            try {
                repeticiones = hm.getValue(caracter)
                hm.put(caracter, repeticiones + 1)
            } catch (e: Exception) {
                if (caracter.isLetter()) hm.put(caracter, 1)
            }
        }
        return hm
    }


    fun resolverSextoProblema(ruta: String): ArrayList<String>{
        val archivo = File(ruta)
        val str = ArrayList<String>()
        sextoProblemaRecur(archivo, "|___", str)
        return str
    }

    fun sextoProblemaRecur(archivo: File, separador: String, str: ArrayList<String>){
        if (archivo.isDirectory){
            str.add(separador + archivo.name)
            archivo.listFiles().forEach{
                sextoProblemaRecur(it, separador + "|___", str)
            }
        }else {
            str.add(separador + archivo.name)
        }
    }


    fun resolverSeptimoProblema(n: Int): Long {
        //Se puede resolver matemáticamente
        //Se puede ver que hay 2 movimientos que te acercan a la meta: Derecha y abajo
        //Los movimientos son limitados, solo puedes hacer n hacia abajo y n a la derecha
        //Por lo que hay un número total de movimientos 2n
        //Por esto se puede ver como una permutacion con elementos repetidos
        //Entonces solo implementamos la fórmula n!/a!*b!
        //Donde n es 2n y tanto a como b son n
        //Como por ejemplo 6! = 6*5*4*3*2*1 y 3! = 3*2*1
        // en este ejemplo 6!/(3!*3!) queda como (6*5*4*3!)/(3!*3!) se cancela un 3!
        //Se puede simplificar a multiplicar los números de 2n..n entre n*n-1..1
        //Por lo que queda de la siguiente manera
        var numerador: Double = (2 * n).toDouble()
        var denominador: Double = n.toDouble()
        var resultado: Double = 1.0
        for (i in 1..n) {
            resultado *= (numerador / denominador)
            numerador--
            denominador--
        }
        return resultado.roundToLong()
    }

}