package `in`.movie.myapplication.viewModels

import `in`.movie.myapplication.models.MovieData
import `in`.movie.myapplication.repositories.MovieRepository
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

     private val movieRepository : MovieRepository = MovieRepository()
       val liveData : MutableLiveData<List<MovieData>> = MutableLiveData()
     val liveData2 : MutableLiveData<List<MovieData>> = MutableLiveData()


     fun getMovieData(page:String) = viewModelScope.launch {
          val movieList = movieRepository.getMovieData(page)
          liveData.value = movieList!!
     }


     fun getReviewData() = viewModelScope.launch {
          val ratingList = movieRepository.getReviewData()
          liveData2.value = ratingList!!
     }



}