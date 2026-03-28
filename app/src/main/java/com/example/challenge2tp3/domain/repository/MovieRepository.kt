package com.example.challenge2tp3.domain.repository

import com.example.challenge2tp3.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<List<Movie>>
    suspend fun getMovieById(id: Int): Movie?
}
