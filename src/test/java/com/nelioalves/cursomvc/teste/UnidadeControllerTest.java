package com.nelioalves.cursomvc.teste;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nelioalves.cursomc.domain.Unidade;
import com.nelioalves.cursomc.resources.UnidadeResource;
import com.nelioalves.cursomc.services.UnidadeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UnidadeControllerTest {

    @Mock
    private UnidadeService service;

    @InjectMocks
    private UnidadeResource controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<Unidade> unidades = new ArrayList<>();
        unidades.add(new Unidade("Unidade 1"));
        unidades.add(new Unidade("Unidade 2"));

        when(service.findAll()).thenReturn(unidades);

        // Act
        ResponseEntity<List<Unidade>> response = controller.findAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(unidades, response.getBody());
    }
}