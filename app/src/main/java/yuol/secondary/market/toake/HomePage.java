package yuol.secondary.market.toake;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import yuol.secondary.market.toake.Fragments.HomeFragment;
import yuol.secondary.market.toake.Fragments.PersonalCenterFragment;
import yuol.secondary.market.toake.Fragments.ReleaseFragment;
import yuol.secondary.market.toake.Utils.BasedActivity;
import yuol.secondary.market.toake.autoUpdate.AutoUpdate;


public class HomePage extends BasedActivity {
    public boolean updateState = true;//检测更新状态，改值为真则检测更新，保证了该应用每打开一次只会检测一次更新
    private long first=0;//给按键计时

    private HomeFragment homeFragment;
    private ReleaseFragment releaseFragment;
    private PersonalCenterFragment personalCenterFragment;

    private BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        findView();
        setEvent();
    }

    private void setEvent() {
        initFragments();
        setBottomNavigation();
    }

    private void initFragments() {
        homeFragment = new HomeFragment();
        //每次都是replease就不用担心重复添加了，真是个天才
        getSupportFragmentManager().beginTransaction().replace(R.id.home_page_container,homeFragment).commit();
    }

    private void setBottomNavigation() {
        //基础设置
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_DEFAULT)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .setBarBackgroundColor("#ffffff")
                .setActiveColor("#d65d5d")//选中颜色
                //设置点击事件
                .setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        switch (position){
                            case 0:
                                if(homeFragment == null){
                                    //防止反复添加
                                    homeFragment = new HomeFragment();
                                }
                                transaction.replace(R.id.home_page_container,homeFragment);
                                break;
                            case 1:
                                if(releaseFragment == null){
                                    //防止反复添加
                                    releaseFragment = new ReleaseFragment();
                                }
                                transaction.replace(R.id.home_page_container,releaseFragment);
                                break;
                            case 2:
                                if(personalCenterFragment == null){
                                    //防止反复添加
                                    personalCenterFragment = new PersonalCenterFragment();
                                }
                                transaction.replace(R.id.home_page_container,personalCenterFragment);
                                break;
                            default:break;
                        }
                        transaction.commit();//事务提交
                    }

                    @Override
                    public void onTabUnselected(int position) {

                    }

                    @Override
                    public void onTabReselected(int position) {

                    }
                });
         //按钮设置
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home,"首页"))
                .addItem(new BottomNavigationItem(R.drawable.release,"发布"))
                .addItem(new BottomNavigationItem(R.drawable.personal_center,"个人"))
                .initialise();

    }

    private void findView() {
        bottomNavigationBar = findViewById(R.id.home_page_bottomNavigation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //只进行一次更新
        if(updateState){
            updateState = false;
            AutoUpdate.Update(this,manager);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if((System.currentTimeMillis()-first)<2000){
                       finish();
                    }else {
                        first = System.currentTimeMillis();
                        Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }break;
        }
        return super.onKeyDown(keyCode, event);
    }


}
