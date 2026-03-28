package com.example.challenge2tp3.data.repository

import com.example.challenge2tp3.domain.model.Movie
import com.example.challenge2tp3.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl : MovieRepository {
    private val mockMovies = listOf(
        Movie(1, "Inception", "A thief who steals corporate secrets...", "https://image.tmdb.org/t/p/w500/9gk7Y98S06R1o9I0S3XUv7qYl1S.jpg", 8.8, "2010-07-16"),
        Movie(2, "Interstellar", "A team of explorers travel through a wormhole...", "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlSaba7.jpg", 8.6, "2014-11-07"),
        Movie(3, "The Dark Knight", "When the menace known as the Joker wreaks havoc...", "https://image.tmdb.org/t/p/w500/qJ2tW6XJKZgiuT9Kig0F6ZpYbsS.jpg", 9.0, "2008-07-18")
    )

    override fun getPopularMovies(): Flow<List<Movie>> = flow {
        emit(mockMovies)
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return mockMovies.find { it.id == id }
    }
}
