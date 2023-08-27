package com.nelioalves.cursomc.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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
import com.nelioalves.cursomc.domain.TipoComponente;
import com.nelioalves.cursomc.domain.User;
import com.nelioalves.cursomc.dto.DisciplinaComponenteDTO;
import com.nelioalves.cursomc.dto.TipoComponenteDTO;
import com.nelioalves.cursomc.services.ClienteService;
import com.nelioalves.cursomc.services.DisciplinaComponenteService;
import com.nelioalves.cursomc.services.DisciplinaService;
import com.nelioalves.cursomc.services.exceptions.AuthorizationException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/api/vinculos")
public class DisciplinaComponenteResource {
	
    @Autowired
    private DisciplinaComponenteService service;
    
    @Autowired
    private DisciplinaService disciplinaservice;
    
    
    @Autowired
    private ClienteService clienteservice;
	
    @GetMapping("/all")
    public ResponseEntity<List<DisciplinaComponente>> findAll() {
        List<DisciplinaComponente> vinculos = service.findAll();
        return ResponseEntity.ok().body(vinculos);
    }

    @GetMapping("/mydisciplinas")
    public ResponseEntity<Page<DisciplinaComponente>> findMyDisciplinas(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", defaultValue = "2") int pageSize) {
        Page<DisciplinaComponente> entities = service.findMyDisciplinas(pageNumber, pageSize);
        return ResponseEntity.ok().body(entities);
    }
    
    @GetMapping("/mydisciplinas/experimentos/findall")
    public ResponseEntity<Page<DisciplinaComponente>> getEntitiesWithConditions(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @RequestParam(value = "disciplinaId", defaultValue = "10") Integer disciplinaId) {
    	
    	User user  = clienteservice.findMySelf();
    	
    	DisciplinaComponente vinculo = service.findVinculoByDisciplinaId(disciplinaId,user.getId());
    	
		if (user.getId() == vinculo.getUserId().getId()) {
			
			// Integer myDisciplinaId = vinculo.getDisciplinaId().getId();
			Integer myDisciplinaId = disciplinaId;
			
			Page<DisciplinaComponente> entities = service.findMyExperimentosWithPagination(myDisciplinaId, user.getId() ,pageNumber, pageSize);
			return ResponseEntity.ok().body(entities);
		}else {
	        String errorMessage = "Unauthorized access. You are not allowed to delete this record.";
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // or ResponseEntity.ok().body(Page.empty())
		}
		
		// mudar a forma como isso é feito acima;
        
    }
    
    @PostMapping("/salvar")
    public ResponseEntity<DisciplinaComponente> insert(@Valid @RequestBody DisciplinaComponenteDTO objDto) {
    	
    	User user  = clienteservice.findMySelf();
    	
    	DisciplinaComponente obj = service.fromDTO(objDto,user);
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		User user  = clienteservice.findMySelf();
		
		
		Disciplina DisciplinaObj = disciplinaservice.find(id);
		
		DisciplinaComponente obj = service.findByDisciplinaIdWhereExperimentoIsNull(DisciplinaObj);
		
        if (obj == null) {
        	 String errorMessage = "deletion is not allowed";
             throw new ObjectNotFoundException(errorMessage);
        }
				
		if (user.getId() == obj.getUserId().getId()) {
			service.delete(obj.getId());
		}else {
	        String errorMessage = "Unauthorized access. You are not allowed to delete this record.";
	        throw new AuthorizationException(errorMessage);
		}
		
		return ResponseEntity.noContent().build();
	}
	// not used anymore
	
    /*
	@RequestMapping(value = "/mydisciplinas/experimentos/findbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<DisciplinaComponente> find(@PathVariable Integer id) {
		DisciplinaComponente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
    */
}
