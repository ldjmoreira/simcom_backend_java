package com.nelioalves.cursomc.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "reservas")
public class Reserva implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(name = "uuid")
	    private Integer uuid;

		@ManyToOne
		@JoinColumn(name="tipo_componente_id")
	    private TipoComponente tipoComponenteId;

	    
		@ManyToOne
		@JoinColumn(name="experimento_id")
	    private Experimento experimentoId;
		


	    private Integer quantidade;

	    // Constructors, getters, and setters

	    public Reserva() {
	    }

	    public Reserva(Integer uuid, TipoComponente tipoComponenteId, Experimento experimentoId, Integer quantidade) {
	        this.uuid = uuid;
	        this.tipoComponenteId = tipoComponenteId;
	        this.experimentoId = experimentoId;
	        this.quantidade = quantidade;
	    }
	    
	    public Reserva( TipoComponente tipoComponenteId, Experimento experimentoId, Integer quantidade) {

	        this.tipoComponenteId = tipoComponenteId;
	        this.experimentoId = experimentoId;
	        this.quantidade = quantidade;
	    }

	    // Getters and Setters

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public Integer getUuid() {
	        return uuid;
	    }

	    public void setUuid(Integer uuid) {
	        this.uuid = uuid;
	    }

	    public TipoComponente getTipoComponenteId() {
	        return tipoComponenteId;
	    }

	    public void setTipoComponenteId(TipoComponente tipoComponenteId) {
	        this.tipoComponenteId = tipoComponenteId;
	    }

	    public Experimento getExperimentoId() {
	        return experimentoId;
	    }

	    public void setExperimentoId(Experimento experimentoId) {
	        this.experimentoId = experimentoId;
	    }

	    public Integer getQuantidade() {
	        return quantidade;
	    }

	    public void setQuantidade(Integer quantidade) {
	        this.quantidade = quantidade;
	    }
}
