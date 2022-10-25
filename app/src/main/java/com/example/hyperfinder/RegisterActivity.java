package com.example.hyperfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email, password;
    private EditText fname, mname, lname, age, sex, contact;
    private Button btnRegister;
    private TextView textLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        fname = findViewById(R.id.register_firstName);
        mname = findViewById(R.id.register_mName);
        lname = findViewById(R.id.register_lastName);
        age = findViewById(R.id.register_age);
        sex = findViewById(R.id.register_sex);
        contact = findViewById(R.id.register_contactNumber);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        btnRegister = findViewById(R.id.register);
        textLogin = findViewById(R.id.text_login);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void Register() {
        String firstName = fname.getText().toString().trim();
        String midName = mname.getText().toString().trim();
        String lastname = lname.getText().toString().trim();
        String yr = age.getText().toString().trim();
        String gender = sex.getText().toString().trim();
        String cNumber = contact.getText().toString().trim();
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (user.isEmpty()) {
            email.setError("Email can not be empty");
        } if (pass.isEmpty()) {
            password.setError("Password can not be empty");
        } if (firstName.isEmpty()) {
            fname.setError("First Name can not be empty");
        } if (midName.isEmpty()) {
            mname.setError("Middle Name can not be empty");
        } if (lastname.isEmpty()) {
            lname.setError("Last Name can not be empty");
        } if (yr.isEmpty()) {
            age.setError("Age can not be empty");
        } if (gender.isEmpty()) {
            sex.setError("Sex can not be empty");
        } if (cNumber.isEmpty()) {
            contact.setError("Contact Number can not be empty");
        } else {
            mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "User registered successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registered Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}