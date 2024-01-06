package tr.com.provera.pameraapi.service.impl;

import org.springframework.stereotype.Service;
import tr.com.provera.pameraapi.dto.NoteDto;
import tr.com.provera.pameraapi.exception.EntityNotFoundException;
import tr.com.provera.pameraapi.mapper.NoteMapper;
import tr.com.provera.pameraapi.model.Note;
import tr.com.provera.pameraapi.repository.NoteRepository;
import tr.com.provera.pameraapi.service.NoteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public NoteServiceImpl(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    @Override
    public NoteDto createNote(NoteDto noteDto) {
        Note note = noteMapper.toEntity(noteDto);
        Note savedNote = noteRepository.save(note);
        return noteMapper.toDto(savedNote);
    }

    @Override
    public NoteDto getNoteById(String id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id: " + id));
        return noteMapper.toDto(note);
    }

    @Override
    public List<NoteDto> getAllNotes() {
        return noteRepository.findAll()
                .stream()
                .map(noteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NoteDto updateNote(String id, NoteDto noteDto) {
        Note existingNote = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id: " + id));

        Note updatedNote = noteMapper.toEntity(noteDto);
        updatedNote.setId(existingNote.getId()); // Ensure the correct ID is maintained
        Note savedNote = noteRepository.save(updatedNote);

        return noteMapper.toDto(savedNote);
    }

    @Override
    public void deleteNote(String id) {
        Optional<Note> existingNote = noteRepository.findById(id);
        if (existingNote.isPresent()) {
            noteRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Note not found with id: " + id);
        }
    }
}
