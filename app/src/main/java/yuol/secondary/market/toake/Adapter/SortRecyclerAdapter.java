package yuol.secondary.market.toake.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import yuol.secondary.market.toake.R;

public class SortRecyclerAdapter extends RecyclerView.Adapter<SortRecyclerAdapter.LocalHolder>{
    private List<String> data ;
    //每个列表的页面
    public SortRecyclerAdapter(List<String> data){
        this.data = data;
    }

    @NonNull
    @Override
    public LocalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popup_release_sort_item,viewGroup,false);
        return new LocalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalHolder localHolder, int i) {
        localHolder.tag.setText(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class LocalHolder extends RecyclerView.ViewHolder {
        TextView tag;
        LinearLayout container;
        LocalHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.popup_release_sort_item_tag);
            container=itemView.findViewById(R.id.popup_release_sort_item_container);
        }
    }
}
