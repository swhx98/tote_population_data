package com.tote.application

import com.android.volley.Response
import java.text.DecimalFormat
import java.text.NumberFormat

class MainActivityPresenter(
    private val view: MainActivityContract.View,
    private val dataHandler: DataHandler
) : MainActivityContract.Presenter, Response.Listener<String> {
    override fun start() {
        dataHandler.makeCallToApi(this)
    }

    override fun onResponse(response: String) {
        view.setAdapter(CustomAdapter(dataHandler.getList()))
        view.setNationTitle(dataHandler.getList()[0].getNation())
    }
}