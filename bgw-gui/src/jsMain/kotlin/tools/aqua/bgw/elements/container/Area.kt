package tools.aqua.bgw.elements.container

import AreaData
import ComponentViewData
import PaneData
import csstype.*
import emotion.react.css
import org.w3c.dom.HTMLDivElement
import react.*
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import tools.aqua.bgw.builder.NodeBuilder
import tools.aqua.bgw.builder.VisualBuilder
import tools.aqua.bgw.elements.bgwContents
import tools.aqua.bgw.elements.bgwVisuals
import tools.aqua.bgw.elements.cssBuilder
import tools.aqua.bgw.handlers

external interface AreaProps : Props {
    var data : AreaData
}

fun PropertiesBuilder.cssBuilderIntern(componentViewData: AreaData) {
    cssBuilder(componentViewData)
}

val Area = FC<AreaProps> { props ->
    bgwArea {
        id = props.data.id
        className = ClassName("area")
        css {
            cssBuilderIntern(props.data)
        }

        +VisualBuilder.build(props.data.visual)

        bgwContents {
            className = ClassName("components")
            props.data.components.forEach {
                +NodeBuilder.build(it)
            }
        }
    }
}

inline val bgwArea: IntrinsicType<HTMLAttributes<HTMLDivElement>>
    get() = "bgw_area".unsafeCast<IntrinsicType<HTMLAttributes<HTMLDivElement>>>()