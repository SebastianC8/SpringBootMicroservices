package com.microservice.course.services;

import com.microservice.course.client.IStudentClient;
import com.microservice.course.dto.StudentDTO;
import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.persistence.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IStudentClient studentClient;

    @Override
    public List<Course> findAll() {
        return (List<Course>) this.courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return this.courseRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        this.courseRepository.save(course);
    }

    @Override
    public StudentByCourseResponse findStudentsByIdCourse(Long idCourse) {
        // Get course
        Course course = this.courseRepository.findById(idCourse).orElse(new Course());

        // Get students
        List<StudentDTO> studentDTOList = this.studentClient.findAllStudentsByCourse(idCourse);

        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .studentDTOList(studentDTOList)
                .build();
    }

}
