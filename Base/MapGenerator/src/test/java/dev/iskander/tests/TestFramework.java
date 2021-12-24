package dev.iskander.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TestFramework {

	@BeforeAll
	static void setUp() {
	}

	@Test
	void testOne() {
		System.out.println(LocalDateTime.now());

	}

}
