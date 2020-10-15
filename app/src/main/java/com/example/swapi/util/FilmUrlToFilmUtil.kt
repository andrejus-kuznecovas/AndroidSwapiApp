package com.example.swapi.util

import com.example.swapi.model.Film

/**
 * 1. Add repo and call api from repo only
 * 2. Add dagger
 * 3. Clean the arch
 * 4. Intro page with animation
 * 5. UI cleanup, use Material D
 * 6. Testing testing
 * 7. gradle cleanup and version updates
 *
 * BUGS:
 * 1. app crash on rotation
 * 2. search filtering
 * */
class FilmUrlToFilmUtil {
    companion object {
        fun filmUrlListToFilmList(filmUrls: List<String>, allMovies: List<Film>): List<Film> {
            val filteredMovies = allMovies.filter { movie -> filmUrls.contains(movie.filmUrl) }
            return filteredMovies
        }
    }
}