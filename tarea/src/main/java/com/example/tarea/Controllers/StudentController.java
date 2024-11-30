package com.example.tarea.Controllers;

import com.example.tarea.Entities.Student;
import com.example.tarea.Repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/")
public class StudentController {

    final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student/{facultad}")
    public List<Student> sub1(@PathVariable String facultad) {
        return studentRepository.findByFacultadOrderByFacultadDesc(facultad);

    }

    @PostMapping(value = "/student/agregar")
    public ResponseEntity<HashMap<String, Object>> agregarEstudiante(
            @RequestBody Student newStudent) {

        HashMap<String, Object> response = new HashMap<>();
        List<Student> students = studentRepository.findByFacultad(newStudent.getFacultad());
        if(students.size() < 10){
            if(newStudent.getGpa() < 3.5){
                response.put("Estado", "no creado");
                response.put("Motivo", "no cumple con el mínimo de 3.5 de gpa");
            } else {
                studentRepository.save(newStudent);
                response.put("Estado", "creado");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }

        } else {
            response.put("Estado", "no creado");
            response.put("Motivo", "ya se llegó al límite de máximo 10 estudiantes");
        }
        return ResponseEntity.badRequest().body(response);

    }

    public void actualizarGpa(Integer id, Float nuevoGpa) {
        Student estudiante = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        estudiante.setGpa(nuevoGpa);
        studentRepository.save(estudiante);
    }

    public void eliminarEstudiante(Integer id) {
        studentRepository.deleteById(id);
    }


}
