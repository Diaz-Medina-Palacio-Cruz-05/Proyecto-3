package administrador.Proyectos.interfazgrafica;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import administradorProyectos.modelo.Actividad;
import administradorProyectos.modelo.PaqueteTareas;
import administradorProyectos.modelo.Participante;

public class listaScroll extends JScrollPane
{
	private JList<String> lista;
	private DefaultListModel<String> model;
	private VentanaPrincipal principal;
	
	public listaScroll(VentanaPrincipal principal)
	{
		this.principal = principal;
		
		model = new DefaultListModel<String>();
		lista = new JList<String>(model);
		setViewportView(lista);
	}
	
	public void cargarParticipantes()
	{
		for (Participante nombre: principal.proyecto.getParticipantes())
		{
			model.addElement(nombre.getNombre());
		}
	}
	
	public void cargarActividades()
	{
		for (Actividad act: principal.proyecto.getActividades())
		{
				model.addElement(act.getTitulo());
		}
	}
	
	public void cargarPaquetes()
	{
		for (PaqueteTareas act: principal.proyecto.getPaquetes())
		{
				model.addElement(act.getNombre());
		}
	}
	
	public void actualizarParticipantes()
	{
		ArrayList<Participante> partic = principal.proyecto.getParticipantes();
		Integer sizepartic = partic.size();
		Participante ultimoElemento = partic.get(sizepartic - 1);
		model.addElement(ultimoElemento.getNombre());
	}
	
	public void actualizarActividades()
	{
		ArrayList<Actividad> act = principal.proyecto.getActividades();
		Integer sizeact = act.size();
		Actividad ultimoElemento = act.get(sizeact -1);
		model.addElement(ultimoElemento.getTitulo());
	}
	
	public void actualizarPaquetes()
	{
		ArrayList<PaqueteTareas> act = principal.proyecto.getPaquetes();
		Integer sizeact = act.size();
		PaqueteTareas ultimoElemento = act.get(sizeact -1);
		model.addElement(ultimoElemento.getNombre());
	}
}
