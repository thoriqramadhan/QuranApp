package com.thoriqramadhan.quranapp.presentation.quran


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.thoriqramadhan.quranapp.core.data.QuranRepository
import com.thoriqramadhan.quranapp.core.data.Resource
import com.thoriqramadhan.quranapp.core.domain.model.QuranEdition
import com.thoriqramadhan.quranapp.core.domain.model.Surah

class QuranViewModel(private val quranRepository: QuranRepository) : ViewModel() {
    fun getListSurah(): LiveData<Resource<List<Surah>>> = quranRepository.getListSurah().asLiveData()

    fun getDetailSurahWithQuranEdition(number: Int) : LiveData<Resource<List<QuranEdition>>> =
        quranRepository.getDetailSurahWithQuranEdition(number).asLiveData()
}

