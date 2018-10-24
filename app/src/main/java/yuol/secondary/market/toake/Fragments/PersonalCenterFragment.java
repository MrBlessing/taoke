package yuol.secondary.market.toake.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yuol.secondary.market.toake.Adapter.SortRecyclerAdapter;
import yuol.secondary.market.toake.R;
import yuol.secondary.market.toake.Utils.ActivityCollector;

public class PersonalCenterFragment extends Fragment {
private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_personal_center, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        List<String> data = new ArrayList<>();
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("a");
        data.add("b");
        LinearLayoutManager manager1 = new LinearLayoutManager(ActivityCollector.currentActivity());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager1);
        recyclerView.setAdapter(new SortRecyclerAdapter(data));
        return view;
    }

}
