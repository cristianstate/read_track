package com.fitness.myapplication6.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.fitness.myapplication6.R;
import com.fitness.myapplication6.data.model.Book;
import com.fitness.myapplication6.databinding.FragmentAddBookBinding;
import com.fitness.myapplication6.ui.viewmodel.BookViewModel;

/**
 * Fragment responsabil pentru adăugarea unei noi cărți în baza de date.
 */
public class AddBookFragment extends Fragment {

    // ViewModel-ul partajat pentru gestionarea datelor despre cărți
    private BookViewModel viewModel;

    // Binding pentru accesul la componentele din layout
    private FragmentAddBookBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflăm layout-ul fragmentului
        binding = FragmentAddBookBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obținem instanța ViewModel-ului partajat la nivelul activității
        viewModel = new ViewModelProvider(requireActivity()).get(BookViewModel.class);

        // Tratăm marginea inferioară și laterală pentru bara de navigare a sistemului
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(insets.left, 0, insets.right, insets.bottom);
            return windowInsets;
        });

        // Configurăm acțiunile butoanelor "Salvează" și "Anulează"
        binding.buttonSave.setOnClickListener(v -> saveBook());
        binding.buttonCancel.setOnClickListener(v -> Navigation.findNavController(v).popBackStack());
    }

    /**
     * Validează datele introduse în formular și salvează noua carte prin intermediul ViewModel-ului.
     */
    private void saveBook() {
        String title = binding.editTextTitle.getText().toString().trim();
        String author = binding.editTextAuthor.getText().toString().trim();
        String category = binding.editTextCategory.getText().toString().trim();
        String chaptersStr = binding.editTextChapters.getText().toString().trim();
        String pagesStr = binding.editTextPages.getText().toString().trim();

        // Verificăm dacă toate câmpurile sunt completate
        if (title.isEmpty() || author.isEmpty() || category.isEmpty() || chaptersStr.isEmpty() || pagesStr.isEmpty()) {
            Toast.makeText(getContext(), R.string.msg_fill_all_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        int chapters = Integer.parseInt(chaptersStr);
        int pages = Integer.parseInt(pagesStr);

        // Creăm obiectul carte și îl salvăm
        Book book = new Book(title, author, category, chapters, pages, 0, "");
        viewModel.insert(book);

        Toast.makeText(getContext(), R.string.msg_book_saved, Toast.LENGTH_SHORT).show();
        
        // Revenim la ecranul anterior
        Navigation.findNavController(requireView()).popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Curățăm referința de binding pentru a preveni scurgerile de memorie (memory leaks)
        binding = null;
    }
}
