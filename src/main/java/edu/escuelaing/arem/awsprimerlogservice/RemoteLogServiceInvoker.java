package edu.escuelaing.arem.awsprimerlogservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RemoteLogServiceInvoker {
    private static final String USER_AGENT = "Mozilla/5.0";
    private String[] get_URL = {};
    private int selectecService = 0;
    public RemoteLogServiceInvoker(String[] invokeUrl) {
        get_URL = invokeUrl;
    }

    public String invoke(String msg) throws IOException {

        URL obj;
        if (selectecService > 2){
            selectecService = 0;
        }

        if(msg != null) {
            obj = new URL(get_URL[selectecService] + "?msg=" + msg);
            selectecService = selectecService + 1;
        } else {
            obj = new URL(get_URL[selectecService]);
        }

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");

        return response.toString();
    }
}
