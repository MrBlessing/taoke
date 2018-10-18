package yuol.secondary.market.toake.Broadcast;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import yuol.secondary.market.toake.Utils.ActivityCollector;

public class UpdateBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        //获取更新链接
        final String url = intent.getStringExtra("url");
        boolean state = intent.getBooleanExtra("state",false);
        if(state){
            //弹窗直接获取栈顶的活动
            AlertDialog dialog = new AlertDialog.Builder(ActivityCollector.currentActivity())
                    .setTitle("更新提示")
                    .setMessage("您有新的版本推送，是否更新?")
                    .setCancelable(false)
                    .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent_h = new Intent(Intent.ACTION_VIEW);
                            intent_h.setData(Uri.parse(url));
                            ActivityCollector.currentActivity().startActivity(intent_h);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .create();
            dialog.show();
        }else{
            Toast.makeText(context, "已是最新版本", Toast.LENGTH_SHORT).show();
        }

    }
}
