package com.example.demo.service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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


    public StudentDTO findById(Long id) {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(id);
        if(optionalStudentEntity.isPresent()) {
            // 있다
            StudentEntity studentEntity = optionalStudentEntity.get();
            return StudentDTO.toSaveDTO(studentEntity);
        } else {
            // 없다
            return null;
        }

        // 고급버전
//        studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException()); // 예외처리
//        return StudentDTO.toSaveDTO(studentEntity);
    }

    public void update(StudentDTO studentDTO) {
        StudentEntity studentEntity = StudentEntity.toUpdateEntity(studentDTO);
        /*
            save()에 넘기는 엔티티 객체에 pk값이 들어있으면 update 쿼리가 나가고
            pk값이 없으면 insert 쿼리가 나감
        */
        studentRepository.save(studentEntity);

    }
}
