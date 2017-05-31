package ec.solmedia.themoviedb;

import ec.solmedia.themoviedb.api.TheMovieDBAPI
import ec.solmedia.themoviedb.api.TheMovieDBDataResponse
import ec.solmedia.themoviedb.api.TheMovieDBResponse
import ec.solmedia.themoviedb.model.Media
import ec.solmedia.themoviedb.view.feature.MediaManager
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers.anyInt
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import retrofit2.Call
import retrofit2.Response
import rx.observers.TestSubscriber

class MediaManagerTest {
    var testSub = TestSubscriber<Media>()
    var apiMock = mock<TheMovieDBAPI>()
    var callMock = mock<Call<TheMovieDBResponse>>()

    @Before
    fun setup() {
        testSub = TestSubscriber<Media>()
        apiMock = mock<TheMovieDBAPI>()
        callMock = mock<Call<TheMovieDBResponse>>()
        `when`(apiMock.get(anyString(), anyInt())).thenReturn(callMock)
    }

    @Test
    fun testSuccess_basic() {
        //prepare
        val mediaResponse = TheMovieDBResponse(1, listOf())
        val response = Response.success(mediaResponse)

        `when`(callMock.execute()).thenReturn(response)

        //call
        val mediaManager = MediaManager(apiMock)
        mediaManager.get(anyString(), anyInt()).subscribe(testSub)

        //assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertCompleted()
    }

    @Test
    fun testSuccess_checkOneMedia() {
        //prepare
        val mediaData = TheMovieDBDataResponse(
                "id",
                "title",
                "overview",
                5.5f,
                7,
                "poster_path",
                "backdrop_path"
        )

        //prepare
        val mediaResponse = TheMovieDBResponse(1, listOf(mediaData))
        val response = Response.success(mediaResponse)

        `when`(callMock.execute()).thenReturn(response)

        //call
        val mediaManager = MediaManager(apiMock)
        mediaManager.get(anyString(), anyInt()).subscribe(testSub)

        //assert
        testSub.assertNoErrors()
        testSub.assertValueCount(1)
        testSub.assertCompleted()

        assert(testSub.onNextEvents[0].mediaItems[0].title == mediaData.title)
        assert(testSub.onNextEvents[0].mediaItems[0].overView == mediaData.overview)
    }

    @Test
    fun testError() {
        //prepare
        val responseError = Response.error<TheMovieDBResponse>(500,
                ResponseBody.create(MediaType.parse("application/json"), ""))
        `when`(callMock.execute()).thenReturn(responseError)

        //call
        val mediaManager = MediaManager(apiMock)
        mediaManager.get(anyString(), anyInt()).subscribe(testSub)

        // assert
        assert(testSub.onErrorEvents.size == 1)
    }

}

inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)