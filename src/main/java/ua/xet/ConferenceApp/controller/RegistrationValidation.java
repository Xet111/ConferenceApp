package ua.xet.ConferenceApp.controller;

public interface RegistrationValidation {
    public static final String REGEX_PASS = "[1]{3}";
    public static final String REGEX_USERNAME = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";
}
