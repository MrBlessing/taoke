package yuol.secondary.market.toake;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import yuol.secondary.market.toake.Utils.BasedActivity;
import yuol.secondary.market.toake.Utils.LogUtil;
import yuol.secondary.market.toake.Utils.NetworkUtils;
import yuol.secondary.market.toake.bean.ImageUrl;

public class StartActivity extends BasedActivity {

    //限时跳转
    private CountDownTimer timer = new CountDownTimer(300,3000) {
        @Override
        public void onTick(long l) {

        }
        @Override
        public void onFinish() {
            Intent intent = new Intent(StartActivity.this,HomePage.class);
            startActivity(intent);
            finish();
        }
    };
    private static final String TAG = "StartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        load();
        LogUtil.d(TAG, PreferenceManager.getDefaultSharedPreferences(this).getString("Json_imageUrl","null"));
        loadStartImage();
        applyForPermission();
    }

    private void loadStartImage() {
        ImageView image = findViewById(R.id.start_image);
        //获得已经储存好的json数据
        String json = PreferenceManager.getDefaultSharedPreferences(this).getString("Json_imageUrl",null);
        if(json!=null){
            //解析json数据
            Gson gson = new Gson();
            ImageUrl imageUrl = gson.fromJson(json,ImageUrl.class);
            Glide.with(this)
                    .load(imageUrl.getStart())
                    .into(image);
        }else {
            //保存的数据为空的话再次请求一遍数据
            NetworkUtils.loadJsonImageUrl();
        }
    }

    private void load() {
        ////提前加载图片Json数据,并保存
        NetworkUtils.loadJsonImageUrl();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    Toast.makeText(this, "正在加载中", Toast.LENGTH_SHORT).show();
                    return true;
                }break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void applyForPermission() {
        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String[] permission =  permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this,permission,1);
        }else {
            //如果权限通过开始跳转页面
            timer.start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int res : grantResults){
                        if(res != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "需要全部权限才能使用该应用", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    timer.start();
                }
        }
    }
}
