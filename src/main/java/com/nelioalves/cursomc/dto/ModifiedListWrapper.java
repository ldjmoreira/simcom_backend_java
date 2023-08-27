package com.nelioalves.cursomc.dto;

import java.io.Serializable;
import com.nelioalves.cursomc.domain.Reserva;
import java.util.List;

public class ModifiedListWrapper implements Serializable {

    private List<Reserva> modifiedList;

    // Constructors
    public ModifiedListWrapper() {
    }

    public ModifiedListWrapper(List<Reserva> modifiedList) {
        this.modifiedList = modifiedList;
    }

    // Getters and setters
    public List<Reserva> getModifiedList() {
        return modifiedList;
    }

    public void setModifiedList(List<Reserva> modifiedList) {
        this.modifiedList = modifiedList;
    }
}
