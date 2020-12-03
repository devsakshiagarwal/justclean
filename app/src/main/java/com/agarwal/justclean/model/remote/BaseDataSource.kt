package com.agarwal.justclean.model.remote

import android.util.Log
import com.agarwal.justclean.utils.Response

abstract class BaseDataSource {
  protected suspend fun <T> getResult(call: suspend () -> retrofit2.Response<T>): Response<T> {
    try {
      val response = call()
      if (response.isSuccessful) {
        val body = response.body()
        if (body != null) return Response.success(body)
      }
      return error(" ${response.code()} ${response.message()}")
    } catch (e: Exception) {
      return error(e.message ?: e.toString())
    }
  }

  private fun <T> error(message: String): Response<T> {
    Log.d("nw_error", message)
    return Response.error(
      "Network call has failed for a following reason: $message")
  }
}