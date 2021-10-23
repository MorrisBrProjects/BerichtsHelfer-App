package com.example.berichtshelfer.loader;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.androidnetworking.common.ResponseType;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.berichtshelfer.loader.objects.Bericht;
import com.example.berichtshelfer.loader.objects.BerichtService;
import com.example.berichtshelfer.loader.objects.DayBericht;
import com.example.berichtshelfer.loader.objects.JsonConverter;
import com.example.berichtshelfer.loader.objects.Task;
import com.example.berichtshelfer.loader.objects.Veriables;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;

public class Networking {


    public void createBericht(Bericht bericht) {
        System.out.println();

        AndroidNetworking.get("http://37.114.47.77:82/createBericht")
                .addQueryParameter("name", bericht.getTitle())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {

                    @Override
                    public void onResponse(String response) {

                    }

                    @Override
                    public void onError(ANError anError) {
                        System.out.println(anError.getErrorDetail());
                    }
                });


       // ANRequest response = AndroidNetworking.post("http://37.114.47.77:82/getBericht")
          //      .setExecutor(Executors.newSingleThreadExecutor())
            //    .getResponseOnlyFromNetwork()
            //    .addBodyParameter("json", JsonConverter.objectToJsonString(bericht))
           //     .build();





    }

    public void createBericht(String json) {
       // System.out.println();

        //AndroidNetworking.get("http://37.114.47.77:82/createBericht")
             //   .addQueryParameter("name", berichtName)
             //   .setTag("test")
             //   .setPriority(Priority.MEDIUM)
             //   .build()
            //    .getAsString(new StringRequestListener() {

                //    @Override
                //    public void onResponse(String response) {

                 //   }

                 //   @Override
                 //   public void onError(ANError anError) {

                 //   }
                //});


        Bericht bericht = (Bericht) JsonConverter.jsonStringToObject(json, Bericht.class);

        String url = "http://37.114.47.77:82/createBericht";
        OkHttpClient client = new OkHttpClient();
        MediaType MIMEType= MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create (MIMEType,JsonConverter.objectToJsonString(bericht));
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //AndroidNetworking.post("http://37.114.47.77:82/createBericht")
//
      //      .addStringBody(new Gson().toJson(bericht)) // posting java object
     //            .setTag("test")
      //           .setPriority(Priority.MEDIUM)
     //            .build()
        //        .getAsJSONArray(new JSONArrayRequestListener() {
            // @Override
        // public void onResponse(JSONArray response) {
        //      // do anything with response
        //  }
        // @Override
        // public void onError(ANError error) {
            // handle error
        //  }
        // });




        // ANRequest response = AndroidNetworking.post("http://37.114.47.77:82/getBericht")
        //      .setExecutor(Executors.newSingleThreadExecutor())
        //    .getResponseOnlyFromNetwork()
        //    .addBodyParameter("json", JsonConverter.objectToJsonString(bericht))
        //     .build();

    }

    public String getBericht(String name) {

        String bericht = null;

        ANRequest request = AndroidNetworking.get("http://37.114.47.77:82/getBericht")
                .addQueryParameter("name", name)
                .setExecutor(Executors.newSingleThreadExecutor())
                .getResponseOnlyFromNetwork()
                .build();

        ANResponse responce = request.executeForString();

        if (responce.isSuccess()) {
            String json = (String) responce.getResult();
            System.out.println("----------------------------|" + json);
            return json;
        } else {
            System.out.println("Error!" + responce.getError());
        }

        return "Not found!";
    }


    public void removeBericht(String name) {
        AndroidNetworking.get("http://37.114.47.77:82/removeBericht")
                .addQueryParameter("name", name)
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .prefetch();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<ArrayList<Bericht>> getBerichteAsync() {
        CompletableFuture<ArrayList<Bericht>> completableFuture = new CompletableFuture<>();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Bericht>>() {
        }.getType();
        AtomicReference<ArrayList<Bericht>> atomicReference = new AtomicReference<>();
        ArrayList<Bericht> berichts = new ArrayList<>();
        @SuppressLint({"NewApi", "LocalSuppress"}) final CompletableFuture<ArrayList<Bericht>> result = new CompletableFuture<>();


        AndroidNetworking.get("http://37.114.47.77:82/getAllBerichte")
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {

                    @Override
                    public void onResponse(String responce) {
                        System.out.println("------------------------------------");
                        System.out.println(responce);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            atomicReference.set(gson.fromJson(responce, type));
                            completableFuture.complete(gson.fromJson(responce, type));
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        System.out.println("------------ERROR------------");
                        System.out.println(anError.getErrorDetail());
                        System.out.println("------------ERROR------------");
                    }
                });


        return completableFuture;
    }

}






