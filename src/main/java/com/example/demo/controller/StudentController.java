package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor

public class StudentController {
    private final StudentService studentService;

    @GetMapping("/save")
    public String save() {
        return "save";
    }

    @PostMapping("/save")
    public String save(StudentDTO studentDTO) {
        studentService.save(studentDTO);
        return "index";
    }

    @GetMapping("/students")
    public String findAll(Model model) {
        List<StudentDTO> studentDTOList = studentService.fainAll();
        model.addAttribute("studentList", studentDTOList);
        return "students";
    }

    @GetMapping("/student/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        try {
            StudentDTO studentDTO = studentService.findById(id);
            model.addAttribute("student", studentDTO);
            return "detail";
        } catch (NoSuchElementException e) {
            return "NotFound";
        }
    }
}

