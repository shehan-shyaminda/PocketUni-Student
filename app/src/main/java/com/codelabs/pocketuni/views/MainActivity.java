package com.codelabs.pocketuni.views;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.codelabs.pocketuni.R;
import com.codelabs.pocketuni.ui.AnnouncementsFragment;
import com.codelabs.pocketuni.ui.HomeFragment;
import com.codelabs.pocketuni.ui.NoticesFragment;
import com.codelabs.pocketuni.ui.ProfileFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView nvDrawer;
    private AnimatedBottomBar bottomBar;
    private ImageView icMenu, btnCalender;
    private TextView fragmentTitle;
    private ConstraintLayout appbarLayout;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBar = findViewById(R.id.bottomMenu);
        fragmentTitle = findViewById(R.id.fragmentTitle);
        icMenu = findViewById(R.id.ic_menu);
        appbarLayout = findViewById(R.id.layout_appbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
        bottomBar.selectTabById(R.id.home,true);
        fragmentTitle.setText("My Events");

        SideDrawerInitializing();

        icMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDrawerPosition();
            }
        });

        bottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab tab, int i1, @NonNull AnimatedBottomBar.Tab tab1) {
                Fragment fragment = null;
                String title = "";
                int color = getResources().getColor(R.color.white);
                switch (tab1.getId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        title = "My Events";
                        color = getResources().getColor(R.color.white);
                        break;
                    case R.id.notices:
                        fragment = new NoticesFragment();
                        title = "Batch Notices";
                        color = getResources().getColor(R.color.white);
                        break;
                    case R.id.announcements:
                        fragment = new AnnouncementsFragment();
                        title = "Public Notices";
                        color = getResources().getColor(R.color.white);
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        title = "My Profile";
                        color = getResources().getColor(R.color.secondary_blue);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                fragmentTitle.setText(title);
                appbarLayout.setBackgroundColor(color);
            }

            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {

            }
        });
    }

    private void SideDrawerInitializing() {
        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        nvDrawer = findViewById(R.id.nav_view);
        mToggle.syncState();
        setupDrawerContent(nvDrawer);

        View hView = nvDrawer.getHeaderView(0);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.contactUs:
                        startActivity(new Intent(MainActivity.this, ContactUsActivity.class));
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.aboutUs:
                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.helpSupport:
                        startActivity(new Intent(MainActivity.this, HelpSupportActivity.class));
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
    }

    public void setDrawerPosition() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            mDrawerLayout.setVisibility(View.VISIBLE);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    public void sideDrawerOpen(View v) {
        setDrawerPosition();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}