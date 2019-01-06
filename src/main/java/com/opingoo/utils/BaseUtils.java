package com.opingoo.utils;

import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.util.Properties;
import java.util.UUID;

public class BaseUtils {

    private static final String SEPARATOR = ",";
    private static Properties props = new Properties();


    public static Response sendInfoResponse(Object content , int code, String responseType, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("content",content);
        return Response.status(code).entity(jsonObject).type(responseType).build();
    }

    public static Response sendUnAuthorizedResponse(HttpServletRequest request){
        return sendInfoResponse("Authorization Required",401,GeneralConstants.responseTypeJSON,request);
    }


    private static String PLAPP_HOME;

    public static String getAPPHome() {
        if (PLAPP_HOME == null) {
            PLAPP_HOME = System.getenv("PLAPP_HOME");
            if (PLAPP_HOME == null) {
                PLAPP_HOME = System.getProperty("PLAPP_HOME");
            }
        }

        if (PLAPP_HOME == null) // everything is null, set to default
            PLAPP_HOME = "./";

        System.out.println("PLAPP_HOME = " + PLAPP_HOME);
        return PLAPP_HOME;
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




}
