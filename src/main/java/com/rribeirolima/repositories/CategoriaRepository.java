package com.rribeirolima.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rribeirolima.domain.Categoria;

//Anotação reposnsável por dizer que essa interface vai ser responsável por acesar o banco de dados da classe categoria
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
