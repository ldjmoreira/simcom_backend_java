package com.nelioalves.cursomc.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.nelioalves.cursomc.services.DisciplinaService;


@RestController
@RequestMapping(value = "/api/disciplinas")
public class DisciplinaResource {

    @Autowired
    private DisciplinaService service;
    
    @GetMapping("/all")
    public ResponseEntity<List<Disciplina>> findAll() {
        List<Disciplina> Disciplina = service.findAll();
        return ResponseEntity.ok().body(Disciplina);
    }
    
	@RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<Disciplina> find(@PathVariable Integer id) {
		Disciplina obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
    
    @GetMapping("/buscar")
    public ResponseEntity<Page<Disciplina>> getEntitiesWithPagination(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", defaultValue = "2") int pageSize) {
        Page<Disciplina> entities = service.findWithConditionsAndPagination(pageNumber, pageSize);
        return ResponseEntity.ok().body(entities);
    }
    
    @PostMapping("/buscar")
    public ResponseEntity<Page<Disciplina>> getEntitiesWithConditions(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @RequestBody String searchRequest) {

        Page<Disciplina> entities = service.findWithConditionsAndPaginationPost(searchRequest, pageNumber, pageSize);
        return ResponseEntity.ok().body(entities);
    }
    
    @PostMapping("/salvar")
    public ResponseEntity<Disciplina> insert(@Valid @RequestBody Disciplina obj) {

        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Disciplina obj, @PathVariable Integer id) {
		
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
    
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = {"/{id}", "/delete/{id}"}, method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
    
}
