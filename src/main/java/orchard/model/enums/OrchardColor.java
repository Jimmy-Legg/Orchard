package orchard.model.enums;

import javafx.scene.paint.Color;

public enum OrchardColor {

	BEIGE(Color.web("#EDF1D6")),
	LIGHT_GREEN(Color.web("#609966")),
	DARK_GREEN(Color.web("#40513B"));
	
	private final Color color;
	
	OrchardColor(Color color) {
		this.color = color;
	}

	public Color color() {
		return color;
	}
	
}
