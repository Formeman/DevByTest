package TestAPI;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

public class APITesting {
    public static void main(String[] args) throws IOException {
        URL obj = new URL("http://dev.by/");
        HttpsURLConnection con;

        con = (HttpsURLConnection) obj.openConnection();

        con.addRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36" );

        con.setRequestMethod("GET");

        System.out.println(con.getResponseCode());
    }
}



