package com.org.learnova.controllers;

import com.org.learnova.dto.MentorRegisterDTO;
import com.org.learnova.dto.MentorRequestDTO;
import com.org.learnova.dto.MentorResponseDTO;
import com.org.learnova.entities.Mentor;
import com.org.learnova.services.MentorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mentor")
public class MentorController {

    private final MentorService mentorService;

    public MentorController(MentorService mentorService){
        this.mentorService = mentorService;
    }

    @PostMapping("/register")
    public ResponseEntity<Mentor> regitserMentor(@RequestBody @Valid MentorRegisterDTO dto){
        return new ResponseEntity<>(this.mentorService.registerMentor(dto),HttpStatus.CREATED);

    }

    @GetMapping("/id/{sId}")
    public ResponseEntity<MentorResponseDTO> getMentorById(@PathVariable(name = "sId") Long sId){
        return ResponseEntity.ok(this.mentorService.getMentorById(sId));

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<MentorResponseDTO> getMentorByEmail(@PathVariable(name = "email") String email){
        return ResponseEntity.ok(this.mentorService.getMentorByEmail(email));

    }

    @PatchMapping("/update")
    public ResponseEntity<MentorResponseDTO> updateMentor(@RequestBody @Valid MentorRequestDTO dto){
        return ResponseEntity.ok(this.mentorService.updateMentor(dto));
    }

    @DeleteMapping("/delete/{mId}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "mId") Long mId){
        this.mentorService.deleteMentor(mId);
        return ResponseEntity.ok("Mentor has been deleted successfully");
    }

}
