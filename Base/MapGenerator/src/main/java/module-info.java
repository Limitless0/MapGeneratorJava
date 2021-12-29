module MapGenerator {
    requires P2IDrawifier;
    requires javafx.controls;
    requires javafx.fxml;
    requires CanvasDrawifier;
    uses dev.iskander.canvasDrawifier.CanvasDrawifier;

    opens dev.iskander.mgj to javafx.fxml;
    exports dev.iskander.mgj;
}