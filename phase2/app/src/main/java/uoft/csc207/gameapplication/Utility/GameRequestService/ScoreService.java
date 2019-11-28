package uoft.csc207.gameapplication.Utility.GameRequestService;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import uoft.csc207.gameapplication.Utility.GameRequestService.Models.LeaderBoard;
import uoft.csc207.gameapplication.LeaderboardActivity;

// specifically made for global scores
public class ScoreService {
    private static final String URL = "http://192.168.2.17:8080/"; // local ip using http for testing

    private static final String LEADERBOARDS = "api/leaderboard/testing/";

    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void getGlobalLeaderboards(String game, final LeaderboardActivity.GlobalLeaderBoardCallback callback) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);

            StringRequest leaderBoardRequest = new StringRequest(Request.Method.GET, URL + LEADERBOARDS + game, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        LeaderBoard leaderBoard = new ObjectMapper().readValue(response, LeaderBoard.class);
                        callback.onSuccess(leaderBoard);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onFailure();
                }
            });
            requestQueue.add(leaderBoardRequest);
            System.out.println("running");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
