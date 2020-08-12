package com.example.home_rent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {
    private EditText email;
    private Button Register;
    private EditText password, confirm_password;


    private FirebaseAuth a;
    String sent_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email);
       // phn = findViewById(R.id.phn);
       // code = findViewById(R.id.code);
        Register= findViewById(R.id.reg);
       // code_button = findViewById(R.id.code_button);
        password= findViewById(R.id.pass);
        confirm_password= findViewById(R.id.c_pass);

        a = FirebaseAuth.getInstance();



        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_email = email.getText().toString();
                String pass = password.getText().toString();
                String c_p = confirm_password.getText().toString();


                if (pass.equals(c_p)) {


                    if (TextUtils.isEmpty(text_email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(c_p)) {

                        Toast.makeText(Register.this, "ERROR_404", Toast.LENGTH_SHORT).show();
                    } else if (pass.length() < 7) {
                        password.setError("Please enter a vali stong password");
                        password.requestFocus();
                        return;
                    } else {
                        registerUser(text_email, pass, c_p);
                    }
                } else {
                    confirm_password.setError("Password didn't match");
                    confirm_password.requestFocus();
                    return;

                   // Toast.makeText(Register.this, "Password Not matching", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


    private void registerUser(String email, String pass1, String cp) {
        a.createUserWithEmailAndPassword(email, pass1).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    }


       /* code_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerification();


            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCode();

            }
        });


    }

    private void verifyCode() {
        String cod= code.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(sent_code, cod);
        signInWithPhoneAuthCredential(credential);
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        a.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //new activity required
                            Toast.makeText(Register.this, "Successfully Registred", Toast.LENGTH_SHORT).show();


                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(Register.this, "Could Not Register", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }

    });
}

    private void sendVerification() {
        String phone = phn.getText().toString();
        if (phone.isEmpty()) {
            phn.setError("Phone number is reqired");
            phn.requestFocus();
            return;
        } else if (phone.length() < 11) {
            phn.setError("Please enter a valid number");
            phn.requestFocus();
            return;
        }



        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            sent_code= s;
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }
    };

    */



