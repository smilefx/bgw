package tools.aqua.bgw.elements.uicomponents

import ComboBoxData
import csstype.*
import data.event.KeyEventAction
import data.event.internal.SelectionChangedEventData
import emotion.react.css
import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLSelectElement
import react.*
import react.dom.html.HTMLAttributes
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select
import tools.aqua.bgw.builder.ReactConverters.toKeyEventData
import tools.aqua.bgw.builder.ReactConverters.toMouseEventData
import tools.aqua.bgw.builder.VisualBuilder
import tools.aqua.bgw.elements.*
import tools.aqua.bgw.event.JCEFEventDispatcher
import tools.aqua.bgw.handlers

external interface ComboBoxProps : Props {
    var data: ComboBoxData
}

fun PropertiesBuilder.cssBuilderIntern(componentViewData: ComboBoxData) {
    cssBuilder(componentViewData)
}

val ComboBox = FC<ComboBoxProps> { props ->
    bgwComboBox {
        id = props.data.id
        className = ClassName("comboBox")
        css {
            cssBuilderIntern(props.data)
        }

        +VisualBuilder.build(props.data.visual)

        select {
            placeholder = props.data.prompt
            css {
                fontBuilder(props.data)
                comboBoxBuilder(props.data)
                placeholder {
                    fontBuilder(props.data)
                    opacity = number(0.5)
                }
                position = Position.absolute
            }
            props.data.items.forEach {
                option {
                    value = it
                    +it
                    selected = it == props.data.selectedItem
                }
            }
            onChange = {
                val value = it.target.value
                //println("Selection changed $value")
                JCEFEventDispatcher.dispatchEvent(SelectionChangedEventData(value).apply { id = props.data.id })
            }
        }

               onContextMenu = {
            it.preventDefault()
            JCEFEventDispatcher.dispatchEvent(it.toMouseEventData(id)) 
        }
        onClick = { JCEFEventDispatcher.dispatchEvent(it.toMouseEventData(id)) }
        onKeyDown = { JCEFEventDispatcher.dispatchEvent(it.toKeyEventData(id, KeyEventAction.PRESS)) }
        onKeyUp = { JCEFEventDispatcher.dispatchEvent(it.toKeyEventData(id, KeyEventAction.RELEASE)) }
        onKeyPress = { JCEFEventDispatcher.dispatchEvent(it.toKeyEventData(id, KeyEventAction.TYPE)) }
    }
}

inline val bgwComboBox: IntrinsicType<HTMLAttributes<HTMLInputElement>>
    get() = "bgw_combo_box".unsafeCast<IntrinsicType<HTMLAttributes<HTMLInputElement>>>()