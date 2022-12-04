package `in`.movie.myapplication.fragments

import `in`.movie.myapplication.adapters.MovieRvAdapter
import `in`.movie.myapplication.databinding.FragmentFirstBinding
import `in`.movie.myapplication.models.MovieData
import `in`.movie.myapplication.viewModels.MovieViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var adapter : MovieRvAdapter
    private var mDataset: ArrayList<MovieData> = ArrayList()
    private var mDataset2: ArrayList<MovieData> = ArrayList()

    private val binding get() = _binding!!
    private var pageNo = 1
    private var canLoadMyFeed = true

    private val vm: MovieViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.movieRv.layoutManager = layoutManager
        binding.movieRv.layoutManager
        adapter = MovieRvAdapter(mDataset)
        binding.movieRv.adapter = adapter
        return binding.root

    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        pagination()
        vm.getMovieData(pageNo.toString())
        pollingReviews()
        setupObserver()
        setupObserver2()
    }


    private fun pollingReviews(){
        lifecycleScope.launch{
        while (true) {
            delay(5000)
            vm.getReviewData()
        } }
    }




    @SuppressLint("NotifyDataSetChanged")
    fun setupObserver(){
        vm.liveData.observe(viewLifecycleOwner) {
            if (it != null) {
                mDataset.addAll(it)
                adapter.notifyDataSetChanged()
                canLoadMyFeed = true
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupObserver2(){
        vm.liveData2.observe(viewLifecycleOwner) {
            if (it != null) {
                mDataset2.clear()
                mDataset2.addAll(it)
                for (i in 0 until mDataset.size) {
                    for (j in 0 until mDataset2.size) {
                        if(mDataset[i].id.toString() == mDataset[j].id.toString()){
                            Log.d("ljghksdjgh2",mDataset2[i].id.toString() +"    "+mDataset2[i].rating.toString() )
                            mDataset[i].rating = mDataset2[j].rating
                            adapter.setData(mDataset)
                            adapter.notifyDataSetChanged()

                        }
                    }
                }
            }
        }
    }



    private fun pagination(){
        binding.movieRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    //check for scroll down
                    val visibleItemCount = Objects.requireNonNull(recyclerView.layoutManager)!!.childCount
                    val totalItemCount = recyclerView.layoutManager!!.itemCount
                    val pastVisiblesItems =  (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
                    if (canLoadMyFeed) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            canLoadMyFeed = false
                            pageNo += 1
                          vm.getMovieData(pageNo.toString())
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

