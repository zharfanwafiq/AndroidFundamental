package com.zharfan.androidfundamental.service

import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import com.zharfan.androidfundamental.activity.BroadcastReceiverActivity

class DownloadService : JobIntentService() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            enqueueWork(this, this::class.java, BroadcastReceiverActivity.SMS_REQUEST_CODE, intent)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleWork(intent: Intent) {
        Log.d(TAG, "Download Service di jalankan")
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        val notifyFinishIntent = Intent(BroadcastReceiverActivity.ACTION_DOWNLOAD_STATUS)
        sendBroadcast(notifyFinishIntent)
    }

    companion object {
        val TAG: String = DownloadService::class.java.simpleName
    }
}