import dev.iskander.pixelWriterDrawifier.PixelWriterDrawifier;

module PixelWriterDrawifier {
	requires CanvasDrawifier;

	provides dev.iskander.canvasDrawifier.CanvasDrawifier with PixelWriterDrawifier;
}