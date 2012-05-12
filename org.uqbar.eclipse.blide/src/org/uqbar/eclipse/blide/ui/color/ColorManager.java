package org.uqbar.eclipse.blide.ui.color;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * @author jfernandes
 */
public class ColorManager {
	protected Map<RGB, Color> colorTable = new HashMap<RGB, Color>(10);

	public void dispose() {
		for (Color color : this.colorTable.values()) {
			 color.dispose();
		}
	}
	public Color getColor(RGB rgb) {
		Color color = this.colorTable.get(rgb);
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			this.colorTable.put(rgb, color);
		}
		return color;
	}
}
