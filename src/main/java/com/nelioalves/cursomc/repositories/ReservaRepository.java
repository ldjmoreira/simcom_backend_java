package com.nelioalves.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.nelioalves.cursomc.domain.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>{

    @Query("SELECT re FROM Reserva re  WHERE (1 = 1) AND " +
            "(re.experimentoId.id = :experimentoId )")
    Page<Reserva> findMyReservasWitPaginationAndWhere(@Param("experimentoId") Integer experimentoId,Pageable pageable);
}
