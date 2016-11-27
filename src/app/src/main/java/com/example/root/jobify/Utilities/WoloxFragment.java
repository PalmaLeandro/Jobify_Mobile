package com.example.root.jobify.Utilities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 *  Custom Fragment implementation to bind basic elements and force the use of
 *  the MVP pattern logically attaching a presenter to this Fragment.
 */
public abstract class WoloxFragment<T extends BasePresenter> extends Fragment {

    public T mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(layout(), container, false);
        mPresenter = createPresenter();
        setUi(v);
        init();
        populate();
        setListeners();
        return v;
    }

    /**
     * Returns the layout id for the inflater so the view can be populated
     */
    protected abstract int layout();

    /**
     * Loads the view elements for the fragment
     */
    protected abstract void setUi(View v);

    /**
     * Initializes any variables that the fragment needs
     */
    protected abstract void init();

    /**
     * Populates the view elements of the fragment
     */
    protected abstract void populate();

    /**
     * Sets the listeners for the views of the fragment
     */
    protected abstract void setListeners();

    /**
     * Create the presenter for this fragment
     */
    protected abstract T createPresenter();

    protected void replaceFragment(int resId, Fragment f) {
        getChildFragmentManager()
                .beginTransaction()
                .add(resId, f)
                .commit();
    }


    public void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resId) {
        Toast.makeText(getActivity(), resId, Toast.LENGTH_SHORT).show();
    }

}
