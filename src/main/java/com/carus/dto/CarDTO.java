package com.carus.dto;

import com.carus.entities.CarEntity;
import lombok.Data;

@Data
public class CarDTO {

    private Long id;
    private String marca;
    private String modelo;
    private String ano;
    private String cor;
    private String placa;
    private String combustivel;
    private String cambio;
    private String portas;
    private String lugares;
    private String malas;

    public CarDTO(CarEntity entity) {
        this.id = entity.getId();
        this.marca = entity.getMarca();
        this.modelo = entity.getModelo();
        this.ano = entity.getAno();
        this.cor = entity.getCor();
        this.placa = entity.getPlaca();
        this.combustivel = entity.getCombustivel();
        this.cambio = entity.getCambio();
        this.portas = entity.getPortas();
        this.lugares = entity.getLugares();
        this.malas = entity.getMalas();
    }

}
