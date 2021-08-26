package com.gnani.voiceauth.rest;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIService {

    @Multipart
    @POST("/enroll")
    Call<Response> enroll(@Header("accesskey") String accesskey, @Header("token") String token, @Header("product") String product, @Part MultipartBody.Part audio_file, @Part("speaker") RequestBody speaker);

    @Multipart
    @POST("/authenticate")
    Call<Response> authenticate(@Header("accesskey") String accesskey, @Header("token") String token, @Header("product") String product, @Part MultipartBody.Part file, @Part("speaker") RequestBody speaker);

    @Multipart
    @POST("/disenroll")
    Call<Response> disenroll(@Header("accesskey") String accesskey, @Header("token") String token, @Header("product") String product, @Part("speaker") RequestBody speaker);
}