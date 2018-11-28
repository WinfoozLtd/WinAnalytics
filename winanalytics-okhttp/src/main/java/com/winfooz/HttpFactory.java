package com.winfooz;

import android.support.annotation.NonNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class HttpFactory extends CallAdapter.Factory {

    private final String baseUrl;

    public HttpFactory(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public CallAdapter<?, ?> get(
            @NonNull Type returnType,
            @NonNull Annotation[] annotations,
            @NonNull Retrofit retrofit) {
        if (getRawType(returnType) != Call.class) {
            return null;
        }
        if (!(returnType instanceof ParameterizedType)) {
            String msg = "Call must have generic type (e.g., Call<ResponseBody>)";
            throw new IllegalStateException(msg);
        }
        Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);
        return new HttpCallAdapter<>(responseType, baseUrl);
    }
}
