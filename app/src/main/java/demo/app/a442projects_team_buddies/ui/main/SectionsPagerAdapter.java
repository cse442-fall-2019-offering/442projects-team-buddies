package demo.app.a442projects_team_buddies.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import demo.app.a442projects_team_buddies.courseToDoListFragment;
import demo.app.a442projects_team_buddies.R;
import demo.app.a442projects_team_buddies.StudentListFragment;
import demo.app.a442projects_team_buddies.courseToDoListFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public String Id;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm,String Id) {
        super(fm);
        this.Id= Id;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment= null;
        Bundle bundle= new Bundle();
        bundle.putString("Course", Id);
        switch (position){
            case 0:




                fragment= new StudentListFragment();
                fragment.setArguments(bundle);
                break;
            case 1:

                fragment= new courseToDoListFragment();
                fragment.setArguments(bundle);
                break;
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}