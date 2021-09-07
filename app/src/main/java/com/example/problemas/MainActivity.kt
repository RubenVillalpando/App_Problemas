package com.example.problemas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.problemas.databinding.ActivityMainBinding
import com.example.problemas.databinding.ProblemaUnoBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingUno: ProblemaUnoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingUno = ProblemaUnoBinding.inflate(layoutInflater)

        val vistaMain = binding.root
        val vistaUno = bindingUno.root





        setContentView(vistaUno)

    }
}