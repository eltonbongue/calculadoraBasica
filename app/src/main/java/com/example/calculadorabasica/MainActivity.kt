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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializa o TextView
        textView = findViewById(R.id.textView)
    }

    // Essa função será chamada pelo atributo android:onClick nos botões
    fun exibirValores(view: View) {

        val button0 = findViewById<Button>(R.id.botao_0)
        val button1 = findViewById<Button>(R.id.botao_1)
        val button2 = findViewById<Button>(R.id.botao_2)
        val button3 = findViewById<Button>(R.id.botao_3)
        val button4 = findViewById<Button>(R.id.botao_4)
        val button5 = findViewById<Button>(R.id.botao_5)
        val button6 = findViewById<Button>(R.id.botao_6)
        val button7 = findViewById<Button>(R.id.botao_7)
        val button8 = findViewById<Button>(R.id.botao_8)
        val button9 = findViewById<Button>(R.id.botao_9)
        val buttonSomar = findViewById<Button>(R.id.botao_Somar)
        val buttonSubtrair = findViewById<Button>(R.id.botao_Subtrair)

        if (view is Button) {
            val valorBotao = view.text.toString()
            textView.append(valorBotao)
        }
    }
}
