import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.backgroundColor
import kotlinx.css.display
import kotlinx.html.js.onClickFunction
import react.*
import styled.css
import styled.styledButton


class Button : RComponent<VideoPlayerProps, RState>() {
    override fun RBuilder.render() {
        styledButton {
            css {
                display = Display.block
                backgroundColor = if(props.unwatchedVideo) Color.lightGreen else Color.red
            }
            attrs {
                onClickFunction = {
                    props.onWatchedButtonPressed(props.video)
                }
            }
            if(props.unwatchedVideo) {
                +"Mark as watched"
            }
            else {
                +"Mark as unwatched"
            }
        }
    }

}

fun RBuilder.button(handler: VideoPlayerProps.() -> Unit): ReactElement {
    return child(Button::class) {
        this.attrs(handler)
    }
}
