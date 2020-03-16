package com.godbeom.baseapp.network;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 *  for REST no Content(call<void>) Exception
 * @since 2019-12-11
 * @author Beom-chul
 */
public class NullOnEmptyConverterFactory extends Converter.Factory {

  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
    final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);

    return new Converter<ResponseBody, Object>() {
      @Override
      public Object convert(ResponseBody body) throws IOException {
        if (body.contentLength() == 0) return null;
        return delegate.convert(body);
      }

    };
  }
}