package com.example.moviesdb.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

object ReadWritePermission {
    const val REQUEST_WRITE_EXTERNAL_STORAGE = 1

    fun isPermissionGranted(activity: Activity): Boolean {
        val writePermission = ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return writePermission == PackageManager.PERMISSION_GRANTED
    }

    fun Fragment.askForPermission(permission:String) {
        requestPermissions(
            arrayOf(permission),
            ReadWritePermission.REQUEST_WRITE_EXTERNAL_STORAGE
        )
    }
}