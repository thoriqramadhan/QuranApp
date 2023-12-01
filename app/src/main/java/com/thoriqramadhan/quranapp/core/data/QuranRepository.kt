package com.thoriqramadhan.quranapp.core.data

import com.thoriqramadhan.quranapp.core.data.network.NetworkBoundResource
import com.thoriqramadhan.quranapp.core.data.network.NetworkResponse
import com.thoriqramadhan.quranapp.core.data.network.RemoteDataSource
import com.thoriqramadhan.quranapp.core.domain.model.QuranEdition
import com.thoriqramadhan.quranapp.network.QuranEditionItems
import com.thoriqramadhan.quranapp.core.domain.model.Surah
import com.thoriqramadhan.quranapp.core.domain.repository.IQuranRepository
import com.thoriqramadhan.quranapp.network.SurahItem
import com.thoriqramadhan.quranapp.utils.DataMapper
import kotlinx.coroutines.flow.Flow

class QuranRepository(private val remoteDataSource: RemoteDataSource) : IQuranRepository {
    override fun getListSurah() : Flow<Resource<List<Surah>>> {
        return object : NetworkBoundResource<List<Surah>, List<SurahItem>>(){
            override fun fetchFromNetwork(data: List<SurahItem>): Flow<List<Surah>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<SurahItem>>> {
                return remoteDataSource.getListSurah()
            }
        }.asFlow()
    }

    override fun getDetailSurahWithQuranEdition(number: Int): Flow<Resource<List<QuranEdition>>> {
        return object : NetworkBoundResource<List<QuranEdition>, List<QuranEditionItems>>(){

            override fun fetchFromNetwork(data: List<QuranEditionItems>): Flow<List<QuranEdition>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<QuranEditionItems>>> {
                return remoteDataSource.getDetailSurahWithQuranEditions(number)
            }
        }.asFlow()
    }
}