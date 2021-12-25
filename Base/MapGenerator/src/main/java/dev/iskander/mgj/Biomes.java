package dev.iskander.mgj;

import javafx.scene.paint.Color;

public enum Biomes {
	GRASSLAND(Color.LIGHTGREEN, 100),
	WOODLAND(Color.DARKGREEN, 100),
	TUNDRA(Color.LIGHTGRAY, 10),
	DESERT(Color.YELLOWGREEN, 10)
	;

	final Color COLOUR;
	final double MAX;

	Biomes(Color color, double maxSize) {
		this.COLOUR = color;
		this.MAX = maxSize;
	}

}
