package yuol.secondary.market.toake;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import yuol.secondary.market.toake.Utils.BasedActivity;
import yuol.secondary.market.toake.autoUpdate.AutoUpdate;


public class HomePage extends BasedActivity {

    public boolean updateState = true;
    private long first=0;//给按键计时
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

    }

    @Override
    protected void onResume() {
        super.onResume();
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
