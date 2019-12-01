package uoft.csc207.gameapplication.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TryGetJSON {
    public static String tryGetString(JSONObject jsonObject, String name, String defaultValue) {
        try {
            return jsonObject.getString(name);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    public static int tryGetInt(JSONObject jsonObject, String name, int defaultValue) {
        try {
            return jsonObject.getInt(name);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    public static JSONObject tryGetJSONObject(JSONArray array, int index, JSONObject defaultValue) {
        try {
            return array.getJSONObject(index);
        } catch (JSONException e) {
            return defaultValue;
        }
    }
}
