@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package tools.aqua.bgw.visual

import tools.aqua.bgw.observable.LimitedDoubleProperty
import tools.aqua.bgw.observable.Observable

/**
 * Visual baseclass.
 */
sealed class Visual : Observable() {
	
	/**
	 * Property for the [transparency] / alpha channel for this [Visual].
	 * Must be set between 0 (full transparent) and 1 (non-transparent / solid).
	 * Default: 1.
	 */
	val transparencyProperty: LimitedDoubleProperty = LimitedDoubleProperty(0, 1, 1)
	
	/**
	 * The [transparency] / alpha channel for this [Visual].
	 * Must be set between 0 (full transparent) and 1 (non-transparent / solid).
	 * Default: 1.
	 */
	var transparency: Double
		get() = transparencyProperty.value
		set(value) {
			transparencyProperty.value = value
		}
	
	/**
	 * Copies this [Visual] to a new object.
	 */
	abstract fun copy(): Visual
	
	companion object {
		/**
		 * An empty [Visual].
		 */
		val EMPTY: Visual = CompoundVisual()
	}
}