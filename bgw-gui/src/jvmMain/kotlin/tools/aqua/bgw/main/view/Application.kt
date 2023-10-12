package tools.aqua.bgw.main.view

import tools.aqua.bgw.core.BoardGameApplication
import tools.aqua.bgw.util.Font

object Application : BoardGameApplication() {
    private val grid = GridGameScene()
    private val ui = UIScene()
    val menuScene = MyMenuScene()
    private val dragDropScene = DragDropScene()

    init {
        loadFont("Rubik.ttf", "Rubik", Font.FontWeight.SEMI_BOLD)
        showGameScene(dragDropScene)
        //showGameScene(ui)
        //showGameScene(scene)
        showMenuScene(menuScene)
    }
}