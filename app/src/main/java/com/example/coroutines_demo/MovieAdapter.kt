package com.example.coroutines_demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coroutines_demo.data.Movie

class MovieAdapter(val moviesList : MutableList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MovieHolder(view)

    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }


    class MovieHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var image = itemView.findViewById<ImageView>(R.id.image)

        fun  bind(movie : Movie) {
            Glide.with(itemView.context).load(movie.posterImage).into(image)
        }

    }


}