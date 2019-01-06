package com.opingoo.sessions;

public class UserSessionObject {


    private static final long serialVersionUID = -3062095866301139774L;

    private static String userId;
    private static String firstName;
    private static String lastName;
    private static String role;
    private boolean isAuthenticated;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        UserSessionObject.userId = userId;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        UserSessionObject.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        UserSessionObject.lastName = lastName;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        UserSessionObject.role = role;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }


    public UserSessionObject(String userId, String firstName, String lastName, String role, boolean isAuthenticated) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String toString() {
        return "UserSessionObject [userId=" + userId + ", firstName="
                + firstName + ", lastName=" + lastName + ", role=" + role  + ", isAuthenticated=" + isAuthenticated + "]";
    }

}
