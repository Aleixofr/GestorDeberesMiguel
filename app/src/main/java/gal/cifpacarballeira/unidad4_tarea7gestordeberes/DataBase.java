package gal.cifpacarballeira.unidad4_tarea7gestordeberes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private static final String nameDB = "HomeworkDB";
    private static final int versionDB = 1;


    public DataBase(@Nullable Context context) {
        super(context, nameDB, null, versionDB);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String CreateTableHomework = """

                """;

        DB.;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("DROP TABLE usuarios");

        sqLiteDatabase.execSQL("CREATE TABLE usuarios(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "email TEXT," +
                "password TEXT)");
        sqLiteDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE usuarios");
        onCreate(sqLiteDatabase);
    }
}
