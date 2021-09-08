package com.example.problemas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.problemas.databinding.*
import com.example.problemas.viewModel.ResolvedorVM
import java.time.Year

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
//        bindingDos.btnMostrarDomingos.setOnClickListener{
//
//        }
    }

    private fun registrarEventosTres() {
        bindingTres.btnFlechaRegreso.setOnClickListener{
            setContentView(vistaMain)
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
        resolvedorVM.repeticionCaracteres.observe(this, {resultado->
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