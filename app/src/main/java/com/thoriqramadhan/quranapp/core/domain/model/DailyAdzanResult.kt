package com.thoriqramadhan.quranapp.core.domain.model

import com.thoriqramadhan.quranapp.core.data.Resource

data class DailyAdzanResult(
    val listLocation : List<String>,
    val adzanTime: Resource<Jadwal>,
    val currentDate : List<String>
)
