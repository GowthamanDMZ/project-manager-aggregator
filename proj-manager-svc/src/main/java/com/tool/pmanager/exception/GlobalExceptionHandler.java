package com.tool.pmanager.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)

    public @ResponseBody

    PMToolException handleCustomException(Exception ex, HttpServletResponse response) {

                response.setHeader("Content-Type", "application/json");

                if (ex instanceof PMToolException) {

                            response.setStatus(((PMToolException) ex).getStatus());

                            return ((PMToolException) ex).transformException();

                }

                else

                {

                            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

                            return returnRestError();

                }

    }



    private PMToolException returnRestError() {

    	PMToolException restError = new PMToolException();

                restError.setErrorCode("500");

                restError.setErrorMessage("Technical Error");

                return restError;

    }

}
