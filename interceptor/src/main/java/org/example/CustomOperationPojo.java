package org.example;

import ca.uhn.fhir.rest.annotation.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CustomOperationPojo {

	private final Logger LOGGER = LoggerFactory.getLogger(CustomOperationPojo.class);

	@Operation(name = "$pojoOperation", manualResponse = true, manualRequest = true)
	public void $pojoOperation(HttpServletRequest theServletRequest, HttpServletResponse theServletResponse)
		throws IOException {
		if (!"POST".equals(theServletRequest.getMethod())) {
			theServletResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Only POST method is supported");
			return;
		}

		LOGGER.info("Received call to $pojoOperation");
		theServletResponse.setContentType("text/plain");
		theServletResponse.getWriter().write("Hello World");
		theServletResponse.getWriter().close();
	}
}