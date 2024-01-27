package com.gunay.retrofitwithkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunay.retrofitwithkotlin.databinding.RecyclerRowBinding
import com.gunay.retrofitwithkotlin.model.CryptoModel

class CryptoAdapter(private val cryptoList: ArrayList<CryptoModel>, private val listener: Listener ): RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    private val colors: Array<String> = arrayOf("#13bd27","#29c1e1","#b129e1","#d3df13","#f6bd0c","#a1fb93","#0d9de3","#ffe48f")

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }

    class CryptoHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoAdapter.CryptoHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }

    override fun onBindViewHolder(holder: CryptoAdapter.CryptoHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClick(cryptoList.get(position))
        }
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))

        holder.binding.textName.text = cryptoList.get(position).currency
        holder.binding.textPrice.text = cryptoList.get(position).price
    }


}