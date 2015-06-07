package com.ywb.server.utils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@RequestMapping(value="/loginaaa", method=RequestMethod.GET)
	String login() {
		return "need login";
	}
}
