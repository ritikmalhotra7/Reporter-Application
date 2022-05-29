package com.complete.newsreporter.adapter

import android.content.Context
import android.content.Intent
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.BlurTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.complete.newsreporter.R
import com.complete.newsreporter.databinding.ItemArticlePreviewBinding
import com.complete.newsreporter.model.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter(val ctx:Context) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    var ls :List<Article> = arrayListOf()
    inner class ArticleViewHolder(val binding: ItemArticlePreviewBinding):RecyclerView.ViewHolder(binding.root) {
    }
//    private val callback = object : DiffUtil.ItemCallback<Article>(){
//        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
//            return newItem.url == oldItem.url
//        }
//        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
//            return newItem == oldItem
//        }
//    }
//    private val differ = AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))}

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentArticle = ls[position]

        holder.binding.root.apply{
            ivArticleImage.load(currentArticle.urlToImage){
                transformations(RoundedCornersTransformation(20F))
                placeholder(R.drawable.ic_news)
            }

            iv_share.setOnClickListener(View.OnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT,"Hey, checkout this news : "+currentArticle.url)
                intent.type = "text/plain"
                context.startActivity(Intent.createChooser(intent,"Share with :"))
            })
            tvSource.text = currentArticle.source?.name
            tvTitle.text = currentArticle.title
            tvDescription.text = currentArticle.description
            tvPublishedAt.text = currentArticle.publishedAt!!.split("T")[0]+"  "+
                    currentArticle.publishedAt!!.split("T")[1].substring(0,currentArticle.publishedAt!!.split("T")[1].length-1)
            tvUrl.text = currentArticle.url
            setOnClickListener{
                Log.d("taget",currentArticle.url.toString())
                onItemClickListener?.let {
                    it(currentArticle)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return ls.size
    }

    private var onItemClickListener:((Article)->Unit)? = null

    fun setOnClickListener(listener:(Article) -> Unit){
        onItemClickListener = listener
    }

    fun setList(newLs:List<Article>){
        val diffUtil = MyDiffUtils(ls,newLs)
        val diffUtilResults = DiffUtil.calculateDiff(diffUtil)
        ls = newLs
        diffUtilResults.dispatchUpdatesTo(this)
    }
}