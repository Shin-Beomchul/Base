package com.godbeom.baseapp.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

/** 스레드 공급자.
 *
 * @since 2019-11-01
 * @author beom-Chul
 */
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(f : () -> Unit) = IO_EXECUTOR.execute(f)
fun mainThread(f : () -> Unit) {
  Handler(Looper.getMainLooper()).post(f)
}