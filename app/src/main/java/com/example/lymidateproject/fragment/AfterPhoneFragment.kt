package com.example.lymidateproject.fragment

import android.app.Activity
import android.app.AlertDialog
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
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.lymidateproject.R
import kotlinx.android.synthetic.main.fragment_after_phone.*


class AfterPhoneFragment : Fragment() {

    var selectedPicture: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_after_phone, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialog = AlertDialog.Builder(view.context)
        val dialogView = layoutInflater.inflate(R.layout.warming_photo_dialog, null)
        dialog.setView(dialogView)
        val asd = dialogView.findViewById(R.id.btn_select_image) as Button
        asd.setOnClickListener {
            uploadClicked(it)
        }
        dialog.setCancelable(false)
        dialog.show()
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


/*        if (ContextCompat.checkSelfPermission(
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
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 2)
        }*/
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
/*        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 2)
            }
        }*/
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
                        profile_picture.setImageBitmap(bitmap)
                    } else {
                        val bitmap =
                            MediaStore.Images.Media.getBitmap(
                                view?.context?.contentResolver,
                                selectedPicture
                            )
                        profile_picture.setImageBitmap(bitmap)
                    }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

/*       if (!(requestCode != 1 || resultCode != 2 || RESULT_OK || data == null)) {
            selectedPicture = data.data
            try {
                if (selectedPicture != null) {
                    if (Build.VERSION.SDK_INT >= 28) {
                        val source = ImageDecoder.createSource(view?.context?.contentResolver!!, selectedPicture!!)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        profile_picture.setImageBitmap(bitmap)
                    } else {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            view?.context?.contentResolver,
                            selectedPicture
                        )
                        profile_picture.setImageBitmap(bitmap)
                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }*/
        super.onActivityResult(requestCode, resultCode, data)
    }
}