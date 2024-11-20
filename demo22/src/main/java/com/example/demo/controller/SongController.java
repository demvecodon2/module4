package com.example.demo.controller;

import com.example.demo.model.Song;
import com.example.demo.service.ISongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/song")
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




    @GetMapping("/list")
    public String listSongs(Model model) {
        List<Song> songs = songService.getAllSongs();
        model.addAttribute("songs", songs);
        return "listSong";
    }
    @GetMapping("/update/{id}")
    public String showUpdateSongForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Song song = songService.getSongById(id);
            if (song != null) {
                model.addAttribute("song", song);
                return "updateSong";
            } else {
                redirectAttributes.addFlashAttribute("error", "Song not found");
                return "redirect:/song/list";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error occurred: " + e.getMessage());
            return "redirect:/song/list";
        }
    }


    @PostMapping("/update/{id}")
    public String updateSong(@PathVariable("id") Long id, @Valid @ModelAttribute Song song, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "updateSong";  // Trả lại form nếu có lỗi
        }

        if (song == null || song.getId() == null) {
            redirectAttributes.addFlashAttribute("error", "Invalid song data.");
            return "redirect:/song/list";
        }

        try {
            song.setId(id);  // Cập nhật ID cho song
            songService.updateSong(song);  // Cập nhật bài hát
            redirectAttributes.addFlashAttribute("message", "Song has been updated successfully.");
            return "redirect:/song/list";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating song: " + e.getMessage());
            return "redirect:/song/list";
        }
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
