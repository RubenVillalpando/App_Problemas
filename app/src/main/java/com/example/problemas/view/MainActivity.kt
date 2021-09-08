package com.example.problemas.view

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.problemas.databinding.*
import com.example.problemas.viewModel.ResolvedorVM
import java.text.ParseException
import java.time.LocalDate
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
            Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            val mensaje: String = "La fecha de inicio necesita ser menor que la final"
            Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show()
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
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        }catch(e: Exception){
            val mensaje: String = "El año de inicio debe ser menor al final"
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        }
    }

    private fun registrarEventosCuatro() {
        bindingCuatro.btnFlechaRegreso.setOnClickListener{
            setContentView(vistaMain)
        }
    }

    private fun registrarEventosCinco() {
        bindingCinco.btnFlechaRegreso.setOnClickListener{
            setContentView(vistaMain)
        }
        bindingCinco.btnMostrarCaracteres.setOnClickListener{
            resolvedorVM.resolverProblema5(bindingCinco.etTextoEntrada.text.toString())
        }
    }

    private fun registrarEventosSeis() {
        bindingSeis.btnFlechaRegreso.setOnClickListener{
            setContentView(vistaMain)
        }
    }

    private fun registrarEventosSiete() {
        bindingSiete.btnFlechaRegreso.setOnClickListener{
            setContentView(vistaMain)
        }
    }

    private fun registrarObservadores() {
//        registrarObservadoresUno()
//        registrarObservadoresDos()
//        registrarObservadoresTres()
//        registrarObservadoresCuatro()
        registrarObservadoresCinco()
//        registrarObservadoresSeis()
//        registrarObservadoresSiete()
    }

    private fun registrarObservadoresUno() {
        TODO("Not yet implemented")
    }

    private fun registrarObservadoresDos() {
        TODO("Not yet implemented")
    }

    private fun registrarObservadoresTres() {
        TODO("Not yet implemented")
    }

    private fun registrarObservadoresCuatro() {
        TODO("Not yet implemented")
    }

    private fun registrarObservadoresCinco() {
        resolvedorVM.respuestaProblemaCinco.observe(this, {resultado->
            bindingCinco.tvRespuestaProblema5.setText(resultado)
        })
    }

    private fun registrarObservadoresSeis() {
        TODO("Not yet implemented")
    }

    private fun registrarObservadoresSiete() {
        TODO("Not yet implemented")
    }

}