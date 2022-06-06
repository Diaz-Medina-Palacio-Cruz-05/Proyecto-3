package administrador.Proyectos.interfazgrafica;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import administradorProyectos.modelo.Actividad;
import administradorProyectos.modelo.Participante;
import administradorProyectos.modelo.Proyecto;

public class VentanaPrincipal extends JFrame implements WindowListener
{
	private panelIzquierdaVP panelIzquierda;
	private panelCentroVP panelCentro;
	private panelDerechaVP panelDerecha;
	public Proyecto proyecto;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public VentanaPrincipal()
	{
		setSize(1100, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(this);
		setLocationRelativeTo(null);
		setTitle("ADMINISTRADOR DE PROYECTOS");
		setLayout(new BorderLayout());
		proyecto = null;
		
		panelIzquierda = new panelIzquierdaVP(this);
		panelCentro = new panelCentroVP(this);
		panelDerecha = new panelDerechaVP(this);
		
		add(panelIzquierda, BorderLayout.WEST);
		add(panelCentro, BorderLayout.CENTER);
		add(panelDerecha, BorderLayout.EAST);
		
	}
	
	public static void main(String[] args) throws Exception
	{
		VentanaPrincipal ventana = new VentanaPrincipal();
		ventana.setVisible(true);
	}
	
	public void nuevoPaquete(String nombrePaquete, String descPaquete, Integer seleccionado) 
	{
		proyecto.nuevoPaquete(nombrePaquete, descPaquete, seleccionado);
		panelDerecha.actualizarPaquetes();
		
	}

	public void nuevoParticipante(String nombre, String correo) 
	{
		proyecto.añadirParticipante(nombre, correo);
		panelDerecha.actualizarParticipantes();
	}

	public void nuevaActividad(String titulo, String descripcion, Integer tipo, Integer encargado, Integer paquete) 
	{
		String elTipo = proyecto.getTipos().get(tipo);
		String elEncargado = proyecto.getParticipantes().get(encargado).getNombre();
		proyecto.crearActividad(titulo, elTipo, descripcion, elEncargado, paquete);
		panelDerecha.actualizarActividades();
	}

	public void cargarProyecto(String archivo) throws Exception 
	{
		proyecto = new Proyecto();
		FileInputStream file = new FileInputStream("data/" + archivo + ".ser");
        ObjectInputStream in = new ObjectInputStream(file);
        proyecto = (Proyecto) in.readObject();
        file.close();
        in.close();
        panelCentro.actualizar();
        panelDerecha.cargarInfo();
        panelIzquierda.actualizar();
	}

	public void crearProyecto(String titulo, String descripcion, String fechaFinal, String duenoNombre,
			String duenocorreo, String tipos) 
	{
		proyecto = new Proyecto();
		proyecto.setTitulo(titulo);
		proyecto.setDescripcion(descripcion);
		Participante dueno = new Participante(duenoNombre, duenocorreo, true);
		proyecto.getParticipantes().add(dueno);
		try 
		{
			Date ff = sdf.parse(fechaFinal);
			proyecto.setFechaFinal(ff);
		} 
		catch (ParseException e) 
		{
			System.out.println("ERROR FECHA FINAL");
		}
		
		String[] tiposSeparados = tipos.split("[,]", 0);
		
		for (String elemento: tiposSeparados)
	      {
	          proyecto.getTipos().add(elemento);
	      }
		panelCentro.actualizar();
		panelDerecha.actualizarParticipantes();
		panelIzquierda.actualizar();
	}
	
	private void guardarAlSalir() throws Exception
	{
		String nombreProyecto = proyecto.getTitulo();
		FileOutputStream file = new FileOutputStream("data/" + nombreProyecto + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(proyecto);
        out.close();
        file.close();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		if (proyecto != null)
		{
			try {
				guardarAlSalir();
				System.out.println("Se guardo com exito!!!");
			} catch (Exception e1) 
			{
				System.out.println("ERROR NO SE PUDO GUARDAR / NO EXISTE PROYECTO");
				e1.printStackTrace();
			}
		}
		else
		{
			System.out.println("No hay nada que guardar");
		}
	}

	@Override
	public void windowClosed(WindowEvent e) 
	{
		// TODO Auto-generated method stub	
		
	}

	@Override
	public void windowIconified(WindowEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
