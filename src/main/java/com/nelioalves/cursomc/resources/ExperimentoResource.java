package com.nelioalves.cursomc.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Disciplina;
import com.nelioalves.cursomc.domain.DisciplinaComponente;
import com.nelioalves.cursomc.domain.Experimento;
import com.nelioalves.cursomc.domain.User;
import com.nelioalves.cursomc.dto.DisciplinaComponenteDTO;
import com.nelioalves.cursomc.services.ClienteService;
import com.nelioalves.cursomc.services.DisciplinaComponenteService;
import com.nelioalves.cursomc.services.DisciplinaService;
import com.nelioalves.cursomc.services.ExperimentoService;
import com.nelioalves.cursomc.services.exceptions.AuthorizationException;



@RestController
@RequestMapping(value = "/api/experimento")
public class ExperimentoResource {

    @Autowired
    private ExperimentoService service;
    
    @Autowired
    private ClienteService clienteservice;
    
    @Autowired
    private DisciplinaComponenteService dpservice;
    
    @Autowired
    private DisciplinaService dservice;
    
    

    
	@RequestMapping(value = "/mydisciplinas/findbyid/{experimentoId}", method = RequestMethod.GET)
	public ResponseEntity<Experimento> find(@PathVariable Integer experimentoId) {
		
        User user  = clienteservice.findMySelf();
        
        DisciplinaComponente vinculo = dpservice.findVinculoByExperimentoId(experimentoId,user.getId());
    	
		if (user.getId() == vinculo.getUserId().getId()) {
					
			Experimento obj = service.find(experimentoId);
			
			return ResponseEntity.ok().body(obj);
		}else {
	        String errorMessage = "Unauthorized access. You are not allowed to delete this record.";
	        throw new AuthorizationException(errorMessage);
		}
		
		
	}
	
    @PostMapping("/salvar/{disciplinaid}")
    public ResponseEntity<Experimento> insert(@PathVariable Integer disciplinaid,
    		@Valid @RequestBody Experimento obj) {
    	
    	User user  = clienteservice.findMySelf();
    	
    	Disciplina  objDisciplina = dservice.find(disciplinaid);
    	// precisa confirmar se o usuario realmente tem essa disciplina #depois
    	
        Experimento objExpSaved = service.insert(obj);
        
        DisciplinaComponente objSaved = dpservice.insertFromExperimento(user,objDisciplina,objExpSaved);
        
        
        return ResponseEntity.noContent().build();
    }
    
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Experimento> update(@Valid @RequestBody Experimento obj, @PathVariable Integer id) {
		
        User user  = clienteservice.findMySelf();
        
        DisciplinaComponente vinculo = dpservice.findVinculoByExperimentoId(id,user.getId());
        
		if (user.getId() == vinculo.getUserId().getId()) {
			
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.ok().body(obj);
			
		}else {
	        String errorMessage = "Unauthorized access. You are not allowed to delete this record.";
	        throw new AuthorizationException(errorMessage);
		}
        

	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		
        User user  = clienteservice.findMySelf();
        
        DisciplinaComponente vinculo = dpservice.findVinculoByExperimentoId(id,user.getId());
        
		if (user.getId() == vinculo.getUserId().getId()) {
			
			service.delete(id);
			dpservice.delete(vinculo.getId());
			
			return ResponseEntity.noContent().build();
			
		}else {
	        String errorMessage = "Unauthorized access. You are not allowed to delete this record.";
	        throw new AuthorizationException(errorMessage);
		}
		

	}
	
}
