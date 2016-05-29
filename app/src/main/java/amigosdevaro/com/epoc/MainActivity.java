package amigosdevaro.com.epoc;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
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
import amigosdevaro.com.epoc.UI_exacerbaciones.Exacerbaciones;

public class MainActivity extends AppCompatActivity {

    public static final String STATE_TAB = "tab";
    public static Integer tab_index = 0;

    private  Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
     FloatingActionButton editMedicina;

    public static boolean fabVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("lifecycle","onCreate");
        super.onCreate(savedInstanceState);
        Log.d("MainOnCreate", tab_index.toString());
        setContentView(R.layout.activity_main);

        DbHelper helper = new DbHelper(this);
        EpocDB.initEpocDB(helper);


        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        setOverflowButtonColor(toolbar,R.color.white);


        /*final FloatingActionButton*/ editMedicina = (FloatingActionButton) findViewById(R.id.action_edit_medicine);
        editMedicina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.tab_index=1;
                startActivity(new Intent(MainActivity.this, DisplayMeds.class));
                editMedicina.show();
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
                    Log.d("fab", "hide");
                    editMedicina.hide();
                } else if (position == 1) {
                    Log.d("fab", "show");
                    editMedicina.show();
                } else if (position == 2) {
                    Log.d("fab", "hide");
                    editMedicina.hide();
                }
                tab_index = position;
                Log.d("Adapter", MainActivity.tab_index.toString());
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        tabLayout = (TabLayout) findViewById(R.id.appbartabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.setupWithViewPager(viewPager);

        TabLayout.Tab tab = tabLayout.getTabAt(0);
        tab.select();

        TabLayout.Tab tab1 = tabLayout.getTabAt(tab_index);
        tab1.select();

        if(tab_index==1){ editMedicina.show();
            Log.d("fab", "show");}
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


       // tabLayout.TabLayoutOnPageChangeListener

        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.file_document_box));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.pill_white));
        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.walk));

        editMedicina.show();
        Log.d("fab", "show");

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
                startActivity(new Intent(this,Exacerbaciones.class));
                return true;
            case R.id.action_settings:

                return true;
            case R.id.action_mis_medicamentos:
                startActivity(new Intent(this, DisplayMeds.class));
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

    @Override
    protected void onResume() {
        Log.d("lifecycle","onResume");
        Log.d("onResumeMA",MainActivity.tab_index.toString());
        if(MainActivity.tab_index.equals(1)){
            //Log.d("onResumeMA","show");
            Log.d("fab" , "show");
            editMedicina.show();
        }
        super.onResume();

    }

    @Override
    protected void onStart() {
        Log.d("fab" , "show");
        this.editMedicina.show();
        Log.d("lifecycle","onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {

        Log.d("lifecycle","onStop");
        super.onStop();
    }

    @Override
    protected void onPause() {

        Log.d("lifecycle","onPause");
        super.onPause();
    }

    @Override
    protected void onDestroy() {

        Log.d("lifecycle","onDestroy");
        super.onDestroy();
    }


    @Override
    protected void onRestart() {

        Log.d("lifecycle","onRestart");
        super.onRestart();
    }

    public  void setOverflowButtonColor(final Toolbar toolbar, final int color) {
        Drawable drawable = toolbar.getOverflowIcon();
        if(drawable != null) {
            drawable = DrawableCompat.wrap(drawable);
            drawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.MULTIPLY);
            DrawableCompat.setTint(drawable, getResources().getColor(R.color.white));
            toolbar.setOverflowIcon(drawable);
        }
    }
}
