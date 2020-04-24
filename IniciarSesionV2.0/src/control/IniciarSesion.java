package control;

import java.util.ArrayList;
import beans.IniciarSesionIH;
import beans.Cliente;
import modelo.ClienteDAO;
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
}