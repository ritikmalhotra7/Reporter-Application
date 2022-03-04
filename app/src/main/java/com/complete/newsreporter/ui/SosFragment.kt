package com.complete.newsreporter.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.complete.newsreporter.R
import com.complete.newsreporter.databinding.FragmentSosBinding

class SosFragment : Fragment(R.layout.fragment_sos) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val b = FragmentSosBinding.inflate(inflater)
        b.ivAmbulance.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + "102")
            startActivity(intent)
        }
        b.ivPolice.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + "100")
            startActivity(intent)
        }
        b.ivFire.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + "101")
            startActivity(intent)
        }
        return b.root
    }
}