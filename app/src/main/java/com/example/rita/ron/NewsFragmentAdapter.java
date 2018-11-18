package com.example.rita.ron;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class NewsFragmentAdapter extends FragmentPagerAdapter {


    /**
     * Context of the app
     */
    private Context mContext;


    public NewsFragmentAdapter(Context context, FragmentManager fm) {

        super(fm);

        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Politics();
        } else if (position == 1) {
            return new Technology();
        } else {
            return new Football();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.politics);
        } else if (position == 1) {
            return mContext.getString(R.string.technology);
        } else {
            return mContext.getString(R.string.football);
        }
    }


}
