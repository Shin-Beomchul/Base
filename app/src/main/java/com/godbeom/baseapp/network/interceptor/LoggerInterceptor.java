package com.godbeom.baseapp.network.interceptor;

import com.godbeom.baseapp.util.DLog;
import java.io.IOException;
import java.util.Objects;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class LoggerInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();

        long startMills = System.currentTimeMillis();
        DLog.i("[->][" + method + "] Request : " + request.url());

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            if( copy.body() != null ) {
                copy.body().writeTo(buffer);
                DLog.i("[->]["+method+"] RequestBody : " + buffer.readUtf8());
            }
        } catch (final IOException e) {
            // do nothing
        }

        Response response = chain.proceed(request);
        String body = Objects.requireNonNull(response.body()).string();

        long endMills = System.currentTimeMillis();
        DLog.i("[<-][" + method + "] Response : " + request.url() + " (Spend Time : " + (endMills-startMills) + ")\n" + response);

        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), body))
                .message(body)
                .build();
    }
}