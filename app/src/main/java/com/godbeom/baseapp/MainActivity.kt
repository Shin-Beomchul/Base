package com.godbeom.baseapp

import android.content.Intent
import android.os.Bundle
import com.godbeom.baseapp.base.BaseActivity
import com.godbeom.baseapp.view.ActTest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTest.setOnClickListener {
            val intent = Intent(this, ActTest::class.java)
            this.startActivity(intent)
        }

    }
}
