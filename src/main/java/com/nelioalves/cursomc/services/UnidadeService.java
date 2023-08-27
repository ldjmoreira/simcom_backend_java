package com.nelioalves.cursomc.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.nelioalves.cursomc.repositories.UnidadeRepository;
import com.nelioalves.cursomc.domain.TipoComponente;
import com.nelioalves.cursomc.domain.Unidade;

import java.util.List;
import java.util.Optional;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository repo;

    public Unidade find(Integer id) {
        Optional<Unidade> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Unidade.class.getName()));
    }
    
    public List<Unidade> findAll() {
        return repo.findAll();
    }

}
