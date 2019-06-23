package TestAPI;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

    public class APITesting1 {
        public static void main(String[] args) throws IOException {
            URL obj = new URL("http://dev.by/");
            HttpsURLConnection con;

            con = (HttpsURLConnection) obj.openConnection();

            con.addRequestProperty("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36" );

            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();

            String output;

            while ((output=br.readLine())!=null){
                sb.append(output);}

            System.out.println(sb.toString());
        }
    }

