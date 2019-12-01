package uoft.csc207.gameapplication.Utility.GameRequestService;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import uoft.csc207.gameapplication.RegisterUserActivity;

/**
 * Connect to rest API, and make the registration application.
 */
public class RegisterService extends RestApiConnector {
    public static final String REGISTER = "api/users/register";

    /**
     * Make the registration application.
     *
     * @param callback   Return the state of the application.
     * @param jsonObject The Json object storing the game information.
     */
    public void register(JSONObject jsonObject, final CallBack callback) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, URL + REGISTER, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    callback.onSuccess();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onFailure();
                }
            });
            callback.onWait();
            requestQueue.add(loginRequest);
            System.out.println("testing");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
