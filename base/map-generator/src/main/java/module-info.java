module MapGenerator {
    requires CanvasDrawifier;
	requires P2IDrawifier;
	opens dev.iskander.mgj to javafx.fxml;
	exports dev.iskander.mgj;
}