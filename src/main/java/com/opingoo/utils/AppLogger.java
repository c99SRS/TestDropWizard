package com.opingoo.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AppLogger {

    private static AppLogger instance = null;
    long longConfigCheckInterval = 60000L;

    private static Logger logger;

    static {
        try {
            String log4jConf = BaseUtils.getAPPHome() + File.separator + "log4j.properties";
            instance =  AppLogger.getInstance("App",log4jConf);
            instance.logInfo("AppLogger" , "Intializing Logger.... \t configuration file: " + log4jConf);



        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static synchronized AppLogger getInstance(String name, String propFile){

        if (instance == null){
            instance = new AppLogger();
            instance.init(name,propFile);
        }
        return instance;
    }

    public void init(String name, String propFile){

        try {
            PropertyConfigurator.configureAndWatch(propFile, longConfigCheckInterval);
            logger = Logger.getLogger(propFile);
            logger.info("ClientLogger " + name+ " logging initilaized.....");
        }catch (Exception ex){
            throw new RuntimeException("ClientLogger init failure: " + ex.getMessage());
        }
    }

    public static AppLogger getLogger(){
        if (instance == null){
            throw new RuntimeException("ClientLogger not initialized");
        }
        return instance;
    }


    public void logInfo(String className,String msg){
        logger.info("[" + className + "] <>" + msg);
    }

    public void logError(String className, String msg) {
        logger.error("["+className + "] <> " + msg);
    }

    public void logError(String className, String msg,Exception e) {
        logException("["+className + "] <> " + msg, e);
    }

    public void logWarn(String className, String msg) {
        logger.warn("["+className + "] <> " + msg);
    }

    public void logDebug(String className, String msg) {
        logger.debug("["+className + "] <> " + msg);
    }

    public void logFatal(String className, String msg) {
        logger.fatal("["+className + "] <> " + msg);
    }

    public void log2Console(String className, String message) {
        logger.info("["+className + "] <> " + message);
        System.out.println(message);
    }


    public void logException(String messsage,Exception ex){
        try {

            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));

            String exceptionAsString = sw.toString();
            logFatal(messsage,exceptionAsString);
        }catch (Exception e){
            logFatal(messsage," ERROR !!! Couldn't  log the exceptions !!!");
        }
    }

}
