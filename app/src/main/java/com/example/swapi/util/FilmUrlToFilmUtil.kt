package com.example.swapi.util

import com.example.swapi.model.Film

class FilmUrlToFilmUtil {
    companion object {
        fun filmUrlListToFilmList(filmUrls: List<String>, allMovies: List<Film>): List<Film> {
            val filteredMovies = allMovies.filter { movie -> filmUrls.contains(movie.filmUrl) }
            return filteredMovies
        }
    }
}