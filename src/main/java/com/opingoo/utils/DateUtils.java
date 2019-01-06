package com.opingoo.utils;

public class DateUtils {

    private static AppLogger logger = AppLogger.getLogger();
    private static final String CLASSNAME = DateUtils.CLASSNAME;

    public static final long SECONDS_IN_A_HOUR =  (60 * 60);
    public static final long MILLISECONDS_IN_A_HOUR = (1000 * SECONDS_IN_A_HOUR);

    public static final long SECONDS_IN_A_DAY =  (60 * 60 * 24);
    public static final long MILLISECONDS_IN_A_DAY = (1000 * SECONDS_IN_A_DAY);
    public static final int TWO_YR_CHILD_AGE_IN_DAYS = 730;

    public static long getTimeStamp() {
        return System.currentTimeMillis() / 1000L;
    }


}
