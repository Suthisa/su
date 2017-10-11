package app.kesorn.suthisa.su.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.kesorn.suthisa.su.MainActivity;
import app.kesorn.suthisa.su.R;

/**
 * Created by suthisa on 10/10/2017.
 */

public class RegisterFragment extends Fragment  {
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



    }

}
