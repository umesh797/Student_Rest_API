package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Student;
import com.example.demo.dao.StudentRepo;
import com.example.demo.exception.ResourceNotFoundException;

@RestController
//@RequestMapping("/StudentRecord")
public class StudentController 
{
	@Autowired
	StudentRepo stuRepo;
	
	@GetMapping("/students")
	public List<Student> getStudent()
	{
		List<Student> st=stuRepo.findAll();
		
		return st;
	}
	
	@RequestMapping(value="/studentSave", method=RequestMethod.POST)
	public Student saveStu(@RequestBody Student stu)
	{
		return stuRepo.save(stu);
	}
	
	@GetMapping("/students/{ids}")
	public Student getById(@PathVariable Long ids) throws ResourceNotFoundException
	{
		Student st=stuRepo.findById(ids).orElseThrow(()->new ResourceNotFoundException("Employee Not Found"));
		
		return  st;
	}
	
	

	@GetMapping("/students/dele/{id}")
	public void deleteById(@PathVariable Long id)
	{
		
		stuRepo.deleteById(id); 
	}
	
	@PutMapping("/students/update")
	public void updateById(@RequestBody Student st,Long id)
	{
		
		stuRepo.save(st); 
	}
}
