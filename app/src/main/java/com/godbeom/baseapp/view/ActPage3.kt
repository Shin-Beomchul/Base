package com.godbeom.baseapp.view

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.godbeom.baseapp.R
import com.godbeom.baseapp.adapter.MatchRxPagingAdapter
import com.godbeom.baseapp.databinding.ActivityActPage3Binding
import com.godbeom.baseapp.repository.MatchRepositoryRxRemoteImpl
import com.godbeom.baseapp.viewmodel.MatchViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_act_page3.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ActPage3 : AppCompatActivity() {

    private val mDisposable = CompositeDisposable()
    private lateinit var matchViewModel:MatchViewModel
    private lateinit var mAdapter: MatchRxPagingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_page3)

        matchViewModel = getViewModel()

        mAdapter = MatchRxPagingAdapter()
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)

        mAdapter.addLoadStateListener { loadState ->
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

//            errorState?.let {
//                AlertDialog.Builder(applicationContext)
//                    .setTitle("error")
//                    .setMessage(it.error.localizedMessage)
//                    .setNegativeButton("취소") { dialog, _ ->
//                        dialog.dismiss()
//                    }
//                    .setPositiveButton("재시도") { _, _ ->
//                        mAdapter.retry()
//                    }
//                    .show()
//            }
        }

        mDisposable.add(matchViewModel.getMatchsPageData("sbc0830").subscribe {
            mAdapter.submitData(lifecycle, it) // submitData후. DataSource를 호출함. Adapter 바인딩 할것.
        })
    }

    override fun finish() {
        super.finish()
        mDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}