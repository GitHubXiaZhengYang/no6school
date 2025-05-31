package com.straight_ocean.job;


public class JobClientData {
    private static String job;

    public static String getJob() {
        return job;
    }

    public static void setJob(String job) {
        JobClientData.job = job;
    }
}
