package com.example.coroutines_demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.example.coroutines_demo.data.Movie
import org.json.JSONException


class MainActivity : AppCompatActivity() {

    private lateinit var  rv : RecyclerView
    var movieList = mutableListOf<Movie>()
    var requestQueue : RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()

    }

    fun setupView(){
        rv = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = MovieAdapter(mutableListOf())
        requestQueue = VolleyInstance.getmInstance(this)?.requestQueue?:null
        fetchMovies()

    }

    private fun fetchMovies() {
        val url = "https://www.json-generator.com/api/json/get/cfsXpFGwwO?indent=2"
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                for (i in 0 until response.length()) {
                    try {
                        val jsonObject = response.getJSONObject(i)
                        val title = jsonObject.getString("title")
                        val overview = jsonObject.getString("overview")
                        val poster = jsonObject.getString("poster")
                        val rating = jsonObject.getDouble("rating")
                        val movie = Movie(title, poster, overview, rating)
                        movieList.add(movie)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    val adapter = MovieAdapter(movieList)
                    rv.setAdapter(adapter)
                }
            }
        ) { error -> Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show() }
        requestQueue?.add(jsonArrayRequest)
    }
}