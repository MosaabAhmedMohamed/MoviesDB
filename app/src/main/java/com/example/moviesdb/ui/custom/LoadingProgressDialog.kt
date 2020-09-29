package com.example.moviesdb.ui.custom

import android.animation.ObjectAnimator
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import com.example.moviesdb.R
import com.example.moviesdb.util.Animation
import kotlinx.android.synthetic.main.item_loading.*

class LoadingProgressDialog(context: Context) : ProgressDialog(context) {

    private var rotateLoading: ImageView? = null
    private var animator: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_loading)
        rotateLoading = rotate_loading
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator = rotateLoading?.let { Animation.setBouncingAnimation(it, 0, 250) }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator?.cancel()
    }

    companion object{
        fun showLoadingDialog(context: Context?): ProgressDialog? {
            val progressDialog =
                LoadingProgressDialog(context!!)
            progressDialog.show()
            if (progressDialog.window != null) {
                progressDialog.window!!
                    .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                progressDialog.window!!.setLayout(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
            }
            progressDialog.isIndeterminate = true
            progressDialog.setCancelable(false)
            progressDialog.setCanceledOnTouchOutside(false)
            return progressDialog
        }

    }
}