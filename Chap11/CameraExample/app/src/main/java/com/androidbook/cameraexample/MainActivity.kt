package com.androidbook.cameraexample

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var btnPhoto: Button
    lateinit var ivPhoto: ImageView
    lateinit var m_imageFile: File
    lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnPhoto = findViewById(R.id.btn_photo)
        ivPhoto = findViewById(R.id.iv_photo)

        btnPhoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(getPackageManager()) != null) {
                val photoFile: File = createImageFile()
                val packageName = "com.androidbook.cameraexample.fileprovider"
                val photoURI = FileProvider.getUriForFile(this, packageName, photoFile)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                launcher.launch(intent)
                m_imageFile = photoFile

            } else Toast.makeText(this, "카메라를 오픈할 수 없습니다", Toast.LENGTH_SHORT).show()
        }
        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {

                val bitmap :Bitmap = BitmapFactory.decodeFile(m_imageFile.getAbsolutePath())
                ivPhoto.setImageBitmap(bitmap);
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile: File = File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir )
        return imageFile
    }
}