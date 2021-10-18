package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.databinding.ActivityDetalhesProdutoBinding
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.ui.extensions.formataParaMoedaPortuguesa
import br.com.alura.orgs.ui.extensions.tentaCarregarImagem

class DetalhesProdutoActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        tentaCarregarProduto()
    }

    private fun tentaCarregarProduto() {
        // tentativa de buscar o produto se ele existir,
        // caso contrário, finalizar a Activity
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            preencheCampos(produtoCarregado)
        } ?: run {
            Toast.makeText(this, "deu ruim xxt", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        binding.apply {
            activityDetalhesProdutoImagem.tentaCarregarImagem(
                produtoCarregado.imagem,
                this@DetalhesProdutoActivity
            )
            activityDetalhesProdutoValor.text = produtoCarregado.valor.formataParaMoedaPortuguesa()
            activityDetalhesProdutoNome.text = produtoCarregado.nome
            activityDetalhesProdutoDescricao.text = produtoCarregado.descricao
        }
    }
}