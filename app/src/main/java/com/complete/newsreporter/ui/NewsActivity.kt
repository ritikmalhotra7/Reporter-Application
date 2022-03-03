package com.complete.newsreporter.ui

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.complete.newsreporter.R
import com.complete.newsreporter.database.ArticleDatabase
import com.complete.newsreporter.database.NewsRepository
import com.complete.newsreporter.databinding.ActivityNewsBinding
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.activity_news.view.*


class NewsActivity : AppCompatActivity() {
    private var _binding:ActivityNewsBinding? = null
    val binding : ActivityNewsBinding get() = _binding!!

    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository :NewsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelFactory = NewsViewModelProviderFactory(application,repository)
        newsViewModel = ViewModelProvider(this,viewModelFactory).get(NewsViewModel::class.java)
        /*bottomNavigationView.background = null*/

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
        binding.fab.setOnClickListener {
           newsNavHostFragment.findNavController().navigate(R.id.searchNewsFragment)
        }
       /* setCurrentFragment(BreakingNewsFragment())
        binding.fab.setOnClickListener {
            setCurrentFragment(SearchNewsFragment())
        }
        bottomAppBar.bottomNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.breakingNewsFragment -> setCurrentFragment(BreakingNewsFragment())
                R.id.sosFragment -> setCurrentFragment(SosFragment())
                R.id.tvFragment -> setCurrentFragment(TvFragment())
                R.id.savedNewsFragment -> setCurrentFragment(SavedNewsFragment())
            }
            true
        }*/
        /*val navView: BottomNavigationView = binding.bottomNavigationView

        val navController = findNavController(R.id.newsNavHostFragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.breakingNewsFragment, R.id.tvFragment, R.id.searchNewsFragment,R.id.sosFragment,R.id.savedNewsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)*/
    }
    private fun setCurrentFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().apply {

            replace(R.id.flFragment,fragment)
            commit()
        }
        Log.d("taget","hogya change")
    }
}