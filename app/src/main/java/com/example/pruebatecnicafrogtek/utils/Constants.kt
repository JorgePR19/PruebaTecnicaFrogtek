package com.example.pruebatecnicafrogtek.utils

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import java.text.SimpleDateFormat
import java.util.Locale

object Constants {
    const val BASE_URL = "https://punkapi.online/v3/"
    const val BEERS_ENDPOINT = "beers"
    const val IMAGE_ENDPOINT = "https://punkapi.online/v3/images/"
    const val ERROR_APPEND = "Algo salió mal al cargar los datos."
    const val ERROR_END_LIST = "No hay más contenido para mostrar."

    fun String.formatDate(): String {
        val inputFormat = SimpleDateFormat("MM/yyyy", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMMM yyyy", Locale("es", "MX"))

        return try {
            val date = inputFormat.parse(this)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            this
        }
    }

    fun  CombinedLoadStates.getTypeOfError(): LoadState.Error? {
        val error =
            when {
            this.refresh is LoadState.Error -> this.refresh as LoadState.Error
            this.append is LoadState.Error -> this.append as LoadState.Error
            this.prepend is LoadState.Error -> this.prepend as LoadState.Error
            else -> null
        }

        return error
    }
}