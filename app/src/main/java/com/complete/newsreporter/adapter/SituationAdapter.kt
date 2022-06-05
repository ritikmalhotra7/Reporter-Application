package com.complete.newsreporter.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.complete.newsreporter.databinding.ItemListBinding
import com.complete.newsreporter.model.Situations

class SituationAdapter(val ctx : Context, val ls :ArrayList<Situations>) :
    RecyclerView.Adapter<SituationAdapter.Viewholder>() {
    inner class Viewholder (val b: ItemListBinding):RecyclerView.ViewHolder(b.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val currentString = ls.get(position)

        holder.b.apply {
           title.text = currentString.name
            onClickListener {mListner?.let{it(currentString)}}
        }
    }

    override fun getItemCount(): Int {
        return ls.size
    }
    private var mListner:((Situations)->Unit)? = null
    fun onClickListener(listener:((Situations)->Unit)){
        mListner = listener
    }
}
