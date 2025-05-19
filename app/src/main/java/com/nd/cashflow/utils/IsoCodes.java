package com.nd.cashflow.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class IsoCodes {

    public static class IsoCode {
        private String iso;
        private String name;
        public IsoCode(String iso, String name) {
            this.iso = iso;
            this.name = name;
        }

        public String getIso() {
            return this.iso;
        }

        public String getName() {
            return this.name;
        }
    }

    public static Map<String, IsoCode> ISO_CODES = new HashMap<>();

    static {
        Map<String, IsoCode> map = new HashMap<>();

        map.put("hu_HU", new IsoCode("HUF", "Magyar forint"));
        map.put("en_US", new IsoCode("USD", "Amerikai dollár"));
        map.put("en_EN", new IsoCode("EUR", "Euró"));
        map.put("es_ES", new IsoCode("EUR", "Euró"));
        ISO_CODES = Collections.unmodifiableMap(map);
    }
}
