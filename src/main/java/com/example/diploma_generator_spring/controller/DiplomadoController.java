package com.example.diploma_generator_spring.controller;

import com.example.diploma_generator_spring.dto.DiplomadoRequest;
import com.example.diploma_generator_spring.dto.DiplomadoResponse;
import com.example.diploma_generator_spring.model.Diplomado;
import com.example.diploma_generator_spring.repository.DiplomadoRepository;
import com.example.diploma_generator_spring.service.DiplomadoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diplomados")
public class DiplomadoController {

    @Autowired
    private DiplomadoMapper diplomadoMapper;

    @Autowired
    private DiplomadoRepository diplomadoRepository;

    @Operation(summary = "Cria um diplomado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Diplomado criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos", content = @Content(schema = @Schema()))})
    @PostMapping
    public ResponseEntity<DiplomadoResponse> createDiplomado(
            @Valid @RequestBody DiplomadoRequest diplomadoRequest) {
        Diplomado diplomadoConvertido = diplomadoMapper.requestToDiplomado(diplomadoRequest);
        Diplomado diplomadoCriado = diplomadoRepository.save(diplomadoConvertido);

        DiplomadoResponse diplomadoResponse = diplomadoMapper.diplomadoToResponse(diplomadoCriado);
        return new ResponseEntity<>(diplomadoResponse, HttpStatus.CREATED);
    }

    @Operation
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diplomado encontrado", content = @Content(schema = @Schema(implementation = DiplomadoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Diplomado não encontrado", content = @Content(schema = @Schema(implementation = DiplomadoResponse.class)))})
    @GetMapping
    public ResponseEntity<List<DiplomadoResponse>> getAllDiplomados() {
        List<Diplomado> diplomados = diplomadoRepository.findAll();
        List<DiplomadoResponse> diplomadoResponses = new ArrayList<>();
        for (Diplomado diplomado : diplomados) {
            diplomadoResponses.add(diplomadoMapper.diplomadoToResponse(diplomado));
        }
        return ResponseEntity.ok(diplomadoResponses);
    }

    @Operation
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diplomado encontrado", content = @Content(schema = @Schema(implementation = DiplomadoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Diplomado não encontrado", content = @Content(schema = @Schema(implementation = DiplomadoResponse.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<DiplomadoResponse> getDiplomadoById(@PathVariable Long id) {
        Diplomado diplomado = diplomadoRepository.findById(id).orElse(null);
        if (diplomado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(diplomadoMapper.diplomadoToResponse(diplomado));
    }

    @Operation
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diplomado atualizado", content = @Content(schema = @Schema(implementation = DiplomadoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Diplomado não encontrado", content = @Content(schema = @Schema(implementation = DiplomadoResponse.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<DiplomadoResponse> updateDiplomado(@PathVariable Long id, @Valid @RequestBody DiplomadoRequest diplomadoRequest){
        Optional<Diplomado> diplomadoSalvo = diplomadoRepository.findById(id);
        if (diplomadoSalvo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Diplomado diplomadoConvertido = diplomadoMapper.requestToDiplomado(diplomadoRequest);
        diplomadoConvertido.setId(id);
        Diplomado diplomadoAtualizado = diplomadoRepository.save(diplomadoConvertido);
        DiplomadoResponse diplomadoResponse = diplomadoMapper.diplomadoToResponse(diplomadoAtualizado);
        return new ResponseEntity<>(diplomadoResponse, HttpStatus.OK);
    }

}
