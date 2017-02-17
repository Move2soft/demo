package com.fireball.demo.model;


import com.fireball.demo.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by UV on 29-Nov-16.
 */
public class NavigationList {
    public static NavigationList _naviNavigationList;
    public static List<Integer> imageList;
    public static List<String> titleList;
    public static String location = "Surat,Magob";
    public static String cartCount;

    public static NavigationList getInstance() {
        if (_naviNavigationList == null) {
            _naviNavigationList = new NavigationList();
        }
        imageList = Arrays.asList(R.drawable.ic_company, R.drawable.ic_company
                , R.drawable.ic_company, R.drawable.ic_company, R.drawable.ic_company,
                R.drawable.ic_company, R.drawable.ic_company, R.drawable.ic_company,
                R.drawable.ic_company, R.drawable.ic_company);
        titleList = Arrays.asList(location, "My Account", "My Cart", "My Order", "Offer Zone",
                "Contact Us", "Share Us", "Rate Us", "About Us", "Logout");
        return _naviNavigationList;
    }

    public static List<Integer> getImageList() {
        return imageList;
    }

    public static List<String> getTitleList() {
        return titleList;
    }

    public static void changeLocation(String mylocation) {
        location = mylocation;
        titleList.set(0, mylocation);
    }
}
