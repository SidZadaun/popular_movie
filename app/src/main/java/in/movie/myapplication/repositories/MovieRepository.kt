package `in`.movie.myapplication.repositories

import `in`.movie.myapplication.models.MovieData
import `in`.movie.myapplication.networkApis.ApiClient

class MovieRepository {

    suspend fun  getMovieData( page: String) : MovieData? {
        return ApiClient.startRequest()?.getMovieData("/top_rated",page)
    }

}