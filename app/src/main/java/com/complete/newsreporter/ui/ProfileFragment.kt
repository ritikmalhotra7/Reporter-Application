package com.complete.newsreporter.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import coil.load
import com.complete.newsreporter.R
import com.complete.newsreporter.utils.Constants.firebaseAuth
import com.complete.newsreporter.utils.Constants.storageReference
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {


    private val REQUEST_CODE: Int = 100

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageprofile.load(storageReference.child(firebaseAuth.currentUser!!.uid).downloadUrl.toString())

        fabprofile.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            imageprofile.load(data?.data)
            storageReference.child(firebaseAuth.currentUser!!.uid).putFile(data?.data!!)
                .addOnCompleteListener { Log.d("taget", "completed") }
        }
    }

}