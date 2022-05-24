package com.complete.newsreporter.ui

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import coil.load
import com.complete.newsreporter.R
import com.complete.newsreporter.model.User
import com.complete.newsreporter.utils.Constants
import com.complete.newsreporter.utils.Constants.Companion.dbReferenceUsers
import com.complete.newsreporter.utils.Constants.Companion.firebaseAuth
import com.complete.newsreporter.utils.Constants.Companion.storageReference
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_setting.*
import java.io.File
import java.util.ArrayList


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
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            imageprofile.load(data?.data)
            storageReference.child(firebaseAuth.currentUser!!.uid).putFile(data?.data!!).addOnCompleteListener { Log.d("taget","completed") }
        }
    }

}