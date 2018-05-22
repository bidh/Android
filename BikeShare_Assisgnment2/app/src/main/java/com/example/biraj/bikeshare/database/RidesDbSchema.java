package com.example.biraj.bikeshare.database;

/**
 * Created by biraj on 5/22/2018.
 */

public class RidesDbSchema {
    public static final class RideTable{
        public static final String Name="rides";

        public static final class Cols{
            public static final String UUID="uuid";
            public static final String BIKENAME="bikename";
            public static final String STARTLOCATION="startlocation";
            public static final String STARTDATETIME="startdatetime";
            public static final String ENDLOCATION="endlocation";
            public static final String ENDDATETIME="enddatetime";
            public static final String PRICE="price";
        }
    }
}
