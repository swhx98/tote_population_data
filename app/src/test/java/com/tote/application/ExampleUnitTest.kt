package com.tote.application

import com.android.volley.RequestQueue
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ExampleUnitTest {

    @MockK
    private lateinit var dataHandler: DataHandler
    @MockK
    private lateinit var queue: RequestQueue
    @MockK
    private lateinit var request: StringRequest
    @MockK
    private lateinit var view: MainActivityContract.View
    @MockK
    private lateinit var list: ArrayList<ToteDataPojo>
    @MockK
    private lateinit var toteDataPojo: ToteDataPojo

    private lateinit var presenter: MainActivityPresenter

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        presenter = MainActivityPresenter(view, dataHandler)
    }

    @Test
    fun `test data handler deals with bad response`() {
        val slot = slot<Response.Listener<String>>()
        every {
            queue.add(StringRequest(Request.Method.GET, any(), capture(slot), any()))
        } answers {
            request
            slot.captured.onResponse()
        }
        presenter = MainActivityPresenter(view, DataHandler(queue))
        presenter.start()
    }

    @Test
    fun `test set adapter and set title are called on view on start`() {
        every { dataHandler.getList() } returns list
        every { list[any()] } returns toteDataPojo
        every { toteDataPojo.getNation() } returns NATION
        val slot = slot<Response.Listener<String>>()
        every { dataHandler.makeCallToApi(capture(slot)) } answers {
            slot.captured.onResponse(CALLBACK)
        }
        presenter.start()
        verify { view.setAdapter(any()) }
        verify { view.setNationTitle(NATION) }
    }

    companion object {
        private const val NATION = "Nation"
        private const val CALLBACK = "callback"
    }
}