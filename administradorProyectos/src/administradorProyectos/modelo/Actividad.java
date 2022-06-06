package administradorProyectos.modelo;

import java.io.Serializable;
import java.util.Date;

public class Actividad implements Serializable
{
	//Atributos 
	private String titulo;
	private Date fechaInicio;
	private Date fechaFinal;
	private String tipo;
	private String descripcion;
	private Participante encargado;
	private Cronometro cronometro;
	private Boolean terminado;
	private Boolean estado;
	
	// Constructor 
	public Actividad(String titulo, String tipo, String descripcion, Participante encargado)
	{
		this.titulo = titulo;
		fechaInicio = new Date();
		fechaFinal = null;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.encargado = encargado;
		this.cronometro = new Cronometro();
		terminado = false;
		estado = false;
	}
	
	//Getters
	
	public Date getFechaInicial()
	{
		return fechaInicio;
	}
	
	public Date getFechaFinal()
	{
		return fechaFinal;
	}
	
	public String getTitulo()
	{
		return titulo;
	}
	
	public String getTipo()
	{
		return tipo;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public Participante getEncargado()
	{
		return encargado;
	}
	
	public Boolean getTerminado()
	{
		return terminado;
	}
	
	public Cronometro getCronometro()
	{
		return cronometro;
	}
	
	public Boolean getEstado()
	{
		return estado;
	}
	
	//Setters
	
	public void setFechaInicial(Date actualizacion)
	{
		fechaInicio = actualizacion;
	}
	
	public void setFechaFinal(Date actualizacion)
	{
		fechaFinal = actualizacion;
	}
	
	public void setTitulo(String actualizacion)
	{
		titulo = actualizacion;
	}
	
	public void setTipo(String actualizacion)
	{
		tipo = actualizacion;
	}
	
	public void setDescripcion(String actualizacion)
	{
		descripcion = actualizacion;
	}
	
	
	//Metodos
	
	public void estadoCronometro(Integer estado, Date fecha)
	{
		switch(estado)
		{
		case 1 -> {cronometro.setPausa(fecha); this.estado = true;}
		case 2 -> {cronometro.continuarCronometro(fecha); this.estado = false;}
		}	
	}
	
	public void setTerminado(Date fechaFinal)
	{
		this.fechaFinal = fechaFinal;
		if (cronometro.getEstado() == false)
		{
			estadoCronometro(1, fechaFinal);
		}
		cronometro.calcularTiempoCronometrado(fechaInicio, fechaFinal);
		terminado = true;
	}
	
}
