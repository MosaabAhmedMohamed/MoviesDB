package com.example.moviesdb.util

import android.app.DownloadManager
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import java.io.File

object DownloadManagerUtil {

    fun getDirectory(): File {
        val directory =  File(Environment.DIRECTORY_PICTURES)
        if (!directory.exists()) {
            directory.mkdirs()
        }
        return directory
    }

    fun getRequest(url: String, directory: String): DownloadManager.Request {
        val downloadUri = Uri.parse(url)
        return DownloadManager.Request(downloadUri).apply {
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(url.substring(url.lastIndexOf("/") + 1))
                .setDescription("")
                .setDestinationInExternalPublicDir(
                    directory,
                    url.substring(url.lastIndexOf("/") + 1)
                )
        }
    }

    fun startDownload(
        downloadManager: DownloadManager,
        request: DownloadManager.Request,
        loadStatus: (result: String?) -> Unit
    ) {
        var msg: String? = ""
        var lastMsg = ""

        val downloadId = downloadManager.enqueue(request)
        val query = DownloadManager.Query().setFilterById(downloadId)

        var downloading = true
        while (downloading) {
            val cursor: Cursor = downloadManager.query(query)
            cursor.moveToFirst()
            if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                downloading = false
            }
            val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
            msg = statusMessage( status)
            if (msg != lastMsg) {
                msg?.let {
                    loadStatus.invoke(msg)
                }
                lastMsg = msg ?: ""
            }
            cursor.close()
        }
    }


    fun statusMessage(status: Int): String? {
        var msg = ""
        msg = when (status) {
            DownloadManager.STATUS_FAILED -> "Download has been failed, please try again"
            DownloadManager.STATUS_PAUSED -> "Paused"
            DownloadManager.STATUS_PENDING -> "Pending"
            DownloadManager.STATUS_RUNNING -> "Downloading..."
            DownloadManager.STATUS_SUCCESSFUL -> "Image downloaded successfully"
            else -> "There's nothing to download"
        }
        return msg
    }

}