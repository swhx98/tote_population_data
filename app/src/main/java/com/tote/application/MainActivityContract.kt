package com.tote.application

interface MainActivityContract {
    interface View {
        fun setAdapter(adapter: CustomAdapter)

        fun setNationTitle(nationTitle: String)
    }

    interface Presenter {
        fun start()
    }

}