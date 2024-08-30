package com.example.calculadora2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.analizadores.*
import org.w3c.dom.Text
import java.io.StringReader


class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvInput: TextView
    private var currentInput =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.txtResult)
        tvInput = findViewById(R.id.InputText)

        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.buttonPlus, R.id.buttonMinus,
            R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonEquals, R.id.buttonClear,
            R.id.buttonL,R.id.buttonR, R.id.buttonPunt
        )

        for (buttonId in buttons) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {onButtonClick(button)}
        }
    }

    private fun onButtonClick(button: Button) {
        val buttonText = button.text.toString()

        when (buttonText) {
            "=" -> {
                evaluarExpresion(currentInput)
            }
            "C" -> clearInput()
            else -> {
                currentInput += buttonText
                tvInput.text =currentInput
            }
        }
    }

    private fun clearInput() {
        currentInput =""
        tvInput.text=""
        tvResult.text = "0"
    }
    private fun evaluarExpresion(expresion: String) {
        Toast.makeText(this,"iniciando"+ expresion,Toast.LENGTH_LONG).show()
        try {
            val lexer = Lexer(StringReader(expresion)) // Usar la clase generada por JFlex
            val parser = Parser(lexer) // Usar la clase generada por CUP
            parser.parse()// Esto retorna un Symbol
            val resultado = parser.resultadoFinal

            val resultadoFinal = resultado as Double // Extraer el valor del Symbol

            tvResult.text = resultadoFinal.toString() // Muestra el resultado en el TextView
            currentInput = resultadoFinal.toString() // Actualiza el input actual con el resultado

            Toast.makeText(this,"finalizando"+ resultadoFinal,Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            tvResult.text = "Error" // Manejo de errores
        }
    }


}