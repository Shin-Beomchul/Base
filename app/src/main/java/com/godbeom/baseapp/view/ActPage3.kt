package com.godbeom.baseapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.godbeom.baseapp.R
import com.godbeom.baseapp.viewmodel.MatchViewModel
import kotlinx.android.synthetic.main.activity_act_page3.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ActPage3 : AppCompatActivity() {
    private lateinit var matchViewModel:MatchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_page3)
        matchViewModel = getViewModel()
        matchViewModel.getMatchsPageData("sbc0830").subscribe {
            it
            //   mAdapter.submitData(lifecycle, it)  submitData후. DataSource를 호출함. Adapter 바인딩 할것.
        }

        recyclerView.adapter = null
    }
}