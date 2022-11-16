package `in`.movie.myapplication.networkApis

import `in`.movie.myapplication.models.MovieData
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    //movieData
    @GET("3/movie{key}")
    suspend fun getMovieData(@Path("key", encoded = true) key: String,@Query("page", encoded = true)pageNo: String) : MovieData



}