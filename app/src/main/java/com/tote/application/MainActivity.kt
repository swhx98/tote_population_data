package com.tote.application

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

//import com.androidnetworking.AndroidNetworking
//import com.androidnetworking.error.ANError
//import com.androidnetworking.interfaces.JSONArrayRequestListener


class MainActivity : AppCompatActivity(), MainActivityContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvNationTitle: TextView
    private lateinit var adapter: CustomAdapter
    private lateinit var presenter: MainActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainActivityPresenter(
            this,
            DataHandler(Volley.newRequestQueue(this))
        )
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        tvNationTitle = findViewById(R.id.nation_title)

        presenter.start()

    }

    override fun setAdapter(adapter: CustomAdapter) {
        this.adapter = adapter
        recyclerView.adapter = adapter
    }

    override fun setNationTitle(nationTitle: String) {
        this.tvNationTitle.text = nationTitle

    }
}