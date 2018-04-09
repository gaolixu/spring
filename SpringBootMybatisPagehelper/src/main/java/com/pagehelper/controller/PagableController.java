package com.pagehelper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.pagehelper.domain.model.Person;
import com.pagehelper.page.PageInfo;
import com.pagehelper.service.PersonService;



@Controller 
@RequestMapping(value = "/person")
public class PagableController {

    @Autowired
    private PersonService personService;

  
    
    @RequestMapping("/info")
	public String findBookNoQuery(ModelMap modelMap,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {
		
		   PageHelper.startPage(page, 3);
	        List<Person> postIn = personService.findAll();
	        System.out.println(postIn+"===========");
	        //用PageInfo对结果进行包装
	        
	        PageInfo<Person> pageResult = new PageInfo<Person>(postIn);
	        modelMap.addAttribute("pageInfo", pageResult);
	        return "index";
	}
}