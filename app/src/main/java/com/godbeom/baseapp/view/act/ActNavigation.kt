package com.godbeom.baseapp.view.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.godbeom.baseapp.R

/*base : basic
* 변경 : 딥링크 진입 후 백버튼 시 홈화면 @See Match
*      <deepLink app:uri="www.example.com/user/{userName}" />
        <deepLink app:uri="www.example.com/match" />
*
        * todo auth with navigation
        *


* */
class ActNavigation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_navigation)

    }
}