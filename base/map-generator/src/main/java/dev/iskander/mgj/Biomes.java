package dev.iskander.mgj;

import javafx.scene.paint.Color;

public enum Biomes { //maxSize must be greater than 1 to work correctly with line forming
	GRASSLAND(Color.LIGHTGREEN, 100),
	DENSE_WOODLAND(Color.DARKGREEN, 70),
	SPARSE_WOODLAND(Color.YELLOWGREEN, 30),
	MOUNTAIN(Color.LIGHTGRAY, 4),
	WATER(Color.BLUE, 2)
	;

	public final Color COLOUR;
	public final double MAX;

	Biomes(Color color, double maxSize) {
		this.COLOUR = color;
		this.MAX = maxSize;
	}

}
