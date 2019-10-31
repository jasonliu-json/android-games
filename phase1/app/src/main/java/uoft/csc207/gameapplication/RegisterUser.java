package uoft.csc207.gameapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegisterUser extends AppCompatActivity {
    private Button registerButton;
    private EditText emailInput;
    private EditText usernameInput;
    private EditText passwordInput;
    private EditText passwordConfirmationInput;

    private static final String FILE = "UserData.json";
    private JSONObject jsonObject;

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
        load();




        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerEmail = emailInput.getText().toString();
                registerUsername = usernameInput.getText().toString();
                registerPassword = passwordInput.getText().toString();
                registerPasswordConfirmation = passwordConfirmationInput.getText().toString();
                if (!registerPassword.equals(registerPasswordConfirmation)) {
                    showToast("The passwords do not match");
                }
                else if (!RegisterUtility.validEmail(registerEmail)) {
                    showToast("The email you entered does not seem to be valid");
                }
                else if (!RegisterUtility.strongPassword(registerPassword)) {
                    showToast("Your password should be at least 8 character long " +
                            "contains at least 1 \"@#$%-_=+!^&*\\\", 1 lower and upper case and" +
                            " 1 number and no spaces are allowed. ");
                }
                else if (validUser()) {
                    register();
                    Intent gameActivity = new Intent(RegisterUser.this,
                                                     Login.class);
                    startActivity(gameActivity);
                }
            }
        });
    }
    private void register() {
        try {
            JSONObject newUser = new JSONObject();
            JSONArray userdata = jsonObject.getJSONArray("users");
            Integer userId = userdata.length();
            newUser.put("userId", userId.toString());
            newUser.put("username", registerUsername);
            newUser.put("password", RegisterUtility.hash(registerPassword, "SHA-256"));
            newUser.put("email", registerEmail);
            userdata.put(newUser);
            save();
            showToast("Successfully Registered");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean validUser() {
        JSONArray userdata;
        try {
            userdata = jsonObject.getJSONArray("users");
            for (int i = 0; i < userdata.length(); i++) {
                JSONObject user = userdata.getJSONObject(i);
                boolean sameEmail = user.getString("email").equals(registerEmail);
                boolean sameUser = user.getString("username").equals(registerUsername);
                if (sameUser) {
                    showToast("username is taken");
                    return false;
                }
                else if (sameEmail) {
                    showToast("email is taken");
                    return false;
                }
            }
            return true;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void save() {
        String jsonText = jsonObject.toString();
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE, MODE_PRIVATE);
            fileOutputStream.write(jsonText.getBytes());
            fileOutputStream.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void load() {
        try {
            int i = 0;
            String jsonString = "";
            try {
                FileInputStream fileInputStream = openFileInput(FILE);
                while ((i = fileInputStream.read()) != -1) {
                    jsonString += (char) i;
                }
            }
            catch (FileNotFoundException e) {
                BufferedReader bufferReader = new BufferedReader(
                        new InputStreamReader(getAssets().open(FILE)));
                while ((i = bufferReader.read()) != -1) {
                    jsonString += (char) i;
                }
            }
            jsonObject = new JSONObject(jsonString);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showToast(String text) {
        Toast.makeText(RegisterUser.this, text, Toast.LENGTH_LONG).show();
    }
}
