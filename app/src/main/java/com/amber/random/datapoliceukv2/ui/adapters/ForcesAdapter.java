package com.amber.random.datapoliceukv2.ui.adapters;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amber.random.datapoliceukv2.R;
import com.amber.random.datapoliceukv2.databinding.ForceItemRowLayoutBinding;
import com.amber.random.datapoliceukv2.model.force.ForceItem;
import com.amber.random.datapoliceukv2.ui.fragments.ForcesListFragment;

import java.lang.ref.WeakReference;
import java.util.List;

public class ForcesAdapter extends RecyclerView.Adapter<ForcesAdapter.ForceViewHolder> {

    private final WeakReference<ForcesListFragment> mForcesListFragment;
    private List<ForceItem> mForces;
    private MutableLiveData<ForceItem> mSelectedItem = new MutableLiveData<>();

    public ForcesAdapter(ForcesListFragment forcesListFragment) {
        super();
        mForcesListFragment = new WeakReference(forcesListFragment);
    }

    public LiveData<ForceItem> getLastSelectedItem() {
        return mSelectedItem;
    }

    public void setForces(List<ForceItem> forces) {
        mForces = forces;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ForceViewHolder holder, int position) {
        holder.bind(mForces.get(position));
    }

    @Override
    public ForceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ForceItemRowLayoutBinding binding = DataBindingUtil.
                inflate(inflater, R.layout.force_item_row_layout, parent, false);
        return new ForceViewHolder(mSelectedItem, binding);
    }

    @Override
    public int getItemCount() {
        return mForces.size();
    }

    public static class ForceViewHolder extends BaseController {
        private final WeakReference<MutableLiveData<ForceItem>> mSelectedItemWR;
        private ForceItemRowLayoutBinding mForceItemRowLayoutBinding;

        public ForceViewHolder(MutableLiveData<ForceItem> selectedItem, ForceItemRowLayoutBinding forceItemRowLayoutBinding) {
            super(forceItemRowLayoutBinding.getRoot());
            mSelectedItemWR = new WeakReference(selectedItem);
            mForceItemRowLayoutBinding = forceItemRowLayoutBinding;
            mForceItemRowLayoutBinding.setController(this);
        }

        void bind(ForceItem forceItem) {
            mForceItemRowLayoutBinding.setForceItem(forceItem);
            mForceItemRowLayoutBinding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            MutableLiveData<ForceItem> selectedItem = mSelectedItemWR.get();
            if (null != selectedItem)
                selectedItem.setValue(mForceItemRowLayoutBinding.getForceItem());
        }
    }
}
