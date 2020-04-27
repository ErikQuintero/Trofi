package modelo;

import beans.Repartidor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class RepartidorDAO extends SimpleJdbcDaoSupport{

	private static final String repartidorEnElsistema = "SELECT * FROM repartidor";

	class ProyectosRowMapper implements ParameterizedRowMapper<Repartidor>  {

		public Repartidor mapRow( ResultSet rs, int n) throws SQLException {
			Repartidor usuario = new Repartidor();
			usuario.setCorreo( rs.getString( "correo_e" ));
			usuario.setNombre( rs.getString( "nombre" ) );
			usuario.setApaterno( rs.getString( "a_paterno" ) );
			usuario.setAmaterno( rs.getString( "a_materno" ) );
			usuario.setContrasenia( rs.getString( "contrasenia" ) );
			return usuario;
		}
	}

	public ArrayList<Repartidor> listaDeRepartidores() {
		SimpleJdbcTemplate sjdbc = getSimpleJdbcTemplate();
		return ((ArrayList<Repartidor>) sjdbc.query(repartidorEnElsistema, new ProyectosRowMapper()));	  	
	}
}