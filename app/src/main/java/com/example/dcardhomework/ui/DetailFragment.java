package com.example.dcardhomework.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.dcardhomework.R;
import com.example.dcardhomework.data.Items;
import com.example.dcardhomework.viewmodel.RepoViewModel;

public class DetailFragment extends Fragment {

    private ImageView imgRepo;
    private TextView tvName, tvDes, tvStart, tvWatch, tvFork, tvLanguages, tvOwner;
    private Button btnToWeb;
    private Items items;
    private RepoViewModel repoViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeVariables(view);

        getParentFragmentManager().setFragmentResultListener("requestRepo", this, (requestKey, result) -> {
            items = result.getParcelable("repo");
            repoViewModel.getSingleRepo(items.getOwner().getLogin(), items.getName());
            repoViewModel.getSingleRepoLive().observe(requireActivity(), singleRepo -> tvWatch.setText(String.valueOf(singleRepo.getSubscribers_count())));
            Glide.with(requireActivity()).load(items.getOwner().getAvatar_url()).into(imgRepo);
            tvName.setText(items.getName());
            if (items.getDescription() == null) {
                tvDes.setText("－");
            } else {
                tvDes.setText(items.getDescription());
            }
            tvStart.setText(String.valueOf(items.getStargazers_count()));
            tvFork.setText(String.valueOf(items.getForks_count()));
            if (items.getLanguage() == null) {
                tvLanguages.setText("－");
            } else {
                tvLanguages.setText(items.getLanguage());
            }
            tvOwner.setText(items.getOwner().getLogin());
        });

        btnToWeb.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://github.com/" + items.getFull_name());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            btnToWeb.getContext().startActivity(intent);
        });
    }

    private void initializeVariables(View view) {
        imgRepo = view.findViewById(R.id.img_repo);
        tvName = view.findViewById(R.id.tv_repo_name);
        tvDes = view.findViewById(R.id.tv_repo_des);
        tvStart = view.findViewById(R.id.tv_start);
        tvWatch = view.findViewById(R.id.tv_watch);
        tvFork = view.findViewById(R.id.tv_fork);
        tvLanguages = view.findViewById(R.id.tv_languages);
        tvOwner = view.findViewById(R.id.tv_owner);
        btnToWeb = view.findViewById(R.id.btn_to_web);
        repoViewModel = new ViewModelProvider(requireActivity()).get(RepoViewModel.class);
    }
}