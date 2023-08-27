package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Disciplina;
import com.nelioalves.cursomc.domain.User;
import com.nelioalves.cursomc.dto.DisciplinaComponenteDTO;
import com.nelioalves.cursomc.dto.TipoComponenteDTO;
import com.nelioalves.cursomc.domain.DisciplinaComponente;
import com.nelioalves.cursomc.domain.Experimento;
import com.nelioalves.cursomc.domain.TipoComponente;
import com.nelioalves.cursomc.repositories.DisciplinaComponenteRepository;
import com.nelioalves.cursomc.resources.utils.HandleInputs;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class DisciplinaComponenteService {
	
    @Autowired
    private DisciplinaComponenteRepository repo;
    
    @Autowired
    private ClienteService clienteservice;
    
    @Autowired
    private DisciplinaService disciplinaservice;
    
    
	public DisciplinaComponente find(Integer id) {
		
		Optional<DisciplinaComponente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " ));
		
	}
	
    public List<DisciplinaComponente> findAll() {
        return repo.findAll();
    }
    
    public DisciplinaComponente findVinculoByDisciplinaId(Integer disciplinaId , Integer userId) {
        // Implement the logic to find DisciplinaComponente by disciplinaId
        // You can use a repository or any other mechanism to query the data

        // Assuming you have a repository called 'disciplinaComponenteRepository'
        return repo.findVinculoBydisciplinaIdAndUserId(disciplinaId,userId);
    }
    
    public DisciplinaComponente findVinculoByExperimentoId(Integer experimentoId , Integer userId) {
    	 
    	return repo.findVinculoByExperimentoIdAndUserId(experimentoId,userId);
    }
    
    public DisciplinaComponente findByDisciplinaIdWhereExperimentoIsNull(Disciplina disciplina) {
        // Implement the logic to find DisciplinaComponente by disciplinaId
        // You can use a repository or any other mechanism to query the data

        // Assuming you have a repository called 'disciplinaComponenteRepository'
        return repo.findByDisciplinaIdAndExperimentoIdIsNull(disciplina);
    }
    
    public Page<DisciplinaComponente> findMyDisciplinas(int pageNumber, int pageSize) {
        // Create a PageRequest object with pagination parameters
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        
        User user  = clienteservice.findMySelf();
      
        Integer userId = user.getId();

        Page<DisciplinaComponente> result = repo.findWitPaginationAndWhere(userId,pageRequest);

        return result;
    }
    
    public DisciplinaComponente fromDTO(DisciplinaComponenteDTO objDto, User user) {
        return new DisciplinaComponente(null, disciplinaservice.find(objDto.getDisciplinaId()),
        		clienteservice.find(user.getId())) ;    
    }
    
    public DisciplinaComponente insert(DisciplinaComponente obj) {
        obj.setId(null);
        obj.setDisciplinaId(obj.getDisciplinaId());
        obj.setUserId(obj.getUserId());

        return repo.save(obj);
    }
    
    public DisciplinaComponente insertFromExperimento(User objUser,Disciplina objDisciplina , Experimento objExp) {
    	DisciplinaComponente dc = new DisciplinaComponente();
    	dc.setId(null);
    	dc.setDisciplinaId(objDisciplina);
    	dc.setExperimentoId(objExp);
    	dc.setUserId(objUser);

        return repo.save(dc);
    }
    
	public void delete(Integer id) {
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
    public Page<DisciplinaComponente> findMyExperimentosWithPagination(Integer  disciplinaId, Integer userId , int pageNumber, int pageSize) {
        // Create a PageRequest object with pagination parameters
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        

        Page<DisciplinaComponente> result = repo.findMyExperimentosWithPagination(disciplinaId,userId, pageRequest);

        return result;

    }
    

}
