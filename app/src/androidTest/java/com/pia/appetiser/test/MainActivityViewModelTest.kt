package com.pia.appetiser.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pia.appetiser.test.data.entity.mapper.toDisplayableItunesDetails
import com.pia.appetiser.test.domain.appusecase.*
import com.pia.appetiser.test.domain.model.ItunesDetailEntity
import com.pia.appetiser.test.presentation.ui.main.MainActivityViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityViewModelTest {

//    @get: Rule
//    @JvmField
//    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainActivityViewModel

    @MockK
    lateinit var loadTVShowsUseCase: LoadTVShowsUseCase

    @MockK
    lateinit var loadFeaturedMoviesUseCase: LoadFeaturedMoviesUseCase

    @MockK
    lateinit var loadTopMusicUseCase: LoadTopMusicUseCase

    @MockK
    lateinit var updateTopMusicUseCase: UpdateTopMusicUseCase

    @MockK
    lateinit var updateTVShowsUseCase: UpdateTVShowsUseCase

    @MockK
    lateinit var updateFeaturedMoviesUseCase: UpdateFeaturedMoviesUseCase


    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = MainActivityViewModel(loadTVShowsUseCase,loadFeaturedMoviesUseCase,loadTopMusicUseCase,updateTopMusicUseCase,updateTVShowsUseCase,updateFeaturedMoviesUseCase)
    }

    @Test
    fun loadTvShows() {
        val movies = generateDummyMovies()
        val mappedMovies = movies.map { it.toDisplayableItunesDetails() }
        val values = Single.just(mappedMovies)

        every { loadTVShowsUseCase.invoke() } returns values
        viewModel.loadTVShows()
        Thread.sleep(1000)
        print("viewmodel po = "+viewModel.tvShowList.value)
        Assert.assertTrue(viewModel.tvShowList.value == Result.success(mappedMovies))
    }

    @Test
    fun loadTopMusic() {
        val movies = generateDummyMovies()
        val mappedMovies = movies.map { it.toDisplayableItunesDetails() }
        val values = Single.just(mappedMovies)

        every { loadTopMusicUseCase.invoke() } returns values
        viewModel.loadTVShows()
        Thread.sleep(1000)
        Assert.assertTrue(viewModel.topMusicList.value == Result.success(values))
    }

    @Test
    fun loadFeaturedMovies() {
        val movies = generateDummyMovies()
        val mappedMovies = movies.map { it.toDisplayableItunesDetails() }
        val values = Single.just(mappedMovies)

        every { loadFeaturedMoviesUseCase.invoke() } returns values
        viewModel.loadTVShows()
        Thread.sleep(1000)
        Assert.assertTrue(viewModel.featuredMovieList.value == Result.success(values))
    }

    @Test
    fun refresh(){
        val value = Completable.complete()

        every { updateTVShowsUseCase.invoke()} returns value
        every { updateFeaturedMoviesUseCase.invoke()} returns value
        every { updateTopMusicUseCase.invoke()} returns value
        viewModel.refresh()
        Thread.sleep(1000)
        Assert.assertTrue(viewModel.isDataUpdated.value == Result.success(true))
    }


    private fun generateDummyMovies(): List<ItunesDetailEntity> {
        val movie1 = ItunesDetailEntity(
            wrapperType = "track",
            kind = "feature-movie",
            artistId = 345346894,
            trackId = 349046103,
            artistName = "Ron Underwood",
            trackName = "Heart and Souls",
            artistViewUrl = "https://itunes.apple.com/au/artist/20th-century-fox-film/345346894?uo=4",
            trackViewUrl = "https://itunes.apple.com/au/movie/where-the-heart-is/id273382037?uo=4",
            previewUrl = "https://video-ssl.itunes.apple.com/itunes-assets/Video118/v4/5d/87/94/5d879472-fb61-f602-057a-8df4fa5d82c1/mzvf_4859464661014510400.640x480.h264lc.U.p.m4v",
            artworkUrl100 = "https://is5-ssl.mzstatic.com/image/thumb/Video/2d/87/15/mzl.botxulgc.jpg/100x100bb.jpg",
            collectionPrice = 13.99,
            trackPrice = 13.99,
            releaseDate = "2000-09-26T07:00:00Z",
            trackTimeMillis = 7198752,
            primaryGenreName = "Comedy",
            contentAdvisoryRating = "M",
            shortDescription = "When college freshman, Chris (Jacob Elordi) meets senior Sam (Tiera Skovbye), they quickly fall in",
            longDescription = "When college freshman, Chris (Jacob Elordi) meets senior Sam (Tiera Skovbye), they quickly fall in love and look ahead to a lifetime together. Meanwhile, businessman Jorge (Adan Canto) meets flight attendant Leslie (Radha Mitchell), and they embark on their own love story. Whilst Chris and Sam’s romance unfolds, Jorge is struggling with a progressive illness, and soon these two love stories dramatically intersect, forcing painful decisions to be made. Based on a true story and starring an incredible cast, 2 Hearts is a touching story of romance, compassion and sacrifice."
        )

        val movie2 = ItunesDetailEntity(
            wrapperType = "track",
            kind = "feature-movie",
            artistId = 345346894,
            trackId = 349046103,
            artistName = "Ron Underwood",
            trackName = "Heart and Souls",
            artistViewUrl = "https://itunes.apple.com/au/artist/20th-century-fox-film/345346894?uo=4",
            trackViewUrl = "https://itunes.apple.com/au/movie/where-the-heart-is/id273382037?uo=4",
            previewUrl = "https://video-ssl.itunes.apple.com/itunes-assets/Video118/v4/5d/87/94/5d879472-fb61-f602-057a-8df4fa5d82c1/mzvf_4859464661014510400.640x480.h264lc.U.p.m4v",
            artworkUrl100 = "https://is5-ssl.mzstatic.com/image/thumb/Video/2d/87/15/mzl.botxulgc.jpg/100x100bb.jpg",
            collectionPrice = 13.99,
            trackPrice = 13.99,
            releaseDate = "2000-09-26T07:00:00Z",
            trackTimeMillis = 7198752,
            primaryGenreName = "Comedy",
            contentAdvisoryRating = "M",
            shortDescription = "When college freshman, Chris (Jacob Elordi) meets senior Sam (Tiera Skovbye), they quickly fall in",
            longDescription = "When college freshman, Chris (Jacob Elordi) meets senior Sam (Tiera Skovbye), they quickly fall in love and look ahead to a lifetime together. Meanwhile, businessman Jorge (Adan Canto) meets flight attendant Leslie (Radha Mitchell), and they embark on their own love story. Whilst Chris and Sam’s romance unfolds, Jorge is struggling with a progressive illness, and soon these two love stories dramatically intersect, forcing painful decisions to be made. Based on a true story and starring an incredible cast, 2 Hearts is a touching story of romance, compassion and sacrifice."
        )

        val movie3 = ItunesDetailEntity(
            wrapperType = "track",
            kind = "feature-movie",
            artistId = 345346894,
            trackId = 349046103,
            artistName = "Ron Underwood",
            trackName = "Heart and Souls",
            artistViewUrl = "https://itunes.apple.com/au/artist/20th-century-fox-film/345346894?uo=4",
            trackViewUrl = "https://itunes.apple.com/au/movie/where-the-heart-is/id273382037?uo=4",
            previewUrl = "https://video-ssl.itunes.apple.com/itunes-assets/Video118/v4/5d/87/94/5d879472-fb61-f602-057a-8df4fa5d82c1/mzvf_4859464661014510400.640x480.h264lc.U.p.m4v",
            artworkUrl100 = "https://is5-ssl.mzstatic.com/image/thumb/Video/2d/87/15/mzl.botxulgc.jpg/100x100bb.jpg",
            collectionPrice = 13.99,
            trackPrice = 13.99,
            releaseDate = "2000-09-26T07:00:00Z",
            trackTimeMillis = 7198752,
            primaryGenreName = "Comedy",
            contentAdvisoryRating = "M",
            shortDescription = "When college freshman, Chris (Jacob Elordi) meets senior Sam (Tiera Skovbye), they quickly fall in",
            longDescription = "When college freshman, Chris (Jacob Elordi) meets senior Sam (Tiera Skovbye), they quickly fall in love and look ahead to a lifetime together. Meanwhile, businessman Jorge (Adan Canto) meets flight attendant Leslie (Radha Mitchell), and they embark on their own love story. Whilst Chris and Sam’s romance unfolds, Jorge is struggling with a progressive illness, and soon these two love stories dramatically intersect, forcing painful decisions to be made. Based on a true story and starring an incredible cast, 2 Hearts is a touching story of romance, compassion and sacrifice."
        )

        return listOf(movie1,movie2,movie3)
    }


}