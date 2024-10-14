package com.example.diploma_generator_spring.controller;

import com.example.diploma_generator_spring.dto.CursoRequest;
import com.example.diploma_generator_spring.dto.CursoResponse;
import com.example.diploma_generator_spring.model.Curso;
import com.example.diploma_generator_spring.repository.CursoRepository;
import com.example.diploma_generator_spring.service.CursoMapper;
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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoMapper cursoMapper;
    @Autowired
    private CursoRepository cursoRepository;

    @Operation(summary = "Cria um curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Atributos inválidos", content = @Content(schema = @Schema()))})
    @PostMapping
    public ResponseEntity<CursoResponse> createCurso(
            @Valid @RequestBody CursoRequest cursoRequest) {
        Curso cursoConvertido = cursoMapper.requestToCurso(cursoRequest);
        Curso cursoCriado = cursoRepository.save(cursoConvertido);

        CursoResponse cursoResponse = cursoMapper.cursoToResponse(cursoCriado);
        return new ResponseEntity<>(cursoResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Busca todos os cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cursos encontrados"),
            @ApiResponse(responseCode = "404", description = "Cursos não encontrados", content = @Content(schema = @Schema()))})
    @GetMapping
    public ResponseEntity<Iterable<CursoResponse>> getAllCursos() {
        Iterable<Curso> cursos = cursoRepository.findAll();
        List<CursoResponse> cursoResponses = StreamSupport.stream(cursos.spliterator(), false)
                .map(cursoMapper::cursoToResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(cursoResponses, HttpStatus.OK);
    }

    @Operation(summary = "Busca um curso por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado", content = @Content(schema = @Schema()))})
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> getCursoById(@PathVariable Long id) {
        Curso curso = cursoRepository.findById(id).orElse(null);
        if (curso == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CursoResponse cursoResponse = cursoMapper.cursoToResponse(curso);
        return new ResponseEntity<>(cursoResponse, HttpStatus.OK);
    }

    @Operation(summary = "Deleta um curso por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado", content = @Content(schema = @Schema()))})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCursoById(@PathVariable Long id) {
        if (!cursoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cursoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}