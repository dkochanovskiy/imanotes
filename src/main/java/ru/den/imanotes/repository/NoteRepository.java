package ru.den.imanotes.repository;

import org.springframework.data.repository.CrudRepository;
import ru.den.imanotes.model.Note;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Integer> {
    List<Note> findByTag(String tag);
}
