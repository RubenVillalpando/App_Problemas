package com.example.problemas.view

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.problemas.databinding.*
import com.example.problemas.viewModel.ResolvedorVM
import java.text.NumberFormat
import java.text.ParseException
import java.util.*

class MainActivity : AppCompatActivity() {

    private val resolvedorVM: ResolvedorVM by viewModels()

    //region Inicializar los bindings de las pantallas
    private lateinit var bindingMain: ActivityMainBinding
    private lateinit var bindingUno: ProblemaUnoBinding
    private lateinit var bindingDos: ProblemaDosBinding
    private lateinit var bindingTres: ProblemaTresBinding
    private lateinit var bindingCuatro: ProblemaCuatroBinding
    private lateinit var bindingCinco: ProblemaCincoBinding
    private lateinit var bindingSeis: ProblemaSeisBinding
    private lateinit var bindingSiete: ProblemaSieteBinding
    //endregion

    //region Inicializar los constraintLayouts de las pantallas
    private lateinit var vistaMain: ConstraintLayout
    private lateinit var vistaUno: ConstraintLayout
    private lateinit var vistaDos: ConstraintLayout
    private lateinit var vistaTres: ConstraintLayout
    private lateinit var vistaCuatro: ConstraintLayout
    private lateinit var vistaCinco: ConstraintLayout
    private lateinit var vistaSeis: ConstraintLayout
    private lateinit var vistaSiete: ConstraintLayout
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inicializarBindings()

        crearVistas()


        // Vista principal de la aplicación.
        setContentView(vistaMain)

        registrarObservadores()
        registrarEventos()

    }


    // Guarda los layouts en la memoria para accesar a ellos más fácil.
    private fun inicializarBindings() {
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        bindingUno = ProblemaUnoBinding.inflate(layoutInflater)
        bindingDos = ProblemaDosBinding.inflate(layoutInflater)
        bindingTres = ProblemaTresBinding.inflate(layoutInflater)
        bindingCuatro = ProblemaCuatroBinding.inflate(layoutInflater)
        bindingCinco = ProblemaCincoBinding.inflate(layoutInflater)
        bindingSeis = ProblemaSeisBinding.inflate(layoutInflater)
        bindingSiete = ProblemaSieteBinding.inflate(layoutInflater)
    }


    private fun crearVistas() {
        vistaMain = bindingMain.root
        vistaUno = bindingUno.root
        vistaDos = bindingDos.root
        vistaTres = bindingTres.root
        vistaCuatro = bindingCuatro.root
        vistaCinco = bindingCinco.root
        vistaSeis = bindingSeis.root
        vistaSiete = bindingSiete.root
    }


    private fun registrarEventos() {
        registrarEventosMain()
        registrarEventosUno()
        registrarEventosDos()
        registrarEventosTres()
        registrarEventosCuatro()
        registrarEventosCinco()
        registrarEventosSeis()
        registrarEventosSiete()
    }


    private fun registrarEventosMain() {
        bindingMain.btnProblemaUno.setOnClickListener {
            setContentView(vistaUno)
        }
        bindingMain.btnProblemaDos.setOnClickListener {
            setContentView(vistaDos)
        }
        bindingMain.btnProblemaTres.setOnClickListener {
            setContentView(vistaTres)
        }
        bindingMain.btnProblemaCuatro.setOnClickListener {
            setContentView(vistaCuatro)
        }
        bindingMain.btnProblemaCinco.setOnClickListener {
            setContentView(vistaCinco)
        }
        bindingMain.btnProblemaSeis.setOnClickListener {
            setContentView(vistaSeis)
        }
        bindingMain.btnProblemaSiete.setOnClickListener {
            setContentView(vistaSiete)
        }

    }


    private fun registrarEventosUno() {
        bindingUno.btnFlechaRegreso.setOnClickListener{
            setContentView(vistaMain)
        }
        bindingUno.btnMostrarClima.setOnClickListener {
            mostrarClima()
        }

    }

    private fun mostrarClima() {
            try{
                val lat = bindingUno.etLatitud.text.toString().toDouble()
                val lon = bindingUno.etLongitud.text.toString().toDouble()
                resolvedorVM.resolverProblema1(lat, lon)
            } catch (e: NumberFormatException){
                val mensaje = "Los 2 campos deben estar llenos"
            }

    }

    private fun registrarEventosDos() {
        bindingDos.btnFlechaRegreso.setOnClickListener{
            setContentView(vistaMain)
        }
        bindingDos.etFechaInicio.setOnClickListener{
            mostrarElegirFecha(bindingDos.etFechaInicio)
        }
        bindingDos.etFechaFin.setOnClickListener {
            mostrarElegirFecha(bindingDos.etFechaFin)
        }
        bindingDos.btnMostrarDomingos.setOnClickListener{
            mostrarDomingos()
        }
    }

    private fun mostrarDomingos() {
        val format = SimpleDateFormat("dd/MM/yyyy")
        try {
            val fechaInicio: Date = format.parse(bindingDos.etFechaInicio.text.toString())
            val fechaFin: Date = format.parse(bindingDos.etFechaFin.text.toString())
            resolvedorVM.resolverProblema2(fechaInicio, fechaFin)
        }catch(e: ParseException){
            val mensaje: String = "Necesitas poner las 2 fechas"
            mostrarToast(mensaje)
        }catch (e: Exception){
            val mensaje: String = "La fecha de inicio necesita ser menor que la final"
            mostrarToast(mensaje)
        }
    }

    private fun mostrarElegirFecha(editText: EditText) {
        val electorFecha = electorFechaFragment{ day, month, year ->
            mostrarFecha(day, month, year, editText)}
        electorFecha.show(supportFragmentManager, "electorFecha")
    }

    private fun mostrarFecha(day: Int, month: Int, year: Int, editText: EditText) {
        val correctMonth: Int = month + 1
        editText.setText("$day/$correctMonth/$year")
    }

    private fun registrarEventosTres() {
        bindingTres.btnFlechaRegreso.setOnClickListener{
            setContentView(vistaMain)
        }

        bindingTres.btnMostrarAnioBisiesto.setOnClickListener {
            mostrarAniosBisiestos()
        }
    }

    private fun mostrarAniosBisiestos() {
        try{
            val anioInicio: Int = bindingTres.etAnioInicio.text.toString().toInt()
            val anioFin: Int = bindingTres.etAnioFin.text.toString().toInt()
            resolvedorVM.resolverProblema3(anioInicio, anioFin)
        }catch(e: NumberFormatException){
            val mensaje: String = "Debes llenar los 2 campos"
            mostrarToast(mensaje)
        }catch(e: Exception){
            val mensaje: String = "El año de inicio debe ser menor al final"
            mostrarToast(mensaje)
        }
    }

    private fun registrarEventosCuatro() {
        bindingCuatro.btnFlechaRegreso.setOnClickListener{
            setContentView(vistaMain)
        }
        bindingCuatro.btnMostrarNumero.setOnClickListener {
            mostrarNumero()
        }
    }

    private fun mostrarNumero() {
        try{
            val tamanioMatriz = bindingCuatro.etTamaOMatriz.text.toString().toInt()
            val rotaciones = bindingCuatro.etRotaciones.text.toString().split(",")
            val listaRotaciones = rotaciones.map{it.toInt()}
            val coordenadas = Pair<Int, Int>(
                bindingCuatro.etCoordenadaX.text.toString().toInt(),
                bindingCuatro.etCoordenadaY.text.toString().toInt()
            )
            resolvedorVM.resolverProblema4(tamanioMatriz, listaRotaciones, coordenadas)
        } catch (e: NumberFormatException){
            val mensaje = "Todos los campos deben estar llenos con valores válidos"
            mostrarToast(mensaje)
        } catch (e: Exception){
            val mensaje = "las coordenadas deben ser menores al tamaño de la matriz"
            mostrarToast(mensaje)
        }
    }

    private fun registrarEventosCinco() {
        bindingCinco.btnFlechaRegreso.setOnClickListener{
            setContentView(vistaMain)
        }
        bindingCinco.btnMostrarCaracteres.setOnClickListener{
            mostrarCaracteres()
        }
    }

    private fun mostrarCaracteres() {
        try {
            resolvedorVM.resolverProblema5(bindingCinco.etTextoEntrada.text.toString())
        }catch (e: NullPointerException){
            val mensaje = "Debes de llenar el campo correspondiente"
            mostrarToast(mensaje)
        }
    }

    private fun registrarEventosSeis() {
        bindingSeis.btnFlechaRegreso.setOnClickListener{
            setContentView(vistaMain)
        }
        bindingSeis.btnMostrarArbol.setOnClickListener {
            mostrarArbol()
        }
    }

    private fun mostrarArbol() {
        try {
            resolvedorVM.resolverProblema6(
                bindingSeis.etRuta.text.toString()
            )
        } catch (e: Exception){
            val mensaje = "Debe llenar el campo con una ruta a un archivo"
        }
    }

    private fun registrarEventosSiete() {
        bindingSiete.btnFlechaRegreso.setOnClickListener{
            setContentView(vistaMain)
        }
        bindingSiete.btnMostrarCaminos.setOnClickListener {
           mostrarCaminos()
        }
    }

    private fun mostrarCaminos() {
        try {
            resolvedorVM.resolverProblema7(
                bindingSiete.etTamanioMatrizEntrada.text.toString().toInt()
            )
        } catch(e: NumberFormatException){
            val mensaje = "Debes llenar el campo con un número"
            mostrarToast(mensaje)
        } catch(e: Exception){
            val mensaje = "El resultado es más grande de lo que puede calcular el sistema"
            mostrarToast(mensaje)
        }
    }



    private fun registrarObservadores() {
//        registrarObservadoresUno()
        registrarObservadoresDos()
        registrarObservadoresTres()
        registrarObservadoresCuatro()
        registrarObservadoresCinco()
        registrarObservadoresSeis()
        registrarObservadoresSiete()
    }

    private fun registrarObservadoresUno() {
        TODO("Not yet implemented")
    }

    private fun registrarObservadoresDos() {
        resolvedorVM.respuestaProblemaDos.observe(this, {resultado->
            bindingDos.tvResultado2.setText("Hay $resultado meses donde domingo es el último día")
        })
    }

    private fun registrarObservadoresTres() {
        resolvedorVM.respuestaProblemaTres.observe(this, {resultado->
            bindingTres.tvResultado3.setText(resultado)
        })
    }

    private fun registrarObservadoresCuatro() {
        resolvedorVM.respuestaProblemaCuatro.observe(this, {resultado->
            bindingCuatro.tvResultado4.setText("Número en el índice = $resultado")
        })
    }

    private fun registrarObservadoresCinco() {
        resolvedorVM.respuestaProblemaCinco.observe(this, {resultado->
            bindingCinco.tvResultado5.setText(resultado)
        })
    }

    private fun registrarObservadoresSeis() {
        resolvedorVM.respuestaProblemaSeis.observe(this, {resultado->
            val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultado)
            bindingSeis.lvArbolArchivos.adapter = listAdapter
        })
    }

    private fun registrarObservadoresSiete() {
        resolvedorVM.respuestaProblemaSiete.observe(this, {resultado->
            val resString = NumberFormat.getIntegerInstance().format(resultado)
            bindingSiete.tvRespuesta7.setText("Caminos posibles = $resString")
        })
    }

    private fun mostrarToast(mensaje: String){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG ).show()
    }

}