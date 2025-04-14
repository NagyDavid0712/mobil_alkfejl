package com.nd.cashflow.modules;

public class CFFieldsChecker {

    public static String checkPasswordField(String field) {
        if (field.isEmpty()) { return "A jelszó mező nem lehet üres!"; }
        if (field.length() < 6) { return "A jelszó hosszának minimum 6 karakter hosszúnak kell lennie!"; }
        if (!field.matches("^[a-zA-Z0-9]+$")) { return "A jelszó csak minimum kis és nagy betűket illetve számokat tartalmazhat!"; }
        return null;
    }

    public static String checkEmailField(String field) {
        if (field.isEmpty()) { return "Az E-Mail mező kitöltése kötelező!"; }
        if (!field.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) { return "Az E-Mail nem felel meg a formátumnak!"; }
        return null;
    }

    public static String checkBaseField(String field) {
        if (field.isEmpty()) { return "A név mezőt kötelező kitölteni!"; }
        if (!field.matches("^[a-zA-Z]+$")) { return "A név mező csak kis és nagybetűket tartalmazhat!"; }
        return null;
    }

}
