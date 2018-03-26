package com.mvc.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	private static final Logger LOGGER = Logger.getLogger(StudentController.class.getName());

	
	@RequestMapping(value = "/students", method = RequestMethod.GET)  
    @ResponseBody  
    public List<Student> getStudentList() {  
		LOGGER.error("test -----------------4444022222222226663------- error");
		
		LOGGER.info("test ------------------------------- info");
		
		LOGGER.debug("test ------------------------ debug");
        List<Student> students = new ArrayList<Student>(0);  
        students.add(new Student(1, "test", "lastName", 2));
        return students;  
    }  
	

}
