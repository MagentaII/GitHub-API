package com.example.dcardhomework.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dcardhomework.R;
import com.example.dcardhomework.data.Items;
import com.example.dcardhomework.databinding.CellRepoBinding;

import java.util.List;
import java.util.Objects;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    private final List<Items> itemsList;
    private final Context context;
    private final ClickedListeners clickedListeners;

    public RepoAdapter(List<Items> itemsList, Context context, ClickedListeners clickedListeners) {
        this.itemsList = itemsList;
        this.context = context;
        this.clickedListeners = clickedListeners;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CellRepoBinding binding = CellRepoBinding.inflate(layoutInflater, parent, false);
        return new RepoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        Items items = itemsList.get(position);
        holder.bind(items);
        Glide.with(context).load(items.getOwner().getAvatar_url())
                .placeholder(R.drawable.ic_baseline_search_24_2)
                .error(R.drawable.ic_account_circle_48)
                .into(holder.binding.imgRepo);
        holder.binding.cardView.setOnClickListener(v -> clickedListeners.onCardClicked(items));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    static class RepoViewHolder extends RecyclerView.ViewHolder {
        private final CellRepoBinding binding;

        public RepoViewHolder(CellRepoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void bind(Items items){
            binding.setItems(items);
            binding.executePendingBindings(); //讓binding立即更新畫面，所以每次onBindViewHolder後都會立刻更新，以免快速滑動等情況導致UI顯示錯誤。
        }
    }


    public interface ClickedListeners {
        void onCardClicked(Items items);
    }


    public void clearItems() {
        int size = this.itemsList.size();
        this.itemsList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void swapItems(List<Items> newItems) {
        if (newItems == null) {
            int oldSize = this.itemsList.size();
            this.itemsList.clear();
            notifyItemRangeRemoved(0, oldSize);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new RepoDiffCallback(this.itemsList, newItems));
            this.itemsList.clear();
            this.itemsList.addAll(newItems);
            result.dispatchUpdatesTo(this);
        }
    }

    private static class RepoDiffCallback extends DiffUtil.Callback {

        private final List<Items> mOldList;
        private final List<Items> mNewList;

        RepoDiffCallback(List<Items> oldList, List<Items> newList) {
            this.mOldList = oldList;
            this.mNewList = newList;
        }

        @Override
        public int getOldListSize() {
            return mOldList != null ? mOldList.size() : 0;
        }

        @Override
        public int getNewListSize() {
            return mNewList != null ? mNewList.size() : 0;
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            int oldId = mOldList.get(oldItemPosition).id;
            int newId = mNewList.get(newItemPosition).id;
            return Objects.equals(oldId, newId);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Items olditems = mOldList.get(oldItemPosition);
            Items newitems = mNewList.get(newItemPosition);
            return Objects.equals(olditems, newitems);
        }
    }
}
