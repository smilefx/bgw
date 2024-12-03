package tools.aqua.bgw.elements.layoutviews

import CameraPaneData
import InternalCameraPaneData
import csstype.PropertiesBuilder
import web.cssom.ClassName
import web.cssom.number
import emotion.react.css
import kotlinx.browser.document
import kotlinx.browser.window
import web.timers.setInterval
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLUnknownElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.events.WheelEvent
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML.div
import tools.aqua.bgw.builder.LayoutNodeBuilder
import tools.aqua.bgw.builder.NodeBuilder
import tools.aqua.bgw.builder.VisualBuilder
import tools.aqua.bgw.elements.uicomponents.ButtonProps
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.round
import kotlin.random.Random
import web.cssom.*
import data.event.KeyEventAction
import data.event.internal.ScrollChangedEventData
import data.event.internal.ZoomChangedEventData
import emotion.css.css
import react.*
import react.dom.html.ReactHTML.h1
import tools.aqua.bgw.*
import tools.aqua.bgw.builder.ReactConverters.toKeyEventData
import tools.aqua.bgw.builder.ReactConverters.toMouseEventData
import tools.aqua.bgw.elements.*
import tools.aqua.bgw.event.JCEFEventDispatcher
import web.cssom.atrule.width
import web.dom.Element

internal val CameraPaneNew = FC<CameraPaneProps> { props ->
    TransformWrapper {
        style = jsObject {
            width = "100%"
            height = "100%"
            position = "relative"
            overflow = "hidden"
        }

        TransformComponent {
            bgwCameraContent {
                css {
                    width = fit()
                    height = fit()
                    minWidth = 100.pct
                    minHeight = 100.pct
                    // padding = 100.cqh
                    display = Display.flex
                    justifyContent = JustifyContent.center
                    alignItems = AlignItems.center
                    position = Position.absolute
                    left = 0.px
                    top = 0.px
                    overflow = Overflow.hidden
                }

                bgwVisuals {
                    className = ClassName("visuals")
                    +VisualBuilder.build(props.data.visual)
                }

                if (props.data.target != null) {
                    bgwCameraTarget {
                        id = props.data.target?.id + "Target"
                        className = ClassName("target")
                        +props.data.target?.let { LayoutNodeBuilder.build(it) }
                    }
                }
            }
        }
    }
}