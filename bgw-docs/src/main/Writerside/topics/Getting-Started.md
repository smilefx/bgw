[MauMauRules]: https://en.wikipedia.org/wiki/Mau_Mau_(card_game)
[BGW]: https://github.com/tudo-aqua/bgw
[JavaFX 17]: https://openjfx.io/openjfx-docs/
[AzulZuluOpenJDK]: https://www.azul.com/downloads/?version=java-11-lts&package=jdk-fx#download-openjdk
[Mac M1]: https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx#download-openjdk

[BoardGameApplicationKDoc]: https://tudo-aqua.github.io/bgw/bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.core/-board-game-application/index.html
[BoardGameSceneKDoc]: https://tudo-aqua.github.io/bgw/bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.core/-board-game-scene/index.html
[MenuSceneKDoc]: https://tudo-aqua.github.io/bgw/bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.core/-menu-scene/index.html

[GameComponentKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.components.gamecomponentviews/-game-component-view/index.html
[StaticComponentViewKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.components/-static-component-view/index.html
[LabelKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.components.uicomponents/-label/index.html
[ButtonKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.components.uicomponents/-button/index.html
[ContainerKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.components.container/-game-component-container/index.html
[CardStackKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.components.container/-card-stack/index.html
[LinearLayoutKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.components.container/-linear-layout/index.html

[showGameSceneKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.core/-board-game-application/show-game-scene.html
[showMenuSceneKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.core/-board-game-application/show-menu-scene.html
[showKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.core/-board-game-application/show.html
[addComponentsKDoc]: https://tudo-aqua.github.io/bgw/bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.core/-scene/add-components.html

[UIComponentDoc]: components/uicomponents/uicomponents.md
[LayoutViewDoc]: components/layout/layout.md
[VisualsDoc]: visual.md

# Getting Started

The creation of a simple board game application begins with the instantiation of a [BoardGameApplication][BoardGameApplicationKDoc] object. Typically, you would create a singleton that extends [BoardGameApplication][BoardGameApplicationKDoc] to serve as the main controller for your game.

````kotlin
object MauMauApplication : BoardGameApplication(
    windowTitle = "MauMau"
)
````

This serves as the main frame for the game. The fundamental structure of the game is established by defining [BoardGameScenes][BoardGameSceneKDoc] and [MenuScenes][MenuSceneKDoc]. 

## Scenes
The first step involves the creation of two classes, `MauMauGameScene` and `MauMauMenuScene`, which inherit from the respective base classes.

````kotlin
class MauMauGameScene : BoardGameScene(
    background = ImageVisual("bg.jpg")
)
````

````kotlin
class MauMauMenuScene : MenuScene(
    width = 300,
    height = 500,
    background = ColorVisual(Color.WHITE)
)
````
The `MauMauMenuScene` is configured with a `height` of 500<tooltip term="Pixels">px</tooltip> and a `width` of 300<tooltip term="Pixels">px</tooltip>, while the `MauMauGameScene` adopts the default <tooltip term="FullHD">FullHD</tooltip> size.

While the `MauMauGameScene` uses a background image, a simple solid white backdrop was chosen for the `MauMauMenuScene`.
> For further information on visuals, please visit the [Visual][VisualsDoc] section.
{style="note"}

## First application
With the scenes defined, the application can be started by calling the `show()` method inside the `init` block of the `MauMauApplication` object.

````kotlin
object MauMauApplication : BoardGameApplication(
    windowTitle = "MauMau"
) {
    val mauMauMenuScene = MauMauMenuScene()
    val mauMauGameScene = MauMauGameScene()

    init {
        show()
    }
}
````

> We will not yet see anything on the screen, as we have not yet added any components to the scenes or shown them.
