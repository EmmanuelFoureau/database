package be.evoliris.formation.hypesql.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Locale;

import be.evoliris.formation.hypesql.db.DbHelper;
import be.evoliris.formation.hypesql.models.Movie;

public class MovieDAO {
    public static final String TABLE_NAME = "movie";
    public static final String COL_ID = "movie_id";
    public static final String COL_TITLE = "movie_title";
    public static final String COL_SYPNOSIS = "movie_sypnosis";
    public static final String COL_DIRECTOR = "movie_director";

//    public static final String CREATE = "CREATE TABLE movie(" +
//            COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            COL_TITLE+ " VARCHAR(50) NOT NULL, " +
//            COL_SYPNOSIS+ " VARCHAR(255) NOT NULL, " +
//            COL_DIRECTOR+ " VARCHAR(50));";
    public static final String CREATE = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR(50) NOT NULL, %s VARCHAR(255), %s VARCHAR(50) NOT NULL)", TABLE_NAME, COL_ID, COL_TITLE, COL_SYPNOSIS, COL_DIRECTOR);
    public static final String DROP = String.format("DROP TABLE %s;", TABLE_NAME);

    private DbHelper helper;
    private Context context;
    private SQLiteDatabase db;

    public MovieDAO(Context context){
        this.context = context;
        this.helper = new DbHelper(this.context);
    }

    public MovieDAO openReadable(){
        this.db = this.helper.getReadableDatabase();
        return this;
    }
    public MovieDAO openWriteable(){
        this.db = this.helper.getWritableDatabase();
        return this;
    }

    public void close(){
        this.db.close();
        this.helper.close();
    }

    public void update(Movie movie){
        ContentValues values = new ContentValues();
        values.put(COL_ID, movie.getId());
        values.put(COL_TITLE, movie.getTitle());
        values.put(COL_SYPNOSIS, movie.getSypnosis());
        values.put(COL_DIRECTOR, movie.getDirector());

        db.update(TABLE_NAME, values, String.format(Locale.FRENCH, "%s = %d", COL_ID, movie.getId()), null);
    }
    public long insert(Movie movie){
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, movie.getTitle());
        values.put(COL_SYPNOSIS, movie.getSypnosis());
        values.put(COL_DIRECTOR, movie.getDirector());

        long idInserted = db.insert(TABLE_NAME, null, values);
        movie.setId(idInserted);
        return idInserted;
    }
    public Movie findById(long id){
        Cursor cursor = db.query(TABLE_NAME, null, COL_ID+ " = "+ id, null, null, null, null);

        if(cursor == null)return null;
        if(cursor.getCount() == 0)return null;
        cursor.moveToFirst();

        return cursorToMovie(cursor);
    }

    private Movie cursorToMovie(Cursor cursor){
        Movie movie = new Movie();

        movie.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        movie.setTitle(cursor.getString(cursor.getColumnIndex(COL_TITLE)));
        movie.setSypnosis(cursor.getString(cursor.getColumnIndex(COL_SYPNOSIS)));
        movie.setDirector(cursor.getString(cursor.getColumnIndex(COL_DIRECTOR)));

        return movie;
    }

}
