package com.example.inshortsclone.util

import android.view.View

open class OnClickListener(
    private var position: Int,
    onClickCallback: OnClickCallback,
    var type: String = ""
) : View.OnClickListener {

    private var onClickCallback : OnClickCallback? = onClickCallback

    override fun onClick(p0: View) {
        onClickCallback?.onClicked(p0 , position , type)
    }

    interface OnClickCallback {
        fun onClicked(view: View, position: Int, type: String)
    }
}