package com.opingoo.utils;

import lombok.Getter;

public class GeneralConstants {

    public static final String responseTypeJSON = javax.ws.rs.core.MediaType.APPLICATION_JSON;
    public static final String USER_SESSION_ATTRIBUTE = "userId";
    public static final String ADMIN = "admin";


    public enum ActivityStatus {

        ACTIVE(1),
        INACTIVE(0),
        REJECTED(-1),
        DELETED(-2),;

        @Getter
        int value;

        ActivityStatus(int value){
            this.value = value;
        }
    }


}
