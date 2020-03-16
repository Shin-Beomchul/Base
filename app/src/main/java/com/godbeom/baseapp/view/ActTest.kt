package com.godbeom.baseapp.view


import android.os.Bundle
import android.widget.Toast
import com.godbeom.baseapp.R
import com.godbeom.baseapp.base.BaseActivity
import com.godbeom.baseapp.dto.UserDTO
import com.godbeom.baseapp.persistence.room.AppDatabase
import com.godbeom.baseapp.util.ioThread
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_act_test.*
import org.koin.android.ext.android.inject
import kotlin.random.Random

class ActTest : BaseActivity() {
    private val appDatabase: AppDatabase by inject()
    private var compositeDisposable = CompositeDisposable()
    private val stringBuilder:StringBuilder = java.lang.StringBuilder()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_test)


        btnUserInsert.setOnClickListener {
            ioThread { val ran = Random.nextInt(0, 9999999)
                appDatabase.getUserDAO().insert(UserDTO("start+ $ran", "김시작 + $ran"))  }
        }

        btnGetUser.setOnClickListener {
            compositeDisposable.add(appDatabase.getUserDAO().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { users ->
                    stringBuilder.append("# ======userDTO====== #")
                    stringBuilder.append("name : ${users.get(0).userId}")
                    stringBuilder.append("\n")
                    stringBuilder.append("# ======userDTO====== # ")
                    stringBuilder.append("name : ${users.get(0).userName}")
                    stringBuilder.append("\n")
                    tvDebug.text = stringBuilder.toString()
                    Toast.makeText(this, "userSize : " + users.size, Toast.LENGTH_LONG).show()
                })
        }




    }
}
