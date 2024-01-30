package ddwu.mobile.finalreport_02_20201036;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MovieDBManger {

    MovieDBHelper movieDBHelper = null;
    Cursor cursor = null;

    public MovieDBManger(Context context) {
        movieDBHelper = new MovieDBHelper(context);
    }

    public ArrayList<Movie> getAllMovies() {
        SQLiteDatabase movieDB = movieDBHelper.getReadableDatabase();
        Cursor cursor = movieDB.rawQuery("SELECT * FROM " + MovieDBHelper.TABLE_NAME, null);

        ArrayList movieArrayList = new ArrayList();
        while(cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_ID));
            int poster = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_POSTER_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_TITLE));
            String director = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_DIRECTOR));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_RELEASE_DATE));
            String company = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_PRODUCTION_COMPANY));

            movieArrayList.add(new Movie(_id, poster, title, director, date, company));
        }

        cursor.close();
        movieDBHelper.close();

        return movieArrayList;
    }

    public boolean addNewMovie(Movie newMovie) {
        SQLiteDatabase movieDB = movieDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MovieDBHelper.COL_POSTER_ID, R.mipmap.movie_icon);
        values.put(MovieDBHelper.COL_TITLE, newMovie.getTitle());
        values.put(MovieDBHelper.COL_DIRECTOR, newMovie.getDirector());
        values.put(MovieDBHelper.COL_RELEASE_DATE, newMovie.getDate());
        values.put(MovieDBHelper.COL_PRODUCTION_COMPANY, newMovie.getCompany());

        long count = movieDB.insert(MovieDBHelper.TABLE_NAME, null, values);

        movieDBHelper.close();

        if (count > 0) return true;
        return false;
    }

    public boolean removeMovie(long id) {
        SQLiteDatabase movieDB = movieDBHelper.getWritableDatabase();

        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] {String.valueOf(id)};

        int result = movieDB.delete(MovieDBHelper.TABLE_NAME, whereClause, whereArgs);

        movieDBHelper.close();

        if(result > 0) return true;
        return false;
    }

    public ArrayList<Movie> getMovieByTitle(String title) {
        SQLiteDatabase movieDB = movieDBHelper.getReadableDatabase();
        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();

        String whereClause = MovieDBHelper.COL_TITLE + "=?";
        String[] whereArgs = new String[] { title };

        try {
            cursor = movieDB.query(MovieDBHelper.TABLE_NAME, null, whereClause, whereArgs, null, null, null, null);
            cursor.moveToFirst();

            long id = cursor.getLong(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_ID));
            int moviePoster = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_POSTER_ID));
            String movieTitle = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_TITLE));
            String movieDirector = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_DIRECTOR));
            String movieDate = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_RELEASE_DATE));
            String movieCompany = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_PRODUCTION_COMPANY));

            Movie movie = new Movie(id, moviePoster, movieTitle, movieDirector, movieDate, movieCompany);
            movieArrayList.add(movie);

            cursor.close();
            movieDBHelper.close();

            return movieArrayList;
        } catch (android.database.CursorIndexOutOfBoundsException e) {
            return null;
        }
    }

    public ArrayList<Movie> getMovieByDirector(String director) {
        SQLiteDatabase movieDB = movieDBHelper.getReadableDatabase();
        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();

        String whereClause = MovieDBHelper.COL_DIRECTOR + "=?";
        String[] whereArgs = new String[] { director };

        try {
            cursor = movieDB.query(MovieDBHelper.TABLE_NAME, null, whereClause, whereArgs, null, null, null, null);
            cursor.moveToFirst();

            long id = cursor.getLong(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_ID));
            int moviePoster = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_POSTER_ID));
            String movieTitle = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_TITLE));
            String movieDirector = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_DIRECTOR));
            String movieDate = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_RELEASE_DATE));
            String movieCompany = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_PRODUCTION_COMPANY));

            Movie movie = new Movie(id, moviePoster, movieTitle, movieDirector, movieDate, movieCompany);
            movieArrayList.add(movie);

            cursor.close();
            movieDBHelper.close();

            return movieArrayList;
        } catch (android.database.CursorIndexOutOfBoundsException e) {
            return null;
        }
    }

    public boolean modifyMovie(Movie updateMovie) {
        SQLiteDatabase movieDB = movieDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(MovieDBHelper.COL_TITLE, updateMovie.getTitle());
        row.put(MovieDBHelper.COL_DIRECTOR, updateMovie.getDirector());
        row.put(MovieDBHelper.COL_RELEASE_DATE, updateMovie.getDate());
        row.put(MovieDBHelper.COL_PRODUCTION_COMPANY, updateMovie.getCompany());

        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(updateMovie.get_id()) };

        int result = movieDB.update(MovieDBHelper.TABLE_NAME, row, whereClause, whereArgs);

        movieDBHelper.close();

        if (result > 0) return true;
        return false;
    }
}
