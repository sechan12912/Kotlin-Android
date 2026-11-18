package com.androidbook.framelayoutproject

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var ivObjBody:ImageView
    lateinit var ivObjDress:ImageView
    lateinit var ivObjDress1: ImageView
    lateinit var ivObjDress2:ImageView
    lateinit var ivObjDress3:ImageView

    lateinit var ivObjCrown:ImageView
    lateinit var ivObjCrown1: ImageView
    lateinit var ivObjCrown2:ImageView

    lateinit var ivObjNecklace:ImageView
    lateinit var ivObjNeck1: ImageView
    lateinit var ivObjNeck2: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    ivObjBody = findViewById(R.id.imageViewBody)
    ivObjCrown = findViewById(R.id.imageViewCrown)
    ivObjNecklace = findViewById(R.id.imageViewNecklace)
    ivObjDress = findViewById(R.id.imageViewDress)

    ivObjCrown1 = findViewById(R.id.imageViewCrown1)
    ivObjCrown2 = findViewById(R.id.imageViewCrown2)

    ivObjDress1 = findViewById(R.id.imageViewDress1)
    ivObjDress2 = findViewById(R.id.imageViewDress3)
    ivObjDress3 = findViewById(R.id.imageViewDress3)

    ivObjNeck1 = findViewById(R.id.imageViewNeck1);
    ivObjNeck2 = findViewById(R.id.imageViewNeck2);

    ivObjCrown.setVisibility(View.INVISIBLE)
    ivObjNecklace.setVisibility(View.INVISIBLE)
    ivObjDress.setVisibility(View.INVISIBLE)

    }

    fun onClickChoice(view: View) {
        when (view.getId()) {

            R.id.imageViewDress1 -> {
                ivObjDress.setVisibility(View.VISIBLE)
                ivObjDress.setImageResource(R.drawable.dress1)
            }

            R.id.imageViewDress2 -> {
                ivObjDress.setVisibility(View.VISIBLE)
                ivObjDress.setImageResource(R.drawable.dress2)
            }

            R.id.imageViewDress3 -> {
                ivObjDress.setVisibility(View.VISIBLE)
                ivObjDress.setImageResource(R.drawable.dress3)
            }

            R.id.imageViewCrown1 -> {
                ivObjCrown.setVisibility(View.VISIBLE)
                ivObjCrown.setImageResource(R.drawable.crown1)
            }
            R.id.imageViewCrown2 -> {
                ivObjCrown.setVisibility(View.VISIBLE)
                ivObjCrown.setImageResource(R.drawable.crown2)
            }
            R.id.imageViewNeck1 -> {
                ivObjNecklace.setVisibility(View.VISIBLE)
                ivObjNecklace.setImageResource(R.drawable.necklace1)
            }
            R.id.imageViewNeck2 -> {
                ivObjNecklace.setVisibility(View.VISIBLE)
                ivObjNecklace.setImageResource(R.drawable.necklace2)
            }

        }
    }

    fun onClickReset(view: View?) {

        ivObjCrown.setVisibility(View.INVISIBLE)
        ivObjNecklace.setVisibility(View.INVISIBLE)
        ivObjDress.setVisibility(View.INVISIBLE)

    }

}