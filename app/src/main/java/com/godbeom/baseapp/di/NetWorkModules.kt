package com.godbeom.baseapp.di
import android.content.Context
import com.godbeom.baseapp.network.DenJobAPI
import com.godbeom.baseapp.network.EndpointAPI
import com.godbeom.baseapp.network.interceptor.LoggerInterceptor
import com.godbeom.baseapp.network.NullOnEmptyConverterFactory
import com.godbeom.baseapp.network.interceptor.CacheInterceptor
import com.godbeom.baseapp.network.interceptor.RestHeaderInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.threeten.bp.Instant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException


/**
 * @since 2019-12-06
 * @author Beom-chul
 */


val networkModule = module {
    single { RestHeaderInterceptor() }
    single { LoggerInterceptor() }
    single { CacheInterceptor(androidContext()) }
    single { NullOnEmptyConverterFactory() }


    single (named("basicOkHttp")){ provideOkHttpClient(get(),get()) }
    single (named("endpointAPI")){ provideEndpointAPI(get(named("endpoint"))) }
    single (named("endpoint")) { provideRetrofit("http://www.google.com", get(named("basicOkHttp")), get()) }


    single (named("denJobAPI")) { provideDenJobAPI(get(named("denJob")))}
    single (named("denJob")) { provideRetrofit("https://job.denall.com", get(named("basicOkHttp")), get()) }


    single (named("cacheOkHttp")) { provideCacheOkHttpClient(androidContext(), get(), get(),get()) }
    single (named("cacheEndpoint")) { provideRetrofit("http://www.google.com", get(named("cacheOkHttp")), get()) }
    single (named("cacheEndpointAPI")){ provideEndpointAPI(get(named("cacheEndpoint"))) }


}

/** httpClient 의존성 주입 */
fun provideOkHttpClient(restHeaderInterceptor: RestHeaderInterceptor, loggerInterceptor: LoggerInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder()
            .addInterceptor(loggerInterceptor)
            .addInterceptor(restHeaderInterceptor).build()
}

/** httpClient 의존성 주입 */
fun provideCacheOkHttpClient(context: Context, cacheInterceptor: CacheInterceptor, restHeaderInterceptor: RestHeaderInterceptor, loggerInterceptor: LoggerInterceptor): OkHttpClient {
    val cacheSize = (5 * 1024 * 1024).toLong()
    val cache = Cache(context.cacheDir, cacheSize)

    return OkHttpClient().newBuilder()
        .cache(cache)
        .addInterceptor(cacheInterceptor)
        .addInterceptor(loggerInterceptor)
        .addInterceptor(restHeaderInterceptor).build()
}

/**Retrofit 의존성 주입 */
fun provideRetrofit(baseURL:String, okHttpClient: OkHttpClient, nullOnEmptyConverterFactory:NullOnEmptyConverterFactory): Retrofit  {
    return Retrofit.Builder()
        .baseUrl(baseURL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(nullOnEmptyConverterFactory)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttpClient)
        .build()

}

/**Retrofit Interface 의존성 주입 */
fun provideEndpointAPI(retrofit: Retrofit): EndpointAPI = retrofit.create(EndpointAPI::class.java)
fun provideDenJobAPI(retrofit: Retrofit): DenJobAPI = retrofit.create(DenJobAPI::class.java)




















// PLAN B Gson
fun provideGson(): Gson {
    return GsonBuilder().registerTypeAdapter(Instant::class.java, InstantConverter().nullSafe()).create()
}

/** for Gson
 *   why : 1.서버측 시간 타입 : Instant
 *         2. android에서 java.time.Instant 사용 하려면 min SDK가 26이어야함.
 *         3. min sdk < 26 환경에서 Instant타입을 사용하려면 Backport Instant 컨버팅이 필요.
 * @since 2019-12-11
 * @author Beom-chul
 */
class InstantConverter : TypeAdapter<Instant>() {
    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Instant) {
        out.value(value.toString())
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Instant {
        return Instant.parse(`in`.nextString())
    }
}









