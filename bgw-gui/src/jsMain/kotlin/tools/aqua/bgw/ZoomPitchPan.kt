@file:JsModule("react-zoom-pan-pinch")
@file:JsNonModule

package tools.aqua.bgw

import csstype.PropertiesBuilder
import react.ComponentClass
import react.FC
import react.PropsWithChildren


@JsName("TransformWrapper")
external val TransformWrapper: ComponentClass<TransformWrapperProps>

external interface TransformWrapperProps : PropsWithChildren {
    var style : dynamic
}


@JsName("TransformComponent")
external val TransformComponent: ComponentClass<TransformComponentProps>

external interface TransformComponentProps : PropsWithChildren { }