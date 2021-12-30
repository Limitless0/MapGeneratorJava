module P2IDrawifier {
	requires CanvasDrawifier;

	provides dev.iskander.canvasDrawifier.CanvasDrawifier with dev.iskander.pathToImageDrawifier.PathToImageDrawifier;
	exports dev.iskander.pathToImageDrawifier;
}