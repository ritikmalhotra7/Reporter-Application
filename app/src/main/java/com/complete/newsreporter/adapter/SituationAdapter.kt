package com.complete.newsreporter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.complete.newsreporter.databinding.ItemArticlePreviewBinding
import com.complete.newsreporter.databinding.SituationlsBinding
import com.complete.newsreporter.model.Situations
import kotlinx.android.synthetic.main.situationls.view.*

class SituationAdapter(val ctx : Context, val ls :ArrayList<Situations>) :
    RecyclerView.Adapter<SituationAdapter.Viewholder>() {
    inner class Viewholder (val b:SituationlsBinding):RecyclerView.ViewHolder(b.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(SituationlsBinding.inflate(LayoutInflater.from(parent.context),parent,false))}

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val currentString = ls.get(position)

        holder.b.apply {
            tvPosition.text = (position-1).toString()
            tvName.text = currentString.name
            tvDesc.text = currentString.desc
        }
    }

    override fun getItemCount(): Int {
        return ls.size
    }
}
