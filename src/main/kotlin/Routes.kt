import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

suspend fun fetchVideo(id: Int): Video {
    val responsePromise = window.fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
    val response = responsePromise.await()
    val jsonPromise = response.json()
    val json = jsonPromise.await()
    return json.unsafeCast<Video>()
}

suspend fun fetchVideos(): List<Video> = coroutineScope {
    (1..25).map { id ->
        async {
            fetchVideo(id)
        }
    }.awaitAll()
}
