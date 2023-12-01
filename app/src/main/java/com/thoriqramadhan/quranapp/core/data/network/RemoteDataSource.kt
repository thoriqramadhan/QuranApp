package com.thoriqramadhan.quranapp.core.data.network

import android.util.Log
import com.thoriqramadhan.quranapp.network.QuranApiService
import com.thoriqramadhan.quranapp.network.QuranEditionItems
import com.thoriqramadhan.quranapp.network.SurahItem
import com.thoriqramadhan.quranapp.network.adzan.AdzanApiService
import com.thoriqramadhan.quranapp.network.adzan.CityItem
import com.thoriqramadhan.quranapp.network.adzan.JadwalItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val quranApiService: QuranApiService,
    private val adzanApiService: AdzanApiService
) {
    suspend fun getListSurah() : Flow<NetworkResponse<List<SurahItem>>> =
        flow {
            try {
                val surahResponse = quranApiService.getListSurah()
                val listSurah = surahResponse.listSurah
                emit(NetworkResponse.Success(listSurah))
            }catch (e: Exception){
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName,"error" + e.localizedMessage)
            }

        }.flowOn(Dispatchers.IO)

    suspend fun getDetailSurahWithQuranEditions(number: Int) : Flow<NetworkResponse<List<QuranEditionItems>>> =
        flow {
            try {
                val ayahResponse = quranApiService.getDetailSurahWithQuranEditions(number)
                val quranEdition = ayahResponse.quranEditionItems
                emit(NetworkResponse.Success(quranEdition))
            }catch (e: Exception){
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, "Error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun searchCity(city: String): Flow<NetworkResponse<List<CityItem>>> =
        flow {
            try {
                val cityResponse = adzanApiService.searchCity(city)
                val cities = cityResponse.listCity
                emit(NetworkResponse.Success(cities))
            }catch (e: Exception){
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, "error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDailyAdzanTime(
        id: String,
        year: String,
        month: String,
        date: String
    ): Flow<NetworkResponse<JadwalItem>> = flow<NetworkResponse<JadwalItem>> {
        try {
            val dailyResponse = adzanApiService.getDailyAdzanTime(id, year, month, date)
            val jadwalResponse = dailyResponse.data.jadwal
            emit(NetworkResponse.Success(jadwalResponse))
        } catch (e: Exception) {
            emit(NetworkResponse.Error(e.toString()))
            Log.e(RemoteDataSource::class.java.simpleName, "error " + e.localizedMessage)
        }
    }.flowOn(Dispatchers.IO)
}
