package nyc.muaadh_melhi_develpoer.unit5midassesment.RecyclerViewTools;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nyc.muaadh_melhi_develpoer.unit5midassesment.R;
import nyc.muaadh_melhi_develpoer.unit5midassesment.model.Result;

/**
 * Created by c4q on 1/24/18.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleViewHolder> {
    private List<Result> resultList=new ArrayList<>();

    public PeopleAdapter(List<Result> resultList) {
        this.resultList = resultList;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutRes = 0;
        layoutRes = R.layout.person_item_view;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.onBind(result);

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
