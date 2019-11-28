package uoft.csc207.gameapplication.Utility.GameRequestService;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;

import uoft.csc207.gameapplication.Utility.GameRequestService.Models.Token;
import uoft.csc207.gameapplication.Utility.GameRequestService.Models.User;
import uoft.csc207.gameapplication.PersonalScoresActivity;

public class GetUserService extends RestApiConnector{
    public static final String GET_USER = "api/tokens/user/";
    private User user = new User();

    public void getUser(Token token, final CallBack callback) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            JSONObject loginInfo = new JSONObject();
            loginInfo.put("token", token.getToken());
            loginInfo.put("username", token.getUsername());
            JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, URL + GET_USER, loginInfo, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        User userInfo = objectMapper.readValue(response.toString(), User.class);
                        user.setTimePlayed(userInfo.getTimePlayed());
                        user.setTotalPoints(userInfo.getTotalPoints());
                        user.setUsername(userInfo.getUsername());
                        user.setUserScores(userInfo.getUserScores());
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

    public User getUser() {
        return user;
    }
}
