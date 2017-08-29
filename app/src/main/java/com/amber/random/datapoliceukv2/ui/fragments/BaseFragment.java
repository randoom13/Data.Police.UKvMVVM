package com.amber.random.datapoliceukv2.ui.fragments;

import android.arch.lifecycle.LifecycleFragment;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.Snackbar;

import com.amber.random.datapoliceukv2.viewmodel.BaseViewModel;

import javax.inject.Inject;

public abstract class BaseFragment<B extends ViewDataBinding, T extends BaseViewModel>
        extends LifecycleFragment {

    @Inject
    protected T mViewModel;
    protected B mBinding;

    protected final void bindView(int layout) {
        if (mViewModel == null)
            throw new IllegalStateException("mViewModel must not be null and should be injected via fragmentComponent.inject(this);");
        mBinding = DataBindingUtil.setContentView(this.getActivity(), layout);
    }

    @Override
    public void onStop() {
        mViewModel.clearSubscriptions();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        super.onDestroyView();
    }

    protected void error(Throwable e) {
        Snackbar snackbar = Snackbar.make(mBinding.getRoot(), e.getMessage(), Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }
}
