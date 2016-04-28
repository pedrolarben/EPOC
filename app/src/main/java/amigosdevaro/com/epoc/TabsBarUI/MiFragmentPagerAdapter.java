package amigosdevaro.com.epoc.TabsBarUI;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import amigosdevaro.com.epoc.MedicinadUI.DisplayMedicinasFragment;
import amigosdevaro.com.epoc.R;

/**
 * Created by betipedro on 20/04/2016.
 */
public class MiFragmentPagerAdapter extends FragmentPagerAdapter {
    Context context;
    String[] tabTitles ;

    public MiFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
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
            fragment = new Fragment_a();

        }
        if(position==1){
            fragment = /*new Fragment_b();*/new DisplayMedicinasFragment();
        }
        if(position==2){
            fragment = new Fragment_c();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
