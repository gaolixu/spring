package com.pagehelper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.pagehelper.domain.model.Person;
import com.pagehelper.page.PageInfo;
import com.pagehelper.service.PersonService;

@RestController
public class StockController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "stock", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object stock(long id) {
        int result = personService.updateAge(id);
        return result > 0;
    }
    
   
}