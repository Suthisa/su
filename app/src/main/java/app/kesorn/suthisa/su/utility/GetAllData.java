package app.kesorn.suthisa.su.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by suthisa on 10/12/2017.
 */

public class GetAllData extends AsyncTask<String,Void,String>{//Alt+Enter create methode
    private Context context;

    public GetAllData(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();   //consquarup
            Request request = builder.url(params[0]).build();
            Response response = okHttpClient.newCall(request).execute();

            return response.body().string();



        } catch (Exception e) {
            e.printStackTrace();
            return null;//if dont return null
        }


    }
}//Main Class
