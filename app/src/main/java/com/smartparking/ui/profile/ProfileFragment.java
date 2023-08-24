package com.smartparking.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.smartparking.R;
import com.smartparking.RegisterLogin.LoginActivity;
import com.smartparking.classes.User;
import com.smartparking.utils.AppConstants;
import com.smartparking.utils.BasicUtils;
import com.smartparking.utils.notifications.AlarmUtils;
import com.smartparking.utils.notifications.NotificationReceiver;
import com.smartparking.utils.services.MyParkingService;

public class ProfileFragment extends Fragment {

    LinearLayout personalDetailsBtn,changePasswordBtn,aboutMeBtn,logoutBtn,upiDetailsBtn;
    TextView nameText;
    User userObj;
    AppConstants globalClass;
    FirebaseAuth auth;
    BasicUtils utils=new BasicUtils();

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        initComponents(root);
        attachListeners();

        if(!utils.isNetworkAvailable(getActivity().getApplication())){
            Toast.makeText(getActivity(), "No Network Available!", Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    private void initComponents(View root) {
        globalClass=(AppConstants)getActivity().getApplicationContext();
        userObj=globalClass.getUserObj();
        logoutBtn = root.findViewById(R.id.logoutBtn);
        nameText = root.findViewById(R.id.nameText);
        personalDetailsBtn = root.findViewById(R.id.personalDetailsBtn);
        changePasswordBtn = root.findViewById(R.id.changePasswordBtn);
        aboutMeBtn = root.findViewById(R.id.aboutMeBtn);
        upiDetailsBtn = root.findViewById(R.id.upiDetailsBtn);
        nameText.setText(userObj.name);
    }

    private void attachListeners() {
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                getActivity().stopService(new Intent(getActivity(), MyParkingService.class));
                AlarmUtils.cancelAllAlarms(getActivity(),new Intent(getActivity(), NotificationReceiver.class));
                Toast.makeText(getActivity(), "Logout Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        personalDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PersonalDetailsActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        upiDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UpiDetailsActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        aboutMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AboutMeActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

}