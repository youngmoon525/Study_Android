package com.example.project2_clone;

//DB에 안드로이드는 바로 연결하는 방법이 없다.
//인터넷 사용권한을 사용하고 인터넷을 통해서 => Middle => DB => Middle => Android
//현재)App 무조건 Server랑 연동을 해서 변경된 데이터가 있으면 저장이 되고 유지가 되는형태.


import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.android.internal.http.multipart.MultipartEntity;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.InputStream;

import okhttp3.MultipartBody;


public class ConnDB extends AsyncTask<String , String , String> {
    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse res;
    HttpEntity entity;
    MultipartEntityBuilder builder;
    final static String HTTPIP = "http://192.168.0.60";
    final static String SPRPATH = "/mid/";
    private String postURL ;

    @Override
    protected String doInBackground(String... strings) {
        InputStream inputStream;
        postURL = HTTPIP + SPRPATH + "aa.and" ;
        // MultipartEntityBuild 생성
        builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        try {

            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            inputStream = httpClient.execute(httpPost).getEntity().getContent();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entity != null) entity = null;
            if (res != null)  res = null;
            if (httpPost != null) httpPost = null;
            if (httpClient != null) httpClient = null;
        }

        return null;
    }
}
