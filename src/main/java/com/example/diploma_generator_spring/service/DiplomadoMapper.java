package com.example.diploma_generator_spring.service;

import com.example.diploma_generator_spring.dto.DiplomadoRequest;
import com.example.diploma_generator_spring.dto.DiplomadoResponse;
import com.example.diploma_generator_spring.model.Diplomado;
import org.springframework.stereotype.Service;

@Service
public class DiplomadoMapper {

    public Diplomado requestToDiplomado(DiplomadoRequest diplomadoRequest){
        Diplomado diplomado = new Diplomado();
        diplomado.setNome(diplomadoRequest.nome());
        diplomado.setNacionalidade(diplomadoRequest.nacionalidade());
        diplomado.setNaturalidade(diplomadoRequest.naturalidade());
        diplomado.setRg(diplomadoRequest.rg());
        return diplomado;
    }

    public DiplomadoResponse diplomadoToResponse(Diplomado diplomado){
        return new DiplomadoResponse(
                diplomado.getId(),
                diplomado.getNome(),
                diplomado.getNacionalidade(),
                diplomado.getNaturalidade(),
                diplomado.getRg()
        );
    }
}
