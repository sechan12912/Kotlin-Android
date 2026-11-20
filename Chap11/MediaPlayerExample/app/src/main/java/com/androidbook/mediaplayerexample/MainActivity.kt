package com.androidbook.mediaplayerexample

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer
    var pause:Boolean = false

    lateinit var playBtn : Button
    lateinit var pauseBtn : Button
    lateinit var stopBtn : Button
    lateinit var  txtSname : TextView

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
        txtSname.text = "sound1.mp3"

        playBtn = findViewById(R.id.playBtn)
        pauseBtn = findViewById(R.id.pauseBtn)
        stopBtn = findViewById(R.id.stopBtn)
        mediaPlayer = MediaPlayer.create(applicationContext,R.raw.sound1)

        playBtn.setOnClickListener{
            if(pause){
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.start()
                pause = false
                Toast.makeText(this, "재생중 ", Toast.LENGTH_SHORT).show()
            } else{
                if(!mediaPlayer.isPlaying)  mediaPlayer = MediaPlayer.create(applicationContext,
                                                            R.raw.sound1)
                mediaPlayer.start()
                Toast.makeText(this, "재생 중", Toast.LENGTH_SHORT).show()
            }

            mediaPlayer.setOnCompletionListener {
                Toast.makeText(this, "재생 완료", Toast.LENGTH_SHORT).show()
            }
        }

        pauseBtn.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                pause = true
                Toast.makeText(this, "재생 중지", Toast.LENGTH_SHORT).show()
            }
        }

        stopBtn.setOnClickListener{
            if(mediaPlayer.isPlaying || pause.equals(true)){
                pause = false
                mediaPlayer.stop()
                Toast.makeText(this, "재생 멈춤", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
