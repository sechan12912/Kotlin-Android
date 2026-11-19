package com.androidbook.buttoneventexample04

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn = findViewById(R.id.button_id)
        var listener = MyListener();
        btn.setOnClickListener(listener)

    }
}
class MyListener : View.OnClickListener {
    override fun onClick(p0: View?) {
        if (p0?.id == R.id.button_id) {
            var btn :Button= p0.findViewById(R.id.button_id)
            btn.setText("버튼 클릭 이벤트")
        }
    }
}