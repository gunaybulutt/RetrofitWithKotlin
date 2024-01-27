package com.gunay.retrofitwithkotlin.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(

    // eger degiskenlerin degerini gelecek json verilerindeki ile aynı yaparsan @SerializedName kullanmana gerek yok bu data snıflarının ozelligi
    //@SerializedName("currency")
    val currency: String,

    //@SerializedName("price")
    val price: String) {
}