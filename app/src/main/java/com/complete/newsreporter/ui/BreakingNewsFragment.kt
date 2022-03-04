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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.complete.newsreporter.R
import com.complete.newsreporter.adapter.NewsAdapter
import com.complete.newsreporter.databinding.FragmentBreakingNewsBinding
import com.complete.newsreporter.utils.Constants.Companion.QUERY_SIZE
import com.complete.newsreporter.utils.Resources
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.coroutines.NonDisposableHandle.parent


class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel:NewsViewModel
    lateinit var newsAdapter : NewsAdapter
    private var _binding: FragmentBreakingNewsBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).newsViewModel
        setUpRecyclerView()

        newsAdapter.setOnClickListener {article->
            val bundle = Bundle().apply {
                putSerializable("article",article)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer{response->
            Log.d("taget",response.data?.articles?.size.toString())
             when(response){
                 is Resources.Success ->{
                     hideProgressBar()
                     response.data?.let {newsResponse ->  
                         newsAdapter.differ.submitList(newsResponse.articles.toList())
                         Log.d("taget",newsResponse.articles.size.toString())
                         val totalPages = newsResponse.totalResults/ QUERY_SIZE + 2
                         isLastPage = totalPages == viewModel.breakingNewsPage
                         if(isLastPage){
                             rvBreakingNews.setPadding(0,0,0,0)
                         }

                     }
                 }
                 is Resources.Error ->{
                     hideProgressBar()
                     response.data?.let{
                         Toast.makeText(activity,"An Error occured $it",Toast.LENGTH_SHORT).show()
                         Log.d("taget","Something is wrong")
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
        isLoading = false
    }
    private fun showProgressBar(){
        avi.show()
        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true

            }
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotOnLastPage = !isLoading && !isLastPage
            val isAtLastItem = (firstVisibleItemPosition == totalItemCount-visibleItemCount)
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_SIZE
            val shouldPaginate = isNotLoadingAndNotOnLastPage &&
                    isNotAtBeginning && isTotalMoreThanVisible && !isAtLastItem && isScrolling
            if(shouldPaginate){
                viewModel.getBreakingNews("in")
                isScrolling = false
            }
            super.onScrolled(recyclerView, dx, dy)
        }
    }
    private fun setUpRecyclerView(){
        newsAdapter = NewsAdapter(requireActivity())
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@BreakingNewsFragment.scrollListener)

            val helper: SnapHelper = LinearSnapHelper()
            helper.attachToRecyclerView(this)
        }
    }
}