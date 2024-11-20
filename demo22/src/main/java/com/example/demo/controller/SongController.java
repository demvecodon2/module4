package com.example.demo.controller;

import com.example.demo.model.Song;
import com.example.demo.service.ISongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/song/list")
public class SongController {

    @Autowired
    private ISongService songService;

    @GetMapping("/add")
    public String showAddSongForm(Model model) {
        model.addAttribute("song", new Song());
        return "addSong";
    }


    @PostMapping("/add")
    public String addSong(@Valid @ModelAttribute Song song, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addSong";
        }
        songService.saveSong(song);
        model.addAttribute("message", "Song has been added successfully.");
        return "redirect:/song/list";
    }


    @GetMapping("/update/{id}")
    public String showUpdateSongForm(@PathVariable("id") Long id, Model model) {
        Song song = songService.getSongById(id);
        if (song != null) {
            model.addAttribute("song", song);
            return "updateSong";
        } else {
            model.addAttribute("error", "Song not found");
            return "redirect:/song/list";
        }
    }




    @PostMapping("/update/{id}")
    public String updateSong(@PathVariable("id") Long id, @Valid @ModelAttribute Song song, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "updateSong";
        }
        song.setId(id);
        songService.updateSong(song);
        model.addAttribute("message", "Song has been updated successfully.");
        return "redirect:/song/list";
    }


    @GetMapping("/list")
    public String listSongs(Model model) {
        List<Song> songs = songService.getAllSongs();
        model.addAttribute("songs", songs);
        return "listSong";
    }


    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable("id") Long id, Model model) {
        songService.deleteSong(id);
        model.addAttribute("message", "Song has been deleted successfully.");
        return "redirect:/song/list";
    }


    @GetMapping("/search")
    public String searchSongs(@RequestParam("title") String title, Model model) {
        List<Song> songs = songService.findSongsByTitle(title);
        model.addAttribute("songs", songs);
        return "listSong";
    }
}
