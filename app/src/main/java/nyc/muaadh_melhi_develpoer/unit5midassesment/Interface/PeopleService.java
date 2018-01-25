package nyc.muaadh_melhi_develpoer.unit5midassesment.Interface;



import nyc.muaadh_melhi_develpoer.unit5midassesment.info.Info;
import nyc.muaadh_melhi_develpoer.unit5midassesment.model.People;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by c4q on 1/13/18.
 */

public interface PeopleService {
    // https://randomuser.me/api/?nat=us&inc=name,location,cell,email,dob,picture&results=20
    @GET(Info.API_KEY + "/?nat=us&inc=name,location,cell,email,dob,picture&results=20")
    Call<People> getPeople();

}
