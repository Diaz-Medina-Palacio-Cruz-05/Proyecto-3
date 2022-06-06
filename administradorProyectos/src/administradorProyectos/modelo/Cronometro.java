package administradorProyectos.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Cronometro implements Serializable
{
	//Atributos 
	private Date fechaPausa;
	private Long tiempoTotalPausado;
	private Long tiempoCronometrado;
	private String tiempoInvertido;
	private Integer cantPausados;
	private Boolean pausa;
	
	//Constructor
	public Cronometro() 
	{
		fechaPausa = null;
		tiempoInvertido = null;
		tiempoCronometrado = 0L;
		tiempoTotalPausado = 0L;
		pausa = false;
		cantPausados = 0;
	}
	
	//Setters
	
	public void setPausa(Date pauseDate)
	{
		if (pausa == false) 
		{
			fechaPausa = pauseDate;
			pausa = true;
		}
		else
		{
			System.out.println("El cronometro ya esta pausado.");
		}
		
	}
	
	//Getters
	
	public String getTiempoInvertido()
	{
		return tiempoInvertido;
	}
	
	public Long getTiempoCronometrado()
	{
		return tiempoCronometrado;
	}
	
	public Integer getCantPausado()
	{
		return cantPausados;
	}
	public Long getTiempoTotalPausado()
	{
		return tiempoTotalPausado;
	}
	
	public Boolean getEstado()
	{
		return pausa;
	}
	//Metodos
	
	public void calcularTiempoCronometrado(Date fechaInicial, Date finalDate)
	{
		long difference_In_Time
        = (finalDate.getTime() - fechaInicial.getTime()) - tiempoTotalPausado;
		
		long difference_In_Seconds
        	= TimeUnit.MILLISECONDS
              .toSeconds(difference_In_Time)
              % 60;

	    long difference_In_Minutes
	        = TimeUnit
	              .MILLISECONDS
	              .toMinutes(difference_In_Time)
	          % 60;
	
	    long difference_In_Hours
	        = TimeUnit
	              .MILLISECONDS
	              .toHours(difference_In_Time)
	          % 24;
	
	    long difference_In_Days
	        = TimeUnit
	              .MILLISECONDS
	              .toDays(difference_In_Time)
	          % 365;
	
	    long difference_In_Years
	        = TimeUnit
	              .MILLISECONDS
	              .toDays(difference_In_Time)
	          / 365l;
	    tiempoCronometrado = difference_In_Time;
	    tiempoInvertido = (
	        "El tiempo invertido en la actividad fue: " +
	        difference_In_Years
	        + " años, "
	        + difference_In_Days
	        + " dias, "
	        + difference_In_Hours
	        + " horas, "
	        + difference_In_Minutes
	        + " minutos, "
	        + difference_In_Seconds
	        + " segundos");
	}
	
	public void continuarCronometro(Date continuar)
	{
		if (pausa == true)
		{
			long diferencia = (continuar.getTime() - fechaPausa.getTime());
			tiempoTotalPausado += diferencia;
			cantPausados += 1;
			pausa = false;
		}
		else 
		{
			System.out.println("\nEl Cronometro no esta pausado.");
		}
		
	}
}
