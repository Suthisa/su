package app.kesorn.suthisa.su.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import org.jibble.simpleftp.SimpleFTP;

import java.io.File;
import java.nio.channels.AlreadyConnectedException;

import app.kesorn.suthisa.su.MainActivity;
import app.kesorn.suthisa.su.R;
import app.kesorn.suthisa.su.utility.MyAlert;
import app.kesorn.suthisa.su.utility.MyConstant;

/**
 * Created by suthisa on 10/10/2017.
 */

public class RegisterFragment extends Fragment  {
    //Explicit
    private String nameString, userString, passwordString;
    private Uri uri;
    private ImageView imageView;
    private boolean aBoolean = true;
    private String tag = "11cctV1";//today tag

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_register,container,false);
        return view;

    } //onCreate view

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Toolbar Controler
        toolbarControler();


//        HUmenController Controller
        HUmenController();

        }//Main Methode

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {

             //
             Log.d(tag, "Result OK");

            aBoolean = false;

            try {
                uri = data.getData();
                Bitmap bitmap = BitmapFactory
                        .decodeStream(getActivity().getContentResolver().openInputStream(uri));
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                Log.d(tag, "e ==>" + e.toString());
            }

        }//if

    }//OnActivityResult

    private void HUmenController() {
        imageView = (ImageView) getView().findViewById(R.id.imageview);
        imageView.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Please Choose App"), 1);


            }//OnClick

        });
    }


    private void toolbarControler() {
        //Connfing ToolBar
        Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        //Setup Title
        ((MainActivity)getActivity()).setTitle(getResources() .getString(R.string.new_regidter));

        //Back Controler
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        //Save Controller
        ImageView imageView = (ImageView) getView().findViewById((R.id.imvSave));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get value form
                EditText nameEditText = (EditText) getView().findViewById(R.id.edtName);
                EditText valueEditText = (EditText) getView().findViewById(R.id.edtUser);
                EditText passwordEditText = (EditText) getView().findViewById(R.id.edtPassword);
//                Change Data type
                nameString = nameEditText.getText().toString().trim();
                userString = valueEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //check space
                if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {
                    //Have Spece
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("HaveSpace", "Please Fail All Every Blank");

                } else if (aBoolean) {
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("No Image","PleaseChoose Image");

                } else {
                    upLoadValueToServer();

                }

            }
        });


    }//Tool bar controll

    private void upLoadValueToServer() {
        //Find Patch of IMage
        String strPathImage = "";

        String[] strings = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri,strings,
                null,null,null);
        if (cursor != null) {
            cursor.moveToFirst();
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            strPathImage = cursor.getString(index);


        } else {
            strPathImage = uri.getPath();
        }
        Log.d(tag, "Path of Image ==>" + strPathImage);
        //Uplode File to server
        try {
            //Connect Protocol FTP
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                    .Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            SimpleFTP simpleFTP = new SimpleFTP();
            //myconstan run
            MyConstant myConstant = new MyConstant();
            simpleFTP.connect(
                    myConstant.getHostString(),
                    myConstant.getPortAnInt(),
                    myConstant.getUserString(),
                    myConstant.getPasswordString()


            );
            simpleFTP.bin();
            simpleFTP.cwd("ImageTong");
            simpleFTP.stor(new File(strPathImage));
            simpleFTP.disconnect();








        } catch (Exception e) {
            Log.d(tag, "e upload ==>" + e.toString());

        }


    }//upload
}//main clase
