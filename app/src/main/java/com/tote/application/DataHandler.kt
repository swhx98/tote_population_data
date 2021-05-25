package com.tote.application

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import java.util.ArrayList

class DataHandler(private val queue: RequestQueue,
                  private val list: ArrayList<ToteDataPojo> = ArrayList()) {

    private lateinit var response: JSONObject

    @JvmName("getList")
    fun getList() = list

    fun makeCallToApi(callback: Response.Listener<String>) {
        val dataHandlerListener = Response.Listener<String> {
            setDataHandlerFields(it)
            callback.onResponse(it)
        }
        queue.add(
            StringRequest(Request.Method.GET, URL, dataHandlerListener,{ }))
    }

    private fun populateListWithResponseData() {
        val data = response.getJSONArray(DATA)
        for (i in 0 until data.length()) {
            val toteDataPojo = ToteDataPojo()
            toteDataPojo.parseJsonObject(data.getJSONObject(i))
            list.add(toteDataPojo)
        }
    }

    private fun setDataHandlerFields(response: String) {
        this.response = JSONObject(response)
        populateListWithResponseData()
    }

    private companion object {
        const val DATA = "data"
        const val URL = "https://datausa.io/api/data?drilldowns=Nation&measures=Population"
    }
}