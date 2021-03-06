/*
* Copyright 2018 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.kubekbreha.javalogin.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kubekbreha.javalogin.MainActivity;
import com.kubekbreha.javalogin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mRegisterButton;

    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;

    private AnimationDrawable mAnimationDrawable;
    private RelativeLayout mRelativeLayout;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        mRelativeLayout = view.findViewById(R.id.register_gradient);
        mEmailField = view.findViewById(R.id.edit_email);
        mPasswordField = view.findViewById(R.id.edit_password);
        mRegisterButton = view.findViewById(R.id.confirm_register_button);

        // Inflate the layout for this fragment
        mAnimationDrawable = (AnimationDrawable) mRelativeLayout.getBackground();
        mAnimationDrawable.setEnterFadeDuration(4500);
        mAnimationDrawable.setExitFadeDuration(4500);
        mAnimationDrawable.start();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getActivity().getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        mAuth = FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(getContext());

        registerButtonListener();

        return view;
    }

    /**
     * Listener on mRegisterButton.
     * On click confirm registration.
     */
    private void registerButtonListener(){
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });
    }



    /**
     * Register new user using email.
     */
    private void doRegister() {
        String email = mEmailField.getText().toString().trim();
        String password = mPasswordField.getText().toString().trim();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            mProgress.setMessage("Singin Up");
            mProgress.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        mProgress.dismiss();
                        updateUI();
                    } else {
                        mProgress.dismiss();
                        Toast.makeText(getActivity(), "Registration error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Open MainActivity.
     * Called when user is authenticated.
     */
    private void updateUI() {
        Toast.makeText(getActivity(), "registered", Toast.LENGTH_SHORT).show();

        Intent accountIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(accountIntent);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        getActivity().finish();
    }

}
