package com.example.cineenigma.data;

import android.provider.BaseColumns;

public class RaitingFilmContract {
    public static final class RaitingFilmEntry implements BaseColumns {
        public static final String TABLE_NAME = "raitingfilm";
        public static final String COLUMN_film_title = "film_title";
        public static final String COLUMN_film_username = "username";
        public static final String COLUMN_film_date = "date";
        public static final String COLUMN_film_scenario = "scenario";
        public static final String COLUMN_film_realisation = "realisation";
        public static final String COLUMN_film_music = "music";
        public static final String COLUMN_film_commentaire = "commentaire";
    }
}
