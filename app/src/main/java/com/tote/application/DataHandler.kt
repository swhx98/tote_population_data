package com.tote.application

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class DataHandler(
    private val queue: RequestQueue,
    private val list: ArrayList<ToteDataPojo> = ArrayList()
) {

    private lateinit var response: JSONObject

    @JvmName("getList")
    fun getList() = list

    fun makeCallToApi(callback: Response.Listener<String>) {
        val dataHandlerListener = Response.Listener<String> {
            setDataHandlerFields(it)
            callback.onResponse(it)
        }
        queue.add(
            StringRequest(Request.Method.GET, URL, dataHandlerListener, { })
        )
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
        try {
            this.response = JSONObject(response)
            populateListWithResponseData()
        } catch(jsonException: JSONException){
            jsonErrorScenario()
        }
    }

    private fun jsonErrorScenario() {
        val errorPojo = ToteDataPojo()
        val errorObject = JSONObject()
        errorObject.put(ToteDataPojo.NATION, "json error")
        errorObject.put(ToteDataPojo.YEAR, "error")
        errorObject.put(ToteDataPojo.POPULATION, "error")
        errorPojo.parseJsonObject(errorObject)
        list.add(errorPojo)
    }

    private companion object {
        const val DATA = "data"
        const val URL = "https://datausa.io/api/data?drilldowns=Nation&measures=Population"
    }
}