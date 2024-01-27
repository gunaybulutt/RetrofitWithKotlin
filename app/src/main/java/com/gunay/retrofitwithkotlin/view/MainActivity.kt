package com.gunay.retrofitwithkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gunay.retrofitwithkotlin.adapter.CryptoAdapter
import com.gunay.retrofitwithkotlin.databinding.ActivityMainBinding
import com.gunay.retrofitwithkotlin.model.CryptoModel
import com.gunay.retrofitwithkotlin.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class MainActivity : AppCompatActivity(), CryptoAdapter.Listener {

    lateinit var binding: ActivityMainBinding
    private val base = "https://raw.githubusercontent.com/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private var cryptoAdapter: CryptoAdapter? = null

    //Disposible --->> aktivite veya fragment yaşam döngüsünden çıktığında gerekli call'ları yok eder
    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        compositeDisposable = CompositeDisposable()

        val leyoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@MainActivity)
        binding.recyclerView.layoutManager = leyoutManager

        loadData()

    }


    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CryptoAPI::class.java)


        compositeDisposable?.add(
            retrofit.getData()
                .subscribeOn(Schedulers.io()) // kayıt olma --veriyi alma(dinleme) isleminin yapilacagi yer
                .observeOn(AndroidSchedulers.mainThread()) // veriyi isleyecegimiz yer
                .subscribe(this::handleResponse))// verinin aktarıldıgı yer)

    }

    private fun handleResponse(cryptoList: List<CryptoModel>) {
        cryptoModels = ArrayList(cryptoList)

        cryptoModels?.let {
            cryptoAdapter = CryptoAdapter(it, this@MainActivity)
            binding.recyclerView.adapter = cryptoAdapter
        }


    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this, "Clicked : ${cryptoModel.currency}", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy(){
        super.onDestroy()
        compositeDisposable?.clear()
    }

}








































