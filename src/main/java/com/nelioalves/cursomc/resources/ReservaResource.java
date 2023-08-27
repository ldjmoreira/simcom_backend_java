package com.nelioalves.cursomc.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
import com.nelioalves.cursomc.domain.Reserva;
import com.nelioalves.cursomc.dto.ReservaDTO;
import com.nelioalves.cursomc.resources.utils.ReservaDTOconverter;
import com.nelioalves.cursomc.domain.User;
import com.nelioalves.cursomc.dto.DisciplinaComponenteDTO;
import com.nelioalves.cursomc.dto.ModifiedListWrapper;
import com.nelioalves.cursomc.services.ClienteService;
import com.nelioalves.cursomc.services.DisciplinaComponenteService;
import com.nelioalves.cursomc.services.ReservaService;
import com.nelioalves.cursomc.services.exceptions.AuthorizationException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/api/reservas")
public class ReservaResource {

    @Autowired
    private ReservaService service;
    
    @Autowired
    private ClienteService clienteservice;
    
    @Autowired
    private DisciplinaComponenteService dpservice;
    
    @GetMapping("/all")
    public ResponseEntity<List<Reserva>> findAll() {
        List<Reserva> reserva = service.findAll();
        return ResponseEntity.ok().body(reserva);
    }
    
    
    @RequestMapping(value = "/myreserva/buscar/{experimentoId}", method = RequestMethod.GET)
    public ResponseEntity<Page<Reserva>> findbyId(
        @PathVariable Integer experimentoId,
        @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
        @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        User user = clienteservice.findMySelf();

        DisciplinaComponente vinculo = dpservice.findVinculoByExperimentoId(experimentoId, user.getId());

        if (user.getId() == vinculo.getUserId().getId()) {
        	
            Page<Reserva> entities = service.findMyReservas(pageNumber, pageSize,experimentoId);
            
            return ResponseEntity.ok().body(entities);
        } else {
            String errorMessage = "Unauthorized access. You are not allowed to delete this record.";
            throw new AuthorizationException(errorMessage);
        }
    }
    
    @PostMapping("/salvartodos/{experimentoId}")
    public ResponseEntity<String> insert(
    		@RequestBody Map<String, Map<String, Object>> productMap,@PathVariable Integer experimentoId) {
    	
    	List<ReservaDTO> products = ReservaDTOconverter.convertProductMapToList(productMap);
    	
        User user = clienteservice.findMySelf();

        DisciplinaComponente vinculo = dpservice.findVinculoByExperimentoId(experimentoId, user.getId());

        if (user.getId() == vinculo.getUserId().getId()) {
        	List<Reserva> reservaList	= service.fromListDTO(products);
        	service.saveProducts(reservaList);
            
            return ResponseEntity.ok("Records saved successfully");
        } else {
            String errorMessage = "Unauthorized access. You are not allowed to delete this record.";
            throw new AuthorizationException(errorMessage);
        }
        
        
    }
    
    
	@RequestMapping(value = "/delete/{id}/{experimentoid}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id, @PathVariable Integer experimentoid) {
		User user  = clienteservice.findMySelf();
		
		
		DisciplinaComponente DiscCompObj = dpservice.findVinculoByExperimentoId(experimentoid,user.getId());
		
        if (DiscCompObj == null) {
       	 String errorMessage = "you are not allowed to delete this record";
            throw new ObjectNotFoundException(errorMessage);
       }

		service.delete(id);

		
		return ResponseEntity.noContent().build();
	}
    
    
}
