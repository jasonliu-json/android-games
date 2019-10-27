package uoft.csc207.gameapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private Button loginButton;
    private TextView registerText;

    private EditText emailInput;
    private EditText passwordInput;

    String loginEmail;
    String loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.login_button);
        registerText = (TextView) findViewById(R.id.register_text);
        emailInput = (EditText) findViewById(R.id.login_email_field);
        passwordInput = (EditText) findViewById(R.id.login_password_field);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginEmail = emailInput.getText().toString();
                loginPassword = passwordInput.getText().toString();
                if (loginEmail.equals("test@gmail.com") && loginPassword.equals("password")) {
                    showToast("Login Successful");
                }
                else {
                    showToast("Incorrect login credentials");
                }
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerUser = new Intent(Login.this, RegisterUser.class);
                startActivity(registerUser);
            }
        });
    }
    private void showToast(String text) {
        Toast.makeText(Login.this, text, Toast.LENGTH_SHORT).show();
    }
}
