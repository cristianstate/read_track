package com.fitness.myapplication6.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.fitness.myapplication6.data.model.Book;
import com.fitness.myapplication6.databinding.ItemBookBinding;

/**
 * Adapter pentru RecyclerView responsabil cu afișarea listei de cărți.
 * Utilizează {@link ListAdapter} și {@link DiffUtil} pentru actualizări eficiente ale interfeței grafice.
 */
public class BookAdapter extends ListAdapter<Book, BookAdapter.BookViewHolder> {

    // Listener pentru gestionarea evenimentelor de apăsare pe un element din listă
    private final OnItemClickListener listener;

    /**
     * Interfață pentru tratarea clicurilor pe elementele listei.
     */
    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    /**
     * Constructor pentru BookAdapter.
     *
     * @param listener Listener-ul pentru clicuri pe elemente
     */
    public BookAdapter(OnItemClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    /**
     * Callback pentru optimizarea performanței RecyclerView prin compararea elementelor vechi și noi.
     */
    private static final DiffUtil.ItemCallback<Book> DIFF_CALLBACK = new DiffUtil.ItemCallback<Book>() {
        @Override
        public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            // Verifică dacă elementele au același ID
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            // Verifică dacă conținutul afișat s-a modificat
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getAuthor().equals(newItem.getAuthor());
        }
    };

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflăm layout-ul pentru un element din listă prin ViewBinding
        ItemBookBinding binding = ItemBookBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        // Conectăm datele cărții curente la ViewHolder
        holder.bind(getItem(position));
    }

    /**
     * ViewHolder ce conține referințe către elementele vizuale din layout-ul unei cărți.
     */
    class BookViewHolder extends RecyclerView.ViewHolder {
        private final ItemBookBinding binding;

        public BookViewHolder(ItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            // Setează acțiunea de clic pe întreaga înregistrare
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }

        /**
         * Populează elementele vizuale cu informațiile din obiectul Book.
         *
         * @param book Obiectul carte
         */
        public void bind(Book book) {
            binding.textViewTitle.setText(book.getTitle());
            binding.textViewAuthor.setText(book.getAuthor());
        }
    }
}
