package tr.com.provera.pameraapi.service;

import tr.com.provera.pameraapi.dto.NoteDto;

import java.util.List;

public interface NoteService {

    NoteDto createNote(NoteDto noteDto);

    NoteDto getNoteById(String id);

    List<NoteDto> getAllNotes();

    NoteDto updateNote(String id, NoteDto noteDto);

    void deleteNote(String id);
}
