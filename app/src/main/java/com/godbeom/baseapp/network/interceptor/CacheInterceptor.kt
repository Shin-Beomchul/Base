package com.godbeom.baseapp.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

// force SelfCache (Receive Header change)
// request : chain.request() App -> Server
// response : chain.proceed(request) Serve -> App
// 제크 왓슨 : https://github.com/square/retrofit/issues/693
// force Cache : https://github.com/square/okhttp/issues/1265
// https://medium.com/@I_Love_Coding/how-does-okhttp-cache-works-851d37dd29cd
class CacheInterceptor(var context: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {


        // Get the request from the chain.
        var request = chain.request()
        /*
        *  Leveraging the advantage of using Kotlin,
        *  we initialize the request and change its header depending on whether
        *  the device is connected to Internet or not.
        */
        request = if (isInternetAvailable(context))
        /*
        *  If there is Internet, get the cache that was stored 5 seconds ago.
        *  If the cache is older than 5 seconds, then discard it,
        *  and indicate an error in fetching the response.
        *  The 'max-age' attribute is responsible for this behavior.
        */
            request.newBuilder().header("Cache-Control", "public, max-age=$ACTIVITY_CACHE_TIME").build()
        else
        /*
        *  If there is no Internet, get the cache that was stored 7 days ago.
        *  If the cache is older than 7 days, then discard it,
        *  and indicate an error in fetching the response.
        *  The 'max-stale' attribute is responsible for this behavior.
        *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
        */
            request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=$DISCONNECT_CACHE_TIME").build()
        // End of if-else statement

        // Add the modified request to the chain.
        return chain.proceed(request)
    }


    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }


    companion object{
        var ACTIVITY_CACHE_TIME = 300 // 5min
        var DISCONNECT_CACHE_TIME = 60 * 60 * 24 * 7 // 7day
    }
}