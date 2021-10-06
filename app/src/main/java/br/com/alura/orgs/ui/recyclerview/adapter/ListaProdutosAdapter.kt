package br.com.alura.orgs.ui.recyclerview.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.model.Produto
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()


    inner class ViewHolder(private val binding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var imageLoader = ImageLoader.Builder(context)
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder(context))
                } else {
                    add(GifDecoder())
                }
            }
            .build()

        fun vincula(produto: Produto) {
            val nome = binding.produtoItemNome
            nome.text = produto.nome
            val descricao = binding.produtoItemDescricao
            descricao.text = produto.descricao
            val valor = binding.produtoItemValor
            val valorEmMoeda = formataParaMoedaEuro(produto.valor)
            valor.text = valorEmMoeda
            val imagem = binding.imageView

            val visibilidade = if (produto.imagem != null) View.VISIBLE
            else View.GONE

            imagem.visibility = visibilidade

            imagem.load(produto.imagem, imageLoader) {
                fallback(R.drawable.erro)
                error(R.drawable.erro)
                placeholder(R.drawable.plano_de_fundo_carregamento)
            }
        }

        private fun formataParaMoedaEuro(valor: BigDecimal): String? {
            val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "pt"))
            return formatador.format(valor)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
