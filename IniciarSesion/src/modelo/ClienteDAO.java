package modelo;

import beans.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class ClienteDAO extends SimpleJdbcDaoSupport{

	private static final String clientesEnElsistema = "SELECT * FROM cliente";

	class ProyectosRowMapper implements ParameterizedRowMapper<Cliente>  {

		public Cliente mapRow( ResultSet rs, int n) throws SQLException {
			Cliente usuario = new Cliente();
			usuario.setCorreo( rs.getString("correo_e"));
			usuario.setNombre( rs.getString("nombre"));
			usuario.setApaterno( rs.getString("a_paterno"));
			usuario.setAmaterno( rs.getString("a_materno"));
			usuario.setContrasenia( rs.getString("contrasenia"));
			usuario.setNumero( rs.getString("telefono"));
			return usuario;
		}
	}

	public ArrayList<Cliente> listaDeClientes() {
		SimpleJdbcTemplate sjdbc = getSimpleJdbcTemplate();
		return ((ArrayList<Cliente>) sjdbc.query(clientesEnElsistema, new ProyectosRowMapper()));	  	
	}
}