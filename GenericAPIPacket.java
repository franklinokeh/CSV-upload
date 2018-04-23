
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seun
 */


public class GenericAPIPacket {

    private String appName;
    private String baseUrl;
    private HashMap<String, String> serviceParams;

    
    public String getAppName() {
        return appName;
    }

    
    public void setAppName(String serviceURL) {
        this.appName = serviceURL;
    }

    
    public HashMap<String, String> getServiceParams() {
        return serviceParams;
    }

    
    public void setServiceParams(HashMap<String, String> serviceParams) {
        this.serviceParams = serviceParams;
    }

    
    public String getBaseUrl() {
        return baseUrl;
    }

    
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}


