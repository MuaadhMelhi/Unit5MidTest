package nyc.muaadh_melhi_develpoer.unit5midassesment.RecyclerViewTools;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import nyc.muaadh_melhi_develpoer.unit5midassesment.MainActivity;
import nyc.muaadh_melhi_develpoer.unit5midassesment.R;
import nyc.muaadh_melhi_develpoer.unit5midassesment.fragment.DetailFragment;
import nyc.muaadh_melhi_develpoer.unit5midassesment.model.Result;

/**
 * Created by c4q on 1/24/18.
 */

class PeopleViewHolder extends RecyclerView.ViewHolder {
    private CircleImageView circlePersonImage;
    private TextView personName;

    public PeopleViewHolder(View itemView) {
        super(itemView);
        circlePersonImage = itemView.findViewById(R.id.people_cir_image);
        personName = itemView.findViewById(R.id.person_name);
    }

    public void onBind(final Result result) {
        Picasso.with(itemView.getContext())
                .load(result.getPicture().getLarge())
                .into(circlePersonImage);
        personName.setText(result.getName().getFirst() + " " + result.getName().getLast());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MainActivity main = (MainActivity) itemView.getContext();
                main.getMoreInfo(result);// that's the name of your method in the MainActivity


            }
        });
    }
}
