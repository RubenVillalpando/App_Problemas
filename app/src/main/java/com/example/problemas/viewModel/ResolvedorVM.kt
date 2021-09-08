package com.example.problemas.viewModel

import android.icu.util.Calendar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.problemas.model.Resolvedor
import java.time.Year
import java.util.*
import kotlin.collections.HashMap

class ResolvedorVM: ViewModel() {

    private val resolvedor = Resolvedor()


    var repeticionCaracteres = MutableLiveData<String>("")


    fun resolverProblema1(latitud: Double?, longitud: Double?){

    }


    fun resolverProblema2(fechaInicio: Date, fechaFin: Date){
        print(fechaInicio.toString())
        print(fechaFin.toString())
//        Calendar.DECEMBER.
    }


    fun resolverProblema3(anioInicio: Year, anioFin: Year){
        print(anioInicio.toString())
        print(anioFin.toString())
    }


    fun resolverProblema4(tamanioMatriz: Int, rotaciones: IntArray, coordenada: Pair<Int, Int>){

    }


    fun resolverProblema5(texto: String){
        var hm: HashMap<Char, Int> = resolvedor.resolverQuintoProblema(texto)
        var str = StringBuilder()
        var keysArr: CharArray = hm.keys.toCharArray()
        str.append(keysArr[0])
            .append(" = ")
            .append(hm.getValue(keysArr[0]))
        for (i in 1 until keysArr.size){
            str.append(", ")
                .append(keysArr[i])
                .append(" = ")
                .append(hm.getValue(keysArr[i]))
        }
        repeticionCaracteres.value = str.toString()
    }


    fun resolverProblema6(ruta: String){

    }


    fun resolverProblema7(tamanioGrid: Int){

    }

}