package com.lotto1.a0128

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val img: ImageView = findViewById(R.id.up1_) //변수 선언
        val tv: TextView = findViewById(R.id.tv1_)
        val btn: Button = findViewById(R.id.btn1_)
        var n = (1..45) //배열?
        var number: Int = 0 //빈값으로 초기화하려면 Int? = null

            btn.setOnClickListener {
                number = n.random()

                when(number) { //C언어의 Switch와 유사
                    in 1..10 -> img.setImageResource(R.drawable.yellow_cc)
                    in 11..20 -> img.setImageResource(R.drawable.sky_cc)
                    in 21..30 -> img.setImageResource(R.drawable.red_cc)
                    in 31..40 -> img.setImageResource(R.drawable.gray_cc)
                    in 41..45 -> img.setImageResource(R.drawable.ygreen_cc)
                }
                
                tv.text = number.toString() //형변환
            }
    }
}

