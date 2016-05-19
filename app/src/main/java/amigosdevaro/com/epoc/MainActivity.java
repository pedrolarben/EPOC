package amigosdevaro.com.epoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import amigosdevaro.com.epoc.UI_Medicinas.DisplayMeds;
import amigosdevaro.com.epoc.DB_SQLite.DbHelper;
import amigosdevaro.com.epoc.DB_SQLite.EpocDB;
import amigosdevaro.com.epoc.TabsBarUI.MiFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String STATE_TAB = "tab";
    public static Integer tab_index = 0;

    private  Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public static boolean fabVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainOnCreate", tab_index.toString());
        setContentView(R.layout.activity_main);

        DbHelper helper = new DbHelper(this);
        EpocDB.initEpocDB(helper);


        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton editMedicina = (FloatingActionButton) findViewById(R.id.action_edit_medicine);
        editMedicina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DisplayMeds.class));
            }
        });

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of the tab
            MainActivity.tab_index = savedInstanceState.getInt(STATE_TAB);
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MiFragmentPagerAdapter(getSupportFragmentManager(), this, editMedicina));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {

                    editMedicina.hide();
                } else if (position == 1) {

                    editMedicina.show();
                } else if (position == 2) {

                    editMedicina.hide();
                }
                tab_index=position;
                Log.d("Adapter", MainActivity.tab_index.toString());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        tabLayout = (TabLayout) findViewById(R.id.appbartabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.setupWithViewPager(viewPager);

        TabLayout.Tab tab = tabLayout.getTabAt(tab_index);
        tab.select();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


       // tabLayout.TabLayoutOnPageChangeListener

        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.file_document_box));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.pill_white));
        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.walk));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_exacerbacion:
                Snackbar.make(viewPager, "EXACERBACION //TODO", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                return true;
            case R.id.action_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_HOME){
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the  current tab index
        savedInstanceState.putInt(STATE_TAB, tab_index);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

}
