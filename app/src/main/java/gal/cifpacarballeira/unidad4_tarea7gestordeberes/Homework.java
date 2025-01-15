package gal.cifpacarballeira.unidad4_tarea7gestordeberes;

import android.os.Parcel;
import android.os.Parcelable;

public class Homework implements Parcelable {

    private String subject; // Asignatura (PMDM, AD, etc.)
    private String description; // Descripción del deber
    private String dueDate; // Fecha de entrega en formato dd/MM/yyyy
    private boolean isCompleted; // Estado del deber

    // Constructor
    public Homework(String subject, String description, String dueDate, boolean isCompleted) {
        this.subject = subject;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    // Getters y Setters
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // Implementación de Parcelable
    protected Homework(Parcel in) {
        subject = in.readString();
        description = in.readString();
        dueDate = in.readString();
        isCompleted = in.readByte() != 0;
    }

    public static final Creator<Homework> CREATOR = new Creator<Homework>() {
        @Override
        public Homework createFromParcel(Parcel in) {
            return new Homework(in);
        }

        @Override
        public Homework[] newArray(int size) {
            return new Homework[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(subject);
        dest.writeString(description);
        dest.writeString(dueDate);
        dest.writeByte((byte) (isCompleted ? 1 : 0));
    }
}

