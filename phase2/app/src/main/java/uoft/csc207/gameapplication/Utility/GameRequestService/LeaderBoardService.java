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
public class LeaderBoardService extends RestApiConnector{
    public static final String LEADERBOARDS = "api/leaderboard/testing/";
    private LeaderBoard leaderBoard = new LeaderBoard();

    public void getGlobalLeaderboards(String game, final CallBack callback) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);

            StringRequest leaderBoardRequest = new StringRequest(Request.Method.POST, URL + LEADERBOARDS + game, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        LeaderBoard newLeaderBoard = new ObjectMapper().readValue(response, LeaderBoard.class);
                        leaderBoard.setGame(newLeaderBoard.getGame());
                        leaderBoard.setScores(newLeaderBoard.getScores());
                        callback.onSuccess();
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

    public LeaderBoard getLeaderBoard() {
        return leaderBoard;
    }
}
