package com.pia.appetiser.test.presentation.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.pia.appetiser.test.R
import com.pia.appetiser.test.presentation.AppActivity
import com.pia.appetiser.test.presentation.common.adapter.ItunesMediaAdapter
import com.pia.appetiser.test.presentation.common.ext.getViewModel
import com.pia.appetiser.test.presentation.common.ext.observe
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import com.pia.appetiser.test.presentation.navigation.DetailScreenNavigator
import com.pia.appetiser.test.presentation.ui.common.ResultState
import com.pia.appetiser.test.presentation.ui.search.SearchItunesActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : AppActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var detailScreenNavigator: DetailScreenNavigator

    private val featuredItunesMediaAdapter = ItunesMediaAdapter(::onItunesItemClicked)

    private val topMusicAdapter = ItunesMediaAdapter(::onItunesItemClicked)


    private val viewModel by lazy { getViewModel<MainActivityViewModel>(viewModelFactory) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
        loadContents()
        observeViewModel()
    }

    private fun loadContents() {
        viewModel.refresh()
    }

    private fun observeViewModel() {
        with(viewModel) {
            observe(featuredMovieList, ::updateFeaturedMovies)
            observe(topMusicList, ::displayTopMusic)
            observe(isDataUpdated, ::handleLoadingOfData)
        }
    }

    private fun handleLoadingOfData(result: ResultState<Boolean>?) {
        if (result == null) return
        when (result) {
            is ResultState.Loading -> progress_circular.visibility = View.VISIBLE
            is ResultState.Success -> {
                if (result.data) {
                    viewModel.loadFeaturedMovies()
                    viewModel.loadTopMusic()
                } else toast(R.string.something_went_wrong_error_message).show()
            }
            is ResultState.Error -> {
                viewModel.loadFeaturedMovies()
                viewModel.loadTopMusic()
                toast(R.string.cached_data_message).show()
            }
        }
    }


    private fun setView() {
        toolbar.setOnClickListener {
            startActivity(intentFor<SearchItunesActivity>())
        }

        featured_movies_recycler.isNestedScrollingEnabled = false
        featured_movies_recycler.apply {
            adapter = featuredItunesMediaAdapter
        }

        top_music_recycler.isNestedScrollingEnabled = false
        top_music_recycler.apply {
            adapter = topMusicAdapter
        }
    }

    private fun updateFeaturedMovies(result: ResultState<List<DisplayableItunesDetails>>?) {
        if (result == null) return
        when (result) {
            is ResultState.Loading -> progress_circular.visibility = View.VISIBLE
            is ResultState.Success -> featuredItunesMediaAdapter.setItems(result.data)
            is ResultState.Error -> toast(R.string.something_went_wrong_error_message).show()
        }
    }

    private fun displayTopMusic(result: ResultState<List<DisplayableItunesDetails>>?) {
        if (result == null) return
        when (result) {
            is ResultState.Loading -> progress_circular.visibility = View.VISIBLE
            is ResultState.Success -> topMusicAdapter.setItems(result.data)
            is ResultState.Error -> toast(R.string.something_went_wrong_error_message).show()
        }
    }

    private fun onItunesItemClicked(itunesResponse: DisplayableItunesDetails) {
        detailScreenNavigator.navigate(itunesResponse)
    }
}
