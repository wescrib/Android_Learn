package com.challenge.scrib.firstkotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView = findViewById<TextView>(R.id.textview)
        var addBtn = findViewById<Button>(R.id.addBtn)
        var resetBtn = findViewById<Button>(R.id.resetBtn)
        var n: Int = 0

        textView.textSize = 30.0F
        textView.text = n.toString()

        addBtn.setOnClickListener(View.OnClickListener {
            n += 1
            textView.text = "$n"
        })

        resetBtn.setOnClickListener(View.OnClickListener {
            n = 0
            textView.text = "$n"
        })
    }
}
