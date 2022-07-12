package com.pia.appetiser.test

import com.pia.appetiser.test.data.entity.PagedResponse
import com.pia.appetiser.test.data.entity.ResultResponse
import com.pia.appetiser.test.data.entity.mapper.toDisplayableItunesDetails
import com.pia.appetiser.test.data.entity.mapper.toItunesDetailEntity
import com.pia.appetiser.test.data.source.repository.ItunesRepository
import com.pia.appetiser.test.domain.appusecase.SearchItunesUseCase
import com.pia.appetiser.test.domain.params.SearchItunesParam
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class SearchItunesUseCaseTest {

    lateinit var useCase: SearchItunesUseCase

    @MockK
    lateinit var repository: ItunesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        useCase = SearchItunesUseCase(repository)
    }

    @Test
    fun `Given_the_user_typed_love_when_searching_then_movie_results_should_contain_titles_with_love`() {
        val movies = generateDummyMovies()
        val mappedMovies = movies.results.map {
            it.toItunesDetailEntity()
        }.map {
            it.toDisplayableItunesDetails()
        }

        val sampleParam = SearchItunesParam("love", "au", "movie")
        val values = Single.just(movies)

        every { repository.searchItunesTracks("love", "au", "movie") } returns values

        useCase(sampleParam)
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertValue(mappedMovies)
            .dispose()

        verify(exactly = 1) { repository.searchItunesTracks("love", "au", "movie") }
        mappedMovies.forEach {
            Assert.assertTrue(it.trackName?.contains("love", ignoreCase = true) ?: false)
        }
        Assert.assertEquals(3, mappedMovies.size)
    }

    private fun generateDummyMovies(): PagedResponse<ResultResponse> {
        val movie1 = ResultResponse(
            wrapperType = "track",
            artworkUrl30 = "",
            artworkUrl60 = "",
            kind = "feature-movie",
            artistId = 345346894,
            trackId = 349046103,
            artistName = "Ron Underwood",
            trackName = "Love and Other Drugs",
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

        val movie2 = ResultResponse(
            wrapperType = "track",
            kind = "feature-movie",
            artistId = 345346894,
            trackId = 349046103,
            artistName = "Ron Underwood",
            trackName = "Love All Around",
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

        val movie3 = ResultResponse(
            wrapperType = "track",
            kind = "feature-movie",
            artistId = 345346894,
            trackId = 349046103,
            artistName = "Ron Underwood",
            trackName = "My Forever Love",
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

        val list: List<ResultResponse> = listOf(movie1, movie2, movie3)
        return PagedResponse(1, list, 1, 1)
    }
}