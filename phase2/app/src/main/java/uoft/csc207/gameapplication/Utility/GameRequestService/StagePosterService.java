package uoft.csc207.gameapplication.Utility.GameRequestService;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import uoft.csc207.gameapplication.Utility.GameRequestService.Models.Score;
import uoft.csc207.gameapplication.Utility.GameRequestService.Models.Token;

public class StagePosterService extends RestApiConnector {
    public static final String STAGEPOSTER = "api/tokens/stage/";

    public void postStage(Token token, String stage, final CallBack callback) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);


            ObjectMapper objectMapper = new ObjectMapper();
            JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(token));

            JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.PUT, URL + STAGEPOSTER + stage, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    callback.onSuccess();
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
}