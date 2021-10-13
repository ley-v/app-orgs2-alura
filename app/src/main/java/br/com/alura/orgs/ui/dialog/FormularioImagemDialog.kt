package br.com.alura.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.databinding.FormularioImagemBinding
import br.com.alura.orgs.ui.extensions.tentaCarregarImagem

class FormularioImagemDialog(private val context: Context) {

    fun mostra(quandoImagemCarregada: (urlImagem: String) -> Unit){
        val binding = FormularioImagemBinding.inflate(LayoutInflater.from(context))
        binding.formularioImagemBotaoCarregar.setOnClickListener {
            val url = binding.formularioImagemUrl.text.toString()
            binding.formularioImagemImageview.tentaCarregarImagem(url, context)
        }
        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar") { _, _ ->
                val url = binding.formularioImagemUrl.text.toString()
                quandoImagemCarregada(url)
            }
            .setNegativeButton("Cancelar") { _, _ ->

            }
            .show()
    }
}