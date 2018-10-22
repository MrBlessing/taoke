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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import yuol.secondary.market.toake.Utils.BasedActivity;
import yuol.secondary.market.toake.Utils.FileUtils;
import yuol.secondary.market.toake.Utils.NetworkUtils;

public class StartActivity extends BasedActivity {


    //限时跳转
    private CountDownTimer timer = new CountDownTimer(3000,3000) {
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
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        loadImage();
        applyForPermission();
    }
    private void loadImage() {
        //从服务端获取图片地址
        NetworkUtils.request("http://192.168.137.1/taoke/start_image.conf", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(StartActivity.this, "图片加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                FileUtils.saveByPeference(StartActivity.this,"start_image",response.body().string());
            }
        });
        //加载图片
        String imageUrl = "http://192.168.137.1/taoke/" + PreferenceManager.getDefaultSharedPreferences(this).getString("start_image","start.png");
        image = findViewById(R.id.start_image);
        Glide.with(this)
                .load(imageUrl)
                .into(image);
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
