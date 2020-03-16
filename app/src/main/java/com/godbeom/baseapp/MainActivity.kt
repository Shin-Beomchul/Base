package com.godbeom.baseapp

import android.content.Intent
import android.os.Bundle
import com.godbeom.baseapp.base.BaseActivity
import com.godbeom.baseapp.view.ActTest

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, ActTest::class.java)
        this.startActivity(intent)
    }
}
