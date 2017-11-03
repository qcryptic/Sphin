package qcryptic.sphin.utils;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import qcryptic.sphin.vo.HttpResponseVo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;


/**
 * Created by Kyle on 10/1/2017.
 */
public class SphinUtils {

    private static final Logger LOGGER = Logger.getLogger(SphinUtils.class.getName());

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

    public static HttpResponseVo getHttpStatus(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            return new HttpResponseVo(connection.getResponseCode(), connection.getResponseMessage());
        } catch (Exception e) {
            LOGGER.error("Error accessing URL", e);
            return new HttpResponseVo(-1, e.getMessage() + " ("+ e.getClass().getSimpleName()+")");
        }
    }

}
