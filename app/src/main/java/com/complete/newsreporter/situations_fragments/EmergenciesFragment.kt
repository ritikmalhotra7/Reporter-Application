package com.complete.newsreporter.situations_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.complete.newsreporter.R
import com.complete.newsreporter.adapter.NewsAdapter
import com.complete.newsreporter.adapter.SituationAdapter
import com.complete.newsreporter.databinding.FragmentEmergenciesBinding
import com.complete.newsreporter.model.Article
import com.complete.newsreporter.utils.Constants.Companion.medicalSituations
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.android.synthetic.main.fragment_emergencies.*


class EmergenciesFragment : Fragment() {

    lateinit var adapter: SituationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val b = FragmentEmergenciesBinding.inflate(inflater)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }
    private fun setUpRecyclerView(){
        adapter = SituationAdapter(requireActivity(),medicalSituations )
        rv_situations.apply {
            adapter = adapter
            layoutManager = LinearLayoutManager(activity)
        }

        adapter.notifyDataSetChanged()

    }


}