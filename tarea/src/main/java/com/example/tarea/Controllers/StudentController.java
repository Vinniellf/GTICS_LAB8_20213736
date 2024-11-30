package com.example.tarea.Controllers;

import com.example.tarea.Entities.Student;
import com.example.tarea.Repositories.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{facultad}")
    public List<Student> sub1(@PathVariable String facultad) {
        return studentRepository.findByFacultadOrderByFacultadDesc(facultad);

    }

    @PostMapping(value = "/agregar")
    public ResponseEntity<HashMap<String, Object>> agregarEstudiante(
            @RequestBody Student newStudent) {

        HashMap<String, Object> response = new HashMap<>();
        List<Student> students = studentRepository.findByFacultad(newStudent.getFacultad());
        if(students.size() < 10){
            if(newStudent.getGpa() < 3.5){
                response.put("Motivo", "no cumple con el mínimo de 3.5 de gpa");
                response.put("Estado", "no creado");
            } else {
                studentRepository.save(newStudent);
                response.put("Estado", "creado");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }

        } else {
            response.put("Motivo", "ya se llegó al límite de máximo 10 estudiantes");
            response.put("Estado", "no creado");
        }
        return ResponseEntity.badRequest().body(response);

    }

    @PutMapping(value="/actualizar")
    public ResponseEntity<HashMap<String, Object>> actualizarGpa(
            @RequestBody Student newStudent) {
        HashMap<String, Object> response = new HashMap<>();

        if(newStudent.getIdStudents() != null && newStudent.getIdStudents() > 0){
            Optional<Student> opt = studentRepository.findById(newStudent.getIdStudents());
            if(opt.isPresent()){
                Student student = opt.get();

                if(newStudent.getGpa() != null){
                    student.setGpa(newStudent.getGpa());
                }

                if(newStudent.getFacultad() != null){
                    student.setFacultad(newStudent.getFacultad());
                }

                if(newStudent.getNombre() != null){
                    student.setNombre(newStudent.getNombre());
                }

                if (newStudent.getCreditosCompletados() != null){
                    student.setCreditosCompletados(newStudent.getCreditosCompletados());
                }

                studentRepository.save(newStudent);
                response.put("Estado", "actualizado");
                return ResponseEntity.ok(response);

            } else{
                response.put("Estado", "error");
                response.put("Motivo", "El estudiante a actualizar no existe");
            }
        } else {
            response.put("Estado", "error");
            response.put("Motivo", "Debe enviar un id");
        }
        return ResponseEntity.badRequest().body(response);

    }

    public ResponseEntity<HashMap<String, Object>> eliminarEstudiante(@PathVariable("id") String id) {
        HashMap<String, Object> response = new HashMap<>();

        try{
            Integer idStudent = Integer.parseInt(id);
            if(studentRepository.existsById(idStudent)){
                studentRepository.deleteById(idStudent);
                response.put("Estado", "eliminado correctamente");
                return ResponseEntity.ok(response);
            } else{
                response.put("Estado", "error");
                response.put("Motivo", "El estudiante con id " + id +"no existe");
                return ResponseEntity.badRequest().body(response);
            }

        } catch (NumberFormatException e){
            response.put("Estado", "error");
            response.put("Motivo", "El ID debe ser un número");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionException(HttpServletRequest request){
        HashMap<String, String> response = new HashMap<>();
        if(request.getMethod().equals("Post") || request.getMethod().equals("PUT")){
            response.put("Estado", "error");
            response.put("Motivo", "Debe enviar un estudiante");
        }

        return ResponseEntity.badRequest().body(response);
    }


}
