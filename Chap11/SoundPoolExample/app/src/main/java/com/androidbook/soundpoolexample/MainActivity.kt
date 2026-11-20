package com.androidbook.soundpoolexample

import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var soundPool: SoundPool
    lateinit var playBtn : Button
    lateinit var stopBtn : Button
    lateinit var txtSname : TextView
    var soundId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtSname = findViewById(R.id.txtSname)
        txtSname.text = "effect1.mp3"

        playBtn = findViewById(R.id.playBtn)
        stopBtn = findViewById(R.id.stopBtn)

        soundPool  = SoundPool.Builder().build()
        soundId = soundPool.load(applicationContext, R.raw.effect1, 1)

        playBtn.setOnClickListener{
            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
            Toast.makeText(this, "재생 중", Toast.LENGTH_SHORT).show()        }

        stopBtn.setOnClickListener{
                soundPool.stop(soundId)
                Toast.makeText(this, "재생 멈춤", Toast.LENGTH_SHORT).show()
        }
    }
}