package com.example.assignment1_flixster

import org.json.JSONArray

// data class -> when you wanna convert object from json to class

// Class Constructor
data class Movie(
    // Parameters
    val movieId: Int,
    val title: String,
    val overview: String,

    // Makes variable private not parameter
    private val posterPath: String,
)
//Class Body
{
    // URL base was given
    // More info found here -> https://api.themoviedb.org/3/configuration?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed
    val posterImageURL = "https://image.tmdb.org/t/p/w342/$posterPath"

    // Methods on Movie class you can call without having an instance of Movie
    // ex) if in other class you can do: Movie.fromJsonArray()
    companion object {
        fun fromJsonArray(moviesJSONArr: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()

            for (i in 0 until moviesJSONArr.length()) {
                val singleMovie = moviesJSONArr.getJSONObject(i)
                movies.add(

                    // Create and pass instance of Movie
                    Movie(
                        singleMovie.getInt("id"),
                        singleMovie.getString("title"),
                        singleMovie.getString("overview"),
                        singleMovie.getString("poster_path")
                    )
                )
            }

            return movies;
        }
    }
}
