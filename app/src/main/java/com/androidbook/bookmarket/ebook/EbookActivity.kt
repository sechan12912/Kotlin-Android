package com.androidbook.bookmarket.ebook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.androidbook.bookmarket.R

class EbookActivity : AppCompatActivity() {

    lateinit var videoObj01 : ImageView
    lateinit var videoObj02 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ebook)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ebook)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        videoObj01 = findViewById(R.id.iv_video01)
        videoObj02 = findViewById(R.id.iv_video02)

        videoObj01.setOnClickListener {

            val intent = Intent(this, EbookVideoActivity::class.java)
            intent.putExtra("filename",  R.raw.video1)
            startActivity(intent)
        }

        videoObj02.setOnClickListener {
            val intent = Intent(this, EbookVideoActivity::class.java)
            intent.putExtra("filename",  R.raw.video2)
            startActivity(intent)
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