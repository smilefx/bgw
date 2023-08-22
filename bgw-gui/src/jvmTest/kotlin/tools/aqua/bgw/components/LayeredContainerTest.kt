/*
 * Copyright 2023 The BoardGameWork Authors
 * SPDX-License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tools.aqua.bgw.components

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import tools.aqua.bgw.components.layoutviews.Pane
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.core.DEFAULT_BUTTON_HEIGHT
import tools.aqua.bgw.core.DEFAULT_BUTTON_WIDTH

/** A test class for the [LayeredContainer] interface. */
class LayeredContainerTest {
  /** A test [LayeredContainer]. In this cas a [Pane] */
  private var layeredContainer: Pane<ComponentView> =
      Pane(width = DEFAULT_BUTTON_WIDTH, height = DEFAULT_BUTTON_HEIGHT)

  /** Test component [Button]. */
  private val button1 = Button()
  /** Test component [Button]. */
  private val button2 = Button()
  /** Test component [Button]. */
  private val button3 = Button()
  /** Test component [Button]. */
  private val button4 = Button()
  /** Test component [Button]. */
  private val button5 = Button()

  /**
   * The base setup for every [LayeredContainerTest] test. Adding five buttons to the
   * [layeredContainer]
   */
  @BeforeEach
  fun setUp() {
    layeredContainer.clear()
    layeredContainer.addAll(button1, button2, button3, button4, button5)
  }

  /** Test putting a component to the back of its parent. */
  @Test
  @DisplayName("Test putting a component to the front of its parent")
  fun testToFront() {
    val expectedOrder = listOf<ComponentView>(button1, button3, button4, button5, button2)
    layeredContainer.toFront(button2)
    layeredContainer.components.forEachIndexed { i, child: ComponentView ->
      assertEquals(expectedOrder[i], child)
    }
  }

  /** Test putting a component to the back of its parent. */
  @Test
  @DisplayName("Test putting a component to the back of its parent")
  fun testToBack() {
    val expectedOrder = listOf<ComponentView>(button4, button1, button2, button3, button5)
    layeredContainer.toBack(button4)
    layeredContainer.components.forEachIndexed { i, child: ComponentView ->
      assertEquals(expectedOrder[i], child)
    }
  }

  /** Test setting z-index of multiple components and reordering them. */
  @Test
  @DisplayName("Test setting z-index of multiple components and reordering them")
  fun testSetZLayer() {
    val expectedOrder =
        listOf<Pair<ComponentView, Int>>(
            button4 to -5, button5 to 0, button1 to 3, button2 to 10, button3 to 100)
    for ((button, zIndex) in expectedOrder) {
      layeredContainer.setZIndex(button, zIndex)
    }
    layeredContainer.components.forEachIndexed { i, child: ComponentView ->
      val (button, _) = expectedOrder[i]
      assertEquals(button, child)
    }
  }
}
