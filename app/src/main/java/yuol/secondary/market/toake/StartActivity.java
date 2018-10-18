package yuol.secondary.market.toake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yuol.secondary.market.toake.Utils.BasedActivity;
import yuol.secondary.market.toake.autoUpdate.AutoUpdate;

public class StartActivity extends BasedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //3秒之后跳转

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Intent intent = new Intent(StartActivity.this,MainActivity.class);
                long timeNow = System.currentTimeMillis();

                while(true){
                    if(System.currentTimeMillis()>(timeNow+3000)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(intent);
                                finish();
                            }
                        });
                        break;
                    }
                }
            }
        }).start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //自动检测更新
        AutoUpdate.Update(this,manager);
    }
}
