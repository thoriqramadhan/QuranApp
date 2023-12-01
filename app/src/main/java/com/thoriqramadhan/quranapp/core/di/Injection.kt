package com.thoriqramadhan.quranapp.core.di

import android.content.Context
import com.thoriqramadhan.quranapp.core.data.AdzanRepository
import com.thoriqramadhan.quranapp.core.data.network.RemoteDataSource
import com.thoriqramadhan.quranapp.core.data.QuranRepository
import com.thoriqramadhan.quranapp.core.data.local.CalendarPreferences
import com.thoriqramadhan.quranapp.core.data.local.LocationPreferences
import com.thoriqramadhan.quranapp.network.ApiConfig

object Injection {
    val quranApiService = ApiConfig.quranApiConfig
    val adzanApiService = ApiConfig.adzanApiConfig
    val remoteDataSource = RemoteDataSource(quranApiService, adzanApiService)

    fun provideQuranRepository(): QuranRepository {
        return QuranRepository(remoteDataSource)
    }


    fun provideAdzanRepository(context: Context): AdzanRepository{
        val locationPreferences = LocationPreferences(context)
        val calendarPreferences = CalendarPreferences()
        return AdzanRepository(remoteDataSource, locationPreferences,calendarPreferences )
    }
}