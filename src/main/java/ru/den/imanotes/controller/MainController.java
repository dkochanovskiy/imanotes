package ru.den.imanotes.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.den.imanotes.model.Note;
import ru.den.imanotes.model.User;
import ru.den.imanotes.repository.NoteRepository;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Controller
public class MainController {
    private final NoteRepository noteRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public MainController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Note> notes = noteRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            notes = noteRepository.findByTag(filter);
        } else {
            notes = noteRepository.findAll();
        }

        model.addAttribute("notes", notes);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Note note = new Note(text, tag, user);

        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            note.setFilename(resultFilename);
        }

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
