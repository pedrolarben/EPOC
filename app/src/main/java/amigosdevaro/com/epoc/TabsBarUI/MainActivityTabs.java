package amigosdevaro.com.epoc.TabsBarUI;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import amigosdevaro.com.epoc.MainActivity;
import amigosdevaro.com.epoc.R;

public class MainActivityTabs extends AppCompatActivity {

    private  Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabs);

        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MiFragmentPagerAdapter(getSupportFragmentManager(), this));

        tabLayout = (TabLayout) findViewById(R.id.appbartabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.file_document_box));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.pill_white));
        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.walk));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
