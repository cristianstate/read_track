package com.fitness.myapplication6.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.fitness.myapplication6.R;
import com.fitness.myapplication6.databinding.FragmentBookListBinding;
import com.fitness.myapplication6.ui.adapter.BookAdapter;
import com.fitness.myapplication6.ui.viewmodel.BookViewModel;

/**
 * Fragment principal care afișează lista de cărți și oferă opțiuni de căutare și adăugare.
 */
public class BookListFragment extends Fragment {

    // ViewModel pentru gestionarea datelor cărților
    private BookViewModel viewModel;

    // ViewBinding pentru acces la elementele din UI
    private FragmentBookListBinding binding;

    // Adapter pentru RecyclerView
    private BookAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBookListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obținem ViewModel-ul partajat
        viewModel = new ViewModelProvider(requireActivity()).get(BookViewModel.class);

        // Configurăm RecyclerView, butonul plutitor (FAB) și Bara de Căutare
        setupRecyclerView();
        setupFab();
        setupSearch();

        // Gestionarea marginilor din sistem (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(insets.left, 0, insets.right, insets.bottom);
            return windowInsets;
        });

        // Observăm lista de cărți și o transmitem adapterului când se modifică
        viewModel.getAllBooks().observe(getViewLifecycleOwner(), books -> {
            adapter.submitList(books);
        });
    }

    /**
     * Inițializează RecyclerView-ul și setează acțiunea la apăsarea unui element.
     */
    private void setupRecyclerView() {
        adapter = new BookAdapter(book -> {
            Bundle bundle = new Bundle();
            bundle.putInt("bookId", book.getId());
            // Navigăm către ecranul de detalii pasând ID-ul cărții
            Navigation.findNavController(requireView()).navigate(R.id.action_bookListFragment_to_bookDetailFragment, bundle);
        });
        binding.recyclerView.setAdapter(adapter);
    }

    /**
     * Configurarea butonului plutitor (FAB) pentru adăugarea unei noi cărți.
     */
    private void setupFab() {
        binding.fabAddBook.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_bookListFragment_to_addBookFragment);
        });
    }

    /**
     * Configurarea acțiunilor pentru SearchView și gestionarea filtrării în timp real.
     */
    private void setupSearch() {
        binding.searchView.setupWithSearchBar(binding.searchBar);
        binding.searchView.getEditText().setOnEditorActionListener((v, actionId, event) -> {
            binding.searchBar.setText(binding.searchView.getText());
            viewModel.setSearchQuery(binding.searchView.getText().toString());
            binding.searchView.hide();
            return false;
        });

        // Listener pentru filtrare în timp real la introducerea textului
        binding.searchView.getEditText().addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Actualizăm interogarea de căutare din ViewModel
                viewModel.setSearchQuery(s.toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Eliberăm referința de binding
        binding = null;
    }
}
