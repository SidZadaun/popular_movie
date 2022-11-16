package `in`.movie.myapplication.adapters

import `in`.movie.myapplication.R
import `in`.movie.myapplication.models.Result
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieRvAdapter(
    activity: FragmentActivity?,
    private var mDataset: ArrayList<Result>,
) : RecyclerView.Adapter<MovieRvAdapter.ViewHolder>() {

    private var activity: Activity? = activity


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView
        var desc: TextView

        init {
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.desc)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh: ViewHolder

            val view4: View = LayoutInflater.from(parent.context).inflate(
                R.layout.movie_rv_item, parent, false)
            vh = ViewHolder(view4)
           return vh

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = mDataset[position].title
        holder.desc.text = mDataset[position].overview
        Glide.with(activity!!).load("https://image.tmdb.org/t/p/w342"+ mDataset[position].posterPath).into(holder.image)
    }

    override fun getItemCount(): Int {
        return if (mDataset.size>0){
            mDataset.size
        }else{
            return 0
        }

    }




}
