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

[DeclaringScenes]: Declaring-Scenes.md

# Using Components

Components are the building blocks of a scene. They are the visual elements that are rendered to the screen.

## Coordinates and Scaling

All coordinates, including those for size and position, are defined in a virtual coordinate space that undergoes transformation during rendering. The primary constraint when determining the scene size is the aspect ratio, as it remains unchanged during scaling. If the aspect ratio doesn't match the window size, black bars may appear, which can be customized using visuals.

> All components added to a scene will have their position and size relative to the declared coordinate space they are added to.
{style="note"}

## Declaring Components

Components are declared by instantiating them with the desired parameters. The following example shows how to declare a [Label][LabelKDoc] that displays *"Main menu"* and three [Buttons][ButtonKDoc] that display *"Continue"*, *"New Game"* and *"Exit"*.

## Adding Components

Components can be added to a scene using the `addComponents` method. The order in which components are added to a scene determines the order in which they are rendered. Components can be removed from a scene using the `removeComponents` method.



In this example a [Label][LabelKDoc] should display *"Main menu"* and three [Buttons][ButtonKDoc]
display *"Continue"*, *"New Game"* and *"Exit"*. These /components get declared and added to the scene by
calling [addComponents()][addComponentsKDoc] in the initializer block.

````kotlin
class MauMauMenuScene : MenuScene(width = 300, height = 500, background = ColorVisual(Color.WHITE)) {

    val continueGameButton: Button = Button(
        height = 80,
        width = 200,
        posX = 50,
        posY = 110,
        text = "Continue",
        font = Font(color = Color.WHITE, fontStyle = FontStyle.ITALIC),
        visual = ImageVisual(BUTTON_BG_FILE)
    )

    val newGameButton: Button = Button(
        height = 80,
        width = 200,
        posX = 50,
        posY = 220,
        text = "New Game",
        font = Font(color = Color.WHITE, fontStyle = FontStyle.ITALIC),
        visual = ImageVisual(BUTTON_BG_FILE)
    )
    
    val exitButton: Button = Button(
        height = 80,
        width = 200,
        posX = 50,
        posY = 330,
        text = "Exit",
        font = Font(color = Color.WHITE, fontStyle = FontStyle.ITALIC),
        visual = ImageVisual(BUTTON_BG_FILE)
    )

    private val menuLabel: Label = Label(
        height = 100,
        width = 200,
        posX = 50,
        posY = 0,
        text = "Main menu",
        font = Font(fontWeight = Font.FontWeight.BOLD)
    )

    init {
        addComponents(
            menuLabel,
            continueGameButton,
            newGameButton,
            exitButton,
        )
    }
}
````

````kotlin
class MauMauGameScene : BoardGameScene(background = ImageVisual("bg.jpg")) {

    val drawStack: CardStack<CardView> = CardStack(
        height = 200,
        width = 130,
        posX = 750,
        posY = 360,
        visual = ColorVisual(255, 255, 255, 50)
    )
    
    val gameStack: CardStack<CardView> = CardStack(
        height = 200,
        width = 130,
        posX = 1040,
        posY = 360,
        visual = ColorVisual(255, 255, 255, 50)
    )

    var currentPlayerHand: LinearLayout<CardView> = LinearLayout(
        height = 220,
        width = 800,
        posX = 560,
        posY = 750,
        spacing = -50,
        alignment = Alignment.CENTER,
        visual = ColorVisual(255, 255, 255, 50)
    )

    var otherPlayerHand: LinearLayout<CardView> = LinearLayout<CardView>(
        height = 220,
        width = 800,
        posX = 560,
        posY = 50,
        spacing = -50,
        alignment = Alignment.CENTER,
        visual = ColorVisual(255, 255, 255, 50)
    ).apply {
        rotation = 180.0
    }

    init {
        addComponents(
            drawStack,
            gameStack,
            currentPlayerHand,
            otherPlayerHand
        )
    }
}
```