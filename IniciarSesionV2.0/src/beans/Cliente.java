package beans;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Cliente {
	@NotNull private String nombre;
	@NotNull private String aPaterno;
	@NotNull private String aMaterno;
	@NotEmpty @Email private String correo_e;
	@NotNull private String contrasenia;
	@NotNull private String numero;
	
	public Cliente() {}
	
	public Cliente(String nombre, String apellido1,String apellido2,String numero, String email,String password) {
		this.nombre=nombre;
		this.aPaterno = apellido1;
		this.aMaterno = apellido2;
		this.correo_e = email;
		this.contrasenia = password;
		this.numero = numero; 
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApaterno() {
		return aPaterno;
	}
	
	public void setApaterno(String apellido1) {
		this.aPaterno = apellido1;
	}
	
	public String getAmaterno() {
		return aMaterno;
	}
	
	public void setAmaterno(String apellido2) {
		this.aMaterno = apellido2;
	}
	
	public String getCorreo() {
		return correo_e;
	}
	
	public void setCorreo(String email) {
		this.correo_e = email;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setContrasenia(String password) {
		this.contrasenia = password;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numeroN) {
		this.numero = numeroN;
	}
}