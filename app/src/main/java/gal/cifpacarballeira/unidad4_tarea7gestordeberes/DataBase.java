package gal.cifpacarballeira.unidad4_tarea7gestordeberes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    //Valores de la Base de Datos
    private static final String NAME_DB = "HomeworkDB";
    private static final int VERSION_DB = 1;

    //Valores de la tabla homework
    private static final String TABLE_HOMEWORK = "homework";
    private static final String HOMEWORK_ID = "id";
    private static final String HOMEWORK_SUBJECT = "subject";
    private static final String HOMEWORK_DESCRIPTION = "description";
    private static final String HOMEWORK_DUE_DATE = "dueDate";
    private static final String HOMEWORK_IS_COMPLETED = "isCompleted";

    private static final String CREATE_TABLE_HOMEWORK =
            "CREATE TABLE " + TABLE_HOMEWORK + " (" +
            HOMEWORK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HOMEWORK_SUBJECT + " TEXT, " +
            HOMEWORK_DESCRIPTION + " TEXT, " +
            HOMEWORK_DUE_DATE + " DATE, " +
            HOMEWORK_IS_COMPLETED + " INTEGER);";


    //Constructor de la base de dos
    public DataBase(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL(CREATE_TABLE_HOMEWORK);
        DB.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE homework");
        onCreate(sqLiteDatabase);
    }

    //METODOS DE CRUD
    // Insertar una nueva tarea
    public long insertHomework(Homework homework) {
        SQLiteDatabase DB = this.getWritableDatabase(); // Obtenemos la base de datos en modo escritura
        ContentValues values = new ContentValues(); // Creamos un objeto ContentValues para almacenar los datos

        // Insertamos los valores en el ContentValues
        values.put(HOMEWORK_SUBJECT, homework.getSubject());
        values.put(HOMEWORK_SUBJECT, homework.getDescription());
        values.put(HOMEWORK_DUE_DATE, homework.getDueDate());
        values.put(HOMEWORK_IS_COMPLETED, homework.isCompleted() ? 1 : 0); // Convertimos booleano a 1 o 0

        // Insertamos el deber en la base de datos y devolvemos el ID generado
        long id = DB.insert(TABLE_HOMEWORK, null, values);
        DB.close(); // Cerramos la base de datos
        return id;
    }

    //Borrar una tarea
    public void deleteHomework(int id) {
        SQLiteDatabase DB = this.getWritableDatabase(); // Obtenemos la base de datos en modo escritura
        DB.delete(TABLE_HOMEWORK, HOMEWORK_ID + " = ?", new String[]{String.valueOf(id)}); // Eliminamos el deber
        DB.close(); // Cerramos la base de datos
    }

}
