package yuol.secondary.market.toake.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.LocalAdapter>{
    private List<String> data ;
    private Context context;
    //每个列表的页面
    private int item;
    RecyclerAdapter(Context context , List<String> data,int item){
        this.context = context;
        this.data = data;
        this.item = item;
    }

    @NonNull
    @Override
    public LocalAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(item,viewGroup,false);
        LocalAdapter localAdapter = new LocalAdapter(view);
        return localAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull LocalAdapter localAdapter, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class LocalAdapter extends RecyclerView.ViewHolder {


        public LocalAdapter(@NonNull View itemView) {
            super(itemView);
        }
    }
}
