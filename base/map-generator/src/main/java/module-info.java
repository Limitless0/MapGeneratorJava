module MapGenerator {
    requires CanvasDrawifier;
	requires P2IDrawifier;
	exports dev.iskander.mgj to javafx.graphics;
	opens dev.iskander.mgj to javafx.fxml;
}