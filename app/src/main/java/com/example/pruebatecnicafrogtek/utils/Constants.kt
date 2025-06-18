package com.example.pruebatecnicafrogtek.utils

import java.text.SimpleDateFormat
import java.util.Locale

object Constants {
    const val BASE_URL = "https://punkapi.online/v3/"
    const val BEERS_ENDPOINT = "beers"
    const val IMAGE_ENDPOINT = "https://punkapi.online/v3/images/"

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
}