package com.pia.appetiser.test.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pia.appetiser.test.domain.appusecase.LoadFeaturedMoviesUseCase
import com.pia.appetiser.test.domain.appusecase.LoadTopMusicUseCase
import com.pia.appetiser.test.domain.appusecase.UpdateFeaturedMoviesUseCase
import com.pia.appetiser.test.domain.appusecase.UpdateTopMusicUseCase
import com.pia.appetiser.test.presentation.common.arch.BaseViewModel
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import com.pia.appetiser.test.presentation.ui.common.ResultState
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 *
 * @property refresh initially retrieves data from remote source
 * @property loadFeaturedMovies loads successfully stored movies data from remote
 * @property loadTopMusic loads successfully stored music data from remote
 */

class MainActivityViewModel @Inject constructor(
    private val loadFeaturedMoviesUseCase: LoadFeaturedMoviesUseCase,
    private val loadTopMusicUseCase: LoadTopMusicUseCase,
    private val updateTopMusicUseCase: UpdateTopMusicUseCase,
    private val updateFeaturedMoviesUseCase: UpdateFeaturedMoviesUseCase
) : BaseViewModel() {

    private val featuredMovieListResultLiveData =
        MutableLiveData<ResultState<List<DisplayableItunesDetails>>>()
    val featuredMovieList: LiveData<ResultState<List<DisplayableItunesDetails>>> =
        featuredMovieListResultLiveData

    private val tvShowListResultLiveData =
        MutableLiveData<ResultState<List<DisplayableItunesDetails>>>()
    val tvShowList: LiveData<ResultState<List<DisplayableItunesDetails>>> = tvShowListResultLiveData

    private val topMusicListResultLiveData =
        MutableLiveData<ResultState<List<DisplayableItunesDetails>>>()
    val topMusicList: LiveData<ResultState<List<DisplayableItunesDetails>>> =
        topMusicListResultLiveData

    private val isDataLoadedLiveData = MutableLiveData<ResultState<Boolean>>()
    val isDataUpdated: LiveData<ResultState<Boolean>> = isDataLoadedLiveData


    fun refresh() {
        updateFeaturedMoviesUseCase()
            .doOnSubscribe { isDataLoadedLiveData.postValue(ResultState.Loading()) }
            .observeOn(AndroidSchedulers.mainThread())
            .andThen(updateTopMusicUseCase())
            .subscribe(
                { isDataLoadedLiveData.postValue(ResultState.Success(true)) },
                { isDataLoadedLiveData.postValue(ResultState.Error(it)) }
            ).run(::addToDisposables)
    }

    fun loadFeaturedMovies() {
        loadFeaturedMoviesUseCase()
            .doOnSubscribe { featuredMovieListResultLiveData.postValue(ResultState.Loading()) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    featuredMovieListResultLiveData.postValue(ResultState.Success((it)))
                },
                {
                    featuredMovieListResultLiveData.postValue(ResultState.Error(it))
                }
            ).run(::addToDisposables)
    }

    fun loadTopMusic() {
        loadTopMusicUseCase()
            .doOnSubscribe { topMusicListResultLiveData.postValue(ResultState.Loading()) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    topMusicListResultLiveData.postValue(ResultState.Success((it)))
                },
                {
                    topMusicListResultLiveData.postValue(ResultState.Error(it))
                }
            ).run(::addToDisposables)
    }
}