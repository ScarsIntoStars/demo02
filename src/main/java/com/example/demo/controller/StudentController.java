package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/student/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        try {
            StudentDTO studentDTO = studentService.findById(id);
            model.addAttribute("student", studentDTO);
            return "update";
        } catch (NoSuchElementException e) {
            return "NotFound";
        }
    }

    @PostMapping("/student/update")
    public String update(@ModelAttribute StudentDTO studentDTO) {
        studentService.update(studentDTO);
        return "redirect:/students";
    }
}

