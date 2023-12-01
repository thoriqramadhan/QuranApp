package com.thoriqramadhan.quranapp.core.domain.repository

import com.thoriqramadhan.quranapp.core.data.Resource
import com.thoriqramadhan.quranapp.core.domain.model.QuranEdition
import com.thoriqramadhan.quranapp.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {

    fun getListSurah() : Flow<Resource<List<Surah>>>

    fun getDetailSurahWithQuranEdition(number: Int) : Flow<Resource<List<QuranEdition>>>
}