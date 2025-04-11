package com.nd.cashflow.modules;

public class CFSession {

    private static CFSession instance;

    private Object object;

    private CFSession() {  }

    public static CFSession getInstance() {
        if (instance == null) {
            instance = new CFSession();
        }

        return instance;
    }

    public void setSessionObject(Object o) {
        object = o;
    }

    public Object getSessionObject() {
        return object;
    }
}
