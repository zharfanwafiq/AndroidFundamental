package com.zharfan.androidfundamental.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zharfan.androidfundamental.databinding.ItemListQuotesBinding

class ListQuoteAdapter:RecyclerView.Adapter<ListQuoteAdapter.ViewHolder>() {

    private val listQuotes = ArrayList<String>()

    fun showListQuotes(ListQuotes: ArrayList<String>){
        this.listQuotes.clear()
        this.listQuotes.addAll(ListQuotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListQuoteAdapter.ViewHolder {
        val inflater = ItemListQuotesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ListQuoteAdapter.ViewHolder, position: Int) {
        holder.bind(listQuotes[position])
    }

    inner class ViewHolder(private val binding: ItemListQuotesBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(quote: String)= with(binding){
                    tvQuote.text = quote
                }
            }

    override fun getItemCount(): Int {
        return listQuotes.size
    }
}