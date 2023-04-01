package com.example.dcardhomework.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dcardhomework.R;
import com.example.dcardhomework.data.Items;
import com.example.dcardhomework.data.Repo;
import com.example.dcardhomework.databinding.FragmentHomeBinding;
import com.example.dcardhomework.viewmodel.RepoViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RepoAdapter.ClickedListeners {

    private FragmentHomeBinding binding;
    private RepoViewModel repoViewModel;
    private RepoAdapter repoAdapter;
    private final List<Items> itemsList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.imgSearch.setOnClickListener(v -> doSearch());

        repoAdapter = new RepoAdapter(itemsList, getContext(), this);
        binding.rvRepo.setHasFixedSize(true);
        binding.rvRepo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvRepo.setAdapter(repoAdapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        repoViewModel = new ViewModelProvider(requireActivity()).get(RepoViewModel.class);

        binding.setViewModel(repoViewModel);

        repoViewModel.getItemsListLive().observe(requireActivity(), repoApiResponse -> {
            repoViewModel.isLoading.set(false);
            int code = repoApiResponse.code;
            Repo data = repoApiResponse.body;
            String msg = repoApiResponse.errorMessage;
            if (repoApiResponse.isSuccessful()) {
                assert repoApiResponse.body != null;
                repoAdapter.swapItems(repoApiResponse.body.getItems());
                new CountDownTimer(1200, 1000){
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        binding.viewBackground.setVisibility(View.GONE);
                    }
                }.start();

            } else {
                binding.viewBackground.setVisibility(View.VISIBLE);
                repoViewModel.isError.set(true);
            }
        });
    }

    // 搜尋
    private void doSearch() {
        String query = binding.etSearchRepo.getText().toString();
        repoViewModel.searchRepo(query);
        binding.viewBackground.setVisibility(View.VISIBLE);
        repoViewModel.isError.set(false);
        repoViewModel.isLoading.set(true);
        dismissKeyboard();
        binding.etSearchRepo.getText().clear();
    }

    // 關閉小鍵盤
    private void dismissKeyboard() {
        View view = requireActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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