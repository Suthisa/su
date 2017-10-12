package app.kesorn.suthisa.su.fragment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import app.kesorn.suthisa.su.R;
import app.kesorn.suthisa.su.utility.GetAllData;
import app.kesorn.suthisa.su.utility.MyAlert;
import app.kesorn.suthisa.su.utility.MyConstant;


/**
 * Created by suthisa on 10/10/2017.
 */

public class MainFragment extends Fragment{
    String userString; //การประกาศแบบย่อ
    private String passwordString;//การประกาศแบบยาว
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_main,container,false);

       return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Register

        register();
//SignIn Controller

        signInController();

    }

    private void signInController() {
        Button button = (Button) getView().findViewById(R.id.butSingIn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userEditText = (EditText) getView().findViewById(R.id.edtUser);
                EditText passEditText = (EditText) getView().findViewById(R.id.edtPassword);

                userString = userEditText.getText().toString().trim();
                passwordString = passEditText.getText().toString().trim();

                if (userString.equals("") || passwordString.equals("")) {
                    //hae Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("Have Spece", "Please Faill All Blank");

                } else {
                    //No Space

                    checkUserAndPass();

                }
            }
        });
    }

    private void checkUserAndPass() {
        String tag = "12OCtV1";
        Log.d(tag,"check Working");

        try {
            GetAllData getAllData = new GetAllData(getActivity());
            MyConstant myConstant = new MyConstant();//ดึงคราสนี้มาใช้
            getAllData.execute(myConstant.getUrlGetdata());
            String resultJSON = getAllData.get();
            Log.d(tag, "JSON ==>" + resultJSON);


        } catch (Exception e) {
            Log.d(tag,"e check ==>" + e.toString());
        }
    }

    private void register() {

        TextView textView = (TextView) getView().findViewById(R.id.txtRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Move to REgidter
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentFragmentMain,new RegisterFragment())
                        .addToBackStack(null)
                        .commit();


            }

        });
    }
}//Main Class
