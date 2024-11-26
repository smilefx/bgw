package tools.aqua.bgw.elements.gamecomponentviews

import ComponentViewData
import HexagonViewData
import PaneData
import csstype.PropertiesBuilder
import data.event.DragEventAction
import web.cssom.*
import data.event.KeyEventAction
import emotion.react.css
import kotlinx.browser.document
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import react.*
import react.dom.aria.ariaDescribedBy
import react.dom.aria.ariaDisabled
import react.dom.aria.ariaPressed
import react.dom.aria.ariaRoleDescription
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.dom.svg.ReactSVG
import tools.aqua.bgw.*
import tools.aqua.bgw.builder.NodeBuilder
import tools.aqua.bgw.builder.ReactConverters.toDragEventData
import tools.aqua.bgw.builder.ReactConverters.toKeyEventData
import tools.aqua.bgw.builder.ReactConverters.toMouseEnteredData
import tools.aqua.bgw.builder.ReactConverters.toMouseEventData
import tools.aqua.bgw.builder.ReactConverters.toMouseExitedData
import tools.aqua.bgw.builder.VisualBuilder
import tools.aqua.bgw.elements.bgwVisuals
import tools.aqua.bgw.elements.cssBuilder
import tools.aqua.bgw.event.JCEFEventDispatcher
import tools.aqua.bgw.event.applyCommonEventHandlers
import web.dom.Element
import web.timers.Timeout
import web.timers.clearTimeout
import web.timers.setTimeout
import kotlin.math.sqrt
import kotlin.random.Random

external interface HexagonViewProps : Props {
    var data : HexagonViewData
}

fun PropertiesBuilder.cssBuilderIntern(componentViewData: HexagonViewData) {
    cssBuilder(componentViewData)
    justifyContent = JustifyContent.center
    alignItems = AlignItems.center
}

val HexagonView = FC<HexagonViewProps> { props ->
    val draggable = useDraggable(object : DraggableOptions {
        override var id: String = props.data.id
    })
    var droppable : DroppableResult? = null

    if(props.data.isDroppable) {
        droppable = useDroppable(object : DroppableOptions {
            override var id: String = props.data.id
        })
    }

    val elementRef = useRef<Element>(null)

    bgwHexagonView {
        tabIndex = 0
        id = props.data.id
        className = ClassName("hexagonView")

        ref = elementRef
        useEffect {
            elementRef.current?.let { draggable.setNodeRef(it) }
        }

        if(props.data.isDroppable) {
            useEffect {
                elementRef.current?.let { droppable!!.setNodeRef(it) }
            }
        }

        css {
            cssBuilderIntern(props.data)
            width = (sqrt(3.0) * props.data.size).em
            height = 2 * props.data.size.em
            translate = "${draggable.transform?.x?.px ?: 0.px} ${draggable.transform?.y?.px ?: 0.px}".unsafeCast<Translate>()
            cursor = if(props.data.isDraggable) Cursor.pointer else Cursor.default
        }

        bgwVisuals {
            className = ClassName("visuals")
            +VisualBuilder.build(props.data.visual)
        }

        if(props.data.isDraggable) {
            onPointerDown = {
                draggable.listeners.onPointerDown.invoke(it, props.data.id)
            }
        }

        applyCommonEventHandlers(props.data)

        ariaDescribedBy = draggable.attributes.ariaDescribedBy
        ariaDisabled = draggable.attributes.ariaDisabled
        ariaPressed = draggable.attributes.ariaPressed
        ariaRoleDescription = draggable.attributes.ariaRoleDescription
    }
}

inline val bgwHexagonView: IntrinsicType<HTMLAttributes<Element>>
    get() = "bgw_hexagon_view".unsafeCast<IntrinsicType<HTMLAttributes<Element>>>()