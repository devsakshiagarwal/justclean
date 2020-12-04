package com.agarwal.justclean.model.work_manager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.agarwal.justclean.model.repository.PostRepo
import javax.inject.Inject

class PostWorker @Inject constructor(appContext: Context,
  workerParams: WorkerParameters,
  private val postRepo: PostRepo) : CoroutineWorker(appContext, workerParams) {
  override suspend fun doWork(): Result {
    return try {
      try {
        updatePostRepo()
        Result.success()
      } catch (e: Exception) {
        Result.failure()
      }
    } catch (e: Exception) {
      Log.d("PostWorker", "exception in doWork ${e.message}")
      Result.failure()
    }
  }

  private fun updatePostRepo() {
    postRepo.updateLocalData()
  }
}