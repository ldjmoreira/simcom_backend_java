package com.nelioalves.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.nelioalves.cursomc.domain.Unidade;
import com.nelioalves.cursomc.services.UnidadeService;

@RestController
@RequestMapping(value = "/api/unidade")
public class UnidadeResource {

    @Autowired
    private UnidadeService service;
    
    
    
    @GetMapping("/all")
    public ResponseEntity<List<Unidade>> findAll() {
        List<Unidade> TipoComponentes = service.findAll();
        return ResponseEntity.ok().body(TipoComponentes);
    }
    
}
