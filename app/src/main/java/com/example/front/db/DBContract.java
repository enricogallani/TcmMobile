package com.example.front.db;

import android.provider.BaseColumns;

public class DBContract {
    private DBContract() {}

    /* Inner class that defines the table contents */
    public static class User implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_OFFICE = "office";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_IS_ADM = "id_adm";
    }

    public static class Client implements BaseColumns {
        public static final String TABLE_NAME = "client";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_CNPJ = "cnpj";
        public static final String COLUMN_PAYMENT_METHOD = "payment_method";
        public static final String COLUMN_LOCATION = "location";
    }
}
