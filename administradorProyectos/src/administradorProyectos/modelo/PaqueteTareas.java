package administradorProyectos.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class PaqueteTareas implements Serializable
{
	// Atributos
	private String nombre;
	private String descripcion;
	private ArrayList<Actividad> tareas;
	private ArrayList<PaqueteTareas> paquetes;
	
	//Constructor
	public PaqueteTareas(String nombre, String descripcion)
	{
		this.nombre = nombre;
		this.descripcion = descripcion;
		tareas = new ArrayList<Actividad>();
		paquetes = new ArrayList<PaqueteTareas>();
	}
	
	//getters
	
	public String getNombre()
	{
		return nombre;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public ArrayList<Actividad> getTareas()
	{
		return tareas;
	}
	
	public ArrayList<PaqueteTareas> getPaquetes()
	{
		return paquetes;
	}
	
	//Metodos
	
	public void agregarActividad(Actividad nueva)
	{
		tareas.add(nueva);
	}
	
	public void agregarPaquete(PaqueteTareas nuevo)
	{
		paquetes.add(nuevo);
	}
	
}

