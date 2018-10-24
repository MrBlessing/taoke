package yuol.secondary.market.toake.Utils;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

public class Popup {
    public static void bigPopupWindow(View root,View contentView,int orientation) {
        EasyPopup easyPopup = EasyPopup.create()
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
        easyPopup.showAtLocation(root, Gravity.CENTER,0,0);
    }
}
