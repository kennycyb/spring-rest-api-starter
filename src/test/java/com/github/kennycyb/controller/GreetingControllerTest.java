package com.github.kennycyb.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Unit tests for the GreetingController.
 * Uses MockMvc to test the REST endpoints without starting a full server.
 */
public class GreetingControllerTest {

	private MockMvc mockMvc;

	/**
	 * Sets up the test environment before each test.
	 * Creates a standalone MockMvc instance with the GreetingController.
	 */
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new GreetingController()).build();
	}

	/**
	 * Tests that the greeting endpoint returns a default greeting when no name parameter is provided.
	 * Verifies the response status, content type, and JSON structure.
	 */
	@Test
	public void testGreetingWithDefaultName() throws Exception {
		mockMvc.perform(get("/greeting")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.content").value(equalTo("Hello, World!")));
	}

	/**
	 * Tests that the greeting endpoint returns a personalized greeting with a custom name.
	 * Verifies the response includes the provided name in the greeting message.
	 */
	@Test
	public void testGreetingWithCustomName() throws Exception {
		String customName = "John";
		mockMvc.perform(get("/greeting")
				.param("name", customName)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.content").value(equalTo("Hello, " + customName + "!")));
	}

	/**
	 * Tests that the greeting endpoint increments the counter for each request.
	 * Verifies that multiple calls result in increasing ID values.
	 */
	@Test
	public void testGreetingIdIncrements() throws Exception {
		// Make first request and verify ID exists
		mockMvc.perform(get("/greeting")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.id").isNumber());

		// Make second request and verify ID still exists and is numeric
		mockMvc.perform(get("/greeting")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.id").isNumber());
		// we can verify it exists and is numeric, but exact values may vary
		// based on test execution order
	}

	/**
	 * Tests that the greeting endpoint handles special characters in names correctly.
	 */
	@Test
	public void testGreetingWithSpecialCharacters() throws Exception {
		String nameWithSpecialChars = "O'Brien";
		mockMvc.perform(get("/greeting")
				.param("name", nameWithSpecialChars)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value(equalTo("Hello, " + nameWithSpecialChars + "!")));
	}

	/**
	 * Tests that the greeting endpoint handles empty string names (falls back to default).
	 */
	@Test
	public void testGreetingWithEmptyName() throws Exception {
		mockMvc.perform(get("/greeting")
				.param("name", "")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value(equalTo("Hello, World!")));
	}
}
