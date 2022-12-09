package com.example.practicaexamenfinal.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Platillo (
    var id: String?,
    var nombre: String?,
    var lugarVenta: String?
    ): Parcelable{
        constructor():
                this("","","")
}