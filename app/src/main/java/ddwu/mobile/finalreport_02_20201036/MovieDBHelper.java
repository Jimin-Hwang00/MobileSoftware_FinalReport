package ddwu.mobile.finalreport_02_20201036;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MovieDBHelper extends SQLiteOpenHelper {

    final static String TAG = "MovieDBHelper";

    final static String DB_NAME = "movies.db";
    public final static String TABLE_NAME = "movie_table";
    public final static String COL_ID = "_id";
    public final static String COL_POSTER_ID = "poster";
    public final static String COL_TITLE = "title";
    public final static String COL_DIRECTOR = "director";
    public final static String COL_RELEASE_DATE = "date";
    public final static String COL_PRODUCTION_COMPANY = "production_company";

    public MovieDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " + COL_TITLE + " TEXT, "
               + COL_POSTER_ID + " INT, " + COL_DIRECTOR + " TEXT, " + COL_RELEASE_DATE + " TEXT, " + COL_PRODUCTION_COMPANY + " TEXT)";

        Log.d(TAG, sql);
        sqLiteDatabase.execSQL(sql);

        insertSample(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void insertSample(SQLiteDatabase db) {
        db.execSQL("insert into " + TABLE_NAME + " values (null, 'Her'," + R.mipmap.her + ", 'Spike Jonze', '2013/12/18', 'Annapurna Pictures')");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 'Marriage Story'," + R.mipmap.marriage_story + ", 'Noah Baumbach', '2019/11/06', 'Heyday Films')");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 'Maudie'," + R.mipmap.maudie + ", 'Aisling Walsh', '2016/09/02', 'Rink Rat Productions')");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 'Un divan à Tunis'," + R.mipmap.un_divan_a_tunis + ", 'Manele Ladidi Labbé', '2019/09/04', 'Kazak Productions')");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 'Born to Be Blue'," + R.mipmap.born_to_be_blue + ", 'Robert Budreau', '2015/09/13', 'New Real Films, Lumanity, Black Hangar Studios')");
    }
}
