package br.com.alura.orgs.ui.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

// função de extensão para reutilizar o código de formatação em diferentes pontos do código
fun BigDecimal.formataParaMoedaPortuguesa(): String{
    return kotlin.run {
NumberFormat.getCurrencyInstance(Locale("pt","pt"))
    }.format(this)
}