package yuol.secondary.market.toake;

import android.os.Bundle;

import yuol.secondary.market.toake.Utils.BasedActivity;
import yuol.secondary.market.toake.autoUpdate.AutoUpdate;


public class MainActivity extends BasedActivity {
    public boolean updateState = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(updateState){
            updateState = false;
            AutoUpdate.Update(this,manager);
        }
    }
}
