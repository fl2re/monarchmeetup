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

    @GET("events")
    Call<EventModel> getEvents();

    @POST("events")
    Call<EventModel> saveEvents();

    @POST("users")
    Call<UserModel> submitNewUser(@Body UserModel user);
//    @Headers("X-API-KEY: 0ec69a8e86b311e963d45e85b133aa75")
//    @GET("listings")
//    Call<ListingsObject> getListingsByCity(@Query("locale") String locale,
//                                           @Query("offset") int offset,
//                                           @Query("pageSize") int perPage,
//                                           @Query("location.city") String entityId,
//                                           @Query("filters.price") String priceRange,
//                                           @Query("filters.beds") String bedsRange,
//                                           @Query("filters.baths") String bathsRange,
//                                           @Query("filters.amenities") String amenitiesDesired,
//                                           @Query("sort") String sortOrder,
//                                           @Query("fields") String fields,
//                                           @Query("filters.packageNames") String packageNames);
//
//    @Headers("X-API-KEY: 0ec69a8e86b311e963d45e85b133aa75")
//    @GET("listings")
//    Call<ListingsObject> getListingsByZip(@Query("locale") String locale,
//                                          @Query("offset") int offset,
//                                          @Query("pageSize") int perPage,
//                                          @Query("location.zip") String entityId,
//                                          @Query("filters.price") String priceRange,
//                                          @Query("filters.beds") String bedsRange,
//                                          @Query("filters.baths") String bathsRange,
//                                          @Query("filters.amenities") String amenitiesDesired,
//                                          @Query("sort") String sortOrder,
//                                          @Query("fields") String fields,
//                                          @Query("filters.packageNames") String packageNames);
//
//    @Headers("X-API-KEY: 0ec69a8e86b311e963d45e85b133aa75")
//    @GET("listings")
//    Call<ListingsObject> getListingsBySchool(@Query("locale") String locale,
//                                             @Query("offset") int offset,
//                                             @Query("pageSize") int perPage,
//                                             @Query("location.school") String entityId,
//                                             @Query("filters.price") String priceRange,
//                                             @Query("filters.beds") String bedsRange,
//                                             @Query("filters.baths") String bathsRange,
//                                             @Query("filters.amenities") String amenitiesDesired,
//                                             @Query("sort") String sortOrder,
//                                             @Query("fields") String fields,
//                                             @Query("filters.packageNames") String packageNames);
//
//    @Headers("X-API-KEY: 0ec69a8e86b311e963d45e85b133aa75")
//    @GET("listings")
//    Call<ListingsObject> getListingsByNeighborhood(@Query("locale") String locale,
//                                                   @Query("offset") int offset,
//                                                   @Query("pageSize") int perPage,
//                                                   @Query("location.neighborhood") String entityId,
//                                                   @Query("filters.price") String priceRange,
//                                                   @Query("filters.beds") String bedsRange,
//                                                   @Query("filters.baths") String bathsRange,
//                                                   @Query("filters.amenities") String amenitiesDesired,
//                                                   @Query("sort") String sortOrder,
//                                                   @Query("fields") String fields,
//                                                   @Query("filters.packageNames") String packageNames);
//
//    @Headers("X-API-KEY: 0ec69a8e86b311e963d45e85b133aa75")
//    @GET("listings")
//    Call<ListingsObject> getListingsByLatLong(@Query("locale") String locale,
//                                              @Query("offset") int offset,
//                                              @Query("pageSize") int perPage,
//                                              @Query("location.position.latitude") Double latitude,
//                                              @Query("location.position.longitude") Double longitude,
//                                              @Query("filters.price") String priceRange,
//                                              @Query("filters.beds") String bedsRange,
//                                              @Query("filters.baths") String bathsRange,
//                                              @Query("filters.amenities") String amenitiesDesired,
//                                              @Query("sort") String sortOrder,
//                                              @Query("fields") String fields,
//                                              @Query("filters.packageNames") String packageNames);
//
//    @Headers("X-API-KEY: 0ec69a8e86b311e963d45e85b133aa75")
//    @GET("listings")
//    Call<ListingsObject> getListingsByLatLongForMap(@Query("locale") String locale,
//                                                    @Query("pageSize") int perPage,
//                                                    @Query("location.position.latitude") Double latitude,
//                                                    @Query("location.position.longitude") Double longitude,
//                                                    @Query("filters.price") String priceRange,
//                                                    @Query("filters.beds") String bedsRange,
//                                                    @Query("filters.baths") String bathsRange,
//                                                    @Query("filters.amenities") String amenitiesDesired,
//                                                    @Query("fields") String fields,
//                                                    @Query("filters.packageNames") String packageNames);
//    @Headers("X-API-KEY: 0ec69a8e86b311e963d45e85b133aa75")
//    @GET("listings")
//    Call<ListingsObject> getExploreListingsByLatLng(@Query("locale") String locale,
//                                                    @Query("pageSize") int perPage,
//                                                    @Query("location.position.latitude") Double latitude,
//                                                    @Query("location.position.longitude") Double longitude,
//                                                    @Query("filters.products") String fields);
//
//    @Headers("X-API-KEY: 0ec69a8e86b311e963d45e85b133aa75")
//    @GET("listings")
//    Call<ListingsObject> getExploreListingsByCity(@Query("locale") String locale,
//                                                  @Query("pageSize") int perPage,
//                                                  @Query("location.city") String entityId,
//                                                  @Query("filters.products") String fields);
//
//    @Headers("X-API-KEY: 0ec69a8e86b311e963d45e85b133aa75")
//    @GET("listings/{id}")
//    Call<List<Listings>> getProfileData(
//            @Path("id") String siteId,
//            @Query("locale") String locale,
//            @Query("fields") String fields);
//
//    @GET("suggest")
//    Call<List<SuggestBaseResponse>> getAutoSuggestions(@Query("q") String query,
//                                                       @Query("lat") float latitude,
//                                                       @Query("lon") float longitude,
//                                                       @Query("locale") String locale);

}