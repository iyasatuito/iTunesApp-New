package com.pia.appetiser.test.domain.appusecase

import com.pia.appetiser.test.data.entity.mapper.toDisplayableItunesDetails
import com.pia.appetiser.test.data.entity.mapper.toItunesDetailEntity
import com.pia.appetiser.test.data.source.repository.ItunesRepository
import com.pia.appetiser.test.domain.params.SearchItunesParam
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject



class SearchItunesUseCase @Inject constructor(
    private val itunesRepository: ItunesRepository
)  {
    operator fun invoke(params: SearchItunesParam) : Single<List<DisplayableItunesDetails>>  =
        itunesRepository
            .searchItunesTracks(params.term, params.country, params.media)
            .subscribeOn(Schedulers.io())
            .map { it.results.map { it.toItunesDetailEntity() }.map { it.toDisplayableItunesDetails() } }
}