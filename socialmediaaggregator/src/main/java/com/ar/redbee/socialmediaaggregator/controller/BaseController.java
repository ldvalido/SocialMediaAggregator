package com.ar.redbee.socialmediaaggregator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {

    static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    
	protected Logger getLogger() {
		return logger;
	}
}
