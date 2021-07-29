package ru.den.imanotes.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.den.imanotes.model.Note;
import ru.den.imanotes.model.User;
import ru.den.imanotes.repository.NoteRepository;

import java.util.Map;

@Controller
public class MainController {
    private final NoteRepository noteRepository;

    public MainController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Note> notes = noteRepository.findAll();

        model.put("notes", notes);

        return "main";
    }

    @PostMapping("main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model
    ){
        Note note = new Note(text, tag, user);

        noteRepository.save(note);

        Iterable<Note> notes = noteRepository.findAll();

        model.put("notes", notes);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Note> notes;

        if(filter != null && !filter.isEmpty()){
            notes = noteRepository.findByTag(filter);
        } else {
            notes = noteRepository.findAll();
        }

        model.put("notes", notes);

        return "main";
    }
}
