package qcryptic.sphin.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Created by Kyle on 10/1/2017.
 */
public class genericUtils {

    public static JSONArray readJsonArray(InputStream input) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            String line;
            StringBuilder responseStrBuilder = new StringBuilder();
            while((line =  in.readLine()) != null)
                responseStrBuilder.append(line);
            in.close();
            return new JSONArray(responseStrBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject readJson(InputStream input) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            String line;
            StringBuilder responseStrBuilder = new StringBuilder();
            while((line =  in.readLine()) != null)
                responseStrBuilder.append(line);
            in.close();
            return new JSONObject(responseStrBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
