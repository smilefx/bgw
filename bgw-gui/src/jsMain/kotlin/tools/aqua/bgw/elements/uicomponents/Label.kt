package tools.aqua.bgw.elements.uicomponents

import ComponentViewData
import LabelData
import UIComponentData
import csstype.PropertiesBuilder
import web.cssom.*
import data.event.DragEventAction
import data.event.KeyEventAction
import emotion.react.css
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import react.*
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import tools.aqua.bgw.builder.ReactConverters.toDragEventData
import tools.aqua.bgw.builder.ReactConverters.toKeyEventData
import tools.aqua.bgw.builder.ReactConverters.toMouseEnteredData
import tools.aqua.bgw.builder.ReactConverters.toMouseEventData
import tools.aqua.bgw.builder.ReactConverters.toMouseExitedData
import tools.aqua.bgw.builder.VisualBuilder
import tools.aqua.bgw.elements.*
import tools.aqua.bgw.event.JCEFEventDispatcher
import tools.aqua.bgw.event.applyCommonEventHandlers
import tools.aqua.bgw.handlers
import tools.aqua.bgw.internalSocket
import web.dom.Element

external interface LabelProps : Props {
    var data: LabelData
}

fun PropertiesBuilder.cssBuilderIntern(componentViewData: LabelData) {
    cssBuilder(componentViewData)
}

fun PropertiesBuilder.cssTextBuilderIntern(componentViewData: LabelData) {
    cssTextBuilder(componentViewData)
}

val Label = FC<LabelProps> { props ->
    bgwLabel {
        tabIndex = 0
        id = props.data.id
        className = ClassName("label")
        css {
            cssBuilderIntern(props.data)
        }

        bgwVisuals {
            className = ClassName("visuals")
            +VisualBuilder.build(props.data.visual)
        }

        bgwText {
            className = ClassName("text")
            css {
                cssTextBuilderIntern(props.data)
            }
            +props.data.text
        }

        applyCommonEventHandlers(props.data)
    }
}

inline val bgwLabel: IntrinsicType<HTMLAttributes<Element>>
    get() = "bgw_label".unsafeCast<IntrinsicType<HTMLAttributes<Element>>>()