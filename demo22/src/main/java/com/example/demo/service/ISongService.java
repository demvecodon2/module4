package com.example.demo.service;

import com.example.demo.model.Song;

import java.util.List;
import java.util.Optional;

public interface ISongService {

    void saveSong(Song song);

    void updateSong(Song song);

    Song getSongById(Long id);

    List<Song> getAllSongs();

    void deleteSong(Long id);

    List<Song> findSongsByTitle(String title);

}
