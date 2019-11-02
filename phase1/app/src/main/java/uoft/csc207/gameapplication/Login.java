package uoft.csc207.gameapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login extends AppCompatActivity {
    private Button loginButton;
    private TextView registerText;

    private static final String FILE = "UserData.json";
    private JSONObject jsonObject;

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
        load();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginEmail = emailInput.getText().toString();
                loginPassword = passwordInput.getText().toString();
                String userName = verifyLogin(loginEmail, loginPassword);
                if (userName != null) {
                    Intent mainMenuActivity = new Intent(Login.this, MainMenuActivity.class);
                    mainMenuActivity.putExtra("username", userName);
                    startActivity(mainMenuActivity);
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

    /**
     * load the json from assets or from the android memory
     */
    private void load() {
        try {
            int i = 0;
            String jsonString = "";
            try {
                FileInputStream fileInputStream = openFileInput(FILE);
                while ((i = fileInputStream.read()) != -1) {
                    jsonString += (char) i;
                }
                fileInputStream.close();
            }
            catch (FileNotFoundException e) {
                BufferedReader bufferReader = new BufferedReader(
                        new InputStreamReader(getAssets().open(FILE)));
                while ((i = bufferReader.read()) != -1) {
                    jsonString += (char) i;
                }
                bufferReader.close();
            }
            System.out.println(jsonString);
            jsonObject = new JSONObject(jsonString);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hashes the password and compares to the hash
     * @param email user email used to login
     * @param password user password used to login
     * @return the username if the email and password is valid or else return null
     */
    public String verifyLogin(String email, String password) {
        String passwordHash = RegisterUtility.hash(password, "SHA-256");
        JSONArray userdata;
        try {
            userdata = jsonObject.getJSONArray("users");
            for (int i = 0; i < userdata.length(); i++) {
                JSONObject user = userdata.getJSONObject(i);
                boolean sameUser = user.getString("email").equals(email);
                boolean samePass = user.getString("password").equals(passwordHash);
                if (sameUser && samePass) {
                    showToast("Welcome " + user.getString("username"));
                    return user.getString("username");
                }
            }
            return null;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Displays the text on screen
     * @param text a text to be displayed on screen
     */
    private void showToast(String text) {
        Toast.makeText(Login.this, text, Toast.LENGTH_SHORT).show();
    }
}
