package com.abex.arena.web.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
	private final Logger logger = LoggerFactory.getLogger(VersionController.class);

	public VersionController(){
		
	}
	
	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public @ResponseBody String version() {
		logger.debug("version is executed!");
		return "Arena-WebApp Version 1.0";
	}

}
