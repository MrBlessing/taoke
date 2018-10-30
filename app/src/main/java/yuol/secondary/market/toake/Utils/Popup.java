package yuol.secondary.market.toake.Utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import yuol.secondary.market.toake.R;

public class Popup {
    public static EasyPopup easyPopup;
    //默认的加载弹窗


    public static void bigPopupWindow(View root,View contentView,int orientation) {
        easyPopup = EasyPopup.create()
                //设置要加载的内容
                .setContentView(contentView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.6f)
                //变暗的背景颜色
                .setDimColor(Color.GRAY)
                .apply();
        //设置加载内容的位置
        easyPopup.showAtLocation(root, orientation,0,0);
    }

    public static void sidePopupWindow(View anchor,View contentView) {
        EasyPopup easyPopup = EasyPopup.create()
                //设置要加载的内容
                .setContentView(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.6f)
                //变暗的背景颜色
                .setDimColor(Color.GRAY)
                .apply();
        //设置加载内容的位置
        easyPopup.showAtAnchorView(anchor , YGravity.ABOVE, XGravity.RIGHT,200,0);
    }


    public static void hintPopupWindow(View root,View contentView) {
        easyPopup = EasyPopup.create()
                //设置要加载的内容
                .setContentView(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.6f)
                //变暗的背景颜色
                .setDimColor(Color.GRAY)
                .apply();
        //设置加载内容的位置
        easyPopup.showAtLocation(root, Gravity.CENTER,0,0);
    }

    //带有定时功能的中间弹窗
    public static void hintPopupWindow(View root,long time,String content) {
        //加载默认的页面
        View hint = LayoutInflater.from(ActivityCollector.currentActivity()).inflate(R.layout.popup_hint,null);
        TextView text = hint.findViewById(R.id.popup_hint_content);
        text.setText(content);
        //为了不关闭掉其他的popup,所以不用静态变量
        final EasyPopup TimeEasyPopup = EasyPopup.create()
                //设置要加载的内容
                .setContentView(hint, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                .apply();
        //设置加载内容的位置
        TimeEasyPopup.showAtLocation(root, Gravity.CENTER,0,0);
        //定时关闭
        CountDownTimer timer = new CountDownTimer(time,time) {
            @Override
            public void onTick(long l) {

            }
            @Override
            public void onFinish() {
                TimeEasyPopup.dismiss();
            }
        }.start();
    }
}
