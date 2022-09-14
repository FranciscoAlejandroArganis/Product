package com.product.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entity.Category;

@ RestController
@ RequestMapping ("/category")
public class CtrlCategory {
	
	private Category [] categories = {new Category (1, "Abarrotes", 1), new Category (2, "Electrónica", 1)};
	
	@ GetMapping
	public ResponseEntity <Category []> listCategories () {
		return new ResponseEntity <> (categories, HttpStatus.OK);
	}
	
	@ GetMapping ("/{category_id}")
	public ResponseEntity <Integer> readCategory (@ PathVariable Integer category_id) {
		return new ResponseEntity <> (category_id, HttpStatus.OK);
	}
	
	@ PostMapping
	public ResponseEntity <String> createCategory (@ Valid @ RequestBody Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors ()) return new ResponseEntity <> (bindingResult.getAllErrors ().get (0).getDefaultMessage (), HttpStatus.BAD_REQUEST);
		Integer category_id = category.getCategory_id ();
		if (category_id == 1 || category_id == 2) return new ResponseEntity <> ("category already exists", HttpStatus.BAD_REQUEST);
		return new ResponseEntity <> ("category created", HttpStatus.CREATED);
	}
	
	@ PutMapping ("/{category_id}")
	public ResponseEntity <String> updateCategory (@ PathVariable Integer category_id, @ Valid @ RequestBody Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors ()) return new ResponseEntity <> (bindingResult.getAllErrors ().get (0).getDefaultMessage (), HttpStatus.BAD_REQUEST);
		if (category_id == 1 || category_id == 2) return new ResponseEntity <> ("category updated", HttpStatus.OK);
		return new ResponseEntity <> ("category does not exist", HttpStatus.BAD_REQUEST);
	}
	
	@ DeleteMapping ("/{category_id}")
	public ResponseEntity <String> deleteCategory (@ PathVariable Integer category_id) {
		if (category_id == 1 || category_id == 2) return new ResponseEntity <> ("category removed", HttpStatus.OK);
		return new ResponseEntity <> ("category does not exist", HttpStatus.BAD_REQUEST);
	}

}
