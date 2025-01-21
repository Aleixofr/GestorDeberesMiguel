package gal.cifpacarballeira.unidad4_tarea7gestordeberes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
        //DB.close();
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
        values.put(HOMEWORK_DESCRIPTION, homework.getDescription());
        values.put(HOMEWORK_DUE_DATE, homework.getDueDate());
        values.put(HOMEWORK_IS_COMPLETED, homework.isCompleted() ? 1 : 0); // Convertimos booleano a 1 o 0

        // Insertamos el deber en la base de datos y devolvemos el ID generado
        long id = DB.insert(TABLE_HOMEWORK, null, values);
        //DB.close(); // Cerramos la base de datos
        return id;
    }

    // Obetener todas las tareas
    public List<Homework> getHomeworkList() {
        List<Homework> homeworkList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HOMEWORK, null, null, null, null, null, HOMEWORK_DUE_DATE);

        if (cursor != null) { // Asegurarnos de que el cursor no sea nulo
            try {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndexOrThrow(HOMEWORK_ID));
                        String subject = cursor.getString(cursor.getColumnIndexOrThrow(HOMEWORK_SUBJECT));
                        String description = cursor.getString(cursor.getColumnIndexOrThrow(HOMEWORK_DESCRIPTION));
                        String dueDate = cursor.getString(cursor.getColumnIndexOrThrow(HOMEWORK_DUE_DATE));
                        boolean isCompleted = cursor.getInt(cursor.getColumnIndexOrThrow(HOMEWORK_IS_COMPLETED)) == 1;

                        homeworkList.add(new Homework(subject, description, dueDate, isCompleted));
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close(); // Cierra el cursor después de usarlo
            }
        }

        //db.close();

        return homeworkList;
    }

    // Obtener una tarea por id
    public Homework getHomework(int id) {

        SQLiteDatabase DB = this.getReadableDatabase();

        Homework homework =  null;

        Cursor cursor = DB.query(TABLE_HOMEWORK, null, HOMEWORK_ID + "= ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            String subject = cursor.getString(cursor.getColumnIndexOrThrow(HOMEWORK_SUBJECT));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(HOMEWORK_DESCRIPTION));
            String dueDate = cursor.getString(cursor.getColumnIndexOrThrow(HOMEWORK_DUE_DATE));
            boolean isCompleted = cursor.getInt(cursor.getColumnIndexOrThrow(HOMEWORK_IS_COMPLETED)) == 1;

            homework = new Homework(subject, description, dueDate, isCompleted);

            cursor.close();
        }
        //DB.close();

        return homework;
    }

    // Actualizar una tarea
    public void updateHomework(Homework homework, int position) {

        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(HOMEWORK_SUBJECT, homework.getSubject());
        values.put(HOMEWORK_DESCRIPTION, homework.getDescription());
        values.put(HOMEWORK_DUE_DATE, homework.getDueDate());
        values.put(HOMEWORK_IS_COMPLETED, homework.isCompleted() ? 1 : 0);

        DB.update(TABLE_HOMEWORK, values, HOMEWORK_ID + " = ?", new String[]{String.valueOf(position)});
    }


    //Borrar una tarea
    public void deleteHomework(int id) {
        SQLiteDatabase DB = this.getWritableDatabase(); // Obtenemos la base de datos en modo escritura
        DB.delete(TABLE_HOMEWORK, HOMEWORK_ID + " = ?", new String[]{String.valueOf(id)}); // Eliminamos el deber
        //DB.close(); // Cerramos la base de datos
    }

}
