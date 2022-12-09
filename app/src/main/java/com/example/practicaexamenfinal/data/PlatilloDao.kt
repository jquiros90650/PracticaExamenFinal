package com.example.practicaexamenfinal.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.practicaexamenfinal.model.Platillo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase


class PlatilloDao {
    //Firebase Vars
    private var codigoUsuario: String
    private var firestore: FirebaseFirestore

    init {
        codigoUsuario = Firebase.auth.currentUser?.email.toString()
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getAllData(): MutableLiveData<List<Platillo>> {
        val listaPlatillo = MutableLiveData<List<Platillo>>()
        firestore
            .collection("platillosPractica")
            .document(codigoUsuario)
            .collection("misPlatillos")
            .addSnapshotListener{ snapshot, e ->
                if(e!=null){
                    return@addSnapshotListener
                }
                if(snapshot != null){
                    val lista = ArrayList<Platillo>()
                    val platillos = snapshot.documents
                    platillos.forEach{
                        val platillo = it.toObject(Platillo::class.java)
                        if(platillo != null){
                            lista.add(platillo)
                        }
                    }
                    listaPlatillo.value = lista
                }
            }
        return listaPlatillo
    }

    fun addLugar(platillo: Platillo){
        val document: DocumentReference
        if(platillo.id.isEmpty()){
            //Agregar
            document = firestore
                .collection("platillosPractica")
                .document(codigoUsuario)
                .collection("misPlatillos")
                .document()
            platillo.id = document.id
        }else{
            //Modificar
            document = firestore
                .collection("platillosPractica")
                .document(codigoUsuario)
                .collection("misPlatillos")
                .document(platillo.id)
        }
        document.set(platillo)
            .addOnCompleteListener{
                Log.d("guardarPlatillo","Guardado con exito")
            }
            .addOnCompleteListener{
                Log.e("guardarPlatillos","Error al guardar")
            }
    }

    fun deleteLugar(platillo: Platillo){
        if(platillo.id.isNotEmpty()){
            firestore
                .collection("platillosPractica")
                .document(codigoUsuario)
                .collection("misPlatillos")
                .document(platillo.id)
                .delete()
                .addOnCompleteListener{
                    Log.d("eliminarPlatillo","Eliminado con exito")
                }
                .addOnCanceledListener {
                    Log.e("eliminarPlatillo", "Error al eliminar")
                }
        }
    }


}