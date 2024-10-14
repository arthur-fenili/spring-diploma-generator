package com.example.diploma_generator_spring.service;

import com.example.diploma_generator_spring.dto.DiplomaRequest;
import com.example.diploma_generator_spring.dto.DiplomaResponse;
import com.example.diploma_generator_spring.dto.DiplomaTextResponse;
import com.example.diploma_generator_spring.model.Diploma;
import com.example.diploma_generator_spring.model.Sexo;
import com.example.diploma_generator_spring.repository.CursoRepository;
import com.example.diploma_generator_spring.repository.DiplomadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class DiplomaMapper {

    private final CursoRepository cursoRepository;
    private final DiplomadoRepository diplomadoRepository;

    @Autowired
    public DiplomaMapper(CursoRepository cursoRepository, DiplomadoRepository diplomadoRepository) {
        this.cursoRepository = cursoRepository;
        this.diplomadoRepository = diplomadoRepository;
    }

    public Diploma requestToDiploma(DiplomaRequest request) {
        Diploma diploma = new Diploma();
        diploma.setDiplomado(diplomadoRepository.findById(request.diplomadoId()).get());
        diploma.setCurso(cursoRepository.findById(request.cursoId()).get());
        diploma.setDataDeConclusao(request.dataConclusao());
        diploma.setSexoReitor(request.sexoReitor());
        diploma.setNomeReitor(request.nomeReitor());
        return diploma;
    }

    public DiplomaResponse diplomaToResponse(Diploma diploma) {
        return new DiplomaResponse(diploma.getId(),
                diploma.getDiplomado(),
                diploma.getCurso().getNome(),
                diploma.getDataDeConclusao(),
                diploma.getSexoReitor(),
                diploma.getNomeReitor());
    }

    public DiplomaTextResponse diplomaTextResponse(Diploma diploma) {
        String tituloReitor;
        String cargoReitor;

        if (diploma.getSexoReitor() == Sexo.M) {
            tituloReitor = "Prof. Dr. " + diploma.getNomeReitor();
            cargoReitor = "reitor";
        } else {
            tituloReitor = "Profa. Dra. " + diploma.getNomeReitor();
            cargoReitor = "reitora";
        }

        String diplomaText = String.format("O %s, %s da Universidade Fake, no uso de suas atribuições, confere a %s, de nacionalidade %s, natural de %s, portador do rg %s, o presente diploma de %s, do curso de %s, por ter concluído seus estudos nesta instituição de ensino no dia %s.",
                tituloReitor,
                cargoReitor,
                diploma.getDiplomado().getNome(),
                diploma.getDiplomado().getNacionalidade(),
                diploma.getDiplomado().getNaturalidade(),
                diploma.getDiplomado().getRg(),
                diploma.getCurso().getNome(),
                diploma.getCurso().getTipo(),
                diploma.getDataDeConclusao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return new DiplomaTextResponse(diplomaText);
    }
}
