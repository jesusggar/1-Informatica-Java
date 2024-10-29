package peliculas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieTest {
    Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie("pelicula");
    }

    @AfterEach
    void tearDown() {
        movie = null;
    }

    @Test
    void shouldThrowExceptionWhenSettingTrailerDurationLongerThan3Minutes() {
        assertThrows(RuntimeException.class, ()-> movie.setDuracionTrailer(4));
    }
}
