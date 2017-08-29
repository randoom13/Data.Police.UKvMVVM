package com.amber.random.datapoliceukv2.ui.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.amber.random.datapoliceukv2.App;
import com.amber.random.datapoliceukv2.R;
import com.amber.random.datapoliceukv2.databinding.ForcesListFragmentBinding;
import com.amber.random.datapoliceukv2.model.force.ForceItem;
import com.amber.random.datapoliceukv2.ui.adapters.ForcesAdapter;
import com.amber.random.datapoliceukv2.viewmodel.ForcesListViewModel;
import com.amber.random.datapoliceukv2.viewmodel.ServiceResult;

import java.util.List;

public class ForcesListFragment extends BaseFragment<ForcesListFragmentBinding, ForcesListViewModel>
        implements SearchView.OnCloseListener, SearchView.OnQueryTextListener,
        MenuItemCompat.OnActionExpandListener, AdapterView.OnItemClickListener {
    private static final String sStateQuery = "sq";
    private ActionBarDrawerToggle toggle = null;
    private ForcesAdapter mForcesAdapter;
    private SearchView mSearchView;
    private CharSequence mInitialQuery;

    //region searchView interfaces implementation

    @Override
    public boolean onQueryTextSubmit(String query) {
        mViewModel.loadData(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onClose() {
        mViewModel.loadData("");
        return true;
    }

    //endregion searchView interfaces implementation

    //region OnActionExpandListener implementation

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        mViewModel.loadData("");
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    //endregion OnActionExpandListener implementation

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        mViewModel.getData().observe(this, this::loadData);
        bindView(R.layout.forces_list_fragment);
        mBinding.setIsLoading(true);
        mForcesAdapter = new ForcesAdapter(this);
        mForcesAdapter.getLastSelectedItem().observe(this, this::onClick);
        mBinding.forces.setAdapter(mForcesAdapter);
        if (null != savedInstanceState) {
            mInitialQuery = savedInstanceState.getCharSequence(sStateQuery);
        }
        initializeDrawer();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        Toolbar toolbar = mBinding.toolbar;
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setTitle(R.string.forces_list_title);
        setHasOptionsMenu(true);
    }

    private void initializeDrawer() {
        mBinding.drawer.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.drawer_row, getResources().getStringArray(R.array.drawer_rows)));
        mBinding.drawer.setOnItemClickListener(this);
        toggle = new ActionBarDrawerToggle(this.getActivity(), mBinding.drawerLayout,
                R.string.drawer_open, R.string.drawer_close);
        mBinding.drawerLayout.addDrawerListener(toggle);
    }

    @Override
    public void onItemClick(AdapterView<?> listView, View row,
                            int position, long id) {
        mBinding.drawerLayout.closeDrawers();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBinding.setIsLoading(true);
        String filter = (null != mSearchView && !mSearchView.isIconified()) ? mSearchView.getQuery().toString() : "";
        mViewModel.loadData(filter);
    }

    private void onClick(ForceItem forceItem) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
        configureSearchView(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search) {
            return true;
        }
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void loadData(ServiceResult<List<ForceItem>> res) {
        mBinding.setIsLoading(false);
        if (res.isSuccessful)
            mForcesAdapter.setForces(res.result);
        else
            error(res.exception);
    }

    @Override
    protected void error(Throwable e) {
        super.error(e);
        mBinding.setIsLoading(false);
    }

    private void configureSearchView(Menu menu) {
        MenuItem search = menu.findItem(R.id.search);
        mSearchView = (SearchView) search.getActionView();
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnCloseListener(this);
        mSearchView.setSubmitButtonEnabled(false);
        mSearchView.setIconifiedByDefault(true);
        MenuItemCompat.setOnActionExpandListener(search, this);

        if (!TextUtils.isEmpty(mInitialQuery)) {
            mSearchView.setIconified(false);
            MenuItemCompat.expandActionView(search);
            mSearchView.setQuery(mInitialQuery, true);
        }
    }
}
