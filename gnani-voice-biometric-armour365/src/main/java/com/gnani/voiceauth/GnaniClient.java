package com.gnani.voiceauth;

import android.util.Log;

import com.gnani.voiceauth.interfaces.VoiceResultListener;
import com.gnani.voiceauth.rest.APIService;
import com.gnani.voiceauth.rest.ApiUtils;
import com.gnani.voiceauth.rest.Response;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class GnaniClient {

    private static final String TAG = "GnaniClient";
    private String TOKEN = "";
    private String ACCESSKEY = "";
    private String PRODUCT = "";

    private APIService mAPIService;
    VoiceResultListener voiceResultListener;

    public GnaniClient(String token, String accesskey, String product, VoiceResultListener voiceResultListener) {

        TOKEN = token;
        ACCESSKEY = accesskey;
        PRODUCT = product;

        mAPIService = ApiUtils.getAPIService();

        this.voiceResultListener = voiceResultListener;

    }

    public void authenticateUser(String speakerName, File file) {
        if (!speakerName.equals("")) {
            if (file.exists()) {

                RequestBody requestFile = RequestBody.create(MediaType.parse("audio/*"), file);

                MultipartBody.Part fileData = MultipartBody.Part.createFormData("audio_file", file.getName(), requestFile);

                RequestBody speaker = RequestBody.create(MediaType.parse("text/plain"), speakerName);

                Call<Response> call = mAPIService.authenticate(ACCESSKEY, TOKEN, PRODUCT, fileData, speaker);

                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        try {
                            Log.d(TAG, "onResponse body: " + response.body());
                            Log.d(TAG, "onResponse code: " + response.code());
                            if (response.body().getStatus_code() == 0 && response.body().getDecision_code() == 0) {
                                voiceResultListener.success(response.body().getMessage());
                            } else if (response.body().getStatus_code() == 0 && response.body().getDecision_code() != 0) {
                                voiceResultListener.failed(response.body().getMessage());
                            } else {
                                voiceResultListener.failed(response.body().getMessage());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            voiceResultListener.failed("Something went wrong\n\nError is: " + e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Log.e("Upload error:", t.getMessage());
                        voiceResultListener.failed("Something went wrong\n\nError is: " + t.getMessage());

                    }
                });

            } else {
                voiceResultListener.failed("File not found");
            }
        }
        else {
            voiceResultListener.failed("Speaker empty");
        }
    }

    public void enrollUser(String speakerName, File file) {
        if (!speakerName.equals("")) {
            if (file.exists()) {

                RequestBody requestFile = RequestBody.create(MediaType.parse("audio/*"), file);

                MultipartBody.Part fileData = MultipartBody.Part.createFormData("audio_file", file.getName(), requestFile);

                RequestBody speaker = RequestBody.create(MediaType.parse("text/plain"), speakerName);

                Call<Response> call = mAPIService.enroll(ACCESSKEY, TOKEN, PRODUCT, fileData, speaker);

                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        try {

                            if(response.body().getStatus_code() == 0 && response.body().getDecision_code() == 0) {
                                voiceResultListener.success(response.body().getMessage());
                            }
                            else {
                                voiceResultListener.failed(response.body().getMessage());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            voiceResultListener.failed("Something went wrong\n\nError is: " + e.getMessage());

                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Log.e("Upload error:", t.getMessage());
                        voiceResultListener.failed("Something went wrong\n\nError is: " + t.getMessage());

                    }
                });

            } else {
                voiceResultListener.failed("File not found");

            }
        } else {
            voiceResultListener.failed("Speaker empty");
        }
    }

    public void disEnrollUser(String speakerName) {
        if (!speakerName.equals("")) {
            RequestBody speaker = RequestBody.create(MediaType.parse("text/plain"), speakerName);

            Call<Response> call = mAPIService.disenroll(ACCESSKEY, TOKEN, PRODUCT, speaker);

            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    try {
                        if (response.body().getStatus_code() == 0) {
                            voiceResultListener.success(response.body().getMessage());

                        } else {
                            voiceResultListener.failed(response.body().getMessage());

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        voiceResultListener.failed("Something went wrong\n\nError is: " + e.getMessage());

                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Log.e("Upload error:", t.getMessage());
                    voiceResultListener.failed("Something went wrong\n\nError is: " + t.getMessage());

                }
            });

        } else {
            voiceResultListener.failed("Speaker empty");
        }
    }

}
