package uoft.csc207.gameapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterUser extends AppCompatActivity {
    private Button registerButton;
    private EditText emailInput;
    private EditText usernameInput;
    private EditText passwordInput;
    private EditText passwordConfirmationInput;

    private static final String FILE = "UserData.json";
    private JSONFileRW fileRW;
    private JSONObject database;

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

        fileRW = new JSONFileRW(FILE, this);
        database = fileRW.load();

        // The on register button
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
                else if (registerUsername.length() > 15) {
                    showToast("Your username is too long");
                }
                else if (validUser()) {
                    register();
                    finish();
                }
            }
        });
    }

    /**
     * Registers the user and adds it to the user data base
     */
    private void register() {
        try {
            JSONObject newUser = new JSONObject();
            JSONArray userdata = database.getJSONArray("users");
            // sets the user data
            Integer userId = userdata.length();
            newUser.put("userId", userId.toString());
            newUser.put("username", registerUsername);
            newUser.put("password", RegisterUtility.hash(registerPassword, "SHA-256"));
            newUser.put("email", registerEmail);

            // sets local score
            JSONArray scores = new JSONArray();
            for (int i = 0; i < 10; i++) {
                scores.put(new JSONObject().put(String.format("top%d", i), "0"));
            }
            newUser.put("topPlays", scores);
            // sets previous gameStates
            newUser.put("savedStage", "0");
            newUser.put("savedPoints", "0");
            // set some extra user data
            newUser.put("totalPoints", "0");
            newUser.put("timePlayed", "0");
            // finally logs data
            userdata.put(newUser);

            fileRW.write(database.toString());
            showToast("Successfully Registered");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return True if this username and email is unique
     */
    private boolean validUser() {
        JSONArray userdata;
        try {
            userdata = database.getJSONArray("users");
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

    /**
     * Displays the text on screen
     * @param text a text to be displayed on screen
     */
    private void showToast(String text) {
        Toast.makeText(RegisterUser.this, text, Toast.LENGTH_LONG).show();
    }

}
