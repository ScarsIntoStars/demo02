package com.example.demo.service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Long save(StudentDTO studentDTO) {
//        System.out.println("studentDTO : " + studentDTO);
//        StudentEntity studentEntity = new StudentEntity();
//        studentEntity.setStudentNumber(studentDTO.getStudentNumber());
//        studentEntity.setStudentName(studentDTO.getStudentName());
//        studentEntity.setStudentMobile(studentDTO.getStudentMobile());
//        studentEntity.setStudentMajor(studentDTO.getStudentMajor());
//        studentRepository.save(studentEntity);

        // DTO -> Entity 변환을 위한 매서드 호출
        StudentEntity studentEntity = StudentEntity.toSaveEntity(studentDTO);
        Long savedId = studentRepository.save(studentEntity).getId();
        return savedId;
    }

    public List<StudentDTO> fainAll() {
        List<StudentEntity> studentEntityList = studentRepository.findAll();    // DB에서 가져온 리스트(Entity형식)
        List<StudentDTO> studentDTOList = new ArrayList<>();                    // studentDTO를 담을 studentDTOList 생성
        for (StudentEntity studentEntity : studentEntityList) {                 //
            StudentDTO studentDTO = StudentDTO.toSaveDTO(studentEntity);        //
            studentDTOList.add(studentDTO);                                     //
////            studentDTOList.add(StudentDTO.toSaveDTO(studentEntity));        // 위 두 줄을 이렇게 한 줄로 표현가능
//        }
//        studentEntityList.forEach(studentEntity -> {                            // 이 방법은 간지
//            studentDTOList.add(StudentDTO.toSaveDTO(studentEntity));
//        });
        /*
            List<StudentEntity> -> List<StudentDTO>로 옮겨 담는 코드 작성
            Entity -> DTO 변환하는 매서드는 DTO에 정의
        */
        }
        return studentDTOList;
    }
}
