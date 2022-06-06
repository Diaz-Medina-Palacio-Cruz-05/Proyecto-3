package administrador.Proyectos.interfazgrafica;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import administradorProyectos.modelo.Actividad;
import administradorProyectos.modelo.Participante;

public class panelInfoParticipantes extends JPanel
{
	private JPanel infoParticpantePrincipal;
	private JPanel infoParticpanteSecundario;
	private JPanel reporteParticipante;
	
	private JLabel lblNombre;
	private JLabel lblCorreo;
	private JLabel lblDueño;
	
	private JTextField txtNombre;
	private JTextField txtCorreo;
	
	private JRadioButton radioDueño;
	
	private JTextArea txtReporte;
	
	private DefaultListModel<String> model;
	private JList<String> lista;
	
	private JScrollPane listaActividades;
	
	
	public panelInfoParticipantes()
	{
		setLayout(new GridLayout(1,2));
		setBorder(new TitledBorder("Información del Participante"));
		
		infoParticpantePrincipal = new JPanel();
		infoParticpanteSecundario =new JPanel();
		reporteParticipante = new JPanel();
		
		lblNombre = new JLabel("Nombre");
		lblCorreo = new JLabel("Correo");
		lblDueño = new JLabel("Dueño");
		
		txtNombre = new JTextField();
		txtCorreo = new JTextField();
		txtReporte = new JTextArea(5, 20);
		
		radioDueño = new JRadioButton();
		
		model = new DefaultListModel<String>();
		lista = new JList<String>(model);
		
		listaActividades = new JScrollPane();
		
		txtNombre.setEditable(false);
		txtCorreo.setEditable(false);
		txtReporte.setEditable(false);
		radioDueño.setEnabled(false);
		
		listaActividades.setViewportView(lista);
		
		infoParticpanteSecundario.setLayout(new GridLayout(4,2));
		infoParticpanteSecundario.add(lblNombre);
		infoParticpanteSecundario.add(txtNombre);
		infoParticpanteSecundario.add(lblCorreo);
		infoParticpanteSecundario.add(txtCorreo);
		infoParticpanteSecundario.add(lblDueño);
		infoParticpanteSecundario.add(radioDueño);
		infoParticpanteSecundario.add(new JLabel("Actividades"));
		
		infoParticpantePrincipal.setLayout(new GridLayout(2,1));
		infoParticpantePrincipal.add(infoParticpanteSecundario);
		infoParticpantePrincipal.add(listaActividades);
		
		reporteParticipante.setLayout(new BorderLayout());
		reporteParticipante.add(new JLabel("Reporte"), BorderLayout.NORTH);
		reporteParticipante.add(txtReporte, BorderLayout.CENTER);
		
		add(infoParticpantePrincipal);
		add(reporteParticipante);
		
		
	}
	
	public void setDueno(Participante dueno)
	{
		txtNombre.setText(dueno.getNombre());
		txtCorreo.setText(dueno.getCorreo());
		dueno.getReporte().generarTextoReporte(dueno.getActividades());
		txtReporte.setText(dueno.getReporte().getReporte());
		radioDueño.setSelected(dueno.isDueño());
		model.removeAllElements();
		for (Actividad actividad: dueno.getActividades())
		{
			model.addElement(actividad.getTitulo());
		}
	}
	
	public void actualizar(Participante participante)
	{
		txtNombre.setText(participante.getNombre());
		txtCorreo.setText(participante.getCorreo());
		participante.getReporte().generarTextoReporte(participante.getActividades());
		txtReporte.setText(participante.getReporte().getReporte());
		radioDueño.setSelected(participante.isDueño());
		model.removeAllElements();
		for (Actividad actividad: participante.getActividades())
		{
			model.addElement(actividad.getTitulo());
		}
	}
	
	
}
