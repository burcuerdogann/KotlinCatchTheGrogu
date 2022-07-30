package com.burcuerdogan.kotlincatchthegrogu

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable {  }
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        imageView.visibility = View.INVISIBLE
        imageView2.visibility = View.INVISIBLE
        imageView3.visibility = View.INVISIBLE
        imageView4.visibility = View.INVISIBLE
        imageView5.visibility = View.INVISIBLE
        imageView6.visibility = View.INVISIBLE
        imageView7.visibility = View.INVISIBLE
        imageView8.visibility = View.INVISIBLE
        imageView9.visibility = View.INVISIBLE
        */

        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)

        hideImages()

        // CountDownTimer
        object : CountDownTimer(16000,1000){
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "Time: ${millisUntilFinished/1000}"
            }
            override fun onFinish() {

                timeText.text = "Time: 0"

                handler.removeCallbacks(runnable)

                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                // Alert
                var alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes"){dialog, which ->
                    // Restart
                    val intent = intent
                    finish()
                    startActivity(intent)

                }
                alert.setNegativeButton("No"){dialog, which ->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                }
                alert.show()
            }
        }.start()
    }

    fun hideImages() {

        runnable = object : Runnable{
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,300)
            }
        }
        handler.post(runnable)

    }

    fun increaseScore(view: View){
        score++
        scoreText.text = "Score: $score"

    }
}
