package com.muruga.proxistudy;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

public class ApiClient {
    private static final String BASE_URL = "https://proxi-mad.onrender.com/api";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();

    public static class ApiResponse {
        public String statusCode;
        public String body;
        public String error;

        public ApiResponse(String statusCode, String body, String error) {
            this.statusCode = statusCode;
            this.body = body;
            this.error = error;
        }
    }

    public static ApiResponse register(String name, String email, String password) {
        try {
            JSONObject json = new JSONObject();
            json.put("name", name);
            json.put("email", email);
            json.put("password", password);

            RequestBody body = RequestBody.create(json.toString(), JSON);
            Request request = new Request.Builder()
                    .url(BASE_URL + "/auth/register")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body() != null ? response.body().string() : "";
            return new ApiResponse(String.valueOf(response.code()), responseBody, null);
        } catch (Exception e) {
            return new ApiResponse(null, null, e.getMessage());
        }
    }

    public static ApiResponse login(String email, String password) {
        try {
            JSONObject json = new JSONObject();
            json.put("email", email);
            json.put("password", password);

            RequestBody body = RequestBody.create(json.toString(), JSON);
            Request request = new Request.Builder()
                    .url(BASE_URL + "/auth/login")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body() != null ? response.body().string() : "";
            return new ApiResponse(String.valueOf(response.code()), responseBody, null);
        } catch (Exception e) {
            return new ApiResponse(null, null, e.getMessage());
        }
    }
}
