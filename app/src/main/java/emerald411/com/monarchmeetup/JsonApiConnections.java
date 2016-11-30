package emerald411.com.monarchmeetup;

/**
 * Created by alexander.dohrn on 11/20/16.
 */

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonApiConnections {

    @GET("login/{userName}")
    Call<UserModel> checkUser(@Path("userName") String userName);

    @GET("events/{id}")
    Call<EventModel> getEvent(@Path("id") String id);

    @GET("events")
    Call<List<EventModel>> getEvents();

    @POST("users")
    Call<UserModel> submitNewUser(@Body UserModel user);

}