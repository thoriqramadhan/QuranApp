package com.thoriqramadhan.quranapp.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thoriqramadhan.quranapp.core.di.Injection
import com.thoriqramadhan.quranapp.presentation.quran.QuranViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(val context: Context) : ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass: Class<T>) : T =
        when{
            modelClass.isAssignableFrom(QuranViewModel::class.java) -> {
                QuranViewModel(Injection.provideQuranRepository()) as T
            }
            modelClass.isAssignableFrom(AdzanViewModel::class.java) -> {
                AdzanViewModel(Injection.provideAdzanRepository(context)) as T
            }
            else -> throw Throwable("Unknow ViewModel class" + modelClass.name)
        }
}