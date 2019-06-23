package TestAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class APITesting3 {
    public static void main(String[] args) throws IOException, InterruptedException {
        String json = "{\"user\":{\"login\":\"asdf\",\"password\":\"asd\"}}";

        URL obj = new URL("https://id.dev.by/@/hello");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.addRequestProperty("referer", "https://id.dev.by/");

        con.setConnectTimeout(5000);
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod("POST");

        OutputStream os = con.getOutputStream();
        os.write(json.getBytes("UTF-8"));
        os.close();


        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }
        System.out.println(sb.toString());
    }
}

