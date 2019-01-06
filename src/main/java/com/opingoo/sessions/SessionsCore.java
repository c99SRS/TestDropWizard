package com.opingoo.sessions;

import com.opingoo.dao.UserDAO;
import com.opingoo.utils.AppLogger;
import com.opingoo.utils.GeneralConstants;

import javax.servlet.http.HttpSession;



/**
 * Author @SmrutiRanjan
 */

public class SessionsCore {

    private static final String CLASSNAME = SessionsCore.class.getName();
    private static AppLogger logger = AppLogger.getLogger();


    public static String getUserIdFromSession(HttpSession httpSession) {
        UserSessionObject userSessionObject = getUserSessionObject(httpSession);
        if (userSessionObject != null) {
            if (userSessionObject.getRole().equals(GeneralConstants.ADMIN) || UserDAO.isApprovedUser(userSessionObject.getUserId()) )
                return userSessionObject.getUserId();
        }

        return null;
    }
    public static UserSessionObject getUserSessionObject(HttpSession httpSession) {
        if (httpSession.getAttribute(GeneralConstants.USER_SESSION_ATTRIBUTE) != null) {
            UserSessionObject userSessionObject = (UserSessionObject) httpSession.getAttribute(GeneralConstants.USER_SESSION_ATTRIBUTE);
            if(userSessionObject != null) {
                if (userSessionObject.getRole().equals(GeneralConstants.ADMIN) || UserDAO.isApprovedUser(userSessionObject.getUserId()))
                    return userSessionObject;
            }
        }

        return null;
    }












}
