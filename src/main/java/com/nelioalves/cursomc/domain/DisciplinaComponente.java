package com.nelioalves.cursomc.domain;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "disciplinas_componentes")
public class DisciplinaComponente implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @ManyToOne
	@JoinColumn(name="disciplina_id")
    private Disciplina disciplinaId;


    @ManyToOne
	@JoinColumn(name="experimento_id")
    private Experimento experimentoId;

	@ManyToOne
	@JoinColumn(name="user_id")
    private User userId;

    private String obs;

    // Constructors, getters, and setters

    public DisciplinaComponente() {
    }

    public DisciplinaComponente(Disciplina disciplinaId, Experimento experimentoId, User userId, String obs) {
        this.disciplinaId = disciplinaId;
        this.experimentoId = experimentoId;
        this.userId = userId;
        this.obs = obs;
    }
    public DisciplinaComponente(Integer id,Disciplina disciplinaId,  User userId ) {
    	this.id = id;
        this.disciplinaId = disciplinaId;
        this.userId = userId;
    }


    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Disciplina getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Disciplina disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public Experimento getExperimentoId() {
        return experimentoId;
    }

    public void setExperimentoId(Experimento experimentoId) {
        this.experimentoId = experimentoId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
