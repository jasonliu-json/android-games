package uoft.csc207.gameapplication.Utility.GameRequestService;

import android.content.Context;

public abstract class RestApiConnector {
    public static final String URL = "http://100.65.209.38:8080/";

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }
}
