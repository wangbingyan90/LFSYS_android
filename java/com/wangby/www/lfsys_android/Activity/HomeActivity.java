package com.wangby.www.lfsys_android.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wangby.www.lfsys_android.Object.Confing;
import com.wangby.www.lfsys_android.R;
import com.wangby.www.lfsys_android.View.ContentFragment;
import com.wangby.www.lfsys_android.View.PersonalFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王炳炎 on 2017/4/26.
 */
public class HomeActivity extends AppCompatActivity {
    //下框栏
    private TabLayout mTabTl;
    //滑动内容窗口
    private ViewPager mContentVp;
    //内容布局
    private List<Fragment> tabFragments;
    //内容配置器
    private ContentPagerAdapter contentAdapter;
    //顶框
    Toolbar toolbar;
    String[] str = new String[]{"失物求助", "拾物招领", "物品发布", "信息", "个人中心"};
    //下栏名字
    String[] strdown = new String[]{"失物", "拾物", "发布", "信息", "个人"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //加载顶框
        toolbar = (Toolbar) findViewById(R.id.topp);
        setSupportActionBar(toolbar);

        mTabTl = (TabLayout) findViewById(R.id.tl_tab);
        mContentVp = (ViewPager) findViewById(R.id.vp_content);
        initContent();

        initTab();

    }

    /**
     * 登陆事件
     * @param v
     */
    public void imageview(View v){
        if(Confing.LOGIN_STATE){
            startActivity(new Intent(this, PersonalActivity.class));
        }else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    /**
     * 配置下边框
     */
    private void initTab() {
        mTabTl.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabTl.setTabTextColors(ContextCompat.getColor(this, R.color.gray), ContextCompat.getColor(this, R.color.blue));
        mTabTl.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.white));
        ViewCompat.setElevation(mTabTl, 10);
        mTabTl.setupWithViewPager(mContentVp);
        mTabTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                toolbar.setTitle(str[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    /**
     * 配置Fragment界面
     */
    private void initContent() {
        tabFragments = new ArrayList<>();
        tabFragments.add(ContentFragment.getFragment("goods_lost"));
        tabFragments.add(ContentFragment.getFragment("goods_found"));
        tabFragments.add(ContentFragment.getFragment("issue"));
        tabFragments.add(ContentFragment.getFragment("message"));
        tabFragments.add(PersonalFragment.getFragment());
        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mContentVp.setAdapter(contentAdapter);

    }


    /**
     * 顶栏添加
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    /**
     * 顶栏事件
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this, SearchActivity.class));
        return super.onOptionsItemSelected(item);
    }


    /**
     * Fragment界面  资源配置器
     */
    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return strdown[position];
        }
    }


}
