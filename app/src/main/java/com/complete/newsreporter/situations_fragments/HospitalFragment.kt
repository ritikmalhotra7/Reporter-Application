package com.complete.newsreporter.situations_fragments

import android.annotation.SuppressLint
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
import com.complete.newsreporter.databinding.FragmentHospitalEmergenciesBinding
import com.complete.newsreporter.model.Article
import com.complete.newsreporter.utils.Constants.medicalSituations
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.android.synthetic.main.fragment_hospital_emergencies.*


class HospitalFragment : Fragment() {

    lateinit var situationAdapter: SituationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val b = FragmentHospitalEmergenciesBinding.inflate(inflater)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpRecyclerView(){
        situationAdapter = SituationAdapter(requireActivity(),medicalSituations )
        rv_situations.apply {
            adapter = situationAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        situationAdapter.notifyDataSetChanged()
        situationAdapter.onClickListener {situation->
            val bundle = Bundle().apply {
                putSerializable("situation",situation)
            }
            findNavController().navigate(R.id.action_emergencyHospitalFragment_to_resultFragment,bundle)
        }
    }


}