package administradorProyectos.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Participante implements Serializable 
{
	//Atributos
	private String nombre;
	private String correo;
	private Boolean dueño;
	private Reporte miReporte;
	private ArrayList<Actividad> misActividades;
	
	//Constructor
	public Participante(String elNombre, String email, Boolean lider) 
	{
		nombre = elNombre;
		correo = email;
		dueño = lider;
		misActividades = new ArrayList<Actividad>();
		miReporte = new Reporte(nombre, correo);
	}
	
	//Getters
	
	public String getNombre()
	{
		return nombre; 
	}
	
	public String getCorreo()
	{
		return correo;
		
	}
	
	public Boolean isDueño()
	{
		return dueño;
	}
	
	public Reporte getReporte()
	{
		return miReporte;
	}
	
	public ArrayList<Actividad> getActividades()
	{
		return misActividades;
	}
}
