package com.nelioalves.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.Componente;
import com.nelioalves.cursomc.domain.TipoComponente;

@Repository
public interface TipoComponenteRepository extends JpaRepository<TipoComponente, Integer> {

    @Query("SELECT t FROM TipoComponente t ORDER BY t.nome")
    Page<TipoComponente> findWithPagination(Pageable pageable);
    
    @Query("SELECT t FROM TipoComponente t  WHERE 1 = 1  AND " +
            "(t.especificacao LIKE %:inputString% OR t.nome LIKE %:inputString% OR t.valor LIKE %:inputString%) ORDER BY t.nome")
    Page<TipoComponente> findWithConditionsAndPaginationPost(@Param("inputString") String inputString, Pageable pageable);
    
}
