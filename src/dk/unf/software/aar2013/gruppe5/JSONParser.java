package dk.unf.software.aar2013.gruppe5;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class JSONParser {
    static InputStream is = null;
    static String json = "";

    public JSONParser(Context context) {
        AssetManager mngr = context.getAssets();
        try {
            is = mngr.open("questions.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {



            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }


    public JSONObject parseJson(String loadJSONFromAsset) {

        json = loadJSONFromAsset;
        JSONObject jObj = null;
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }
}
