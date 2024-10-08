package com.example.facturacion_segunda_entrega.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entity representing a client in the system")
@Entity
@Table(name = "clients")
public class Client {

    @Schema(description = "Unique identifier of the client", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Schema(description = "Client's first name", example = "John", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "name", nullable = false, length = 75)
    private String name;

    @Schema(description = "Client's last name", example = "Doe", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "last_name", nullable = false, length = 75)
    private String lastName;

    @Schema(description = "Client's document number", example = "12345678", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "doc_number", nullable = false, length = 8)
    private String docNumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Sale> sales;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(name, client.name) && Objects.equals(lastName, client.lastName) && Objects.equals(docNumber, client.docNumber) && Objects.equals(sales, client.sales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, docNumber, sales);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", sales=" + sales +
                '}';
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

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}