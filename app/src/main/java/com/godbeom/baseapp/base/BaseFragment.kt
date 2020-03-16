package com.osstem.eos.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import com.godbeom.baseapp.base.OnBackPressed

open class BaseFragment : Fragment(), OnBackPressed {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    // true : action not popBackStack
    override fun onBackPressed(): Boolean? {
        return true
    }
}