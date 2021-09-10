package com.example.problemas.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.problemas.model.Hourly
import com.example.problemas.model.Resolvedor
import com.example.problemas.view.MainActivity
import java.util.*
import kotlin.NullPointerException

class ResolvedorVM: ViewModel() {

    private val resolvedor = Resolvedor()

    var respuestaProblemaUno = MutableLiveData<ArrayList<String>>()
    var respuestaProblemaDos = MutableLiveData<Int>(0)
    var respuestaProblemaTres = MutableLiveData<String>("")
    var respuestaProblemaCuatro = MutableLiveData<Int>(0)
    var respuestaProblemaCinco = MutableLiveData<String>("")
    var respuestaProblemaSeis = MutableLiveData<ArrayList<String>>()
    var respuestaProblemaSiete = MutableLiveData<Long>(0)


    fun resolverProblema1(latitud: Double, longitud: Double, mainAct: MainActivity) {
        if(latitud < -90.0 || latitud > 90.0  || longitud < -180.0 || longitud > 180.0)throw(IllegalArgumentException())
        try {
            respuestaProblemaUno.value = resolvedor.resolverPrimerProblema(latitud, longitud, mainAct)
        } catch(e: Exception){
            throw(e)
        }
    }





    fun resolverProblema2(fechaInicio: Date, fechaFin: Date){

        if (fechaInicio.after(fechaFin)) throw(Exception())

        respuestaProblemaDos.value = resolvedor.resolverSegundoProblema(fechaInicio, fechaFin)
    }



    fun resolverProblema3(anioInicio: Int, anioFin: Int){

        if (anioInicio> anioFin) throw(Exception())

        respuestaProblemaTres.value = resolvedor.resolverTercerProblema(anioInicio, anioFin)
    }


    fun resolverProblema4(tamanioMatriz: Int, rotaciones: List<Int>, coordenada: Pair<Int, Int>){
        if(coordenada.first > tamanioMatriz || coordenada.second > tamanioMatriz) throw(Exception())
        val r: Int = resolvedor.resolverCuartoProblema(tamanioMatriz, rotaciones, coordenada)
        respuestaProblemaCuatro.value = r
    }


    fun resolverProblema5(texto: String){
        if (texto.isEmpty()) throw(NullPointerException())
        val strRespuesta = formatHM(resolvedor.resolverQuintoProblema(texto))
        respuestaProblemaCinco.value = strRespuesta
    }

    private fun formatHM(hm: HashMap<Char, Int>): String {
        val strBuilder = StringBuilder()

        val keysArr: CharArray = hm.keys.toCharArray()
        strBuilder.append(keysArr[0])
            .append(" = ")
            .append(hm.getValue(keysArr[0]))

        for (i in 1 until keysArr.size){
            strBuilder.append(", ")
                .append(keysArr[i])
                .append(" = ")
                .append(hm.getValue(keysArr[i]))
        }
        return strBuilder.toString()
    }


    fun resolverProblema6(ruta: String){
        if (ruta.isEmpty()) throw(Exception())
        respuestaProblemaSeis.value = resolvedor.resolverSextoProblema(ruta)
    }


    fun resolverProblema7(tamanioGrid: Int){
        if (tamanioGrid > 33) throw(Exception())
        respuestaProblemaSiete.value = resolvedor.resolverSeptimoProblema(tamanioGrid)
    }

}