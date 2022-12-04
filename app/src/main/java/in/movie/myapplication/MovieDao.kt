package `in`.movie.myapplication

import `in`.movie.myapplication.models.MovieData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

class MovieDao {
    @Dao
    interface MovieDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun upsert(movieData: MovieData) : Long

    }

}