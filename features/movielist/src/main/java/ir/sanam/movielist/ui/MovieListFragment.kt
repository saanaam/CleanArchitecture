package ir.sanam.movielist.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.sanam.common.base.BaseFragment
import ir.sanam.common.base.BaseViewModel

import ir.sanam.movielist.R
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MovieListFragment : BaseFragment() {
    override fun getViewModel(): BaseViewModel = viewModel

    private val viewModel: MovieViewModel by sharedViewModel()

//    private lateinit var binding: ir.astiag.beforetrip.databinding.FragmentTripsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }


}
