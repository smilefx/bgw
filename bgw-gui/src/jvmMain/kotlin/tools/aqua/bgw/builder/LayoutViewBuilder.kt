package tools.aqua.bgw.builder

import tools.aqua.bgw.components.ComponentView
import tools.aqua.bgw.components.layoutviews.GridPane
import tools.aqua.bgw.components.layoutviews.LayoutView
import tools.aqua.bgw.components.layoutviews.Pane

object LayoutViewBuilder {
    fun build(layoutView: LayoutView<out ComponentView>) {
        when(layoutView) {
            is GridPane<*> -> buildGrid(layoutView)
            is Pane<*> -> buildPane(layoutView)
        }
    }
    private fun buildGrid(gridPane: GridPane<*>) {
        //TODO: Add property for spacing
        gridPane.updateGui = { Frontend.updateScene() }
        gridPane.mapNotNull { it.component }.forEach { component -> ComponentViewBuilder.build(component) }
    }

    private fun buildPane(pane: Pane<*>) {
        pane.observableComponents.guiListener = { _, _ -> Frontend.updateScene() }
        pane.components.forEach { component -> ComponentViewBuilder.build(component) }
    }
}
