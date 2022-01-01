module MapGenerator {
	uses dev.iskander.canvasDrawifier.CanvasDrawifier;
	requires CanvasDrawifier;

	opens dev.iskander.mgj to javafx.fxml;
	exports dev.iskander.mgj;
}