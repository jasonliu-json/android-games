package uoft.csc207.gameapplication.Utility.GameRequestService;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;

import uoft.csc207.gameapplication.Utility.GameRequestService.Models.Token;

import static java.lang.Thread.sleep;

// specifically for accessing user info
public class LoginService extends RestApiConnector{
    public static final String LOGIN = "api/tokens/login";
    private static Token loginToken = new Token();

    public void login(String username, String password, final CallBack callback) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            JSONObject loginInfo = new JSONObject();
            loginInfo.put("username", username);
            loginInfo.put("password", password);
            JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, URL + LOGIN, loginInfo, new Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Token userToken = new ObjectMapper().readValue(response.toString(), Token.class);
                        loginToken.setToken(userToken.getToken());
                        loginToken.setUsername(userToken.getUsername());
                        callback.onSuccess();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }}, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onFailure();
                }
            });
            callback.onWait();
            requestQueue.add(loginRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Token getLoginToken() {
        return loginToken;
    }
}
