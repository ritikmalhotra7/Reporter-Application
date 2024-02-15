package com.complete.newsreporter.ui

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.complete.newsreporter.utils.Constants
import com.complete.newsreporter.utils.read
import com.complete.newsreporter.utils.readPos
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.activity_news.view.*
import kotlinx.android.synthetic.main.fragment_profile.*


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
        bottomNavigationView.background = null
        bottomNavigationView.menu.get(2).isEnabled = false
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
        fab.setOnClickListener {
           newsNavHostFragment.findNavController().navigate(R.id.searchNewsFragment)
        }
        Constants.setRegion(this, this.readPos("pos"))

        /*
        updated code on 15-feb-2024
        val navController = findNavController(R.id.activity_main_fcv)

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener(object:NavController.OnDestinationChangedListener{
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                when(destination.id){
                    R.id.splashFragment -> binding.bottomNavigationView.visibility = View.GONE
                    R.id.emailLoginFragment -> binding.bottomNavigationView.visibility = View.GONE
                    R.id.mobileLoginFragment -> binding.bottomNavigationView.visibility = View.GONE
                    R.id.usernameLoginFragment -> binding.bottomNavigationView.visibility = View.GONE
                    else -> binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        })
        */

        
        /*bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.breakingNewsFragment -> newsNavHostFragment.findNavController().navigate()
            }
            true
        }*/
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
    private fun setCurrentFragment(fragment : Fragment) : Boolean {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.newsNavHostFragment,fragment)
            commit()
        }
        Log.d("taget","hogya change")
        return true
    }
}
