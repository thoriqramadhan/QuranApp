package com.thoriqramadhan.quranapp.presentation.adzan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.thoriqramadhan.quranapp.core.data.AdzanRepository
import com.thoriqramadhan.quranapp.core.data.Resource
import com.thoriqramadhan.quranapp.core.domain.model.DailyAdzanResult

class AdzanViewModel(private val adzanRepository: AdzanRepository) : ViewModel() {
    fun getDailyAdzanTime(): LiveData<Resource<DailyAdzanResult>> =
        adzanRepository.getDailyAdzanTimeResult()


}