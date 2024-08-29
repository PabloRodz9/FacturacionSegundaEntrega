package com.example.facturacion_segunda_entrega.DTO;

import java.util.Objects;

public class ClientDTO {

    private int id;
    private String name;
    private String lastName;
    private String docNumber;

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", docNumber='" + docNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return id == clientDTO.id && Objects.equals(name, clientDTO.name) && Objects.equals(lastName, clientDTO.lastName) && Objects.equals(docNumber, clientDTO.docNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, docNumber);
    }

// Getters and Setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

}