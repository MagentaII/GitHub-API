package com.example.dcardhomework.ui.screens.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dcardhomework.R;
import com.example.dcardhomework.data.models.Items;
import com.example.dcardhomework.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RepoAdapter.ClickedListeners {

    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private RepoAdapter repoAdapter;
    private final List<Items> itemsList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize DataBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        // Initialize ViewModel
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Set the ViewModel in the binding
        binding.setViewModel(homeViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        // Initialize the RecyclerView and Adapter
        repoAdapter = new RepoAdapter(itemsList, getContext(), this);
        binding.rvRepo.setHasFixedSize(true);
        binding.rvRepo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvRepo.setAdapter(repoAdapter);

        // Observe the UI state from ViewModel
        homeViewModel.uiState.observe(getViewLifecycleOwner(), homeUiState -> {
            Log.d(
                    TAG,
                    "isLoading: " + homeUiState.isLoading() +
                            " Items: " + homeUiState.getItems() +
                            " isError: " + homeUiState.isError()
            );

            binding.viewBackground.setVisibility(homeUiState.isLoading() ? View.VISIBLE : View.GONE);

            if (homeUiState.getItems() != null) {
                repoAdapter.swapItems(homeUiState.getItems().getItems());
            } else {
                repoAdapter.swapItems(null);
            }

            binding.viewBackground.setVisibility(homeUiState.isError() ? View.VISIBLE : View.GONE);
        });

        binding.imgSearch.setOnClickListener(v -> doSearch());

        return binding.getRoot();
    }

    // 搜尋
    private void doSearch() {
        String query = binding.etSearchRepo.getText().toString();
        homeViewModel.searchRepo(query);
        dismissKeyboard();
        binding.etSearchRepo.getText().clear();
    }

    // 關閉小鍵盤
    private void dismissKeyboard() {
        View view = requireActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    // 點擊cardView 切換頁面至詳細內容 將資料透過序列化的方式打包傳送過去
    @Override
    public void onCardClicked(Items items) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("repo", items);
        getParentFragmentManager().setFragmentResult("requestRepo", bundle);
        NavHostFragment.findNavController(HomeFragment.this)
                .navigate(R.id.action_homeFragment_to_detailFragment2);
    }
}