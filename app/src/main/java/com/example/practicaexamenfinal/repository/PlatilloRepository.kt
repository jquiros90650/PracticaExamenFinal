package com.example.practicaexamenfinal.repository

import androidx.lifecycle.MutableLiveData
import com.example.practicaexamenfinal.data.PlatilloDao
import com.example.practicaexamenfinal.model.Platillo

class PlatilloRepository (private val platilloDao: PlatilloDao){

    fun addPlatillo(platillo: Platillo){
        platilloDao.addLugar(platillo)
    }

    fun deletePlatillo(platillo: Platillo){
        platilloDao.deleteLugar(platillo)
    }

    val getAllData: MutableLiveData<List<Platillo>> = platilloDao.getAllData()
}