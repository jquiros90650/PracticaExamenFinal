package com.example.practicaexamenfinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.practicaexamenfinal.data.PlatilloDao
import com.example.practicaexamenfinal.model.Platillo
import com.example.practicaexamenfinal.repository.PlatilloRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val getAllData: MutableLiveData<List<Platillo>>
    private val repository: PlatilloRepository = PlatilloRepository(PlatilloDao())

    init{
        getAllData = repository.getAllData
    }

    fun addLugar(platillo: Platillo){
        repository.addPlatillo(platillo)
    }

    fun deleteLugar(platillo: Platillo){
        repository.deletePlatillo(platillo)
    }

}