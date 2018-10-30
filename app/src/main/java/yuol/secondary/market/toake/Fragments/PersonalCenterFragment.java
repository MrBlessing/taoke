package yuol.secondary.market.toake.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import yuol.secondary.market.toake.Login;
import yuol.secondary.market.toake.R;
import yuol.secondary.market.toake.Utils.ActivityCollector;

public class PersonalCenterFragment extends Fragment {
private View view;
private Context context= ActivityCollector.currentActivity();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_personal_center, container, false);
        final ImageView imageView = view.findViewById(R.id.fragment_personal_center_headPortrait);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Login.class);
                context.startActivity(intent);
            }
        });
        return view;
    }

}
