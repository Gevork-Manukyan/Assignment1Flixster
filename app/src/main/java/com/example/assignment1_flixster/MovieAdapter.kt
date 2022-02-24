package com.example.assignment1_flixster

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import okhttp3.HttpUrl.Companion.toHttpUrl

// Adapter is kinda like 'controller' in MVC

// The parameters are also the constructors (that's why there can be private val in parenthesis)
// RecyclerView.Adapter is an abstract class that is of type ViewHolder (in this case the viewholder inner class we defined)
class MovieAdapter(private val context: Context, private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    // End goal is to get the blueprint layout (item_movie.xml), make it into a view, and bind to it the corresponding data of a movie

    // Create and return a viewholder of the type viewholder we defined below (the inner class)
    // Returns essentially an empty/shell viewholder that needs to later be filled with data
    // The viewholder is defined by the view that is given to it (item_movie)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view);
    }

    // Given a viewholder and a position, take the data at the position and bind it into that viewholder
    // Takes the empty/shell viewholder and attaches to it the data of the movie at specific position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    // Inline function
    // Number of items in dataset
    override fun getItemCount() = movies.size


    // View is like 'view' in MVC
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // tv = textView
        // iv = imageView
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)

        // Get references to the individual components in the itemView (imageView and two textViews)
        // and populate them with the correct data in the movie object
        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            Glide.with(itemView).load(movie.posterImageURL).into(ivPoster)
        }

    }
}
