package com.microservice.student.services;

import com.microservice.student.entities.Student;
import com.microservice.student.persistence.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return (List<Student>) this.studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return this.studentRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Student student) {
        this.studentRepository.save(student);
    }

    @Override
    public List<Student> findByIdCourse(Long idCourse) {
        return this.studentRepository.findAllStudents(idCourse);
    }
}
