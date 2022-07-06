package com.pia.appetiser.test.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import com.pia.appetiser.test.domain.appusecase.*
import com.pia.appetiser.test.presentation.common.arch.BaseViewModel
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import javax.inject.Inject
import kotlin.random.Random

/**
 *
 * @property refresh initially retrieves data from remote source
 * @property loadFeaturedMovies loads successfully stored movies data from remote
 * @property loadTopMusic loads successfully stored music data from remote
 * @property loadTVShows loads successfully stored tv shows data from remote
 */

class MainActivityViewModel @Inject constructor(
    private val loadTVShowsUseCase: LoadTVShowsUseCase,
    private val loadFeaturedMoviesUseCase: LoadFeaturedMoviesUseCase,
    private val loadTopMusicUseCase: LoadTopMusicUseCase,
    private val updateTopMusicUseCase: UpdateTopMusicUseCase,
    private val updateTVShowsUseCase: UpdateTVShowsUseCase,
    private val updateFeaturedMoviesUseCase: UpdateFeaturedMoviesUseCase
) : BaseViewModel() {


    private val coverItemResultLiveData = MutableLiveData<Result<DisplayableItunesDetails>>()
    val coverItem: LiveData<Result<DisplayableItunesDetails>> = coverItemResultLiveData

    private val featuredMovieListResultLiveData = MutableLiveData<Result<List<DisplayableItunesDetails>>>()
    val featuredMovieList: LiveData<Result<List<DisplayableItunesDetails>>> = featuredMovieListResultLiveData

    private val tvShowListResultLiveData = MutableLiveData<Result<List<DisplayableItunesDetails>>>()
    val tvShowList: LiveData<Result<List<DisplayableItunesDetails>>>  = tvShowListResultLiveData

    private val topMusicListResultLiveData = MutableLiveData<Result<List<DisplayableItunesDetails>>>()
    val topMusicList: LiveData<Result<List<DisplayableItunesDetails>>> = topMusicListResultLiveData

    private val IsDataLoadedLiveData = MutableLiveData<Result<Boolean>>()
    val isDataUpdated: LiveData<Result<Boolean>> = IsDataLoadedLiveData


    fun refresh(){
        updateTVShowsUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .andThen(updateFeaturedMoviesUseCase())
            .andThen(updateTopMusicUseCase())
            .subscribe(
                {IsDataLoadedLiveData.postValue(Result.success(true))},
                {IsDataLoadedLiveData.postValue(Result.failure(it))}
            ).run(::addToDisposables)

    }

    fun loadFeaturedMovies(){
        loadFeaturedMoviesUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    featuredMovieListResultLiveData.postValue(Result.success(it))
                    coverItemResultLiveData.postValue(Result.success(it[Random.nextInt(0, it.size)]))
                },
                {
                    featuredMovieListResultLiveData.postValue(Result.failure(Throwable("Error")))
                    coverItemResultLiveData.postValue(Result.failure(Throwable("Error")))
                }
            ).run(::addToDisposables)
    }

    fun loadTopMusic(){
        loadTopMusicUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    topMusicListResultLiveData.postValue(Result.success(it))
                },
                {
                    topMusicListResultLiveData.postValue(Result.failure(Throwable("Error")))
                }
            ).run(::addToDisposables)
    }


    fun loadTVShows(){
        loadTVShowsUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { tvShowListResultLiveData.postValue(Result.success(it)) },
                { tvShowListResultLiveData.postValue(Result.failure(Throwable("Error"))) }
            ).run(::addToDisposables)
    }


}