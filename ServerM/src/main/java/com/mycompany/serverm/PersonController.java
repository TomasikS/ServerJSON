/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverm;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/person")
public class PersonController { 
    
    PersonService i= new PersonService ();

        @RequestMapping(value = "/person/get", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> listAllPersons() throws SQLException, ClassNotFoundException, IOException {
		List<Person> persons = i.findAllPersons();
		if (persons.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(persons, HttpStatus.OK);
	}
        
        
        
        
     @PostMapping(path = "/post/json", consumes = MediaType.APPLICATION_JSON_VALUE, 
         produces = MediaType.APPLICATION_JSON_VALUE)   
public ResponseEntity<String> postJSON(@RequestBody List<String> body) throws FileNotFoundException, IOException {
      System.out.println(body);
      
      Person p=new Person(Integer.parseInt(body.get(0)),body.get(1),body.get(2));
        try {
            i.save(p);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
      return ResponseEntity.ok().body("Done");
            
            
   }


@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
	 
		i.deleteById(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}
   
    }
 


  
