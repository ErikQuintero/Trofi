package modelo;

import beans.Administrador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class AdministradorDAO extends SimpleJdbcDaoSupport{

	private static final String administradoresEnElsistema = "SELECT * FROM administrador";

	class ProyectosRowMapper implements ParameterizedRowMapper<Administrador>  {

		public Administrador mapRow( ResultSet rs, int n) throws SQLException {
			Administrador usuario = new Administrador();
			usuario.setCorreo( rs.getString( "correo_e" ));
			usuario.setNombre( rs.getString( "nombre" ) );
			usuario.setApaterno( rs.getString( "a_paterno" ) );
			usuario.setAmaterno( rs.getString( "a_materno" ) );
			usuario.setContrasenia( rs.getString( "contrasenia" ) );
			return usuario;
		}
	}

	public ArrayList<Administrador> listaDeAdministradores() {
		SimpleJdbcTemplate sjdbc = getSimpleJdbcTemplate();
		return ((ArrayList<Administrador>) sjdbc.query(administradoresEnElsistema, new ProyectosRowMapper()));	  	
	}
}