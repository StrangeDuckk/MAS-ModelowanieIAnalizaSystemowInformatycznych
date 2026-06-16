package edupjamas.s30338.entity.Wielodziedziczenie;

import jakarta.persistence.Transient;

public interface ICandidate {
    @Transient
    public Candidate getCandidate();
}
