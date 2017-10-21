package qcryptic.sphin.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.DefaultPropertiesPersister;
import qcryptic.sphin.SphinApplication;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Kyle on 10/1/2017.
 */
public class SphinUtils {

    final private static String path = System.getProperty("user.home") + File.separator + "Sphin" + File.separator + "sphin.properties";

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

    public static void updateProperty(String name, String value) {
        try {
            //Create (if needed) and Open file
            new File(path).createNewFile();
            FileInputStream in = new FileInputStream(path);
            Properties props = new Properties();
            props.load(in);
            in.close();
            //update or add property
            props.setProperty(name, value);
            //Store updated properties
            FileOutputStream out = new FileOutputStream(path);
            props.store(out, null);
            out.close();
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public static void updateProperties(Map<String, String> properties) {
        try {
            System.out.println(path);
            //Create (if needed) and Open file
            new File(path).createNewFile();
            FileInputStream in = new FileInputStream(path);
            Properties props = new Properties();
            props.load(in);
            in.close();
            //update or add properties
            for (Map.Entry<String,String> property : properties.entrySet()) {
                props.setProperty(property.getKey(), property.getValue());
            }
            //Store updated properties
            FileOutputStream out = new FileOutputStream(path);
            props.store(out, null);
            out.close();
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

}
