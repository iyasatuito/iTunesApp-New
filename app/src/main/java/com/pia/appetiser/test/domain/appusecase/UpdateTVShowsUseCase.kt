package com.pia.appetiser.test.domain.appusecase

import com.pia.appetiser.test.data.source.repository.ItunesRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class UpdateTVShowsUseCase @Inject constructor(
    private val itunesRepository: ItunesRepository
)  {
    operator fun invoke() : Completable =
        itunesRepository
            .updateTVShows()
            .subscribeOn(Schedulers.io())

}

