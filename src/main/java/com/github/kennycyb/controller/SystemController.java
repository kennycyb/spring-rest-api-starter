package com.github.kennycyb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.kennycyb.model.General;

@RestController
public class SystemController {
	@RequestMapping("/system/ready")
	public General ready() {
		return new General("OK");
	}

	@RequestMapping("/system/alive")
	public General alive() {
		return new General("ALIVE");
	}
}
