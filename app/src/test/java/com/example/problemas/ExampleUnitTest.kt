package com.example.problemas

import com.example.problemas.model.Resolvedor
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val res = Resolvedor()

    @Test
    fun ProblemaUno(){
        assertEquals(1, res.resolverPrimerProblema(1.0, 2.0))
    }

    @Test
    fun ProblemaCuatro(){
        val coordenada = Pair<Int, Int>(1,2)
        val coordenada2 = Pair<Int, Int>(0, 3)
//        assertEquals(10, res.resolverCuartoProblema(4, intArrayOf(0, 3, -4), coordenada))
//        assertEquals(11, res.resolverCuartoProblema(4, intArrayOf(7, 9, -1), coordenada))
//        assertEquals(7, res.resolverCuartoProblema(4, intArrayOf(-3, -39, 0, 1, -5), coordenada))
//        assertEquals(6, res.resolverCuartoProblema(4, intArrayOf(-2, -3, 9, 0), coordenada))
//        assertEquals(24, res.resolverCuartoProblema(5, intArrayOf(-2, 3, 9, 0), coordenada2))
    }

    @Test
    fun problemaSeis(){
        res.resolverSextoProblema("C:\\Users\\Rupy\\Programming\\AndroidStudioProjects\\Proyectos_Entrevista")
    }

    @Test
    fun problemaSiete(){
        res.resolverSeptimoProblema(9)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


}