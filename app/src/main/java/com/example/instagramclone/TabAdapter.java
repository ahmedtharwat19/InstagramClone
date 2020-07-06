package com.example.instagramclone;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {

//   private Context myContext;
    int totalTabs;

    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }


    @NonNull
    @Override
    public Fragment getItem(int tabPosition) {
        switch (tabPosition){
            case 0:
//                ProfileTab profileTab = new ProfileTab();
                return new ProfileTab();
//                break;
            case 1:
//                UserTab userTab = new UserTab();
                return new UserTab();
//                break;
            case 2:
/*                SharePictureTab sharePictureTab = new SharePictureTab();
                return sharePictureTab;*/
//                break;
                return new SharePictureTab();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        int num;
        num = new Integer(3);
        return num;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Profile";
            case 1:
                return "Users";
            case 2:
                return "Share Picture";
            default:
                return null;
        }

    }
}
