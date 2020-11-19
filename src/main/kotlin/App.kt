import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.css.*
import react.*
import react.dom.*
import styled.css
import styled.styledDiv

external interface AppState : RState {
    var currentVideo: Video?
    var unwatchedVideos: List<Video>
    var watchedVideos: List<Video>
}

class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        unwatchedVideos = listOf()
        watchedVideos = listOf()

        val mainScope = MainScope()
        mainScope.launch {
            val videos = fetchVideos()
            setState {
                unwatchedVideos = videos
            }
        }
    }

    override fun RBuilder.render() {
        h1 {
            +"KotlinReact Project"
        }
        div {
            h3 {
                +"Videos to watch"
            }
            videoList {
                videos = state.unwatchedVideos
                selectedVideo = state.currentVideo
                onSelectVideo = { video ->
                    setState {
                        currentVideo = video
                    }
                }
            }

            h3 {
                +"Videos watched"
            }
            videoList {
                videos = state.watchedVideos
                selectedVideo = state.currentVideo
                onSelectVideo = { video ->
                    setState {
                        currentVideo = video
                    }
                }
            }
            state.currentVideo?.let { currentVideo ->
                videoPlayer {
                    video = currentVideo
                    unwatchedVideo = currentVideo in state.unwatchedVideos
                    onWatchedButtonPressed = {
                        if (kotlinx.css.video in state.unwatchedVideos) {
                            setState {
                                unwatchedVideos -= video
                                watchedVideos += video
                            }
                        } else {
                            setState {
                                watchedVideos -= video
                                unwatchedVideos += video
                            }
                        }
                    }
                }
            }
            state.currentVideo?.let { currentVideo ->
                button {
                    video = currentVideo
                    unwatchedVideo = currentVideo in state.unwatchedVideos
                    onWatchedButtonPressed = {
                        if (video in state.unwatchedVideos) {
                            setState {
                                unwatchedVideos -= video
                                watchedVideos += video
                            }
                        } else {
                            setState {
                                watchedVideos -= video
                                unwatchedVideos += video
                            }
                        }
                    }
                }
            }
        }
    }
}
