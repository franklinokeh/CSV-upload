/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shedrach
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostUtil {

    public static String postTransaction(GenericAPIPacket aPIPacket)
            throws MalformedURLException, IOException {
        String inputLine = null;

        URL myUrl = new URL(composeTrxnURL(aPIPacket));
        System.out.println("Opening connection " + myUrl.toString());
        HttpURLConnection response = (HttpURLConnection) myUrl.openConnection();
        System.out.println("Default Connect/Read timeout " + response.getConnectTimeout() + "/" + response.getReadTimeout());

        response.setConnectTimeout(30000);
        response.setReadTimeout(30000);
        int responseCode = response.getResponseCode();
        System.out.println("HTTP result " + response.getResponseMessage() + " code " + response.getResponseCode());
        String responseMessage = response.getResponseMessage();
        System.out.println("Response message " + responseMessage);
        System.out.println("Response Code " + responseCode);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getInputStream(), StandardCharsets.UTF_8));
            //Throwable localThrowable3 = null;
            try {
                while (in.ready()) {
                    inputLine = in.readLine();
                    System.out.println(inputLine);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (in != null) {
                    // else {
                        in.close();
                   // }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
          //  Logger.getLogger(PostUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inputLine;
    }

    public static String composeTrxnURL(GenericAPIPacket packet) {
        StringBuilder param = new StringBuilder();
        param.append(packet.getBaseUrl()).append(packet.getAppName()).append("?");
        Iterator hashEntry = packet.getServiceParams().keySet().iterator();
        while (hashEntry.hasNext()) {
            try {
                String key = (String) hashEntry.next();
                param.append("&").append(key).append("=").append(URLEncoder.encode((String) packet.getServiceParams().get(key), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(PostUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(param.toString());
        return param.toString();
    }

    public static String formatNetwork(String phone) {
        if (phone.startsWith("234")) {
            phone = phone.replaceFirst("234", "0");
        }
        String fphone = phone.substring(0, 4);
        String network;
        //String network;
        if ((fphone.endsWith("0805")) || (fphone.endsWith("0705")) || (fphone.endsWith("0811")) || (fphone.endsWith("0815")) || (fphone.endsWith("0807"))) {
            network = "Glo";
        } else {
          //  String network;
            if ((fphone.endsWith("0803")) || (fphone.endsWith("0703")) || (fphone.endsWith("0813")) || (fphone.endsWith("0814")) || (fphone.endsWith("0816")) || (fphone.endsWith("0806")) || (fphone.endsWith("0706")) || (fphone.endsWith("0810")) || (fphone.endsWith("0903"))) {
                network = "MTN";
            } else {
               // String network;
                if ((fphone.endsWith("0802")) || (fphone.endsWith("0708")) || (fphone.endsWith("0812")) || (fphone.endsWith("0808")) || (fphone.endsWith("0701")) || (fphone.endsWith("0902"))) {
                    network = "Airtel";
                } else {
                   // String network;
                    if ((fphone.endsWith("0809")) || (fphone.endsWith("0818")) || (fphone.endsWith("0817")) || (fphone.endsWith("0909"))) {
                        network = "Etisalat";
                    } else {
                        network = "invalid";
                    }
                }
            }
        }
        return network;
    }
}

