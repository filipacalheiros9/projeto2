package com.example;

import org.springframework.context.ApplicationContext;

public class AppContextProvider {
    private static ApplicationContext context;
    public static void setApplicationContext(ApplicationContext ctx) {
        context = ctx;
    }
    public static ApplicationContext getApplicationContext() {
        return context;
    }
}
