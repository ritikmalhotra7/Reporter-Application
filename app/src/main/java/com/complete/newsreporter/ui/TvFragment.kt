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
import com.complete.newsreporter.utils.Constants.Companion.ABP_NEWS
import kotlinx.android.synthetic.main.fragment_tv.*

class TvFragment : Fragment(R.layout.fragment_tv) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTvBinding.inflate(inflater)
        val listOfChannelName = listOf("ABP News","NDTV News","India Today News","Ajj Tak News","GNT News")

        binding.tv1.text = listOfChannelName[0]
        binding.iv1.load(R.drawable.aaj_tak)
        binding.iv2.load(R.drawable.ndtv)
        binding.tv2.text = listOfChannelName[1]
        binding.cv1.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(TvFragmentDirections.actionTvFragmentToWatchTvFragment(ABP_NEWS))
            Log.d("taget","clicked")
        }

        return binding.root
    }
}