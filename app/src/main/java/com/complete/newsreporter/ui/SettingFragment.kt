package com.complete.newsreporter.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.navigation.Navigation
import com.complete.newsreporter.R
import com.complete.newsreporter.databinding.FragmentSettingBinding
import com.complete.newsreporter.utils.Constants
import com.complete.newsreporter.utils.Constants.Companion.firebaseAuth
import com.royrodriguez.transitionbutton.TransitionButton
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment(R.layout.fragment_setting) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val b = FragmentSettingBinding.inflate(inflater)

        b.switchNotification.setOnCheckedChangeListener(object:CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                val enabled = "enabled"
                val disabled = "diabled"
                if(isChecked){
                    Toast.makeText(requireContext(),"Your notification will be "+enabled ,Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(),"Your notification will be "+disabled ,Toast.LENGTH_SHORT).show()
                }
            }
        })
        b.ll4.setOnClickListener {
            Navigation.findNavController(b.root).navigate(R.id.action_settingFragment_to_savedNewsFragment)
        }
        b.backbutton.setOnClickListener {
            Navigation.findNavController(b.root).navigate(R.id.action_settingFragment_to_breakingNewsFragment)
        }
        b.profile.setOnClickListener {
            Navigation.findNavController(b.root).navigate(R.id.action_settingFragment_to_profileFragment)
        }
        b.logoutButton.setOnClickListener {
            b.logoutButton.startAnimation()
            b.logoutButton.stopAnimation(
                TransitionButton.StopAnimationStyle.EXPAND,
                TransitionButton.OnAnimationStopEndListener {
                    firebaseAuth.signOut()
                    val intent = Intent(activity, LoginActivity::class.java)
                    activity?.finish()
                    startActivity(intent)

                })
        }
        return b.root
    }
}