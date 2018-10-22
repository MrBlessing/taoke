package yuol.secondary.market.toake.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import yuol.secondary.market.toake.R;

public class HomeFragment extends Fragment {
    private View view;
    private ConvenientBanner convenientBanner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        findView();
        setEvent();
        return view;
    }

    private void findView() {
        convenientBanner = view.findViewById(R.id.fragment_home_ConvenientBanner);
    }

    private void setEvent() {
        setConvenientBanner();
    }

    private void setConvenientBanner() {
        List<String> data = new ArrayList<>();
        data.add("http://192.168.137.1/taoke/1.jpg");
        data.add("http://192.168.137.1/taoke/2.jpg");
        data.add("http://192.168.137.1/taoke/3.png");
        data.add("http://192.168.137.1/taoke/4.png");
        data.add("http://192.168.137.1/taoke/5.jpg");

        //存入数据和Holder
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalHolder();
            }
        },data)
        .setPageIndicator(new int[]{R.drawable.dot_white ,R.drawable.dot_grey})
        .startTurning(2000);
    }

    //适配ConvenientBanner
    class LocalHolder implements Holder<String> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(context).load(data).into(imageView);
        }

    }
}
