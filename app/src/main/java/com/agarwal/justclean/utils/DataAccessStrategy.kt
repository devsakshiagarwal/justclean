package com.agarwal.justclean.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.agarwal.justclean.utils.Response.Status.ERROR
import com.agarwal.justclean.utils.Response.Status.SUCCESS
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
  networkCall: suspend () -> Response<A>,
  saveCallResult: suspend (A) -> Unit): LiveData<Response<T>> =
  liveData(Dispatchers.IO) {
    emit(Response.loading())
    val source = databaseQuery.invoke()
      .map { Response.success(it) }
    emitSource(source)
    val responseStatus = networkCall.invoke()
    if (responseStatus.status == SUCCESS) {
      saveCallResult(responseStatus.data!!)
    } else if (responseStatus.status == ERROR) {
      emit(Response.error(responseStatus.message!!))
      emitSource(source)
    }
  }