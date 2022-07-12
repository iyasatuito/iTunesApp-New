package com.pia.appetiser.test.data.entity

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("wrapperType")
    val wrapperType: String,
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("artistId")
    val artistId: Long?,
    @SerializedName("collectionId")
    val collectionId: Long? = null,
    @SerializedName("trackId")
    val trackId: Long,
    @SerializedName("artistName")
    val artistName: String? = null,
    @SerializedName("collectionName")
    val collectionName: String? = null,
    @SerializedName("trackName")
    val trackName: String?,
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String? = null,
    @SerializedName("trackCensoredName")
    val trackCensoredName: String? = null,
    @SerializedName("artistViewUrl")
    val artistViewUrl: String? = null,
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String? = null,
    @SerializedName("trackViewUrl")
    val trackViewUrl: String? = null,
    @SerializedName("previewUrl")
    val previewUrl: String? = null,
    @SerializedName("artworkUrl30")
    val artworkUrl30: String? = null,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String? = null,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String? = null,
    @SerializedName("collectionPrice")
    val collectionPrice: Double,
    @SerializedName("trackPrice")
    val trackPrice: Double,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String? = null,
    @SerializedName("trackExplicitness")
    val trackExplicitness: String? = null,
    @SerializedName("discCount")
    val discCount: Int = 0,
    @SerializedName("discNumber")
    val discNumber: Int = 0,
    @SerializedName("trackCount")
    val trackCount: Int = 0,
    @SerializedName("trackNumber")
    val trackNumber: Int = 0,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int = 0,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String,
    @SerializedName("isStreamable")
    val isStreamable: Boolean? = false,
    @SerializedName("contentAdvisoryRating")
    val contentAdvisoryRating: String? = null,
    @SerializedName("shortDescription")
    val shortDescription: String?,
    @SerializedName("longDescription")
    val longDescription: String?,
    @SerializedName("hasITunesExtras")
    val hasITunesExtras: Boolean? = false
)