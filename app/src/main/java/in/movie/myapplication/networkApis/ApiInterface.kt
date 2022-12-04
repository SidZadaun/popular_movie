package `in`.movie.myapplication.networkApis

import `in`.movie.myapplication.models.MovieData
import retrofit2.http.*


interface ApiInterface {

    //movieData
    @GET("hiring/assignment/{key}")
    suspend fun getMovieData(@Path("key", encoded = true) key: String,@Query("page", encoded = true)pageNo: String) : List<MovieData>

    //reviewData
    @GET("hiring/assignment/{key}")
    suspend fun getReviewData(@Path("key", encoded = true) key: String) : List<MovieData>


}