package peliculas;

import java.util.List;

public class Movie {

	private String titulo;
	private int duracion;
	private int duracionTrailer;
	private List<String> generos;
	private List<Performer> performers;

	public Movie(String titulo) {
		this.titulo = titulo;
	}

	public void getTitulo() {
		// TODO - implement Movie.getTitulo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param titulo
	 */
	public void setTitulo(int titulo) {
		// TODO - implement Movie.setTitulo
		throw new UnsupportedOperationException();
	}

	public void getDuracion() {
		// TODO - implement Movie.getDuracion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param duracion
	 */
	public void setDuracion(int duracion) {
		// TODO - implement Movie.setDuracion
		throw new UnsupportedOperationException();
	}

	public void getDuracionTrailer() {
		// TODO - implement Movie.getDuracionTrailer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param duracionTrailer
	 */
	public void setDuracionTrailer(int duracionTrailer) {
		if (duracionTrailer > 3) {
			throw new RuntimeException();
		}
		this.duracionTrailer = duracionTrailer;
	}

	public void getGenero() {
		// TODO - implement Movie.getGenero
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param genero
	 */
	public void setGenero(int genero) {
		// TODO - implement Movie.setGenero
		throw new UnsupportedOperationException();
	}

	public void getActores() {
		// TODO - implement Movie.getActores
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param actores
	 */
	public void setActores(int actores) {
		// TODO - implement Movie.setActores
		throw new UnsupportedOperationException();
	}

}