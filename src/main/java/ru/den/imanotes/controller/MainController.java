package ru.den.imanotes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.den.imanotes.model.Note;
import ru.den.imanotes.repository.NoteRepository;

import java.util.Map;

@Controller
public class MainController {
    private final NoteRepository noteRepository;

    public MainController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/")
    public String main(Map<String, Object> model) {
        Iterable<Note> notes = noteRepository.findAll();

        model.put("notes", notes);

        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model){
        Note note = new Note(text, tag);

        noteRepository.save(note);

        Iterable<Note> notes = noteRepository.findAll();

        model.put("notes", notes);

        return "main";
    }
}
