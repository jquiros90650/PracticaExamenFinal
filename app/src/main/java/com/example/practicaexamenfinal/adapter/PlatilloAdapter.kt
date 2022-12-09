package com.example.practicaexamenfinal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicaexamenfinal.model.Platillo


class PlatilloAdapter : RecyclerView.Adapter<PlatilloAdapter.PlatilloViewHolder>(){
    //Se crea una lista para almacenar la informacion de los platillos
    private var listaPlatillos = emptyList<Platillo>()

    inner class PlatilloViewHolder(private val itemBinding:PlatilloFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root){
        fun bind(platillo: Platillo) {
            itemBinding.tvNombre.text = platillo.nombre

            if(platillo.rutaImagen?.isNotEmpty() == true){
                Glide.with(itemBinding.root.context)
                    .load(platillo.rutaImagen)
                    .into(itemBinding.imagen)
            }
            //Evento enviar update
            itemBinding.vistaFila.setOnClickListener{
                val accion = HomeFragmentDirections.actionHomeFragmentToUpdateLugarFragment(platillo)
                itemView.findNavController().navigate(accion)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatilloViewHolder {
        val itemBinding = PlatilloFilaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return PlatilloViewHolder(itemBinding)
    }
    //Para "dibujar" la información de cada lugar...
    override fun onBindViewHolder(holder: PlatilloViewHolder, position: Int) {
        val platilloActual = listaPlatillos[position]
        holder.bind(platilloActual)
    }
    override fun getItemCount(): Int {
        return listaPlatillos.size
    }
    fun setData(platillos: List<Platillo>) {
        this.listaPlatillos = platillos
        notifyDataSetChanged() //Provoca que se redibuje la lista
    }
}