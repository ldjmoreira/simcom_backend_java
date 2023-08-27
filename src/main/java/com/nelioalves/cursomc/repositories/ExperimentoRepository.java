package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nelioalves.cursomc.domain.DisciplinaComponente;
import com.nelioalves.cursomc.domain.Experimento;

public interface ExperimentoRepository extends JpaRepository<Experimento, Integer>{

	
   
}
