package ista.cursoM4A.model;

import jakarta.persistence.*;

@Entity
@Table(name = "registro")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String original;
    private String cifrado;

    // Getters y setters
    public Long getId() { return id; }

    public String getOriginal() { return original; }
    public void setOriginal(String original) { this.original = original; }

    public String getCifrado() { return cifrado; }
    public void setCifrado(String cifrado) { this.cifrado = cifrado; }
}