package com.tote.application

import org.json.JSONObject

class ToteDataPojo {

    private var idNation: String? = null
    private var nation: String? = null
    private var idYear: String? = null
    private var year: String? = null
    private var population: String? = null
    private var slugNation: String? = null

    fun parseJsonObject(jsonObject: JSONObject) {
        idNation = jsonObject.optString(ID_NATION)
        nation = jsonObject.optString(NATION)
        year = jsonObject.optString(YEAR)
        population = jsonObject.optString(POPULATION)
    }

    fun getIdNation() = idNation ?: "empty ID string"

    fun getNation() = nation ?: "empty nation string"

    fun getYear() = year ?: "empty year"

    fun getPopulation() = population ?: "empty population"

    companion object {
        const val ID_NATION = "ID Nation"
        const val NATION = "Nation"
        const val ID_YEAR = "ID Year"
        const val YEAR = "Year"
        const val POPULATION = "Population"
        const val SLUG_NATION = "Slug Nation"
    }
}