package com.example.camerahospital

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    val TAKE_PHOTO = 1001
    val CAMERA_CAPTURE_PHOTO = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButton()
        permission()

    }

    fun permission(){
        ActivityCompat.requestPermissions(this, arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView: ImageView = findViewById(R.id.image_view)
        if (requestCode == CAMERA_CAPTURE_PHOTO) {
            imageView.setImageBitmap(data?.extras?.get("data") as Bitmap)
        }
        if(requestCode == TAKE_PHOTO) {
            imageView.setImageURI(data?.data)
        }
    }

    fun initButton() {
        val cameraButton : Button = findViewById(R.id.camera_button)
        val albumButton : Button = findViewById(R.id.album_button)
        cameraButton.setOnClickListener({
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_CAPTURE_PHOTO)
        })

        albumButton.setOnClickListener({
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, TAKE_PHOTO)
        })
    }
}