package com.example.assignment1_flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException


private const val API_KEY = "e34b1cb04a474e9600a7d1c5ef07069f"

class MainActivity : AppCompatActivity() {

    private val movies = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=$API_KEY"
        val TAG = "CallAPI"

        val client = AsyncHttpClient()
        client.get(NOW_PLAYING_URL, object : JsonHttpResponseHandler() {


            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "onSuccess $json")

                try {
                    val moviesJSONArr = json.jsonObject.getJSONArray("results")
                    movies.addAll(Movie.fromJsonArray(moviesJSONArr))

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception Encountered: $e")
                }
            }
        })
    }
}

