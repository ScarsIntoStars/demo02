package com.example.demo.entity;


import com.example.demo.dto.StudentDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter(AccessLevel.PRIVATE)
@Getter
@Table(name="student_table")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String studentNumber;

    @Column(length = 20, nullable = false)
    private String StudentName;

    @Column(length = 30, nullable = false)
    private String studentMobile;

    @Column(length = 50, nullable = false)
    private String studentMajor;

    // 기본생성자를 private 으로
//    private StudentEntity() {  => 이거 안됨. 프라이빗으로 설정하면 레퍼지토리에서 못쓰기 때문
//    }

    // DTO -> Entity 변환 메서드
    public static StudentEntity toSaveEntity(StudentDTO studentDTO) {
        System.out.println("studentDTO : " + studentDTO);
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setStudentNumber(studentDTO.getStudentNumber());
        studentEntity.setStudentName(studentDTO.getStudentName());
        studentEntity.setStudentMobile(studentDTO.getStudentMobile());
        studentEntity.setStudentMajor(studentDTO.getStudentMajor());
        return studentEntity;
    }

    public static StudentEntity toUpdateEntity(StudentDTO studentDTO) {
        System.out.println("studentDTO : " + studentDTO);
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(studentDTO.getId());
        studentEntity.setStudentNumber(studentDTO.getStudentNumber());
        studentEntity.setStudentName(studentDTO.getStudentName());
        studentEntity.setStudentMobile(studentDTO.getStudentMobile());
        studentEntity.setStudentMajor(studentDTO.getStudentMajor());
        return studentEntity;
    }


}
