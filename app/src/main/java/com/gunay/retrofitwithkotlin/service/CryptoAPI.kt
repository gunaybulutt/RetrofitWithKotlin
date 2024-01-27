package com.gunay.retrofitwithkotlin.service

import com.gunay.retrofitwithkotlin.model.CryptoModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    //https://raw.githubusercontent.com/   --- baz kısmı
    //atilsamancioglu/K21-JSONDataSet/master/crypto.json

    //get içinde verinin geleceği yer belirtilir ve oluşturulan fonksiyonda ise adı ve verilecek cikti belirtilir (call cagrisi icinde bir model listesi)
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")

    //veriler geldiğinde(değişiklik olduğunda) alan ve yayın yapan gözlemlenebilir bir obje ---> observable
    fun getData(): Observable<List<CryptoModel>>

    //fun getData(): Call<List<CryptoModel>>

}