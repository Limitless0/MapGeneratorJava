package dev.iskander.mgj;

import javafx.scene.paint.Color;

public enum Biomes {
	GRASSLAND(Color.LIGHTGREEN, 1000),
	WOODLAND(Color.DARKGREEN, 1000),
	MOUNTAIN(Color.LIGHTGRAY, 100),
	DESERT(Color.YELLOWGREEN, 100)
	;

	final Color COLOUR;
	final double MAX;

	Biomes(Color color, double maxSize) {
		this.COLOUR = color;
		this.MAX = maxSize;
	}

}
