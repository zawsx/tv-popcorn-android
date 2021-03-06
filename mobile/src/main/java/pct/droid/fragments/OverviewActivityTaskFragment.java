package pct.droid.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import java.util.ArrayList;

import pct.droid.base.providers.media.MediaProvider;
import pct.droid.base.providers.media.YTSProvider;
import pct.droid.base.providers.media.types.Media;

public class OverviewActivityTaskFragment extends Fragment implements MediaProvider.Callback {

    public final static String TAG = "pct.droid.overviewactivitytaskfragment";

    private MediaProvider mProvider = new YTSProvider();
    private MediaProvider.Callback mCallback;
    private ArrayList<Media> mItems;
    private int mPage = 1;
    private MediaProvider.Filters mFilters = new MediaProvider.Filters();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (MediaProvider.Callback) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    public void setProvider(MediaProvider provider) {
        mProvider = provider;
    }

    public MediaProvider getProvider() {
        return mProvider;
    }

    public ArrayList<Media> getExistingItems() {
        return mItems;
    }

    public int getCurrentPage() {
        return mPage;
    }

    public void setCurrentPage(int page) {
        mPage = page;
    }

    public MediaProvider.Filters getFilters() {
        return mFilters;
    }

    public void setFilters(MediaProvider.Filters filters) {
        mFilters = filters;
    }

    @Override
    public void onSuccess(ArrayList<Media> items) {
        mPage++;
        mItems = items;
        if (mCallback != null) mCallback.onSuccess(items);
    }

    @Override
    public void onFailure(Exception e) {
        if (mCallback != null) mCallback.onFailure(e);
    }
}
