package administradorProyectos.modelo;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class Proyecto implements Serializable
{
	//Atributos
	private String titulo;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFinal;
	private Participante dueno;
	private ArrayList<String> tipos;
	private ArrayList<Participante> participantes;
	private ArrayList<Actividad> actividades;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	private ArrayList<PaqueteTareas> paquetes;
	
	
	//Constructor
	public Proyecto() 
	{
		titulo = null;
		descripcion = null; 
		fechaInicio = new Date();
		fechaFinal = new Date();
		dueno = null;
		tipos = new ArrayList<String>();
		participantes = new ArrayList<Participante>();
		actividades = new ArrayList<Actividad>();
		paquetes = new ArrayList<PaqueteTareas>();
	}
	
	//La carga, el guardar y el actulizar del proyecto se hace de ultimo.
	
	//Setters
	
	public void setTitulo(String tittle)
	{
		titulo = tittle;
	}
	
	public void setDescripcion (String description)
	{
		descripcion = description;
	}
	
	public void setFechaFinal(Date aproximada)
	{
		fechaFinal = aproximada;
	}
	
	public void setDueño(Participante director)
	{
		dueno = director;
	}
	
	
	//Getters 
	
	public String getTitulo()
	{
		return titulo;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public Date getFechaInicial()
	{
		return fechaInicio; 
	}
	
	public Date getFechaFinal()
	{
		return fechaFinal;
	}
	
	public ArrayList<Participante> getParticipantes()
	{
		return participantes;
	}
	
	public ArrayList<String> getTipos()
	{
		return tipos;
	}
	
	public ArrayList<Actividad> getActividades()
	{
		return actividades;
	}
	
	public ArrayList<PaqueteTareas> getPaquetes() 
	{
		return paquetes;
	}
	
	//Metodos
	
	public void añadirParticipante(String nombre, String correo) 
	{
		Participante nuevoPart = new Participante(nombre, correo, false);
		participantes.add(nuevoPart);
	}
	public Participante buscarParticipante(String nombreParticipante)
	{
		Participante elBuscado = null;
		for (int i = 0; i < participantes.size() && elBuscado == null; i++)
		{
			if (participantes.get(i).getNombre().equals(nombreParticipante))
				elBuscado = participantes.get(i);
		}
		return elBuscado;
	}
	
	public void crearActividad(String titulo, String tipo, String descripcion, String nombreEncargado, Integer paquete) 
	{
		Participante encargado = buscarParticipante(nombreEncargado);
		if (encargado == null)
		{
			System.out.println("\nEl Participante que ingreso no se encuentra en el proyecto.");
			int opcion_seleccionada = Integer.parseInt(input("\nQuiere integrarlo al proyecto? 1. Si 2. No"));
			switch (opcion_seleccionada)
			{
			case 1 -> {String correo = input("Ingrese el correo del Participante: "); encargado = new Participante(nombreEncargado, correo, false); 
						participantes.add(encargado); Actividad nuevaActividad = new Actividad(titulo, tipo, descripcion, encargado);
						actividades.add(nuevaActividad); encargado.getActividades().add(nuevaActividad);}
			case 2 -> System.out.println("Por favor vuelva a intentar crear una Actividad con los datos correctos.");
			default -> System.out.println("Escoja una opciÃ³n valida");
			}
		}
		else 
		{
			Actividad nuevaActividad = new Actividad(titulo, tipo, descripcion, encargado);
			actividades.add(nuevaActividad);
			try
			{
				paquetes.get(paquete).agregarActividad(nuevaActividad);
				encargado.getActividades().add(nuevaActividad);
			}
			catch (Exception ex)
			{
				System.out.println("Esta actividad no tiene paquete");
				encargado.getActividades().add(nuevaActividad);
			}
		}
	}
	
	public ArrayList<Actividad> buscarActividad2(String type, String parametro)
	{
		Actividad laDigna = null;
		ArrayList<Actividad> encontradas = new ArrayList<Actividad>();
		if (type.equals("tipo"))
		{
			for (int i = 0; i < actividades.size() && laDigna == null; i++)
			{
				if (actividades.get(i).getTipo().equals(parametro))
					encontradas.add(actividades.get(i));
			}
		}
		else if (type.equals("participante"))
		{
			for (int i = 0; i < actividades.size() && laDigna == null; i++)
			{
				if (actividades.get(i).getEncargado().getNombre().equals(parametro))
					encontradas.add(actividades.get(i));
			}
		}
		else if (type.equals("titulo"))
		{
			for (int i = 0; i < actividades.size() && laDigna == null; i++)
			{
				if (actividades.get(i).getTitulo().equals(parametro))
					encontradas.add(actividades.get(i));
			}
		}
		else if(type.equals("paquete"))
		{
			for (int i = 0; i < paquetes.size() && laDigna == null; i++)
			{
				if (paquetes.get(i).getNombre().equals(parametro))
				{
					encontradas = paquetes.get(i).getTareas();
					for (PaqueteTareas sb: paquetes.get(i).getPaquetes())
					{
						encontradas.addAll(sb.getTareas());
					}
				}
			}
		}
		else 
		{
			System.out.println("ERROR 00001");
		}
		
		return encontradas;
	}
	
	public Actividad buscarActividad(String type, String parametro)
	{
		Actividad laDigna = null;
		ArrayList<Actividad> encontradas = new ArrayList<Actividad>();
		if (type.equals("tipo"))
		{
			for (int i = 0; i < actividades.size() && laDigna == null; i++)
			{
				if (actividades.get(i).getTipo().equals(parametro))
					encontradas.add(actividades.get(i));
			}
		}
		else if (type.equals("participante"))
		{
			for (int i = 0; i < actividades.size() && laDigna == null; i++)
			{
				if (actividades.get(i).getEncargado().getNombre().equals(parametro))
					encontradas.add(actividades.get(i));
			}
		}
		else if (type.equals("titulo"))
		{
			for (int i = 0; i < actividades.size() && laDigna == null; i++)
			{
				if (actividades.get(i).getTitulo().equals(parametro))
					encontradas.add(actividades.get(i));
			}
		}
		else 
		{
			System.out.println("ERROR 00001");
		}
		
		if (encontradas.isEmpty() == false)
		{
			System.out.println("===== Actividades Encontradas =====");
			for (int j = 0; j < encontradas.size() && laDigna == null; j++)
			{
				String t = encontradas.get(j).getTitulo();
				String ti = encontradas.get(j).getTipo();
				String p = encontradas.get(j).getEncargado().getNombre();
				System.out.println((j + 1) + ". Titulo: " +  t + " - Tipo: " + ti + " - Encargad@: " + p);
			}
			
			int choice = Integer.parseInt(input("\nPor favor seleccione una actividad"));
			
			laDigna = encontradas.get(choice - 1);
		}
		else
		{
			System.out.println("\nNo se encontraron actividades con esos parametros.");
		}
		return laDigna;
	}
	
	public void aEditar(Actividad task, Integer tipoEdicion, String edicion) throws ParseException 
	{
		switch(tipoEdicion)
		{
		case 1-> {Date f = sdf.parse(edicion); task.setFechaInicial(f);}
		case 2 ->{task.setTitulo(edicion);}
		case 3 ->{task.setDescripcion(edicion);}
		case 4 ->{task.setTipo(edicion);}
		case 5 -> estadoCronometroActividad(task);
		case 6 ->{Date f = sdf.parse(edicion); task.setTerminado(f);}
		default -> System.out.println("Error!!!!!");
		}
	} 
	
	public void estadoCronometroActividad(Actividad task) throws ParseException
	{
		
		int choice = Integer.parseInt(input("\nQuiere... 1. Pausar o 2. Continuar el cronometro?"));
		int choose = Integer.parseInt(input("\nQuiere usar 1. la fecha actual o 2. una fecha determinada?"));
		Date edicion = null;
		switch(choose)
		{
		case 1 -> edicion = new Date();
		case 2-> edicion = sdf.parse(input("\nIngrese la fecha determinada en formato dia-Mes-Año"));
		}
		
		task.estadoCronometro(choice, edicion);
	}
	
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}

	public void nuevoPaquete(String nombrePaquete, String descPaquete, Integer seleccionado) 
	{
		PaqueteTareas nuevoP = new PaqueteTareas(nombrePaquete, descPaquete);
		if (seleccionado < 1000)
		{
			paquetes.get(seleccionado).agregarPaquete(nuevoP);
		}
		paquetes.add(nuevoP);
	}

	
}
