package com.example.calculadorabasica

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    private lateinit var button0: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var buttonSomar: Button
    private lateinit var buttonSubtrair: Button
    private lateinit var buttonDividir: Button
    private lateinit var buttonResultado: Button
    private lateinit var limparTela: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textView = findViewById(R.id.textView)

        button0 = findViewById(R.id.botao_0)
        button1 = findViewById(R.id.botao_1)
        button2 = findViewById(R.id.botao_2)
        button3 = findViewById(R.id.botao_3)
        button4 = findViewById(R.id.botao_4)
        button5 = findViewById(R.id.botao_5)
        button6 = findViewById(R.id.botao_6)
        button7 = findViewById(R.id.botao_7)
        button8 = findViewById(R.id.botao_8)
        button9 = findViewById(R.id.botao_9)
        buttonSomar = findViewById(R.id.botao_Somar)
        buttonSubtrair = findViewById(R.id.botao_Subtrair)
        buttonDividir = findViewById(R.id.botao_Dividir)
        buttonResultado = findViewById(R.id.botao_Resultado)
        limparTela = findViewById(R.id.limparTela)
    }

    fun exibirValores(view: View) {
        if (view is Button) {

            if (view.id == R.id.botao_Resultado) {
                val expressao = textView.text.toString()

                if (expressao.contains("x")) {
                    val resultadoMultiplicar = multiplicarNumeros(expressao)
                    textView.text = resultadoMultiplicar.toString()

                }

                if (expressao.contains("+")) {
                    val resultadoSoma = somarNumeros(expressao)
                    textView.text = resultadoSoma.toString()
                }

                if (expressao.contains("-")) {
                    val resultadoSubtrair = subtrairNumeros(expressao)
                    textView.text = resultadoSubtrair.toString()
                }

                else if (expressao.contains("/")) {
                    val resultadoDividir = dividirNumeros(expressao)
                    textView.text = resultadoDividir.toString()
                }

                return
            }

            if (view.id == R.id.limparTela) {
                textView.text = ""
                return
            }

            val valorBotao = view.text.toString()

            if (valorBotao == "-" && (textView.text.isEmpty() || textView.text.last() == '+' || textView.text.last() == 'x' || textView.text.last() == '/')) {
                return // Impede a inserção de "-" se a TextView estiver vazia ou terminar com outro operador
            }

            if (valorBotao == "+" && (textView.text.isEmpty() || textView.text.last() == '+' || textView.text.last() == 'x' || textView.text.last() == '/')) {
                return
            }

            if (valorBotao == "x" && (textView.text.isEmpty() || textView.text.last() == '+' || textView.text.last() == 'x' || textView.text.last() == '/')) {
                return
            }

            if (valorBotao == "/" && (textView.text.isEmpty() || textView.text.last() == '+' || textView.text.last() == 'x' || textView.text.last() == '/')) {
                return
            }

            if (valorBotao == "." && (textView.text.isEmpty() || textView.text.last() == '+' || textView.text.last() == 'x' || textView.text.last() == '/')) {
                return
            }

            textView.append(valorBotao)
        }
    }

    fun somarNumeros(expressao: String): Double {
        val numeros = expressao.split("+")
        var soma = 0.0

        for (numero in numeros) {
            try {
                soma += numero.trim().toDouble()  // Converte para Double
            } catch (e: NumberFormatException) {
                continue
            }
        }
        return soma
    }

    fun subtrairNumeros(expressaoSubtrair: String): Double {
        val numeros = expressaoSubtrair.split("-")
        if (numeros.isEmpty()) return 0.0
        var resultado = numeros[0].trim().toDouble()  // Inicializa com o primeiro número como Double
        for (i in 1 until numeros.size) {
            try {
                resultado -= numeros[i].trim().toDouble()  // Converte para Double
            } catch (e: NumberFormatException) {
                continue
            }
        }
        return resultado
    }

    fun multiplicarNumeros(expressaoMultiplicar: String): Double {
        val numeros = expressaoMultiplicar.split("x")
        var multiplicar = 1.0 // Inicializa como Double

        for (numero in numeros) {
            try {
                multiplicar *= numero.trim().toDouble()  // Converte para Double
            } catch (e: NumberFormatException) {
                continue
            }
        }

        return multiplicar
    }

    fun dividirNumeros(expressaoDividir: String): Double {
        val numeros = expressaoDividir.split("/")
        if (numeros.size != 2) {
            textView.text = "Erro: Expressão inválida"
            return 0.0
        }

        var resultado = numeros[0].trim().toDouble()

        try {
            val divisor = numeros[1].trim().toDouble()

            if (divisor == 0.0) {
                textView.text = "Erro: Divisão por zero"
                return 0.0
            }

            resultado /= divisor
        } catch (e: NumberFormatException) {
            textView.text = "Erro: Número inválido"
            return 0.0
        }

        return resultado
    }
}
