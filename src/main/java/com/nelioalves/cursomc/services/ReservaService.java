package com.nelioalves.cursomc.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.DisciplinaComponente;
import com.nelioalves.cursomc.domain.Reserva;
import com.nelioalves.cursomc.domain.User;
import com.nelioalves.cursomc.dto.ReservaDTO;
import com.nelioalves.cursomc.repositories.ReservaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repo;
    
    @Autowired
    private ClienteService clienteservice;
    
    @Autowired
    private TipoComponenteService tipoComponenteService;
    
    @Autowired
    private ExperimentoService experimentoService ;
    
    public List<Reserva> findAll() {
        return repo.findAll();
    }
    
	public Reserva find(Integer id) {
		
		Optional<Reserva> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " ));
		
	}
    
    
    public Page<Reserva> findMyReservas(int pageNumber, int pageSize, Integer experimentoId) {
        // Create a PageRequest object with pagination parameters
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        


        Page<Reserva> result = repo.findMyReservasWitPaginationAndWhere(experimentoId,pageRequest);

        return result;
    } 
    
    public void saveProducts(List<Reserva> reservaList) {
        // Save the records in the database using the repository
    	repo.saveAll(reservaList);
    }
    
    public List<Reserva> fromListDTO(List<ReservaDTO> reservaDTOList) {
        List<Reserva> reservas = new ArrayList<>();

        for (ReservaDTO reservaDTO : reservaDTOList) {
            try {
                Integer tipoComponenteId = reservaDTO.getTipoComponenteId();
                Integer experimentoId = reservaDTO.getExperimentoId();
                Integer quantidade = reservaDTO.getQuantidade();

                // Assuming you have a constructor in the Reserva class that takes the required parameters.
                Reserva reserva = new Reserva(tipoComponenteService.find(tipoComponenteId) ,experimentoService.find(experimentoId) , quantidade);

                reservas.add(reserva);
            } catch (NumberFormatException | NullPointerException e) {
                // Handle invalid data here.
                // For example, you could log the error or skip the entry.
            }
        }

        return reservas;
    }

	public void delete(Integer id) {
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
    
}
