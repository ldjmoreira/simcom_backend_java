package com.nelioalves.cursomc.resources;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nelioalves.cursomc.services.TipoComponenteService;
import com.nelioalves.cursomc.domain.Componente;
import com.nelioalves.cursomc.domain.Disciplina;
import com.nelioalves.cursomc.domain.TipoComponente;
import com.nelioalves.cursomc.domain.User;
import com.nelioalves.cursomc.dto.TipoComponenteDTO;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/tipocomponente")
public class TipoComponenteResource {

    @Autowired
    private TipoComponenteService service;
    /*
     * @PostMapping("/salvar2")
     * public ResponseEntity<Void> insert2(@Valid @RequestBody TipoComponenteDTO
     * objDto) {
     * TipoComponente obj = service.fromDTO(objDto);
     * obj = service.insert(obj);
     * URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
     * .path("/{id}").buildAndExpand(obj.getId()).toUri();
     * return ResponseEntity.created(uri).build();
     * }
     */
    @GetMapping("/buscar")
    public ResponseEntity<Page<TipoComponente>> getEntitiesWithPagination(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", defaultValue = "2") int pageSize) {
        Page<TipoComponente> entities = service.findWithConditionsAndPagination(pageNumber, pageSize);
        return ResponseEntity.ok().body(entities);
    }

    @PostMapping("/buscar")
    public ResponseEntity<Page<TipoComponente>> getEntitiesWithConditions(
            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
            @RequestParam(value = "size", defaultValue = "10") int pageSize,
            @RequestBody String searchRequest) {

        Page<TipoComponente> entities = service.findWithConditionsAndPaginationPost(searchRequest, pageNumber, pageSize);
        return ResponseEntity.ok().body(entities);
    }
    
	@RequestMapping(value = "/findbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<TipoComponente> find(@PathVariable Integer id) {
		TipoComponente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
    @GetMapping("/all")
    public ResponseEntity<List<TipoComponente>> findAll() {
        List<TipoComponente> TipoComponentes = service.findAll();
        return ResponseEntity.ok().body(TipoComponentes);
    }

    @PostMapping("/salvar")
    public ResponseEntity<TipoComponente> insert(@Valid @RequestBody TipoComponenteDTO objDto) {
        TipoComponente obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody TipoComponenteDTO objDto, @PathVariable Integer id) {
		

		TipoComponente	obj = service.update(objDto,id);
		
		return ResponseEntity.noContent().build();
	}

    @PostMapping("/salvar3")
    public ResponseEntity<Map<String, Object>> insert3(@Valid @RequestBody TipoComponenteDTO objDto) {
        TipoComponente obj = service.fromDTO(objDto);
        obj = service.insert(obj);

        // Create a Map to hold the desired properties
        Map<String, Object> response = new HashMap<>();
        response.put("id", obj.getId());
        response.put("nome", obj.getNome());

        // Create the URI for the newly created resource
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
    
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
