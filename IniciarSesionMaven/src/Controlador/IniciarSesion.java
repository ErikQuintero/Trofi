package Controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.AdministradorDAO;
import Modelo.Administrador;
import DAO.ClienteDAO;
import Modelo.Cliente;
import DAO.RepartidorDAO;
import Modelo.Repartidor;
import Modelo.IniciarSesionIH;



@WebServlet("/IniciarSesion")
public class IniciarSesion extends HttpServlet{
	private static final long serialVersionUID = 1L;
	AdministradorDAO administradorDAO;
	ClienteDAO clienteDAO;
	RepartidorDAO repartidorDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
			administradorDAO = new AdministradorDAO(jdbcURL, jdbcUsername, jdbcPassword);
			clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
			repartidorDAO = new RepartidorDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public IniciarSesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			switch (action){
			case "index":
				index(request, response);
				break;
			case "IniciarSesionAdministrador":
				iniciarSesionAdministrador(request, response);
				break;
			case "verificarAdministrador":
				verificarAdministrador(request, response);
				break;
			case "IniciarSesionCliente":
				iniciarSesionCliente(request, response);
				break;
			case "verificarCliente":
				verificarCliente(request, response);
				break;	
			case "IniciarSesionRepartidor":
				iniciarSesionRepartidor(request, response);
				break;
			case "verificarRepartidor":
				verificarRepartidor(request, response);
				break;		
			default:
				break;
			}			
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hola Servlet..");
		doGet(request, response);
	}
	
	private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void iniciarSesionAdministrador(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/IniciarSesion.jsp");
		dispatcher.forward(request, response);
	}
	
	private void iniciarSesionCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/IniciarSesionCliente.jsp");
		dispatcher.forward(request, response);
	}
	
	private void iniciarSesionRepartidor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		RequestDispatcher dispatcher= request.getRequestDispatcher("/jsp/IniciarSesionRepartidor.jsp");
		dispatcher.forward(request, response);
	}
	
	private void verificarAdministrador(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		if(verificarAdministradorAux(request)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/SesionIniciadaAdministrador.jsp");
			dispatcher.forward(request, response);
		}else {
			mostrarErrorAdministrador(request,response, errorAdministrador(request));
		}
	}
	
	private boolean verificarAdministradorAux(HttpServletRequest request) throws SQLException, ServletException, IOException{
		IniciarSesionIH iniciar = new IniciarSesionIH(request.getParameter("correo"), request.getParameter("contrasenia"));
		List<Administrador> lista = administradorDAO.listarAdministradores();
		for (Administrador cliente : lista){ 
			if((validarCorreo(cliente, iniciar)) && (validarContrasenia(cliente, iniciar))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean validarCorreo(Administrador admin, IniciarSesionIH login){
		if (admin.getCorreo().equals(login.getEmail())){
			return true;
		}else {
			return false;
		}  
	}
	
	public boolean validarContrasenia(Administrador admin, IniciarSesionIH login){
		if (admin.getContrasenia().equals(login.getPassword())){
			return true;
		}else {
			return false;
		}  
	}
	
	private void verificarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		if(verificarClienteAux(request)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/SesionIniciadaCliente.jsp");
			dispatcher.forward(request, response);
		}else {
			mostrarErrorCliente(request,response, errorCliente(request));
		}
	}

	private boolean verificarClienteAux(HttpServletRequest request) throws SQLException, ServletException, IOException{
		IniciarSesionIH iniciar = new IniciarSesionIH(request.getParameter("correo"), request.getParameter("contrasenia"));
		List<Cliente> lista = clienteDAO.listarClientes();
		for (Cliente cliente : lista){ 
			if((validarCorreo(cliente, iniciar)) && (validarContrasenia(cliente, iniciar))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean validarCorreo(Cliente cliente, IniciarSesionIH login){
		if (cliente.getCorreo().equals(login.getEmail())){
			return true;
		}else {
			return false;
		}  
	}
	
	public boolean validarContrasenia(Cliente cliente, IniciarSesionIH login){
		if (cliente.getContrasenia().equals(login.getPassword())){
			return true;
		}else {
			return false;
		}  
	}

	private void verificarRepartidor(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		if(verificarRepartidorAux(request)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/SesionIniciadaRepartidor.jsp");
			dispatcher.forward(request, response);
		}else {
			mostrarErrorRepartidor(request,response, errorRepartidor(request));
		}
	}
	
	private boolean verificarRepartidorAux(HttpServletRequest request) throws SQLException, ServletException, IOException{
		IniciarSesionIH iniciar = new IniciarSesionIH(request.getParameter("correo"), request.getParameter("contrasenia"));
		List<Repartidor> lista = repartidorDAO.listarRepartidores();
		for (Repartidor repartidor : lista){ 
			if((validarCorreoRe(repartidor, iniciar)) && (validarContraseniaRe(repartidor, iniciar))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean validarCorreoRe(Repartidor repartidor, IniciarSesionIH login){
		if (repartidor.getCorreo().equals(login.getEmail())){
			return true;
		}else {
			return false;
		}  
	}
	
	public boolean validarContraseniaRe(Repartidor repartidor, IniciarSesionIH login){
		if (repartidor.getContrasenia().equals(login.getPassword())){
			return true;
		}else {
			return false;
		}  
	}
	
	public String errorCliente(HttpServletRequest request) throws SQLException, ServletException, IOException{
		IniciarSesionIH iniciar = new IniciarSesionIH(request.getParameter("correo"), request.getParameter("contrasenia"));
		List<Cliente> lista = clienteDAO.listarClientes();
		for (Cliente cliente : lista){ 
			if(validarCorreo(cliente, iniciar)){
				return "contrasenia";
			}
		}
		if(correoValido(iniciar.getEmail())){
			return "cvalido";
		}else {
			return "correo";
		}
	}
	
	public String errorAdministrador(HttpServletRequest request) throws SQLException, ServletException, IOException{
		IniciarSesionIH iniciar = new IniciarSesionIH(request.getParameter("correo"), request.getParameter("contrasenia"));
		List<Administrador> lista = administradorDAO.listarAdministradores();
		for (Administrador admin : lista){ 
			if(validarCorreo(admin, iniciar)){
				return "contrasenia";
			}
		}
		if(correoValido(iniciar.getEmail())){
			return "cvalido";
		}else {
			return "correo";
		}
	}
	
	public String errorRepartidor(HttpServletRequest request) throws SQLException, ServletException, IOException{
		IniciarSesionIH iniciar = new IniciarSesionIH(request.getParameter("correo"), request.getParameter("contrasenia"));
		List<Repartidor> lista = repartidorDAO.listarRepartidores();
		for (Repartidor repartidor : lista){ 
			if(validarCorreoRe(repartidor, iniciar)){
				return "contrasenia";
			}
		}
		if(correoValido(iniciar.getEmail())){
			return "cvalido";
		}else {
			return "correo";
		}
	}
	
	public void mostrarErrorCliente(HttpServletRequest request, HttpServletResponse response, String s) throws SQLException, ServletException, IOException{
		if (s.equals("correo")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesionClienteEC.jsp");
			dispatcher.forward(request, response);
		}else if(s.equals("contrasenia")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesionClienteEP.jsp");
			dispatcher.forward(request, response);
		}else if(s.equals("cvalido")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesionClienteEV.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void mostrarErrorAdministrador(HttpServletRequest request, HttpServletResponse response, String s) throws SQLException, ServletException, IOException{
		if (s.equals("correo")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesionAdministradorEC.jsp");
			dispatcher.forward(request, response);
		}else if(s.equals("contrasenia")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesionAdministradorEP.jsp");
			dispatcher.forward(request, response);
		}else if(s.equals("cvalido")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesionAdministradorEV.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public void mostrarErrorRepartidor(HttpServletRequest request, HttpServletResponse response, String s) throws SQLException, ServletException, IOException{
		if (s.equals("correo")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesionRepartidorEC.jsp");
			dispatcher.forward(request, response);
		}else if(s.equals("contrasenia")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesionRepartidorEP.jsp");
			dispatcher.forward(request, response);
		}else if(s.equals("cvalido")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/IniciarSesionRepartidorEV.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	public boolean correoValido(String s) {
		int resultado = s.indexOf("@");
		if(resultado == -1) {
			return true;
	    }else {
	    	return false;
	    } 
	}
}