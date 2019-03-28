package p3l_8980.com.atmaauto.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import p3l_8980.com.atmaauto.R;

import com.yalantis.guillotine.animation.GuillotineAnimation;

public class Beranda extends AppCompatActivity {
    //View Variable
    Toolbar toolbar;
    FrameLayout root;
    BottomNavigationView bottombar;
    View contentHamburger;
    ImageView contentRight;
    TextView title;
    RelativeLayout.LayoutParams fragmentparams;
    View fragmentlayout;
    FloatingActionButton fab;
    //Global Variable
    static int view_position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        IntializeView();
        // Shared Data
    }


    private void IntializeView() {
        toolbar = findViewById(R.id.toolbar);
        title = findViewById(R.id.toolbar_title);
        bottombar = findViewById(R.id.NavigationBot);
        root = findViewById(R.id.root);
        contentHamburger = findViewById(R.id.content_hamburger);
        contentRight = findViewById(R.id.content_right);
        fragmentlayout = findViewById(R.id.scrollView2);
        fragmentparams = (RelativeLayout.LayoutParams) fragmentlayout.getLayoutParams();
        fab = findViewById(R.id.fab);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.hamburger, null);
        root.addView(guillotineMenu);

        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();

        registerForContextMenu(contentRight);

        switch (getIntent().getIntExtra("addDialog",0)){
            case 0: bottombar.setSelectedItemId(R.id.navigation_beranda); switchfragment(R.id.navigation_beranda);break;
            case 1: bottombar.setSelectedItemId(R.id.navigation_supplier); switchfragment(R.id.navigation_supplier);break;
            case 2: bottombar.setSelectedItemId(R.id.navigation_sparepart); switchfragment(R.id.navigation_sparepart);break;
            case 3: bottombar.setSelectedItemId(R.id.navigation_customer); switchfragment(R.id.navigation_customer);break;
        }


        bottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_beranda:
                        if(view_position != 0) switchfragment(R.id.navigation_beranda);
                        return true;
                    case R.id.navigation_supplier:
                        if(view_position != 1) switchfragment(R.id.navigation_supplier);
                        return true;
                    case R.id.navigation_sparepart:
                        if(view_position != 2) switchfragment(R.id.navigation_sparepart);
                        return true;
                    case R.id.navigation_customer:
                        if(view_position != 3) switchfragment(R.id.navigation_customer);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,v.getId(),0, "Reset Balance");
        menu.add(0,v.getId(),0, "See Log");
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }
    public void switchfragment(int id) {
        FragmentManager manager = getSupportFragmentManager();
        fragmentparams.removeRule(RelativeLayout.CENTER_IN_PARENT);
        switch (id) {
            case R.id.navigation_beranda:
                view_position = 0;
                manager.beginTransaction().replace(R.id.fragmentplace, new FragmentBeranda()).commit();
                title.setText("Atma Auto");
                fragmentparams.addRule(RelativeLayout.CENTER_IN_PARENT);
                contentRight.setVisibility(View.INVISIBLE);
                fab.hide();
                break;
        }
        fragmentlayout.setLayoutParams(fragmentparams);
    }

}