package com.complete.newsreporter.situations_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.complete.newsreporter.R
import com.complete.newsreporter.databinding.FragmentFireEmergencyBinding


class FireFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val b = FragmentFireEmergencyBinding.inflate(inflater)



        return b.root
    }
}