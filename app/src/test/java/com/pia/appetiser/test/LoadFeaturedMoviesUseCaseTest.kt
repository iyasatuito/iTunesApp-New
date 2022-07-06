package com.pia.appetiser.test

import com.pia.appetiser.test.data.entity.mapper.toDisplayableItunesDetails
import com.pia.appetiser.test.data.source.repository.ItunesRepository
import com.pia.appetiser.test.domain.appusecase.LoadFeaturedMoviesUseCase
import com.pia.appetiser.test.domain.model.ItunesDetailEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class LoadFeaturedMoviesUseCaseTest {


    lateinit var useCase: LoadFeaturedMoviesUseCase

    @MockK
    lateinit var repository: ItunesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this,relaxed = true)
        useCase = LoadFeaturedMoviesUseCase(repository)
    }

    @Test
    fun `Given_featured_movies_are_not_null_when_fetched_then_results_has_data`() {
        val movies = generateDummyMovies()
        val mappedMovies = movies.map { it.toDisplayableItunesDetails() }
        val values = Single.just(movies)

        every { repository.getFeaturedMovies() } returns values
        useCase()
            .test()
            .awaitDone(3,TimeUnit.SECONDS)
            .assertValue(mappedMovies)
            .dispose()

        verify(exactly = 1) {repository.getFeaturedMovies()}

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