package com.nelioalves.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.nelioalves.cursomc.domain.Disciplina;


public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>{

    @Query("SELECT d FROM Disciplina d")
    Page<Disciplina> findWitPagination(Pageable pageable);
    
    @Query("SELECT d FROM Disciplina d  WHERE (1 = 1) AND " +
            "(d.nome LIKE %:inputString% )")
    Page<Disciplina> findWithConditionsAndPaginationPost(@Param("inputString") String inputString, Pageable pageable);

}
