package `in`.movie.myapplication.fragments

import `in`.movie.myapplication.adapters.MovieRvAdapter
import `in`.movie.myapplication.viewModels.MovieViewModel
import `in`.movie.myapplication.models.Result
import `in`.movie.myapplication.databinding.FragmentFirstBinding
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : Fragment() {

    ;
    private var _binding: FragmentFirstBinding? = null
    private lateinit var adapter : MovieRvAdapter
    private var mDataset: ArrayList<Result> = ArrayList()
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
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.movieRv)
        binding.movieRv.layoutManager
        adapter = MovieRvAdapter(activity,mDataset)
        binding.movieRv.adapter = adapter
        return binding.root

    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagination()
        vm.getMovieData(pageNo.toString())
        setupObserver()
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setupObserver(){
        vm.liveData.observe(viewLifecycleOwner) {
            if (it != null) {
                mDataset.addAll(it.results)
                adapter.notifyDataSetChanged()
                canLoadMyFeed = true


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

