package com.androidbook.mediarecorderexample

import android.Manifest
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var mediaRecorder: MediaRecorder
    lateinit var mediaPlayer: MediaPlayer
    lateinit var audioFilePath :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE),
            MODE_PRIVATE
        )

        val btnStart: Button = findViewById(R.id.btnStart)
        val btnStop:Button = findViewById(R.id.btnStop)
        val btnPlay:Button = findViewById(R.id.btnPlay)

        val outputDir: File? = getExternalFilesDir(null)
        audioFilePath = "${outputDir?.absolutePath}/recording.mp4"

        btnStart.isEnabled = true
        btnStop.isEnabled = false
        btnPlay.isEnabled = false

        btnStart.setOnClickListener {
            btnStart.isEnabled = false
            btnStop.isEnabled = true

            mediaRecorder = MediaRecorder()
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC )
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            mediaRecorder.setOutputFile(audioFilePath)
            mediaRecorder.prepare()
            mediaRecorder.start()
        }

        btnStop.setOnClickListener {
            btnStart.isEnabled = true
            btnStop.isEnabled = false
            btnPlay.isEnabled = true

            mediaRecorder.stop()
            mediaRecorder.release()
        }

        btnPlay.setOnClickListener {
            mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(applicationContext, Uri.parse(audioFilePath))
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
    }
}