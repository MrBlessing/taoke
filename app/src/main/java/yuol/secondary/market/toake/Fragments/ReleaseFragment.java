package yuol.secondary.market.toake.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yuol.secondary.market.toake.Adapter.SortRecyclerAdapter;
import yuol.secondary.market.toake.R;
import yuol.secondary.market.toake.Utils.ActivityCollector;
import yuol.secondary.market.toake.Utils.Popup;


public class ReleaseFragment extends Fragment {
    private View view;
    private RelativeLayout price;
    private RelativeLayout sort;
    private RelativeLayout number;
    private TextView showPrice;
    private TextView showSort;
    private TextView showNumber;
    private Context context = ActivityCollector.currentActivity();
    private static final String TAG = "ReleaseFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_release, container, false);
        findView();
        setEvent();
        return view;
    }

    private void findView() {
        price = view.findViewById(R.id.fragment_release_price);
        sort = view.findViewById(R.id.fragment_release_sort);
        number = view.findViewById(R.id.fragment_release_number);
        showPrice = view.findViewById(R.id.fragment_release_price_text);
        showSort = view.findViewById(R.id.fragment_release_sort_text);
        showNumber = view.findViewById(R.id.fragment_release_number_text);
    }

    private void setEvent() {
        setClickEvent();
    }

    private void setClickEvent() {
        setPrice();
        setSort();
        setNumber();
    }

    private void setNumber() {
        //加载库存输入布局
        final View view_number = LayoutInflater.from(context).inflate(R.layout.popup_release_number,null);

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.bigPopupWindow(view,view_number,Gravity.BOTTOM);
            }
        });

        //设置view_number内部按钮的点击事件
        Button submit = view_number.findViewById(R.id.popup_release_number_submit);
        final EditText inputNumber = view_number.findViewById(R.id.popup_release_price_inputNumber);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(inputNumber.getText())){
                    Popup.easyPopup.dismiss();
                    showNumber.setText(inputNumber.getText()+"件");
                }else {
                    Popup.hintPopupWindow(ReleaseFragment.this.view,1000,"请输入完整内容");
                }


            }
        });
    }

    private void setSort() {
        //加载分类列表
        final View view_sort = LayoutInflater.from(context).inflate(R.layout.popup_release_sort,null);

        //配置sort弹窗中的列表
        RecyclerView recyclerView = view_sort.findViewById(R.id.popup_release_sort_recycler);
        List<String> data = new ArrayList<>();
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("a");
        data.add("b");
        data.add("a");
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("a");
        data.add("b");
        data.add("a");
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("a");
        data.add("b");
        data.add("a");
        LinearLayoutManager manager1 = new LinearLayoutManager(ActivityCollector.currentActivity());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager1);
        recyclerView.setAdapter(new SortRecyclerAdapter(data));
        //设置recycler的点击事件
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.hintPopupWindow(ReleaseFragment.this.view,view_sort);
            }
        });
    }

    private void setPrice() {
        //加载价格输入布局
        final View view_price = LayoutInflater.from(context).inflate(R.layout.popup_release_price,null);
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.bigPopupWindow(view,view_price,Gravity.BOTTOM);
            }
        });

        //设置view_price内部控件事件
        Button submit = view_price.findViewById(R.id.popup_release_price_submit);
        final EditText inputPrice = view_price.findViewById(R.id.popup_release_price_inputPrice);
        final EditText inputFirstPrice = view_price.findViewById(R.id.popup_release_price_inputFirstPrice);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将输入的信息输入到主页面
                if(!TextUtils.isEmpty(inputPrice.getText()) && !TextUtils.isEmpty(inputFirstPrice.getText())){
                    showPrice.setText("￥"+inputPrice.getText()+" / ￥"+inputFirstPrice.getText());
                    Popup.easyPopup.dismiss();
                }else {
                    //加载提示弹窗
                    Popup.hintPopupWindow(ReleaseFragment.this.view,1000,"请输入完整内容");
                }
            }
        });
    }

}
