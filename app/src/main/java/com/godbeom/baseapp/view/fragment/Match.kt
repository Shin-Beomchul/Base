/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.godbeom.baseapp.view.fragment

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback

import androidx.annotation.UiThread
import androidx.navigation.Navigation
import com.godbeom.baseapp.R

/**
 * Shows a warning-up screen.
 */
class Match : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_match, container, false)

        view.findViewById<View>(R.id.play_btn3).setOnClickListener {
             Navigation.findNavController(view).navigate(R.id.action_match_to_in_game)
        }
        // 딥링크 접근 후 백키로 홈 startDestnation으로 이동 하고자 할 경우.
        // * 딥링크 접속 후 즉시 종료 하고자 할 경우 주석처리.
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object: OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    Toast.makeText(activity,"back",Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view).navigateUp()
                }

            }
        )

        return view
    }

    fun handleDeepLink( view:View){
        val name = arguments?.getString("screen") ?: "non"
        when(name){
            "god" -> Navigation.findNavController(view).navigate(R.id.action_title_screen_to_leaderboard)
        }

    }





}
