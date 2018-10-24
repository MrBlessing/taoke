package yuol.secondary.market.toake.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyyoona7.popup.EasyPopup;

import java.util.ArrayList;
import java.util.List;

import yuol.secondary.market.toake.Adapter.SortRecyclerAdapter;
import yuol.secondary.market.toake.HomePage;
import yuol.secondary.market.toake.R;
import yuol.secondary.market.toake.Utils.ActivityCollector;
import yuol.secondary.market.toake.Utils.LogUtil;
import yuol.secondary.market.toake.Utils.Popup;


public class ReleaseFragment extends Fragment {
    private View view;
    private RelativeLayout price;
    private RelativeLayout sort;
    private RelativeLayout number;
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
    }

    private void setEvent() {
        setClickEvent();
    }

    private void setClickEvent() {
        //加载价格列表
        final View view_price = LayoutInflater.from(context).inflate(R.layout.item_release_price,null);
        //加载分类列表
        final View view_sort = LayoutInflater.from(context).inflate(R.layout.item_release_sort,null);

        //配置sort弹窗中的列表
        RecyclerView recyclerView = view_sort.findViewById(R.id.item_release_sort_recycler);
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

        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.bigPopupWindow(view,view_price,Gravity.BOTTOM);
            }
        });
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Popup.hintPopupWindow(ReleaseFragment.this.view,view_sort);
            }
        });

    }


}
