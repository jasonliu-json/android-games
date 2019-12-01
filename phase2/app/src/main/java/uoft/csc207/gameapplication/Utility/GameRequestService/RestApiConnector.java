package uoft.csc207.gameapplication.Utility.GameRequestService;

import android.content.Context;

public abstract class RestApiConnector {
    public static final String URL = "http://192.168.2.17:8080/";

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }
}
