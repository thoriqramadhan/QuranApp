package com.thoriqramadhan.quranapp.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AyahResponse(

	@Json(name="code")
	val code: Int? = null,

	@Json(name="data")
	val quranEditionItems: List<QuranEditionItems>,

	@Json(name="status")
	val status: String? = null
)
@JsonClass(generateAdapter = true)
data class Edition(

	@Json(name="identifier")
	val identifier: String? = null,

	@Json(name="englishName")
	val englishName: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="format")
	val format: String? = null,

	@Json(name="language")
	val language: String? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="direction")
	val direction: String? = null
)
@JsonClass(generateAdapter = true)
data class AyahsItem(

	@Json(name="number")
	val number: Int? = null,


	@Json(name="text")
	val text: String? = null,

	@Json(name="page")
	val page: Int? = null,


	@Json(name="numberInSurah")
	val numberInSurah: Int? = null,

	@Json(name="audio")
	val audio: String? = null
)
@JsonClass(generateAdapter = true)
data class QuranEditionItems(

	@Json(name="number")
	val number: Int? = null,

	@Json(name="englishName")
	val englishName: String? = null,

	@Json(name="numberOfAyahs")
	val numberOfAyahs: Int? = null,

	@Json(name="revelationType")
	val revelationType: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="ayahs")
	val ayahs: List<AyahsItem>,

	@Json(name="englishNameTranslation")
	val englishNameTranslation: String? = null
)
