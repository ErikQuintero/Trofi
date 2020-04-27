package control;

import java.util.ArrayList;
import beans.IniciarSesionIH;
import beans.Cliente;
import modelo.ClienteDAO;
import beans.Administrador;
import modelo.AdministradorDAO;
import beans.Repartidor;
import modelo.RepartidorDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IniciarSesion {
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView mostrarVista(){
		Cliente cliente = new Cliente();
		return new ModelAndView( "IniciarSesion" , "usuario", cliente);
	}
	
	@RequestMapping(value = "/Cliente", method = RequestMethod.GET)
	public ModelAndView mostrarVistaCliente(){
		Cliente cliente = new Cliente();
		return new ModelAndView( "IniciarSesion" , "usuario", cliente);
	}
	
	@RequestMapping(value = "/Admin", method = RequestMethod.POST)
	public ModelAndView mostrarVistaAdmin(){
		Cliente cliente = new Cliente();
		return new ModelAndView( "IniciarSesionAdministrador" , "usuario", cliente);
	}
	
	@RequestMapping(value = "/Repartidor", method = RequestMethod.POST)
	public ModelAndView mostrarVistaRepartidor(){
		Cliente cliente = new Cliente();
		return new ModelAndView( "IniciarSesionRepartidor" , "usuario", cliente);
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
	
	public boolean validarCorreoAdmin(Administrador admin, IniciarSesionIH login){
		if (admin.getCorreo().equals(login.getEmail())){
			return true;
		}else {
			return false;
		}  
	}
	
	public boolean validarContraseniaAdmin(Administrador admin, IniciarSesionIH login){
		if (admin.getContrasenia().equals(login.getPassword())){
			return true;
		}else {
			return false;
		}  
	}
	
	public boolean validarCorreoRe(Repartidor re, IniciarSesionIH login){
		if (re.getCorreo().equals(login.getEmail())){
			return true;
		}else {
			return false;
		}  
	}
	
	public boolean validarContraseniaRe(Repartidor re, IniciarSesionIH login){
		if (re.getContrasenia().equals(login.getPassword())){
			return true;
		}else {
			return false;
		}  
	}
	
	@RequestMapping(value = "/SesionIniciada", method = RequestMethod.POST)
	public ModelAndView iniciar(@ModelAttribute("login") IniciarSesionIH login){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");	            
		ClienteDAO dao = (ClienteDAO)context.getBean("clienteDAO");
		ArrayList<Cliente> lista = dao.listaDeClientes();
		for (Cliente cliente : lista){ 
			if((validarCorreo(cliente, login)) && (validarContrasenia(cliente, login))) {
				return new ModelAndView("SesionIniciada" , "usuario", cliente);
			}
		}
		return new ModelAndView("IniciarSesion" , "login", login);		
	}
	
	@RequestMapping(value = "/SesionIniciadaAdmin", method = RequestMethod.POST)
	public ModelAndView iniciarAdmin(@ModelAttribute("login") IniciarSesionIH login){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");	            
		AdministradorDAO dao = (AdministradorDAO)context.getBean("administradorDAO");
		ArrayList<Administrador> lista = dao.listaDeAdministradores();
		for (Administrador administrador : lista){ 
			if((validarCorreoAdmin(administrador, login)) && (validarContraseniaAdmin(administrador, login))) {
				return new ModelAndView("SesionIniciada" , "usuario", administrador);
			}
		}
		return new ModelAndView("IniciarSesionAdministrador" , "login", login);		
	}
	
	@RequestMapping(value = "/SesionIniciadaRe", method = RequestMethod.POST)
	public ModelAndView iniciarRe(@ModelAttribute("login") IniciarSesionIH login){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");	            
		RepartidorDAO dao = (RepartidorDAO)context.getBean("repartidorDAO");
		ArrayList<Repartidor> lista = dao.listaDeRepartidores();
		for (Repartidor repartidor : lista){ 
			if((validarCorreoRe(repartidor, login)) && (validarContraseniaRe(repartidor, login))) {
				return new ModelAndView("SesionIniciadaRepartidor" , "usuario", repartidor);
			}
		}
		return new ModelAndView("IniciarSesionRepartidor" , "login", login);		
	}
}