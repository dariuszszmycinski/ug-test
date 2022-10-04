package dasz.nbpconsumingrest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class UsdReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static Double getUsdForDate(String date) {
        String url = "http://api.nbp.pl/api/exchangerates/rates/c/usd/" + date + "/?format=json";
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return Double.parseDouble(json.toString().split("bid\":")[1].substring(0, 5));
        } catch (Exception e) {
            System.out.println("not possible to get UsdCurrency");
        }
        return 0.0;
    }
}
