package com.pia.appetiser.test.data.entity

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("wrapperType")
    val wrapperType: String,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("artistId")
    val artistId: Long?,
    @SerializedName("collectionId")
    val collectionId: Long?,
    @SerializedName("trackId")
    val trackId: Long,
    @SerializedName("artistName")
    val artistName: String?,
    @SerializedName("collectionName")
    val collectionName: String?,
    @SerializedName("trackName")
    val trackName: String?,
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String?,
    @SerializedName("trackCensoredName")
    val trackCensoredName: String?,
    @SerializedName("artistViewUrl")
    val artistViewUrl: String?,
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String?,
    @SerializedName("trackViewUrl")
    val trackViewUrl: String?,
    @SerializedName("previewUrl")
    val previewUrl: String?,
    @SerializedName("artworkUrl30")
    val artworkUrl30: String?,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String?,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String?,
    @SerializedName("collectionPrice")
    val collectionPrice: Double,
    @SerializedName("trackPrice")
    val trackPrice: Double,
    @SerializedName("releaseDate")
    val releaseDate: String,
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String,
    @SerializedName("trackExplicitness")
    val trackExplicitness: String,
    @SerializedName("discCount")
    val discCount: Int,
    @SerializedName("discNumber")
    val discNumber: Int,
    @SerializedName("trackCount")
    val trackCount: Int,
    @SerializedName("trackNumber")
    val trackNumber: Int,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String,
    @SerializedName("isStreamable")
    val isStreamable: Boolean?,
    @SerializedName("contentAdvisoryRating")
    val contentAdvisoryRating: String?,
    @SerializedName("shortDescription")
    val shortDescription: String?,
    @SerializedName("longDescription")
    val longDescription: String?,
    @SerializedName("hasITunesExtras")
    val hasITunesExtras: Boolean?
)