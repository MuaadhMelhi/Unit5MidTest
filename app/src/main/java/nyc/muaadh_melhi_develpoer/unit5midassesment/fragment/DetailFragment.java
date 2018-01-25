package nyc.muaadh_melhi_develpoer.unit5midassesment.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import nyc.muaadh_melhi_develpoer.unit5midassesment.R;
import nyc.muaadh_melhi_develpoer.unit5midassesment.model.Result;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private View rootView;
    private TextView name, phoneNumber, email, street, city, state, zipcode, dBrith;
    private KenBurnsView image;
    private String str;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        image = rootView.findViewById(R.id.fr_image);
        name = rootView.findViewById(R.id.fr_name);
        phoneNumber = rootView.findViewById(R.id.fr_phone_number);
        email = rootView.findViewById(R.id.fr_email);
        street = rootView.findViewById(R.id.fr_street);
        city = rootView.findViewById(R.id.fr_city);
        state = rootView.findViewById(R.id.fr_state);
        zipcode = rootView.findViewById(R.id.fr_zipcode);
        dBrith = rootView.findViewById(R.id.date_brith);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        str = bundle.getString("result");
        Result result = new Gson().fromJson(str, Result.class);
        Picasso.with(getActivity().getBaseContext())
                .load(result.getPicture().getLarge())
                .into(image);
        name.setText(result.getName().getFirst() + " " + result.getName().getLast());
        phoneNumber.setText(result.getCell());
        email.setText(result.getEmail());
        street.setText(result.getLocation().getStreet());
        city.setText(result.getLocation().getCity());
        state.setText(result.getLocation().getState());
        zipcode.setText("" + result.getLocation().getPostcode());
        dBrith.setText(result.getDob());

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.new_refersh);
        item.setVisible(false);
    }
}
