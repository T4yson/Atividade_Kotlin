package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etState = findViewById<EditText>(R.id.et_state)
        val etValue = findViewById<EditText>(R.id.et_value)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate)
        val tvPercentageResult = findViewById<TextView>(R.id.tv_percentage_result)
        val tvTotalResult = findViewById<TextView>(R.id.tv_total_result)

        btnCalculate.setOnClickListener {
            val state = etState.text.toString().uppercase().trim()
            val valueString = etValue.text.toString()
            val value = valueString.toFloatOrNull()

            if (value == null) {
                tvTotalResult.text = "R$ 0,00"
                tvTotalResult.setTextColor(Color.WHITE)
                tvPercentageResult.text = "0%"
                etValue.error = "Insira um valor válido"
                return@setOnClickListener
            }

            val icms = when (state) {
                "SC", "ES", "MS", "RS" -> 0.17f
                "GO" -> 0.175f
                "SP", "PR" -> 0.18f
                else -> null
            }

            if (icms == null) {
                tvTotalResult.text = "Estado inválido"
                tvTotalResult.setTextColor(Color.WHITE)
                tvPercentageResult.text = "0%"
                etState.error = "Estado não encontrado"
            } else {
                val percentageStr = "${String.format("%.1f", icms * 100)}%"
                val totalValue = value + (value * icms)
                
                tvPercentageResult.text = percentageStr
                tvTotalResult.text = "R$ ${String.format("%.2f", totalValue)}"


                if (icms <= 0.17f) {
                    tvTotalResult.setTextColor(Color.GREEN)
                } else {
                    tvTotalResult.setTextColor(Color.RED)
                }
            }
        }
    }
}
