package com.agarwal.justclean

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JustClean : Application() {
  override fun onCreate() {
    super.onCreate()
  }
}