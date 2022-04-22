package com.complete.newsreporter.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.complete.newsreporter.R
import com.complete.newsreporter.adapter.NewsAdapter
import com.complete.newsreporter.database.ArticleDatabase
import com.complete.newsreporter.database.NewsRepository
import com.complete.newsreporter.databinding.FragmentBreakingNewsBinding
import com.complete.newsreporter.model.Article
import com.complete.newsreporter.utils.Constants.Companion.QUERY_SIZE
import com.complete.newsreporter.utils.Resources
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.coroutines.NonDisposableHandle.parent


class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel:NewsViewModel
    lateinit var newsAdapter : NewsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).newsViewModel
        setUpRecyclerView()


        viewModel.breakingNews.observe(viewLifecycleOwner, Observer{response->
             when(response){
                 is Resources.Success ->{
                     hideProgressBar()
                     response.data?.let {newsResponse ->  
                         newsAdapter.setList(newsResponse.articles.toList())
                     }
                 }
                 is Resources.Error ->{
                     hideProgressBar()
                     response.data?.let{
                         Toast.makeText(requireContext(),"An Error occured $it",Toast.LENGTH_SHORT).show()
                         Log.d("tagetv","Something is wrong")
                     }
                 }
                 is Resources.Loading ->{
                     showProgressBar()
                 }
             }
        })

    }
    private fun hideProgressBar(){
        avi.hide()
    }
    private fun showProgressBar(){
        avi.show()
    }
    private fun setUpRecyclerView(){
        newsAdapter = NewsAdapter(requireActivity(), arrayListOf<Article>())
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

            val helper: SnapHelper = LinearSnapHelper()
            helper.attachToRecyclerView(this)
        }

        newsAdapter.notifyDataSetChanged()

        newsAdapter.setOnClickListener {article->
            val bundle = Bundle().apply {
                putSerializable("article",article)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )

        }
    }
}