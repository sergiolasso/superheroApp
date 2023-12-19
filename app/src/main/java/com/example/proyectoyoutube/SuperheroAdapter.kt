package com.example.proyectoyoutube

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperheroAdapter(var superheroList  : List<SuperHeroItemResponse> = emptyList(), private val onItemSelected:(String) -> Unit) : RecyclerView.Adapter<SuperheroViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        return SuperheroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    fun updateList(lista: List<SuperHeroItemResponse>){

        superheroList = lista
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {

         return superheroList.size
    }

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        val item = superheroList[position]
        holder.bind(item, onItemSelected)
    }


}