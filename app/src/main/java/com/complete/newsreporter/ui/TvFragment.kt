package com.complete.newsreporter.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import coil.load
import com.complete.newsreporter.R
import com.complete.newsreporter.databinding.FragmentTvBinding
import com.complete.newsreporter.utils.Constants.Companion.AAJ_TAK
import com.complete.newsreporter.utils.Constants.Companion.ABP_NEWS
import com.complete.newsreporter.utils.Constants.Companion.GNT_NEWS
import com.complete.newsreporter.utils.Constants.Companion.INDIA_TODAY_NEWS
import com.complete.newsreporter.utils.Constants.Companion.NDTV_NEWS
import kotlinx.android.synthetic.main.fragment_tv.*

class TvFragment : Fragment(R.layout.fragment_tv) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTvBinding.inflate(inflater)
        val listOfChannelName = listOf("ABP News","NDTV News","India Today News","Ajj Tak News","GNT News")

        binding.tv1.text = listOfChannelName[0]
        binding.iv1.load(R.drawable.abp)
        binding.tv2.text = listOfChannelName[1]
        binding.iv2.load(R.drawable.ndtv)
        binding.tv3.text = listOfChannelName[2]
        binding.iv3.load(R.drawable.india_today)
        binding.tv4.text = listOfChannelName[3]
        binding.iv4.load(R.drawable.aaj_tak)
        binding.tv5.text = listOfChannelName[4]
        binding.iv5.load(R.drawable.gnt)
        binding.cv1.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(TvFragmentDirections.actionTvFragmentToWatchTvFragment(ABP_NEWS))
            Log.d("taget","clicked")
        }
        binding.cv2.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(TvFragmentDirections.actionTvFragmentToWatchTvFragment(NDTV_NEWS))
            Log.d("taget","clicked")
        }
        binding.cv3.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(TvFragmentDirections.actionTvFragmentToWatchTvFragment(INDIA_TODAY_NEWS))
            Log.d("taget","clicked")
        }
        binding.cv4.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(TvFragmentDirections.actionTvFragmentToWatchTvFragment(AAJ_TAK))
            Log.d("taget","clicked")
        }
        binding.cv5.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(TvFragmentDirections.actionTvFragmentToWatchTvFragment(GNT_NEWS))
            Log.d("taget","clicked")
        }
        return binding.root
    }
}