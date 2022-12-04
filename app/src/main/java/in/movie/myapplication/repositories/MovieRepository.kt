package `in`.movie.myapplication.repositories

import `in`.movie.myapplication.models.MovieData
import `in`.movie.myapplication.networkApis.ApiClient
import android.util.Log
import kotlinx.coroutines.delay

class MovieRepository {

    suspend fun  getMovieData( page: String) : List<MovieData>? {
        return ApiClient.startRequest()?.getMovieData("movieList.php",page)
    }

    suspend fun  getReviewData() : List<MovieData>? {
        return ApiClient.startRequest()?.getReviewData("ratingUpdate.php")
    }


}