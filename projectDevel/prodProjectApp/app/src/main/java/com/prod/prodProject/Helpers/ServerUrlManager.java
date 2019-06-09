package com.prod.prodProject.Helpers;

public class ServerUrlManager {

    private static String BASE_URL = "https://prod-project-239707.appspot.com/";

    public static String getRegistrationURL() {
        return BASE_URL + "workerMain";
    }
}
