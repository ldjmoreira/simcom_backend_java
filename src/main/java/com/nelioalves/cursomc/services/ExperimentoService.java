package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Disciplina;
import com.nelioalves.cursomc.domain.DisciplinaComponente;
import com.nelioalves.cursomc.domain.Experimento;
import com.nelioalves.cursomc.repositories.ExperimentoRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ExperimentoService {
	
    @Autowired
    private ExperimentoRepository repo;
	
	public Experimento insert(Experimento obj) {
		obj.setId(null);
		obj.setNome(obj.getNome());
		obj.setObs(obj.getObs());
		obj = repo.save(obj);

		return obj;
	}
	
	public Experimento find(Integer id) {
		
		Optional<Experimento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " ));
		
	}
	public Experimento update(Experimento obj) {
		Experimento newObj = find(obj.getId());
		
		newObj.setNome(obj.getNome());
		newObj.setObs(obj.getObs());
		
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	


}
