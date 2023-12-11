[MauMauRules]: https://en.wikipedia.org/wiki/Mau_Mau_(card_game)
[BGW]: https://github.com/tudo-aqua/bgw
[JavaFX 17]: https://openjfx.io/openjfx-docs/
[AzulZuluOpenJDK]: https://www.azul.com/downloads/?version=java-11-lts&package=jdk-fx#download-openjdk
[Mac M1]: https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx#download-openjdk

[BoardGameApplicationKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.core/-board-game-application/index.html
[BoardGameSceneKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.core/-board-game-scene/index.html
[MenuSceneKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.core/-menu-scene/index.html
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
[addComponentsKDoc]: bgw-gui-kdoc/bgw-gui/tools.aqua.bgw.core/-scene/add-components.html

[UIComponentDoc]: components/uicomponents/uicomponents.md
[LayoutViewDoc]: components/layout/layout.md
[VisualsDoc]: concepts/visual/visual.md

# Welcome to BoardGameWork

[BoardGameWork (BGW)][BGW] is a framework for creating 2D board game applications.
This guide will introduce you to the essential concepts and features of the framework, and guide you through the process
of setting up your first game scene. We will use the popular [MauMau][MauMauRules] card game as our example project.

## Prerequisites

BoardGameWork is built on top of [JavaFX 17][JavaFX 17] and therefore requires at least JDK 11.
Since JavaFX was decoupled from the JavaJDK as of JDK 11, BoardGameWork comes with JavaFX dependencies including their
native libraries for various platforms like Windows, Linux and Mac.

We recommend installing a JDK build that already includes JavaFX to ensure your platform is supported.
[Azul Zulu Builds of OpenJDK][AzulZuluOpenJDK] support a wide range of platforms and architectures including [Mac M1][Mac M1].

## Setup

Start by adding the latest version of BGW as a dependency to your project.

<tabs group="gradleMaven">
    <tab title="Gradle" group-key="gradle">
        <code-block lang="gradle">
        implementation("tools.aqua:bgw-gui:0.9")
        </code-block>
    </tab>
    <tab title="Maven" group-key="maven">
        <code-block lang="xml">
            <![CDATA[
            <dependency>
              <groupId>tools.aqua</groupId>
              <artifactId>bgw-gui</artifactId>
              <version>0.9</version>
            </dependency>
           ]]>
        </code-block>
    </tab>
</tabs>

You also need to specify the correct JVM target in your build system.

<tabs group="gradleMaven">
    <tab title="Gradle" group-key="gradle">
        <code-block lang="gradle">
            compileKotlin {
                kotlinOptions.jvmTarget = "11"
            }
        </code-block>
    </tab>
    <tab title="Maven" group-key="maven">
        <code-block lang="xml">
            <![CDATA[
            <configuration>
                <jvmTarget>11</jvmTarget>
            </configuration>
            ]]>
        </code-block>
    </tab>
</tabs>


## Getting Started

The creation of a simple board game application begins with the instantiation of a [BoardGameApplication][BoardGameApplicationKDoc] object. Typically, you would create a class that extends [BoardGameApplication][BoardGameApplicationKDoc] to serve as the main controller for your game.

````kotlin
class MauMauViewController : BoardGameApplication(windowTitle = "MauMau")
````

This creates a window in which the game can take place. For the game itself a [BoardGameScene][BoardGameSceneKDoc] and
a [MenuScene][MenuSceneKDoc] is declared.

````kotlin
class MauMauViewController : BoardGameApplication(windowTitle = "MauMau") {
    val mauMauMenuScene: MauMauMenuScene = MauMauMenuScene()
    val mauMauGameScene: MauMauGameScene = MauMauGameScene()
}
````

````kotlin
class MauMauGameScene : BoardGameScene(background = ImageVisual("bg.jpg"))
````

````kotlin
class MauMauMenuScene : MenuScene(width = 300, height = 500, background = ColorVisual(Color.WHITE))
````

The menu scene gets a height of 500px and width of 300px while the game scene gets the default size of
FullHD. The game scene gets an image as background and the menu scene solid white. To read more about
visuals click [here][VisualsDoc].

## BoardGameScene and MenuScene

The MauMau example declares a game scene and a menu scene. In menu scenes draggable
components are not usable, only [LayoutViews][LayoutViewDoc] and 
[UIComponents][UIComponentDoc]: In other words /components 
that extend [StaticComponentView][StaticComponentViewKDoc].

A [BoardGameApplication][BoardGameApplicationKDoc] can display one [BoardGameScene][BoardGameSceneKDoc] and one [MenuScene][MenuSceneKDoc] at the same time.
While the menu scene is visible, the game scene gets blurred out.

The scenes can be shown by calling [showGameScene()][showGameSceneKDoc] and [showMenuScene()][showMenuSceneKDoc].
The window gets shown by calling [show()][showKDoc].

````kotlin
class MauMauViewController : BoardGameApplication(windowTitle = "MauMau") {
    val mauMauMenuScene: MauMauMenuScene = MauMauMenuScene()
    val mauMauGameScene: MauMauGameScene = MauMauGameScene()

    init {
        showGameScene(mauMauGameScene)
        showMenuScene(mauMauMenuScene)
        show()
    }
}
````

## Declaring a MenuScene

As shown above, the desired size of the scene can be passed as an argument to the super constructor. As with all
coordinates these are declared in a virtual coordinate space that will be transformed while rendering. Primary
constraint while choosing the scene size is the ratio of height and width as this is not changed by scaling. If the
ratio does not match the windows size, black bars will appear which can be styled using visuals as well.

All /components declared in this scene will relate its position and size to the declared coordinate space.

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

## Declaring a BoardGameScene

[BoardGameScenes][BoardGameSceneKDoc] are the main /components of the game. BoardGameScenes behave just like menu scenes but can
additionally contain [GameComponentViews][GameComponentKDoc] and [GameContainerViews][ContainerKDoc].

For the MauMau example two [CardStacks][CardStackKDoc] and two player hands as [LinearLayouts][LinearLayoutKDoc] are 
necessary.

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
````

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
