package com.thoriqramadhan.quranapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thoriqramadhan.quranapp.databinding.ItemAyahBinding
import com.thoriqramadhan.quranapp.core.domain.model.Ayah
import com.thoriqramadhan.quranapp.core.domain.model.QuranEdition
import com.thoriqramadhan.quranapp.network.AyahsItem
import com.thoriqramadhan.quranapp.network.QuranEditionItems

class SurahAdapter : RecyclerView.Adapter<SurahAdapter.MyViewHolder>() {
    private val listAyah = ArrayList<Ayah>()
    private val quranEditionItems = ArrayList<QuranEdition>()
    private var onItemClickCallback : OnItemClickCallback? = null
    class MyViewHolder(val binding: ItemAyahBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder (
        ItemAyahBinding.inflate(LayoutInflater.from(parent.context), parent,false)
    )

    override fun getItemCount() = listAyah.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataAyah = listAyah[position]
        val quranAudio = quranEditionItems[1].ayahs.get(position)
        val quranIndonesia = quranEditionItems[2].ayahs.get(position)
        holder.binding.apply {
            itemNumberAyah.text = dataAyah.numberInSurah.toString()
            itemAyah.text = dataAyah.text
            itemTranslation.text = quranIndonesia.text
            this.root.setOnClickListener {
                quranAudio.let { data -> onItemClickCallback?.onItemClicked(data) }
            }
        }
    }

    fun setData(dataAyahs: List<Ayah>?, dataQuranEditionItems: List<QuranEdition>?){
        if (dataAyahs == null || dataQuranEditionItems == null) return
        listAyah.clear()
        listAyah.addAll(dataAyahs)
        quranEditionItems.clear()
        quranEditionItems.addAll(dataQuranEditionItems)
    }

    fun setOnItemClicked(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Ayah)
    }

}