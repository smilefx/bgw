package tools.aqua.bgw.elements.container

import CardStackData
import LinearLayoutData
import csstype.PropertiesBuilder
import web.cssom.*
import data.event.KeyEventAction
import emotion.react.css
import org.w3c.dom.HTMLDivElement
import react.*
import react.dom.html.HTMLAttributes
import tools.aqua.bgw.builder.NodeBuilder
import tools.aqua.bgw.builder.ReactConverters.toKeyEventData
import tools.aqua.bgw.builder.ReactConverters.toMouseEventData
import tools.aqua.bgw.builder.VisualBuilder
import tools.aqua.bgw.elements.alignmentBuilder
import tools.aqua.bgw.elements.bgwContents
import tools.aqua.bgw.elements.bgwVisuals
import tools.aqua.bgw.elements.cssBuilder
import tools.aqua.bgw.event.JCEFEventDispatcher
import web.dom.Element

external interface CardStackProps : Props {
    var data : CardStackData
}

fun PropertiesBuilder.cssBuilderIntern(componentViewData: CardStackData) {
    cssBuilder(componentViewData)
}

val CardStack = FC<CardStackProps> { props ->
    bgwCardStack {
        id = props.data.id
        className = ClassName("cardStack")
        css {
            cssBuilderIntern(props.data)
        }

        bgwVisuals {
            className = ClassName("visuals")
            +VisualBuilder.build(props.data.visual)
        }

        bgwContents {
            className = ClassName("components")
            css {
                width = 100.pct
                height = 100.pct
                display = Display.flex
                alignmentBuilder(props.data)
            }

            props.data.components.forEach {
                +NodeBuilder.build(it)
            }
        }

        onContextMenu = {
            it.preventDefault()
            JCEFEventDispatcher.dispatchEvent(it.toMouseEventData(id)) 
        }
        onClick = { JCEFEventDispatcher.dispatchEvent(it.toMouseEventData(id)) }
        onKeyDown = { JCEFEventDispatcher.dispatchEvent(it.toKeyEventData(id, KeyEventAction.PRESS)) }
        onKeyUp = { JCEFEventDispatcher.dispatchEvent(it.toKeyEventData(id, KeyEventAction.RELEASE)) }
    }
}

inline val bgwCardStack: IntrinsicType<HTMLAttributes<Element>>
    get() = "bgw_card_stack".unsafeCast<IntrinsicType<HTMLAttributes<Element>>>()