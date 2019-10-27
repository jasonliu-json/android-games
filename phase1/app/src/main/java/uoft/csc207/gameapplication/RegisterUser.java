package uoft.csc207.gameapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity {
    private Button registerButton;
    private EditText emailInput;
    private EditText usernameInput;
    private EditText passwordInput;
    private EditText passwordConfirmationInput;

    String registerEmail;
    String registerUsername;
    String registerPassword;
    String registerPasswordConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        registerButton = (Button) findViewById(R.id.register_button);
        emailInput = (EditText) findViewById(R.id.register_email);
        usernameInput = (EditText) findViewById(R.id.register_username);
        passwordInput = (EditText) findViewById(R.id.register_password);
        passwordConfirmationInput = (EditText) findViewById(R.id.register_password_confirmation);




        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerEmail = emailInput.getText().toString();
                registerUsername = usernameInput.getText().toString();
                registerPassword = passwordInput.getText().toString();
                registerPasswordConfirmation = passwordConfirmationInput.getText().toString();
                if (!registerPassword.equals(registerPasswordConfirmation)) {
                    showToast("The passwords do not match");
                    return;
                }
                else {
                    Intent gameActivity = new Intent(RegisterUser.this,
                                                     GameActivity.class);
                    startActivity(gameActivity);
                }
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(RegisterUser.this, text, Toast.LENGTH_SHORT).show();
    }
}
