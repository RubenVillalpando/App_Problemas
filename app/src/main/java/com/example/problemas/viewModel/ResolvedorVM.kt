package com.example.problemas.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.problemas.model.Resolvedor
import java.lang.NullPointerException
import java.time.Year
import java.util.*
import kotlin.collections.HashMap

class ResolvedorVM: ViewModel() {

    private val resolvedor = Resolvedor()

    var respuestaProblemaUno = MutableLiveData<String>("")
    var respuestaProblemaDos = MutableLiveData<Int>(0)
    var respuestaProblemaTres = MutableLiveData<String>("")
    var respuestaProblemaCuatro = MutableLiveData<Int>(0)
    var respuestaProblemaCinco = MutableLiveData<String>("")
    var respuestaProblemaSeis = MutableLiveData<String>("")
    var respuestaProblemaSiete = MutableLiveData<Int>(0)



    fun resolverProblema1(latitud: Double?, longitud: Double?){

    }


    fun resolverProblema2(fechaInicio: Date, fechaFin: Date) =
        if (fechaInicio > fechaFin){
            throw(Exception())
        }else{
            resolvedor.resolverSegundoProblema(fechaInicio, fechaFin)
        }


    fun resolverProblema3(anioInicio: Int, anioFin: Int){
        // Año bisiesto es el divisible entre 4, salvo que sea año
        // secular -último de cada siglo, terminado en «00»-,
        // en cuyo caso también ha de ser divisible entre 400.

    }


    fun resolverProblema4(tamanioMatriz: Int, rotaciones: IntArray, coordenada: Pair<Int, Int>){
        val r: Int = resolvedor.resolverCuartoProblema(tamanioMatriz, rotaciones, coordenada)
        respuestaProblemaCuatro.value = r
    }


    fun resolverProblema5(texto: String){

        val hm: HashMap<Char, Int> = resolvedor.resolverQuintoProblema(texto)

        val str = StringBuilder()

        val keysArr: CharArray = hm.keys.toCharArray()
        str.append(keysArr[0])
            .append(" = ")
            .append(hm.getValue(keysArr[0]))

        for (i in 1 until keysArr.size){
            str.append(", ")
                .append(keysArr[i])
                .append(" = ")
                .append(hm.getValue(keysArr[i]))
        }
        respuestaProblemaCinco.value = str.toString()
    }


    fun resolverProblema6(ruta: String){

    }


    fun resolverProblema7(tamanioGrid: Int){

    }

}