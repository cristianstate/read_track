package com.fitness.myapplication6.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.fitness.myapplication6.R;
import com.fitness.myapplication6.data.model.Book;
import com.fitness.myapplication6.databinding.DialogUpdateProgressBinding;
import com.fitness.myapplication6.databinding.FragmentBookDetailBinding;
import com.fitness.myapplication6.ui.viewmodel.BookViewModel;

/**
 * Fragment responsabil pentru afișarea detaliilor unei cărți selectate,
 * precum și pentru actualizarea progresului de lectură sau ștergerea acesteia.
 */
public class BookDetailFragment extends Fragment {

    // ViewModel-ul partajat
    private BookViewModel viewModel;

    // ViewBinding pentru aspectul fragmentului
    private FragmentBookDetailBinding binding;

    // ID-ul cărții selectate primit prin argumentele fragmentului
    private int bookId;

    // Obiectul Book curent ale cărui detalii sunt afișate
    private Book currentBook;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Preluăm ID-ul cărții pasat ca argument
        if (getArguments() != null) {
            bookId = getArguments().getInt("bookId");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBookDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inițializăm ViewModel-ul
        viewModel = new ViewModelProvider(requireActivity()).get(BookViewModel.class);

        // Ajustăm marginile pentru barele sistemului
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(insets.left, 0, insets.right, insets.bottom);
            return windowInsets;
        });

        // Observăm modificările cărții cu ID-ul specificat
        viewModel.getBookById(bookId).observe(getViewLifecycleOwner(), book -> {
            if (book != null) {
                currentBook = book;
                displayBookDetails(book);
            }
        });

        // Setăm listeneri pentru butoanele de actualizare progres și ștergere
        binding.buttonUpdateProgress.setOnClickListener(v -> showUpdateProgressDialog());
        binding.buttonDelete.setOnClickListener(v -> showDeleteConfirmationDialog());
    }

    /**
     * Afișează informațiile despre carte în interfața grafică.
     *
     * @param book Obiectul carte cu datele de afișat
     */
    private void displayBookDetails(Book book) {
        binding.textViewTitle.setText(book.getTitle());
        binding.textViewAuthor.setText(book.getAuthor());
        binding.textViewCategory.setText(book.getCategory());

        String progressSummary = getString(R.string.label_pages_read, book.getCurrentPage(), book.getTotalPages());
        binding.textViewProgressSummary.setText(progressSummary);

        // Actualizăm graficul circular de progres
        binding.progressChart.setProgress(book.getCurrentPage(), book.getTotalPages());

        // Încărcăm coperta cărții folosind biblioteca Glide
        if (book.getImagePath() != null && !book.getImagePath().isEmpty()) {
            Glide.with(this).load(book.getImagePath()).placeholder(R.drawable.placeholder).into(binding.imageViewCover);
        } else {
            binding.imageViewCover.setImageResource(R.drawable.placeholder);
        }
    }

    /**
     * Afișează un dialog de alertă pentru actualizarea numărului de pagini citite.
     */
    private void showUpdateProgressDialog() {
        if (currentBook == null) return;

        DialogUpdateProgressBinding dialogBinding = DialogUpdateProgressBinding.inflate(getLayoutInflater());
        dialogBinding.editTextCurrentPage.setText(String.valueOf(currentBook.getCurrentPage()));

        new AlertDialog.Builder(requireContext())
                .setTitle(R.string.title_update_progress)
                .setView(dialogBinding.getRoot())
                .setPositiveButton(R.string.btn_update, (dialog, which) -> {
                    String input = dialogBinding.editTextCurrentPage.getText().toString();
                    if (!input.isEmpty()) {
                        int newPage = Integer.parseInt(input);
                        // Verificăm dacă numărul de pagini introduse nu depășește numărul total
                        if (newPage <= currentBook.getTotalPages()) {
                            currentBook.setCurrentPage(newPage);
                            viewModel.update(currentBook);
                        } else {
                            Toast.makeText(getContext(), R.string.msg_page_exceed_total, Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton(R.string.btn_cancel, null)
                .show();
    }

    /**
     * Afișează un dialog de confirmare pentru ștergerea cărții curente.
     */
    private void showDeleteConfirmationDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle(R.string.title_delete_book)
                .setMessage(R.string.msg_delete_confirm)
                .setPositiveButton(R.string.btn_delete, (dialog, which) -> {
                    viewModel.delete(currentBook);
                    Navigation.findNavController(requireView()).popBackStack();
                    Toast.makeText(getContext(), R.string.msg_book_deleted, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(R.string.btn_cancel, null)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Prevenim scurgerile de memorie prin anularea referinței la binding
        binding = null;
    }
}
