package com.example.lymidateproject.fragment

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lymidateproject.R
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    var selectedPicture: Uri? = null
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        create_profile_picture.setOnClickListener {
            uploadClicked(it)
        }
        btncreate.setOnClickListener {
            navController?.navigate(R.id.action_registerFragment_to_homePageFragment)
        }

    }

    fun uploadClicked(view: View) {

        if (ContextCompat.checkSelfPermission(
                view.context,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                view.context as Activity,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } else {
            val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i, 2)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(i, 2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 1 || requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPicture = data!!.data
            try {
                if (selectedPicture != null) {
                    if (Build.VERSION.SDK_INT >= 28) {
                        val source = ImageDecoder.createSource(
                            view?.context?.contentResolver!!,
                            selectedPicture!!
                        )
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        create_profile_picture.setImageBitmap(bitmap)
                    } else {
                        val bitmap =
                            MediaStore.Images.Media.getBitmap(
                                view?.context?.contentResolver,
                                selectedPicture
                            )
                        create_profile_picture.setImageBitmap(bitmap)
                    }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}