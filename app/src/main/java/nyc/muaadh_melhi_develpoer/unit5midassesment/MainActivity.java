package nyc.muaadh_melhi_develpoer.unit5midassesment;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.diagonallayout.DiagonalLayout;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.paperdb.Paper;
import nyc.muaadh_melhi_develpoer.unit5midassesment.Interface.PeopleService;
import nyc.muaadh_melhi_develpoer.unit5midassesment.RecyclerViewTools.PeopleAdapter;
import nyc.muaadh_melhi_develpoer.unit5midassesment.fragment.DetailFragment;
import nyc.muaadh_melhi_develpoer.unit5midassesment.info.Info;
import nyc.muaadh_melhi_develpoer.unit5midassesment.model.People;
import nyc.muaadh_melhi_develpoer.unit5midassesment.model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KenBurnsView topImage;
    private TextView topTilte;
    private DiagonalLayout diagonalLayout;
    private PeopleService peopleService;
    private PeopleAdapter peopleAdapter;
    private DetailFragment topFragment;
    private FragmentManager manager;
    private MenuInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        peopleService = Info.getPeopleService();
        //cache data
        String cache = Paper.book().read("cache");
        if (cache != null && !cache.isEmpty()) {
            People result = new Gson().fromJson(cache, People.class);
            List<Result> list = result.getResults();
            getFirstPerson(result.getResults().get(0));
            list.remove(0);
            peopleAdapter = new PeopleAdapter(list);
            peopleAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(peopleAdapter);
        } else {

            //load data if there's internet
            loadPeopleData(peopleService);
        }


    }

    private void setUpView() {

        Paper.init(this);
        recyclerView = findViewById(R.id.source_re);
        topImage = findViewById(R.id.top_image);
        topTilte = findViewById(R.id.top_name);
        diagonalLayout = findViewById(R.id.diagonalLayout);
    }

    private void loadPeopleData(PeopleService peopleService) {
        recyclerView.setVisibility(View.VISIBLE);
        peopleService.getPeople().enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
                Log.d("onResponse: ", "=================================================================");
                Log.d("onResponse: ", response.body().getResults().get(0).getName().getFirst());
                //Get first article
                getFirstPerson(response.body().getResults().get(0));
                //remain Elements
                List<Result> listWithoutFristItem = response.body().getResults();
                listWithoutFristItem.remove(0);
                PeopleAdapter peopleAdapter = new PeopleAdapter(listWithoutFristItem);
                recyclerView.setAdapter(peopleAdapter);
                Paper.book().write("cache", new Gson().toJson(response.body())); //save the info to dataBase

            }

            @Override
            public void onFailure(Call<People> call, Throwable t) {
                Log.d("onFailure: ", "================================================================= ");
                Log.d("onFailure: ", t.getMessage());
                t.printStackTrace();

            }
        });
    }

    private void getFirstPerson(final Result result) {
        Picasso.with(getBaseContext())
                .load(result.getPicture().getLarge())
                .into(topImage);
        topTilte.setText(result.getName().getFirst() + " " + result.getName().getLast());

        diagonalLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                //serLiztion
                String resultAsString = new Gson().toJson(result);
                Bundle bundle = new Bundle();
                bundle.putString("result", resultAsString);
                topFragment = new DetailFragment();
                manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                topFragment.setArguments(bundle);
                transaction.addToBackStack("").replace(R.id.container, topFragment).commit();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.people_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_refersh:
                Toast.makeText(this, "Page is Refresh .......", Toast.LENGTH_SHORT).show();
                loadPeopleData(peopleService);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getMoreInfo(Result result) {
        recyclerView.setVisibility(View.GONE);

        //serLiztion
        String resultAsString = new Gson().toJson(result);
        Bundle bundle = new Bundle();
        bundle.putString("result", resultAsString);
        topFragment = new DetailFragment();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        topFragment.setArguments(bundle);
        transaction.addToBackStack("").replace(R.id.container, topFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStackImmediate();
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }

    }
}
