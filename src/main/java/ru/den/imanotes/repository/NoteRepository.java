package ru.den.imanotes.repository;

import org.springframework.data.repository.CrudRepository;
import ru.den.imanotes.model.Note;

public interface NoteRepository extends CrudRepository<Note, Integer> {
}
