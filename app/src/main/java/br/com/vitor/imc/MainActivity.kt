package br.com.vitor.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import br.com.vitor.imc.extensions.format
import br.com.vitor.imc.extensions.hideKeyboard
import br.com.vitor.imc.extensions.valueDouble
import br.com.vitor.imc.watchers.DecimalTextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etPeso.addTextChangedListener(DecimalTextWatcher(etPeso))
        etAltura.addTextChangedListener(DecimalTextWatcher(etAltura))

        btCalcular.setOnClickListener {
            calcular()
            hideKeyboard()

        }
    }

    private fun calcular() {
        val peso = etPeso.valueDouble()
        val altura = etAltura.valueDouble()

        val imc = peso / (altura * altura)

        when(imc) {
            in 0.0..18.5 -> {
                configuraIMC(imc, R.drawable.masc_abaixo,R.string.magreza)
            }
            in 18.6..24.9 -> {
                configuraIMC(imc, R.drawable.masc_ideal,R.string.peso_normal)
            }
            in 25.0..29.9 -> {
                configuraIMC(imc, R.drawable.masc_sobre,R.string.sobrepeso)
            }
            in 30.0..34.9 -> {
                configuraIMC(imc, R.drawable.masc_obeso,R.string.obesidade_grau_i)
            }
            in 35.0..39.9 -> {
                configuraIMC(imc, R.drawable.masc_obeso,R.string.obesidade_grau_ii)
            }
            in 40.0..Double.MAX_VALUE -> {
                configuraIMC(imc, R.drawable.masc_extremo_obeso,R.string.obesidade_grau_iii)
            }
        }


    }

    private fun configuraIMC(imc: Double, drawableId: Int, stringId: Int) {
        tvIMC.text = getString(R.string.seu_imc, imc.format(2))
        ivIMCStatus.setImageDrawable(ContextCompat.getDrawable(this,drawableId))
        tvIMCStatus.text = getString(stringId)
    }

}
