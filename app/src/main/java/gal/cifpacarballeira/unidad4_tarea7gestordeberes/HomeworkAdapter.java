package gal.cifpacarballeira.unidad4_tarea7gestordeberes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder> {

    private final List<Homework> homeworkList;
    private final OnHomeworkClickListener listener;

    // Constructor
    public HomeworkAdapter(List<Homework> homeworkList, OnHomeworkClickListener listener) {
        this.homeworkList = homeworkList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homework, parent, false);
        return new HomeworkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeworkViewHolder holder, int position) {
        Homework homework = homeworkList.get(position);

        holder.subjectTextView.setText(homework.getSubject());
        holder.descriptionTextView.setText(homework.getDescription());
        holder.dueDateTextView.setText(homework.getDueDate());
        holder.statusTextView.setText(homework.isCompleted() ? "Completado" : "Pendiente");

        holder.itemView.setOnClickListener(v -> listener.onHomeworkClick(homework));

    }

    @Override
    public int getItemCount() {
        return homeworkList.size();
    }

    // ViewHolder
    public static class HomeworkViewHolder extends RecyclerView.ViewHolder {

        TextView subjectTextView;
        TextView descriptionTextView;
        TextView dueDateTextView;
        TextView statusTextView;

        public HomeworkViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectTextView = itemView.findViewById(R.id.subjectTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            dueDateTextView = itemView.findViewById(R.id.dueDateTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
        }
    }

    // Interfaz para manejar clics
    public interface OnHomeworkClickListener {
        void onHomeworkClick(Homework homework);
    }
}

