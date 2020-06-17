
package com.neo.problemtwo.controllers;

import com.neo.problemtwo.client.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private Person person;

    @RequestMapping("/")
    public String healthCheck() {
        return "OK";
    }

    @RequestMapping("/person/get")
    public Person getPerson(@RequestParam(name="name", required=false, defaultValue="Unknown") String name) {
        person.setName(name);
        return person;
    }

    @RequestMapping(value="/person/update", method=RequestMethod.POST)
    public Person updatePerson(@RequestParam(name="name", required=true) String name) {
        person.setName(name);
        return person;
    }

}