package com.agarwal.justclean.model.work_manager

import android.content.Context
import androidx.work.*
import androidx.work.ExistingWorkPolicy.REPLACE

object WorkManagerScheduler {
  fun updateWorkOnInternetConnected(context: Context) {
    val myConstraints = Constraints.Builder()
      .setRequiredNetworkType(NetworkType.CONNECTED)
      .build()
    val refreshCpnWork = OneTimeWorkRequest.Builder(PostWorker::class.java)
      .setConstraints(myConstraints)
      .addTag("postWorkManager")
      .build()

    WorkManager.getInstance(context)
      .enqueueUniqueWork("postWorkManager", REPLACE, refreshCpnWork)
  }
}