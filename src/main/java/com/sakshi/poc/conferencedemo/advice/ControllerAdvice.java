package com.sakshi.poc.conferencedemo.advice;

import com.sakshi.poc.conferencedemo.controllers.SessionsController;
import com.sakshi.poc.conferencedemo.controllers.SpeakersController;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

//@RestControllerAdvice(assignableTypes = {SpeakersController.class})
@RestControllerAdvice(basePackages = "com.sakshi.poc.conferencedemo")
public class ControllerAdvice {

//   @ExceptionHandler(EntityNotFoundException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String handleException()
//    {
//        return "oops something went wrong" ;
//    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public String handleAllException(HttpServletRequest request, Exception e)
//    {
//        String method=request.getMethod();
//        return "oops something went wrong in method:"+method +"with exception:"+e.getMessage();
//    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleAllExceptions(HttpServletRequest request, Exception e) {
        var pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        pd.setTitle("Exception occurred");
        pd.setType(URI.create("https://www.someurl.com/errors/bad-request"));
        pd.setProperty("key", "value");
        return pd;
    }
}
