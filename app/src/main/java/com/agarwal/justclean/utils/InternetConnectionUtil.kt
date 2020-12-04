package com.agarwal.justclean.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity

object InternetConnectionUtil {
  fun isConnectedToInternet(activity: AppCompatActivity): Boolean {
    val connectivityManager = activity.getSystemService(
      Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
  }

  //method for showing a simple testcase
  fun giveSum(a: Int,
    b: Int) = a + b
}