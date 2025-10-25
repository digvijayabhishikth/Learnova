package com.org.learnova.services;

import com.org.learnova.dto.CourseMentorViewDTO;
import com.org.learnova.dto.MentorRegisterDTO;
import com.org.learnova.dto.MentorRequestDTO;
import com.org.learnova.dto.MentorResponseDTO;
import com.org.learnova.entities.Course;
import com.org.learnova.entities.Mentor;
import com.org.learnova.exception.AlreadyExistsException;
import com.org.learnova.exception.NotFoundException;
import com.org.learnova.mappers.CourseMapper;
import com.org.learnova.mappers.MentorMapper;
import com.org.learnova.repositories.CourseRepository;
import com.org.learnova.repositories.EnrollmentRepository;
import com.org.learnova.repositories.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MentorService {

    private final MentorRepository mentorRepository;
    private final MentorMapper mentorMapper;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public MentorService(MentorRepository mentorRepository,MentorMapper mentorMapper,CourseRepository courseRepository,EnrollmentRepository enrollmentRepository, CourseMapper courseMapper){
        this.mentorRepository = mentorRepository;
        this.mentorMapper = mentorMapper;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.courseMapper = courseMapper;
    }

    //register Mentor for first Mentor
    public Mentor registerMentor(MentorRegisterDTO mentorRegisterDTO){
        Mentor mentor = this.mentorMapper.toMentor(mentorRegisterDTO);
        if(this.mentorRepository.existsByEmail(mentorRegisterDTO.getEmail())){
            throw  new AlreadyExistsException("Mentor already exists with given email");
        }

        return this.mentorRepository.save(mentor);
    }

    //get Mentor by id
    public MentorResponseDTO getMentorById(Long mId){
        Mentor mentor = this.mentorRepository.findById(mId)
                .orElseThrow(() -> new NotFoundException("Mentor with given id is not found"));

        return this.mentorMapper.toMentorDTO(mentor);
    }

    //get Mentor by Email
    public MentorResponseDTO getMentorByEmail(String email){
        Mentor mentor = this.mentorRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Mentor with given email is not found"));

        return this.mentorMapper.toMentorDTO(mentor);
    }

    //update Mentor Profile
    public MentorResponseDTO updateMentor(MentorRequestDTO mentorRequestDTO){
        Mentor mentor = this.mentorRepository.findById(mentorRequestDTO.getMentorId())
                .orElseThrow(() -> new NotFoundException("Mentor not found"));

        // update fields
        mentor.setFirstName(mentorRequestDTO.getFirstName());
        mentor.setLastName(mentorRequestDTO.getLastName());
        mentor.setEmail(mentorRequestDTO.getEmail());
        mentor.setCompany(mentorRequestDTO.getCompany());
        mentor.setExperience(mentorRequestDTO.getExperience());

        Mentor updatedMentor = this.mentorRepository.save(mentor);

        return this.mentorMapper.toMentorDTO(updatedMentor);
    }

    //delete mentor
    public void deleteMentor(Long mId){
        this.mentorRepository.deleteById(mId);
    }

    public List<CourseMentorViewDTO> getCoursesByMentorId(Long mId){
        List<Course> list = this.courseRepository.findByMentor_Id(mId);
        if(list.isEmpty())
            throw new NotFoundException("No courses added byt this mentor");
        List<CourseMentorViewDTO> courses = new ArrayList<>();
        for(Course course : list){
            long count = this.enrollmentRepository.countEnrollmentsByCourseId(course.getId());
            courses.add(this.courseMapper.courseMentorViewDTO(course, count));
        }
        return courses;
    }
}
