package com.example.tarea.Repositories;

import com.example.tarea.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByFacultadOrderByFacultadDesc(String facultad);
    List<Student> findByFacultad(String facultad);
    //FILTROS
    /*@Query("SELECT f FROM Flores f WHERE (:tipo = 0 OR f.tipo.id = :tipo) AND (:color = 0  OR f.color.id = :color) AND (:ocasion = 0 OR f.ocasion.id = :ocasion)")
    List<Base> findByTipoColorOcasion(@Param("tipo") Long  tipo, @Param("color") Long  color, @Param("ocasion") Long  ocasion);*/
}
