package com.nelioalves.cursomc.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import com.nelioalves.cursomc.domain.Componente;
import com.nelioalves.cursomc.domain.Disciplina;
import com.nelioalves.cursomc.domain.TipoComponente;
import com.nelioalves.cursomc.domain.User;
import com.nelioalves.cursomc.dto.TipoComponenteDTO;
import com.nelioalves.cursomc.repositories.TipoComponenteRepository;
import com.nelioalves.cursomc.resources.utils.HandleInputs;
import com.nelioalves.cursomc.security.UserSS;

import java.util.Optional;

import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class TipoComponenteService {

    @Autowired
    private TipoComponenteRepository repo;

    @Autowired
    private UnidadeService unidadeService;

    public TipoComponente fromDTO(TipoComponenteDTO objDto) {
        return new TipoComponente(null, objDto.getNome(), objDto.getValor(),
                objDto.getEspecificacao(), objDto.getTipo(), objDto.getValidade(),
                unidadeService.find((objDto.getUnidadeId())));
    }
    
    public TipoComponente find(Integer id) {

        Optional<TipoComponente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + TipoComponente.class.getName()));
    }

    public List<TipoComponente> findAll() {
        return repo.findAll();
    }
    
    public Page<TipoComponente> findWithConditionsAndPagination(int pageNumber, int pageSize) {
        // Create a PageRequest object with pagination parameters
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<TipoComponente> result = repo.findWithPagination(pageRequest);

        return result;
    }
    
    public Page<TipoComponente> findWithConditionsAndPaginationPost(String conditionDirty, int pageNumber, int pageSize) {
        // Create a PageRequest object with pagination parameters
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        String searchTerm = HandleInputs.getInputParam(conditionDirty);

        Page<TipoComponente> result = repo.findWithConditionsAndPaginationPost(searchTerm, pageRequest);

        return result;

    }

    public TipoComponente insert(TipoComponente obj) {
        obj.setId(null);
        obj.setNome(obj.getNome());

        obj.setValor(obj.getValor());
        obj.setEspecificacao(obj.getEspecificacao());
        obj.setTipo(obj.getTipo());
        obj.setValidade(obj.getValidade());
        obj.setUnidadeId(obj.getUnidadeId());

        return repo.save(obj);
    }

	public TipoComponente update(TipoComponenteDTO objDto,Integer id) {
		TipoComponente obj = find(id);
		updateData(objDto, obj);
		return repo.save(obj);
	}
	
	private void updateData(TipoComponenteDTO objDto, TipoComponente obj) {
	    if (objDto.getNome() != null) {
	        obj.setNome(objDto.getNome());
	    }
	    
	    if (objDto.getValor() != null) {
	        obj.setValor(objDto.getValor());
	    }
	    
	    if (objDto.getUnidadeId() != null) {
	        obj.setUnidadeId(unidadeService.find(objDto.getUnidadeId()));
	    }
	    
	    if (objDto.getEspecificacao() != null) {
	        obj.setEspecificacao(objDto.getEspecificacao());
	    }
	    
	    if (objDto.getTipo() != null) {
	        obj.setTipo(objDto.getTipo());
	    }
	    
	    if (objDto.getValidade() != null) {
	        obj.setValidade(objDto.getValidade());
	    }
		
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
}
