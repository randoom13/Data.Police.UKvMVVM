package com.amber.random.datapoliceukv2.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.amber.random.datapoliceukv2.App;
import com.amber.random.datapoliceukv2.R;
import com.amber.random.datapoliceukv2.databinding.ForcesListFragmentBinding;
import com.amber.random.datapoliceukv2.model.force.ForceItem;
import com.amber.random.datapoliceukv2.ui.adapters.ForcesAdapter;
import com.amber.random.datapoliceukv2.viewmodel.ForcesListViewModel;
import com.amber.random.datapoliceukv2.viewmodel.ServiceResult;

import java.util.List;

public class ForcesListFragment extends BaseFragment<ForcesListFragmentBinding, ForcesListViewModel> {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        mViewModel.getData().observe(this, this::loadData);
        bindView(R.layout.forces_list_fragment);
        ForcesAdapter forcesAdapter = new ForcesAdapter();
        mBinding.forces.setAdapter(forcesAdapter);
        forcesAdapter.getLastSelectedItem().observe(this, this::onClick);
             setHasOptionsMenu(true);
        Toolbar toolbar = mBinding.toolbar;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBinding.setIsLoading(true);
        mViewModel.loadData();
    }


    private void onClick(ForceItem forceItem) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadData(ServiceResult<List<ForceItem>> res) {
        mBinding.setIsLoading(false);
        if (res.isSuccessful)
            ((ForcesAdapter) mBinding.forces.getAdapter()).setItems(res.mResult);
        else
            error(res.mException);
    }

    @Override
    protected void error(Throwable e) {
        super.error(e);
        mBinding.setIsLoading(false);
    }
}
