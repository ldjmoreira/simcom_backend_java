package com.nelioalves.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nelioalves.cursomc.domain.Componente;

public interface ComponenteRepository extends JpaRepository<Componente, Integer> {
	/*
    @Query("SELECT c FROM Componente c WHERE c.local IS NULL")
    Page<Componente> findWithNullLocal(Pageable pageable);
	*/
    @Query("SELECT c FROM Componente c INNER JOIN c.tipoComponente tc WHERE 1 = 1  AND " +
            "(tc.especificacao LIKE %:inputString% OR tc.nome LIKE %:inputString% OR c.fornecedor LIKE %:inputString%)")
    Page<Componente> findWithConditionsAndPaginationPost(@Param("inputString") String inputString, Pageable pageable);

    @Query("SELECT c FROM Componente c INNER JOIN c.tipoComponente tc WHERE 1 = 1  AND " +
            "(tc.especificacao LIKE %:inputString% OR tc.nome LIKE %:inputString% OR c.fornecedor LIKE %:inputString%)")
    Page<Componente> findWithConditionsAndPaginationEmprestado(@Param("inputString") String inputString,
            Pageable pageable);

}
