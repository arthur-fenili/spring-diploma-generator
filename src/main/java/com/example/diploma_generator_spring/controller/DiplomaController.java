package com.example.diploma_generator_spring.controller;

import com.example.diploma_generator_spring.dto.DiplomaRequest;
import com.example.diploma_generator_spring.dto.DiplomaResponse;
import com.example.diploma_generator_spring.model.Diploma;
import com.example.diploma_generator_spring.repository.DiplomaRepository;
import com.example.diploma_generator_spring.service.DiplomaMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/diplomas")
public class DiplomaController {

    @Autowired
    private DiplomaMapper diplomaMapper;
    @Autowired
    private DiplomaRepository diplomaRepository;

    @Operation(summary = "Cria um diploma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Diploma criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos")})
    @PostMapping
    public ResponseEntity<DiplomaResponse> createDiploma(
            @Valid @RequestBody DiplomaRequest diplomaRequest) {
        Diploma diplomaConvertido = diplomaMapper.requestToDiploma(diplomaRequest);
        Diploma diplomaCriado = diplomaRepository.save(diplomaConvertido);

        DiplomaResponse diplomaResponse = diplomaMapper.diplomaToResponse(diplomaCriado);
        return new ResponseEntity<>(diplomaResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca todos os diplomas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diplomas encontrados"),
            @ApiResponse(responseCode = "404", description = "Diplomas não encontrados")})
    @GetMapping
    public ResponseEntity<List<DiplomaResponse>> getAllDiplomas() {
        List<Diploma> diplomas = diplomaRepository.findAll();
        List<DiplomaResponse> diplomaResponses = new ArrayList<>();
        for (Diploma diploma : diplomas) {
            diplomaResponses.add(diplomaMapper.diplomaToResponse(diploma));
        }
        return ResponseEntity.ok(diplomaResponses);
    }

    @Operation(summary = "Busca um diploma por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diploma encontrado"),
            @ApiResponse(responseCode = "404", description = "Diploma não encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity<DiplomaResponse> getDiplomaById(@PathVariable Long id) {
        return diplomaRepository.findById(id)
                .map(diploma -> ResponseEntity.ok(diplomaMapper.diplomaToResponse(diploma)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza um diploma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diploma atualizado"),
            @ApiResponse(responseCode = "404", description = "Diploma não encontrado")})
    @PutMapping("/{id}")
    public ResponseEntity<DiplomaResponse> updateDiploma(
            @PathVariable Long id, @Valid @RequestBody DiplomaRequest diplomaRequest) {
        return diplomaRepository.findById(id)
                .map(diploma -> {
                    Diploma diplomaAtualizado = diplomaMapper.requestToDiploma(diplomaRequest);
                    diplomaAtualizado.setId(diploma.getId());
                    Diploma diplomaSalvo = diplomaRepository.save(diplomaAtualizado);
                    return ResponseEntity.ok(diplomaMapper.diplomaToResponse(diplomaSalvo));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta um diploma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Diploma deletado"),
            @ApiResponse(responseCode = "404", description = "Diploma não encontrado")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiploma(@PathVariable Long id) {
        return diplomaRepository.findById(id)
                .map(diploma -> {
                    diplomaRepository.delete(diploma);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Busca um diploma por id, método disponível para todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diploma encontrado"),
            @ApiResponse(responseCode = "404", description = "Diploma não encontrado")})
    @GetMapping("/all/{id}")
    public ResponseEntity<DiplomaResponse> getDiplomaByIdAll(@PathVariable Long id) {
        return diplomaRepository.findById(id)
                .map(diploma -> ResponseEntity.ok(diplomaMapper.diplomaToResponse(diploma)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
