package com.androidbook.audioproject

import android.Manifest
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class CardActivity : AppCompatActivity() {

    lateinit var mediaRecorder: MediaRecorder
    lateinit var audiofile: String

    lateinit var runnable:Runnable
    var handler: Handler = Handler()
    var pause:Boolean = false
    var isRecording = false

    lateinit var recordImgBtn : ImageButton
    lateinit var playImgBtn : ImageButton
    lateinit var pauseImgBtn : ImageButton
    lateinit var stopImgBtn : ImageButton
    lateinit var mediaPlayer: MediaPlayer
    lateinit var seekBar : SeekBar
    lateinit var  tvPass : TextView
    lateinit var  tvDue : TextView
    lateinit var  tvTitle : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_card)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.card)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvTitle = findViewById(R.id.textView)
        recordImgBtn = findViewById(R.id.imageButton_record)
        playImgBtn = findViewById(R.id.imageButton_play)
        pauseImgBtn = findViewById(R.id.imageButton_pause)
        stopImgBtn = findViewById(R.id.imageButton_stop)

        initializeButton(false, false, false)

        seekBar = findViewById(R.id.seek_bar)
        tvPass = findViewById(R.id.tv_pass)
        tvDue  = findViewById(R.id.tv_due)

        ActivityCompat.requestPermissions(
             this,
             arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE),
             MODE_PRIVATE
        )

        audiofile =  Environment.getExternalStorageDirectory().absolutePath + "/Download/letter.mp3"

        recordImgBtn.setOnTouchListener { v, event ->

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        isRecording = true
                        mediaRecorder = MediaRecorder(applicationContext)
                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                        mediaRecorder.setOutputFile(audiofile)
                        mediaRecorder.prepare()
                        mediaRecorder.start()

                        initializeButton(false, false, false)
                        tvTitle.text = "전하는 메시지를 녹음하세요"
                    }
                }

                MotionEvent.ACTION_UP -> {
                    isRecording = false

                    try {
                        mediaRecorder.stop()
                        mediaRecorder.release()

                        tvTitle.text = "전하는 메시지를 들어보세요"
                        initializeButton(true, false, false)

                    } catch (e: RuntimeException) {
                        Toast.makeText(this, "REC 버튼을 길게 누르면서 녹음하세요", Toast.LENGTH_SHORT).show()
                    }

                }
            }
            true
        }

        playImgBtn.setOnClickListener{
            if(pause){
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.start()
                pause = false
            }else{

                mediaPlayer = MediaPlayer.create(applicationContext,   Uri.parse(audiofile))
                mediaPlayer.start()
            }
            initializeSeekBar()
            initializeButton(false, true, true)

            mediaPlayer.setOnCompletionListener {
                initializeButton(true, false, false)
            }
        }

        pauseImgBtn.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                pause = true

                initializeButton(true, false, true)
            }
        }

        stopImgBtn.setOnClickListener{
            if(mediaPlayer.isPlaying || pause.equals(true)){
                pause = false
                seekBar.setProgress(0)
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
                handler.removeCallbacks(runnable)

                initializeButton(true, false, false)

                tvPass.text = ""
                tvDue.text = ""
                Toast.makeText(this, "재생 멈춤", Toast.LENGTH_SHORT).show()
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                if (b) {
                    mediaPlayer.seekTo(i * 1000)
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
        seekBar.max = mediaPlayer.seconds

        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentSeconds

            tvPass.text = "${mediaPlayer.currentSeconds} sec"
            val diff = mediaPlayer.seconds - mediaPlayer.currentSeconds
            tvDue.text = "$diff sec"

            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }
}

val MediaPlayer.seconds:Int
    get() {
        return this.duration / 1000
    }

val MediaPlayer.currentSeconds:Int
    get() {
        return this.currentPosition/1000
    }
