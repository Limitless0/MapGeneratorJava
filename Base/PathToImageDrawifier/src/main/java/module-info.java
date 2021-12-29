module P2IDrawifier {
	requires CanvasDrawifier;
	requires javafx.controls;
	requires javafx.fxml;

	provides dev.iskander.canvasDrawifier.CanvasDrawifier with dev.iskander.pathToImageDrawifier.PathToImageDrawifier;
}