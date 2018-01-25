package nyc.muaadh_melhi_develpoer.unit5midassesment.info;


import nyc.muaadh_melhi_develpoer.unit5midassesment.Interface.PeopleService;
import nyc.muaadh_melhi_develpoer.unit5midassesment.Remote.RetrofitClient;

/**
 * Created by c4q on 1/13/18.
 */

public class Info {
    // https://randomuser.me/api/?nat=us&inc=name,location,cell,email,dob,picture&results=20
    private static final String BASE_URL = "https://randomuser.me/";
    public static final String API_KEY = "api";

    public static PeopleService getPeopleService() {
        return RetrofitClient.getRetrofit(BASE_URL).create(PeopleService.class);
    }

}
