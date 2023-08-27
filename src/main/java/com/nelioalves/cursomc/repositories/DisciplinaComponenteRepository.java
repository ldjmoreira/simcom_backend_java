package com.nelioalves.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nelioalves.cursomc.domain.Disciplina;
import com.nelioalves.cursomc.domain.DisciplinaComponente;

public interface DisciplinaComponenteRepository extends JpaRepository<DisciplinaComponente, Integer>{

    @Query(value = "SELECT dc.* FROM disciplinas_componentes dc " +
            "JOIN (SELECT disciplina_id, MAX(id) as latest_id " +
            "      FROM disciplinas_componentes " +
            "      GROUP BY disciplina_id) latest_ids " +
            "ON dc.disciplina_id = latest_ids.disciplina_id AND dc.id = latest_ids.latest_id " +
            "WHERE (1 = 1) AND (dc.user_id = :userId)", nativeQuery = true)
    Page<DisciplinaComponente> findWitPaginationAndWhere(@Param("userId") Integer userId, Pageable pageable);
    
    @Query("SELECT dc FROM DisciplinaComponente dc  WHERE (1 = 1) AND " +
            "(dc.userId.id = :userId ) AND (dc.disciplinaId.id = :disciplinaId) ")
    Page<DisciplinaComponente> findMyExperimentosWithPagination(@Param("disciplinaId") Integer disciplinaId,
    		@Param("userId") Integer userId,	Pageable pageable);
    
    @Query(value = "SELECT * FROM disciplinas_componentes dc WHERE dc.user_id = :userId AND dc.disciplina_id = :disciplinaId LIMIT 1", nativeQuery = true)
    DisciplinaComponente findVinculoBydisciplinaIdAndUserId(@Param("disciplinaId") Integer disciplinaId,@Param("userId") Integer userId);
    
    @Query("SELECT dc FROM DisciplinaComponente dc WHERE dc.disciplinaId = :disciplina AND dc.experimentoId IS NULL")
    DisciplinaComponente findByDisciplinaIdAndExperimentoIdIsNull(@Param("disciplina") Disciplina disciplina);
    
    @Query(value = "SELECT * FROM disciplinas_componentes dc WHERE dc.user_id = :userId AND dc.experimento_id = :experimentoId LIMIT 1", nativeQuery = true)
    DisciplinaComponente findVinculoByExperimentoIdAndUserId(@Param("experimentoId") Integer experimentoId,@Param("userId") Integer userId);
}
