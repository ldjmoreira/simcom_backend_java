package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.nelioalves.cursomc.domain.Disciplina;

import com.nelioalves.cursomc.repositories.DisciplinaRepository;
import com.nelioalves.cursomc.resources.utils.HandleInputs;

import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository repo;
    
	public Disciplina find(Integer id) {
		
		Optional<Disciplina> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " ));
		
	}
    
    public List<Disciplina> findAll() {
        return repo.findAll();
    }
    
    public Page<Disciplina> findWithConditionsAndPagination(int pageNumber, int pageSize) {
        // Create a PageRequest object with pagination parameters
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<Disciplina> result = repo.findWitPagination(pageRequest);

        return result;
    }
    
    public Page<Disciplina> findWithConditionsAndPaginationPost(String conditionDirty, int pageNumber, int pageSize) {
        // Create a PageRequest object with pagination parameters
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        String searchTerm = HandleInputs.getInputParam(conditionDirty);

        Page<Disciplina> result = repo.findWithConditionsAndPaginationPost(searchTerm, pageRequest);

        return result;

    }
    
	public Disciplina insert(Disciplina obj) {
		obj.setId(null);
		obj.setNome(obj.getNome());
		obj = repo.save(obj);

		return obj;
	}
	
	public Disciplina update(Disciplina obj) {
		Disciplina newObj = find(obj.getId());
		
		newObj.setNome(obj.getNome());
		
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
