package com.org.learnova.mappers;

import com.org.learnova.dto.MentorRegisterDTO;
import com.org.learnova.dto.MentorRequestDTO;
import com.org.learnova.dto.MentorResponseDTO;
import com.org.learnova.entities.Mentor;
import org.springframework.stereotype.Component;


@Component
public class MentorMapper {

    public Mentor toMentor(MentorRegisterDTO dto) {
        Mentor mentor = new Mentor();
        mentor.setFirstName(dto.getFirstName());
        mentor.setLastName(dto.getLastName());
        mentor.setEmail(dto.getEmail());
        mentor.setCompany(dto.getCompany());
        mentor.setExperience(dto.getExperience());
        mentor.setPassword(dto.getPassword());
        return mentor;
    }

    public Mentor toMentor(MentorRequestDTO dto) {
        Mentor mentor = new Mentor();
        mentor.setId(dto.getMentorId());
        mentor.setFirstName(dto.getFirstName());
        mentor.setLastName(dto.getLastName());
        mentor.setEmail(dto.getEmail());
        mentor.setCompany(dto.getCompany());
        mentor.setExperience(dto.getExperience());
        return mentor;
    }

    public MentorResponseDTO toMentorDTO(Mentor mentor) {
        MentorResponseDTO dto = new MentorResponseDTO();
        dto.setFirstName(mentor.getFirstName());
        dto.setLastName(mentor.getLastName());
        dto.setEmail(mentor.getEmail());
        dto.setCompany(mentor.getCompany());
        dto.setExperience(mentor.getExperience());
        dto.setCourses(mentor.getCourses());
        return dto;
    }
}
