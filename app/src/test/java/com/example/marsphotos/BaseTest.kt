import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source

open class BaseTest {

    val mockWebServer = MockWebServer()
// enqueue function takes a file from our test resources and turns it into a fake API response.
    fun enqueue(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val buffer = inputStream.source().buffer()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(buffer.readString(Charsets.UTF_8))
        )
    }
}