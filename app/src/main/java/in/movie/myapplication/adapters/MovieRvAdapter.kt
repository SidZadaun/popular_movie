package `in`.movie.myapplication.adapters

import `in`.movie.myapplication.databinding.MovieRvItemBinding
import `in`.movie.myapplication.models.MovieData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieRvAdapter(
    private var mDataset: ArrayList<MovieData>,
) : RecyclerView.Adapter<MovieRvAdapter.MovieViewHolder>() {

    private lateinit var binding: MovieRvItemBinding


    class MovieViewHolder(binding: MovieRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
                binding = MovieRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
           return MovieViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        binding.title.text = mDataset[position].title
        binding.year.text = mDataset[position].id.toString()
        binding.desc.text = mDataset[position].plot
        binding.rate.text = mDataset[position].rating.toString()
    }

    override fun getItemCount(): Int {
       return mDataset.size
    }

        fun setData(data: List<MovieData>) {
            mDataset.run {
                clear()
                addAll(data)
        }
    }

}
