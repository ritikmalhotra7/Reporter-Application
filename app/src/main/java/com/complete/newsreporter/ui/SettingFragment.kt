package com.complete.newsreporter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.complete.newsreporter.R
import com.complete.newsreporter.databinding.FragmentSettingBinding
import com.complete.newsreporter.utils.Constants.Companion.showNotification

class SettingFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val b = FragmentSettingBinding.inflate(inflater)

        b.switchNotification.setOnCheckedChangeListener(object:CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                showNotification = isChecked
            }
        })
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

}