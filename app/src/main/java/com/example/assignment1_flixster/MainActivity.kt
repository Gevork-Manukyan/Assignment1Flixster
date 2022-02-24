package com.example.assignment1_flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException


private const val API_KEY = "e34b1cb04a474e9600a7d1c5ef07069f"
private const val NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=$API_KEY"
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val movies = mutableListOf<Movie>()

    // 1. Define a dat model class as the data source
    // 2. Add the RecyclerView to the layout
    // 3. Create a custom row layout XML file to visualize the item
    // 4. Create an Adapter and ViewHolder to render the item
    // 5. bind the adapter to the data source to populate the RecyclerView
    // 6. Bind a layout manager to the RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiCall()

    }

    fun apiCall() {
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

