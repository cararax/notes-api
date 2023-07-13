package com.carara.notes.note;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api/notes", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoteResource {

    private final NoteService noteService;

    public NoteResource(final NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAllNotes() {
        log.info("Calling getAllNotes()");
        ResponseEntity<List<NoteDTO>> response = ResponseEntity.ok(noteService.findAll());
        log.info("getAllNotes response: {}", response);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getNote(@PathVariable(name = "id") final Long id) {
        log.info("Calling getNote() for id {}", id);
        ResponseEntity<NoteDTO> response = ResponseEntity.ok(noteService.get(id));
        log.info("getNote response: {}", response);
        return response;
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createNote(@RequestBody @Valid final NoteDTO noteDTO) {
        log.info("Calling createNote() with dto {}", noteDTO.toString());
        final Long createdId = noteService.create(noteDTO);
        log.info("createNote response: {}", createdId);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateNote(@PathVariable(name = "id") final Long id,
                                           @RequestBody @Valid final NoteDTO noteDTO) {
        log.info("Calling updateNote() with id {} and dto {}", id, noteDTO);
        noteService.update(id, noteDTO);
        log.info("Sucessfully updated note with id {}", id);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteNote(@PathVariable(name = "id") final Long id) {
        log.info("Calling deleteNote()");
        noteService.delete(id);
        log.info("Sucefully deleted note with id {}", id);
        return ResponseEntity.noContent().build();
    }

}
