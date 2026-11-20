package com.androidbook.bookmarket.ebook

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.androidbook.bookmarket.R

class EbookVideoActivity : AppCompatActivity() {

    lateinit var videoView : VideoView
    lateinit var btFullscreen : ImageButton
    var isFull = false
    lateinit var layout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ebook_video)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ebookvideo)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val intent = getIntent()
        val resName = intent.getIntExtra("filename", 0)

        btFullscreen = findViewById(R.id.bt_fullscreen)
        layout = findViewById(R.id.videoview_frame)
        videoView = findViewById(R.id.videoView)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        val uri: Uri  = Uri.parse("android.resource://" + getPackageName() + "/" + resName)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

        btFullscreen.setOnClickListener {
            setFullScreen(!isFull)
            if(isFull)
                btFullscreen.setBackgroundResource(R.drawable.icon_ebook_resize02)
            else
                btFullscreen.setBackgroundResource(R.drawable.icon_ebook_resize01 )

        }
    }

    private fun setFullScreen(full: Boolean) {

        isFull = full;
        val params: ViewGroup.LayoutParams = layout.getLayoutParams();

        if (isFull) {
            isFull = true;
            supportActionBar?.hide()
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        } else {
            isFull = false;
            supportActionBar?.show()
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            val metrics: DisplayMetrics = DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            val height: Int = (metrics.density * 250).toInt();
            params.height = height;

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home->{
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
