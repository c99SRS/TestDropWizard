package com.opingoo.utils;

import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

public class BaseUtils {

    private static final String SEPARATOR = ",";
    private static Properties props = new Properties();

    private static String DOMAIN = null;
    public static final String QUARTER_SIZE = "QUARTER_SIZE";


    static {
        try {
            File newFile = new File(getAPPHome() + File.separator + "common.properties");
            FileInputStream f1 = new FileInputStream(newFile);
            props.load(f1);
            f1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Response sendInfoResponse(Object content , int code, String responseType, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("content",content);
        return Response.status(code).entity(jsonObject).type(responseType).build();
    }

    public static Response sendUnAuthorizedResponse(HttpServletRequest request){
        return sendInfoResponse("Authorization Required",401,GeneralConstants.responseTypeJSON,request);
    }

    public static String getDomainName() {
        if (DOMAIN == null)
            DOMAIN = getCommonProperty("DOMAIN_NAME", "127.0.0.1:8080");
        //String domain = getCommonProperty("DOMAIN_NAME", "http://localhost");
        //System.out.println("Returning " + DOMAIN);
        return DOMAIN;
    }

    public static String[] getDomainNames() {
        if (DOMAIN == null)
            DOMAIN = getCommonProperty("DOMAIN_NAME", "127.0.0.1:8080");
        //String domain = getCommonProperty("DOMAIN_NAME", "http://localhost");
        //System.out.println("Returning " + DOMAIN);
        return DOMAIN.split(",");
    }

    private static String OPAPP_HOME;

    public static String getAPPHome() {
        if (OPAPP_HOME == null) {
            OPAPP_HOME = System.getenv("OPAPP_HOME");
            if (OPAPP_HOME == null) {
                OPAPP_HOME = System.getProperty("OPAPP_HOME");
            }
        }

        if (OPAPP_HOME == null) // everything is null, set to default
            OPAPP_HOME = "./";

        System.out.println("OPAPP_HOME = " + OPAPP_HOME);
        return OPAPP_HOME;
    }

    public static String getCommonProperty(String key,String defaultValue){
        return props.getProperty(key,defaultValue);
    }

    public static int getCommonProperty(String key,int defaultValue){
        String value = props.getProperty(key,null);
        if (value == null)
            return defaultValue;
        return Integer.parseInt(value);
    }

    public static String generateNewUUID(){
        return UUID.randomUUID().toString();
    }


    public static boolean isProdEnvironment() {
        return BaseUtils.getCommonProperty("BUILD_ENV", "QA").equals("PROD");
    }


    public static boolean isResponseLoggingEnabled() {
        // It will log all request / response param in file so careful
        return 1 == BaseUtils.getCommonProperty("ENABLE_RESPONSE_LOGGING", 0);
    }


}
