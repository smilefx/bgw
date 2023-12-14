# Handling Events



## Event handler

When the application is started, both menu scene and game scene are shown. The game scene is blurred in the background.

![](menu.png)

To start a new game and close the menu scene, event handlers have to be added. As these button's
actions change the scene, the handlers get set in the view controller.

````kotlin
class MauMauViewController : BoardGameApplication(windowTitle = "MauMau") {
    val mauMauMenuScene: MauMauMenuScene = MauMauMenuScene()
    val mauMauGameScene: MauMauGameScene = MauMauGameScene()

    init {
        registerMenuEvents()

        showGameScene(mauMauGameScene)
        showMenuScene(mauMauMenuScene)
        show()
    }

    private fun registerMenuEvents() {
        mauMauMenuScene.continueGameButton.onMouseClicked = {
            hideMenuScene()
        }

        mauMauMenuScene.newGameButton.onMouseClicked = {
            //Start new game here
            hideMenuScene()
        }

        mauMauMenuScene.exitButton.onMouseClicked = {
            exit()
        }
    }
}
````

Now after pressing the *"New Game"* button the menu scene is hidden and the game scene is active.

![](game.png)
