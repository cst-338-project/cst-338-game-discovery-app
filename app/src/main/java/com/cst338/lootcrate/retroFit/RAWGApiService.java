package com.cst338.lootcrate.retroFit;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RAWGApiService {
    @GET("games") // Call to /games endpoint
    Call<GamesResponse> getGames(
            @Query("key") String apiKey,
            @Query("page") int page,
            @Query("page_size") int pageSize
    );
}
