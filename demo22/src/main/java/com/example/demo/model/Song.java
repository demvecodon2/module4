package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên bài hát không được để trống.")
    @Size(max = 800, message = "Tên bài hát không được vượt quá 800 ký tự.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Tên bài hát không được chứa ký tự đặc biệt.")
    @Column(length = 800, nullable = false)
    private String title;

    @NotBlank(message = "Nghệ sĩ thể hiện không được để trống.")
    @Size(max = 300, message = "Tên nghệ sĩ không được vượt quá 300 ký tự.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Tên nghệ sĩ không được chứa ký tự đặc biệt.")
    @Column(length = 300, nullable = false)
    private String artist;

    @NotBlank(message = "Thể loại nhạc không được để trống.")
    @Size(max = 1000, message = "Thể loại nhạc không được vượt quá 1000 ký tự.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s,]*$", message = "Thể loại nhạc không được chứa ký tự đặc biệt ngoại trừ dấu phẩy.")
    @Column(length = 1000, nullable = false)
    private String genre;

}
