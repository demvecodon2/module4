package com.example.demo.service;

import com.example.demo.model.Song;
import com.example.demo.repository.ISongReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class SongServiceImpl implements ISongService {
    @Autowired
    private  ISongReposiroty songReposiroty;



    @Override
    public void saveSong(Song song) {
        songReposiroty.save(song);
    }

    @Override
    public void updateSong(Song song) {
        songReposiroty.save(song);
    }

    @Override
    public Song getSongById(Long id) {
        Optional<Song> song = songReposiroty.findById(id);
        return song.orElse(null);
    }

    @Override
    public List<Song> getAllSongs() {
        return songReposiroty.findAll();
    }

    @Override
    public void deleteSong(Long id) {
        songReposiroty.deleteById(id);
    }

    @Override
    public List<Song> findSongsByTitle(String title) {
                return songReposiroty.findByTitleContainingIgnoreCase(title);
    }

}
