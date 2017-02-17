package com.fireball.demo.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fireball.demo.R;
import com.fireball.demo.adapter.NavigationDrawerAdapter;
import com.fireball.demo.utils.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @Bind(R.id.drawerListView)
    ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private View listHeaderView;
    private LinearLayoutManager linearlayoutManager;
    private GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bindToolBar();
        initDrawer();


    }

    public void initDrawer() {
        LayoutInflater inflater = getLayoutInflater();
        listHeaderView = inflater.inflate(R.layout.nav_header, null, false);
//        tvMobileNo = (CeliasMediumTextview) listHeaderView.findViewById(R.id.tvMobileNo);

        drawerListView.addHeaderView(listHeaderView);
        drawerListView.setAdapter(new NavigationDrawerAdapter(this));
        ivDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawer_layout, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                selectItemFragment(position);
            }
        });
    }

//    private void selectItemFragment(int position) {
//
//        switch (position) {
//            case 1:
//                Intent searchLocation = new Intent(this, SearchLocation.class);
//                startActivity(searchLocation);
//                break;
//
//            case 3:
//                Intent cart = new Intent(this, CartActivity.class);
//                startActivity(cart);
//                break;
//
//            case 7:
//                String shareBody = "https://play.google.com/store/apps/details?id=" + getPackageName();
//                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//                sharingIntent.setType("text/plain");
//                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Share Here");
//                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
//                break;
//
//            case 8:
//                Uri uri = Uri.parse("market://details?id=" + getPackageName());
//                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
//                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
//                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
//                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//                try {
//                    startActivity(goToMarket);
//                } catch (ActivityNotFoundException e) {
//                    startActivity(new Intent(Intent.ACTION_VIEW,
//                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
//                }
//                break;
////            case 2:
////                Intent myProfile = new Intent(this, ProfileActivity.class);
////                startActivity(myProfile);
////                Constants.IS_MYPROFILE = true;
////                break;
////            case 3:
////                Intent wanToMeetUp = new Intent(this, HomeActivity.class);
////                startActivity(wanToMeetUp);
////                break;
////            case 4:
////                Intent nearBy = new Intent(this, NearByActivity.class);
////                startActivity(nearBy);
////                break;
////            case 5:
////                Intent chat = new Intent(this, ChatListActivity.class);
////                startActivity(chat);
////                break;
////            case 6:
////                Intent friends = new Intent(this, FriendsActivity.class);
////                startActivity(friends);
////                break;
////            case 7:
////                Intent shareMoment = new Intent(this, ShareMoment.class);
////                startActivity(shareMoment);
////                break;
////            case 8:
////                Intent setting = new Intent(this, ApplicationSettingActivity.class);
////                startActivity(setting);
////                break;
////            case 9:
////                Intent notification = new Intent(this, NotificationActivity.class);
////                startActivity(notification);
////                break;
////            case 10:
////                confirmLogout();
////                break;
//        }
//
//
//        drawerListView.setItemChecked(position, true);
//
////        drawerLayout.closeDrawer(drawerListView);
//    }

}
