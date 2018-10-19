package yuol.secondary.market.toake.Utils;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import yuol.secondary.market.toake.Broadcast.UpdateBroadcastReceiver;


public class BasedActivity extends AppCompatActivity {
    private static final String TAG = "BasedActivity";
    public LocalBroadcastManager manager;
    private UpdateBroadcastReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将每一个页面加入ActivityCollector中进行管理
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在ActivityCollector中移除对已经销毁的页面
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //页面在前台的时候注册广播
        manager = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter("yuol.secondary.market.toake.update");
        receiver = new UpdateBroadcastReceiver();
        manager.registerReceiver(receiver , filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //页面在后台的时候解除注册的广播
        if(receiver != null){
            //这里将receiver置为空，自我感觉是为了防止未调用onResume()方法直接调用onPause()的情况
            manager.unregisterReceiver(receiver);
            receiver=null;
        }
    }

}

