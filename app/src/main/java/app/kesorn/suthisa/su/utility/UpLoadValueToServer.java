package app.kesorn.suthisa.su.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by suthisa on 10/12/2017.
 */

public class UpLoadValueToServer extends AsyncTask<String,Void,String>{
    private Context context;

    public UpLoadValueToServer(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {//รอโยนข้อมูลเมื่อต่อ Net
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd","true")
                    .add("Name",params[0])
                    .add("User",params[1])
                    .add("NamePassword",params[2])
                    .add("Image",params[3])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(params[4]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();



        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }
}//Main Class
