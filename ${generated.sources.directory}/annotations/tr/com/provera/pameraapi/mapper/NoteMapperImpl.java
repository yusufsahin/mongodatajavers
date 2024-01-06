package tr.com.provera.pameraapi.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tr.com.provera.pameraapi.dto.NoteDto;
import tr.com.provera.pameraapi.model.Note;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-06T03:31:12+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (GraalVM Community)"
)
@Component
public class NoteMapperImpl implements NoteMapper {

    @Override
    public NoteDto toDto(Note entity) {
        if ( entity == null ) {
            return null;
        }

        NoteDto noteDto = new NoteDto();

        noteDto.setId( entity.getId() );
        noteDto.setName( entity.getName() );
        noteDto.setDescription( entity.getDescription() );

        return noteDto;
    }

    @Override
    public Note toEntity(NoteDto dto) {
        if ( dto == null ) {
            return null;
        }

        Note note = new Note();

        note.setId( dto.getId() );
        note.setName( dto.getName() );
        note.setDescription( dto.getDescription() );

        return note;
    }
}
