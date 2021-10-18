package br.com.alura.orgs.ui.extensions

import android.content.Context
import android.os.Build
import android.widget.ImageView
import br.com.alura.orgs.R
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load


fun ImageView.tentaCarregarImagem(url: String? = null, context: Context) {
    val imageLoader = ImageLoader.Builder(context)
        .componentRegistry {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder(context))
            } else {
                add(GifDecoder())
            }
        }
        .build()

    load(url, imageLoader) {
        fallback(R.drawable.imagem_padrao)
        error(R.drawable.erro)
        placeholder(R.drawable.plano_de_fundo_carregamento_cinza)
    }
}