package builder;

import com.biblioteca.biblioteca.entity.Libro;

public class BuilderLibro {
	

	private static final String CODIGO= "AAS222";
	private static final String NOMBRE_PALINDROMO = "lateleletal";
	private static final String NOMBRE = "CUALQUIERA";
	
	
	private String codigo;
	private String nombre;
	private double precio;
	
	public BuilderLibro() {
		this.codigo = CODIGO;
		this.nombre = NOMBRE;
		
	}
	

	public BuilderLibro conCodigo(String codigo) {
		this.codigo=codigo;
		return this;
	}

	public BuilderLibro conNombre(String nombre) {
		this.nombre=nombre;
		return this;
	}

	
	
	public Libro build() {
		return new Libro(this.codigo, this.nombre);
	}


}
