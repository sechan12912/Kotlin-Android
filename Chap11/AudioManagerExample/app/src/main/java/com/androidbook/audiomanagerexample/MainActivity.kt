package com.androidbook.audiomanagerexample

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var audioManager: AudioManager
    private lateinit var vibrateBtn: Button
    private lateinit var ringBtn: Button
    private lateinit var silentBtn: Button
    private lateinit var volumeUpBtn: Button
    private lateinit var volumeDownBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        silentBtn = findViewById(R.id.btnSilent)
        ringBtn = findViewById(R.id.btnRing)
        vibrateBtn = findViewById(R.id.btnVibrate)
        volumeUpBtn = findViewById(R.id.btnVolumnUp)
        volumeDownBtn = findViewById(R.id.btnVolumnDown)

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        ringBtn.setOnClickListener {
            audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
            showToast("벨소리 모드로 변경됨")
        }

        silentBtn.setOnClickListener {
            audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
            showToast("무음 모드로 변경됨")
        }

        vibrateBtn.setOnClickListener {
            audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
            showToast("진동 모드로 변경됨")
        }

        volumeUpBtn.setOnClickListener {
            val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)
            val newVolume = (maxVolume * 0.9).toInt()
            audioManager.setStreamVolume(AudioManager.STREAM_RING, newVolume,
                                        AudioManager.FLAG_PLAY_SOUND)
            showToast("벨소리 볼륨 높임")
        }

        volumeDownBtn.setOnClickListener {
            val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)
            val newVolume = (maxVolume * 0.25).toInt()
            audioManager.setStreamVolume(AudioManager.STREAM_RING, newVolume,
                                        AudioManager.FLAG_PLAY_SOUND)
            showToast("벨소리 볼륨 낮춤")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}