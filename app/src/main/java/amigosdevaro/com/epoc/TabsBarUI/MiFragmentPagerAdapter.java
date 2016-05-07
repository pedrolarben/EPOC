package amigosdevaro.com.epoc.TabsBarUI;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import amigosdevaro.com.epoc.MainActivity;
import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.UI_Documentacion.DocumentacionFragment;
import amigosdevaro.com.epoc.UI_Medicinas.MedicinasFragment;

/**
 * Created by betipedro on 20/04/2016.
 */
public class MiFragmentPagerAdapter extends FragmentPagerAdapter {
    Context context;
    String[] tabTitles ;
    FloatingActionButton fab ;

    public MiFragmentPagerAdapter(FragmentManager fm, Context context , FloatingActionButton fab) {
        super(fm);
        this.context=context;
        this.fab = fab;
        tabTitles = context.getResources().getStringArray(R.array.tabs);

    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }



    @Override
    public Fragment getItem(int position) {
        Fragment fragment= null;
        if(position==0){
            MainActivity.fabVisible = false;

            fragment = new DocumentacionFragment();


        }
        if(position==1){
            fab.hide();
            MainActivity.fabVisible = true;
            fragment = new MedicinasFragment();//new Fragment_b();
        }
        if(position==2){

            MainActivity.fabVisible = false;
            fragment = new Fragment_c();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
