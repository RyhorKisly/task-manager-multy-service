package by.itacademy.userservice.core.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Messages {
    public static final String ERROR_VERIFY_RESPONSE = "Failed to verify user. Contact support!";
    public static final String WRONG_PASSWORD_RESPONSE = "Wrong password!";
    public static final String NOT_VERIFIED_RESPONSE = "The user is not activated. " +
            "To activate, follow the link sent to the email specified during registration. " +
            "If you didn't receive a link, please contact your administrator.";
    public static final String USER_REGISTERED = "User: %s was registered";
    public static final String USER_ACTIVATED = "User: %s was activated";

    public static final String ERROR_UPDATE_RESPONSE = "Failed to update user. Wrong coordinates!";
    public static final String ERROR_GET_RESPONSE = "Failed to get user(s). Try again or contact support!";
    public static final String USER_NOT_EXIST_RESPONSE = "User with this id does not exist!";
    public static final String USER_EXIST_RESPONSE = "User with this login exists";
    public static final String NAME_MAIL_CONSTRAINT = "users_mail_unique";
    public static final String WRONG_MAIL_RESPONSE = "Wrong mail";
    public static final String USER_SAVED = "User: %s was created";
    public static final String USER_UPDATED = "User: %s was updated";
    public static final String NOT_FOUND_SOME_USERS = "There are non-existent users in the query";
    public static final String NOT_ACTIVATED_RESPONSE = "User: %s, is not activated. " +
            "To activate, follow the link sent to the email specified during registration. " +
            "If you didn't receive a link, please contact your administrator.";
}
