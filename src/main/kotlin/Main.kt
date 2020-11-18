import react.dom.*
import kotlinx.browser.document
import kotlinx.css.*
import styled.*

data class Video(val id: Int, val title: String, val speaker: String, val videoUrl: String)

fun main() {
    val unwatchedVideos = listOf(
            Video(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
            Video(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
            Video(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
    )

    val watchedVideos = listOf(
            Video(4, "Mouseless development", "Jerry the Mouse", "https://youtu.be/PsaFVLr8t4E"),
            Video(5, "Catless development", "Tom the Cat", "https://youtu.be/PsaFVLr8r4E")
    )
    render(document.getElementById("root")) {
        h1 {
            +"KotlinReact Project"
        }
        div {
            h3 {
                +"Videos to watch"
            }
            for(video in unwatchedVideos) {
                p {
                    +"${video.speaker}: ${video.title}"
                }
            }
            h3 {
                +"Videos watched"
            }
            for(video in watchedVideos) {
                p {
                    +"${video.speaker}: ${video.title}"
                }
            }
        }
        styledDiv {
            css {
                position = Position.absolute
                top = 10.px
                right= 10.px
            }
            h3 {
                +"John Doe: Building and breaking things"
            }
            img {
                attrs {
                    src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
                }
            }
        }
    }
}
