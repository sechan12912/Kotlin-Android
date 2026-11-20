package com.androidbook.videoviewproject

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()
    private var pause:Boolean = false
    lateinit var playImgBtn : ImageButton
    lateinit var pauseImgBtn : ImageButton
    lateinit var stopImgBtn : ImageButton
    lateinit var seek_bar : SeekBar
    lateinit var  tv_pass : TextView
    lateinit var  tv_due : TextView
    lateinit var  txtSname : TextView
    lateinit var videoView  : VideoView
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
        txtSname.setText("cat.mp4")
        playImgBtn = findViewById(R.id.imageButton_play)

        pauseImgBtn = findViewById(R.id.imageButton_pause)
        stopImgBtn = findViewById(R.id.imageButton_stop)
        seek_bar = findViewById(R.id.seek_bar)

        tv_pass = findViewById(R.id.tv_pass)
        tv_due  = findViewById(R.id.tv_due)
        videoView   = findViewById(R.id.videoView)

        val uri: Uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cat)
        videoView.setVideoURI(uri)

        playImgBtn.setOnClickListener {

            if(pause){
                videoView.seekTo(videoView.currentPosition)
                videoView.start()
                pause = false
                Toast.makeText(this, "재생중 ", Toast.LENGTH_SHORT).show()
            }else{

                videoView.start()
                Toast.makeText(this, "재생중", Toast.LENGTH_SHORT).show()

            }

            initializeSeekBar()

            initializeButton(false, true, true)

            videoView.setOnCompletionListener {

                initializeButton(true, false, false)
                Toast.makeText(this, "재생 완료", Toast.LENGTH_SHORT).show()
            }

        }
        pauseImgBtn.setOnClickListener {
            if(videoView.isPlaying) {
                videoView.pause()
                pause = true
                initializeButton(true, false, true)
                Toast.makeText(this, "재생 중지", Toast.LENGTH_SHORT).show()
            }

        }
        stopImgBtn.setOnClickListener {
            if(videoView.isPlaying || pause.equals(true)) {
                pause = false
                seek_bar.setProgress(0)

                videoView.stopPlayback()
                videoView.resume()
                handler.removeCallbacks(runnable)

                initializeButton(true, false, false)
                tv_pass.text = "0 sec"
                tv_due.text = "0 sec"
                Toast.makeText(this, "재생 멈춤", Toast.LENGTH_SHORT).show()
            }
        }
        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
                    videoView.seekTo(i * 1000)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

    }

    private fun initializeButton(isPlay:Boolean, isPause:Boolean, isStop:Boolean) {
        playImgBtn.isEnabled = isPlay
        pauseImgBtn.isEnabled = isPause
        stopImgBtn.isEnabled = isStop
        if( isPlay) playImgBtn.setImageResource(R.drawable.play)
        else playImgBtn.setImageResource(R.drawable.play2)
        if( isPause) pauseImgBtn.setImageResource(R.drawable.pause)
        else pauseImgBtn.setImageResource(R.drawable.pause2)
        if( isStop) stopImgBtn.setImageResource(R.drawable.stop)
        else stopImgBtn.setImageResource(R.drawable.stop2)
    }

    private fun initializeSeekBar() {
        seek_bar.max = videoView.seconds

        runnable = Runnable {
            seek_bar.progress = videoView.currentSeconds

            tv_pass.text = "${videoView.currentSeconds} sec"
            val diff = videoView.seconds - videoView.currentSeconds
            tv_due.text = "$diff sec"

            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }
}
val VideoView.seconds:Int
    get() {
        return this.duration / 1000
    }

val VideoView.currentSeconds:Int
    get() {
        return this.currentPosition/1000
    }