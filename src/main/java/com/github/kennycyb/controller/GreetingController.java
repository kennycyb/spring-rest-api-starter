package com.github.kennycyb.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.kennycyb.model.Greeting;

/**
 * REST controller that handles greeting requests.
 * Provides endpoints for generating personalized greeting messages with unique IDs.
 */
@RestController
public class GreetingController {

	/** Template string used to format greeting messages */
	private static final String TEMPLATE = "Hello, %s!";

	/** Thread-safe counter for generating unique greeting IDs */
	private final AtomicLong counter = new AtomicLong();

	/**
	 * Generates a personalized greeting message with a unique ID.
	 *
	 * @param name the name to include in the greeting (defaults to "World" if not provided)
	 * @return a Greeting object containing a unique ID and the formatted greeting message
	 */
	@RequestMapping("/greeting")
	public Greeting greeting(
			@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE,
				name));
	}
}
