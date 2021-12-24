module MapGenTests {
	requires MapGenerator;
	requires org.junit.jupiter.engine;
	requires org.junit.jupiter.api;

	exports dev.iskander.tests;
	opens dev.iskander.tests to org.junit.platform.commons;
}