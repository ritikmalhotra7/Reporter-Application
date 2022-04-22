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
import com.complete.newsreporter.databinding.FragmentProfileBinding
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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val b = FragmentProfileBinding.inflate(layoutInflater)

        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uid = firebaseAuth.currentUser?.uid
        val url  = storageReference?.child("dp").child(uid!!)
        imageprofile.load(url.toString()){placeholder(R.drawable.icons8_person)}
        dbReferenceUsers.child(uid!!).get().addOnCompleteListener {
            if(it.isSuccessful) {
                val u = it.result.getValue(User::class.java)
                nameprofile.text = u?.name
                if (u!!.email != null && u!!.phone == null) {
                    detailprofile.text = u.email.toString()
                    Log.d("tag", u.email.toString())
                } else if(u!!.phone != null && u!!.email == null) {
                    Log.d("tag", u.phone.toString())
                    detailprofile.text = u.phone.toString()
                }
            }
        }
        val storagRef = storageReference.child(firebaseAuth.currentUser!!.uid).child(firebaseAuth.currentUser!!.uid+"profile")
        val localFile = File.createTempFile("temp","jpg")
        storagRef.getFile(localFile).addOnSuccessListener {
            val bitMap = BitmapFactory.decodeFile(localFile.absolutePath)
            imageprofile.setImageBitmap(bitMap)
        }
        fabprofile.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK)
            galleryIntent.type = "image/*, video/*"
            startActivityForResult(
                Intent.createChooser(galleryIntent, "Select File"),1000
            )
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1000) {
            if(data != null){
                val imageUri = data.data!!
                Constants.storageReference.child(firebaseAuth.currentUser!!.uid).child(firebaseAuth.currentUser!!.uid+"profile")
                    .putFile(data.data!!).addOnCompleteListener {
                        Toast.makeText(activity,"saved", Toast.LENGTH_LONG).show()
                    }
                imageprofile.load(imageUri)
            }else{
                Toast.makeText(activity,"Empty Data", Toast.LENGTH_SHORT).show()
            }

        }
    }

}