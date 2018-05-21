package com.example.biraj.bikeshare.database;

/**
 * Created by biraj on 5/1/2018.
 */

public class RegisterDbSchema {
    public static final class RegisterTable{
        public static final String NAME="registers";

        public static final class Cols{
            public static final String UUID="uuid";
            public static final String TYPE="type";
            public static final String NAME="name";
            public static final String PRICE="price";
            public static final String DATE="date";
            public static final String IMAGE="image";
        }
    }
}
