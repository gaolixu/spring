package com.mvc.rest.logging;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class NDCFilter  extends Filter
{
	  @Override
	  public int decide(final LoggingEvent event) {
	    //if (event.getNDC() == "push----- push --------push") {
	     // return DENY;
	   // }
	    return NEUTRAL;
	  }
	}