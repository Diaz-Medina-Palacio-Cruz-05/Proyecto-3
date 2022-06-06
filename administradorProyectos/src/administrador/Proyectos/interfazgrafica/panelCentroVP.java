package administrador.Proyectos.interfazgrafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import administradorProyectos.modelo.PaqueteTareas;
import administradorProyectos.modelo.Proyecto;

public class panelCentroVP extends JPanel implements ActionListener
{
	private JLabel lblProyecto;
	private JLabel lblDescripcion;
	private JLabel lblFechaInicial;
	private JLabel lblFechaFinal;
	private JTextField txtProyecto;
	private JTextArea txtDescripcion;
	private JTextField txtFechaInicial;
	private JTextField txtFechaFinal;
	private JButton btnParticipante;
	private JButton btnActividad;
	private JButton btnPaquete;
	private JPanel panelInfo;
	private VentanaPrincipal principal;
	private static final String PARTICPANTE = "PARTICIPANTE";
	private static final String ACTIVIDAD = "ACTIVIDAD";
	private static final String PAQUETE = "PAQUETE";
	private DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public panelCentroVP(VentanaPrincipal principal)
	{
		this.principal = principal;
		
		setLayout(new GridLayout(4, 1));
		setBorder(new TitledBorder("Información del proyecto"));
		
		lblProyecto = new JLabel("Título: ");
		lblDescripcion = new JLabel("Descripción: ");
		lblFechaInicial = new JLabel("Fecha Inicial: ");
		lblFechaFinal = new JLabel("Fecha Final: ");
		
		txtProyecto = new JTextField();
		txtProyecto.setEditable(false);
		txtDescripcion = new JTextArea(6, 12);
		txtDescripcion.setEditable(false);
		txtFechaInicial = new JTextField();
		txtFechaInicial.setEditable(false);
		txtFechaFinal = new JTextField();
		txtFechaFinal.setEditable(false);
		
		btnActividad = new JButton("Crear Actividad");
		btnActividad.addActionListener(this);
		btnActividad.setActionCommand(ACTIVIDAD);
		
		btnParticipante = new JButton("Añadir Participante");
		btnParticipante.addActionListener(this);
		btnParticipante.setActionCommand(PARTICPANTE);
		
		btnPaquete = new JButton("Nuevo Paquete de Tareas");
		btnPaquete.addActionListener(this);
		btnPaquete.setActionCommand(PAQUETE);
		
		panelInfo = new JPanel();
		panelInfo.setLayout(new GridLayout(4, 2));
		panelInfo.add(lblProyecto);
		panelInfo.add(txtProyecto);
		panelInfo.add(lblFechaInicial);
		panelInfo.add(txtFechaInicial);
		panelInfo.add(lblFechaFinal);
		panelInfo.add(txtFechaFinal);
		panelInfo.add(lblDescripcion);
		panelInfo.add(txtDescripcion);
		
		
		add(panelInfo);
		add(btnActividad);
		add(btnParticipante);
		add(btnPaquete);
	}
	
	public void actualizar()
	{
		txtProyecto.setText(principal.proyecto.getTitulo());
		txtDescripcion.setText(principal.proyecto.getDescripcion());
		txtFechaInicial.setText(sdf.format(principal.proyecto.getFechaInicial()));
		txtFechaFinal.setText(sdf.format(principal.proyecto.getFechaFinal()));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(PARTICPANTE))
		{
			if (principal.proyecto == null)
			{
				dialWarning error = new dialWarning("NO SE HA CARGADO O ABIERTO NINGUN PROYECTO");
				error.setVisible(true);
			}
			else
			{
				dialogoNuevoParticipante dialParticipante = new dialogoNuevoParticipante(principal);
				dialParticipante.setVisible(true);
			}
			
		}
		else if(comando.equals(ACTIVIDAD))
		{
			if(principal.proyecto == null)
			{
				dialWarning error = new dialWarning("NO SE HA CARGADO O ABIERTO NINGUN PROYECTO");
				error.setVisible(true);
			}
			else
			{
				dialNuevaActividad dialActividades = new dialNuevaActividad(principal);
				dialActividades.setVisible(true);
			}
			
		}
		else if(comando.equals(PAQUETE))
		{
			Integer seleccionado = 1000;
			if(principal.proyecto == null)
			{
				dialWarning error = new dialWarning("NO SE HA CARGADO O ABIERTO NINGUN PROYECTO");
				error.setVisible(true);
			}
			else
			{
				String nombrePaquete = JOptionPane.showInputDialog("Escriba el nombre del paquete");
				String descPaquete = JOptionPane.showInputDialog("Escriba una breve descripción del paquete");
				Integer agregar = JOptionPane.showConfirmDialog(null, "Quiere añadir este paquete a uno existente?","INCEPTION",JOptionPane.YES_NO_OPTION);
				if(agregar == JOptionPane.YES_OPTION) 
				{
					JComboBox paquetesDisponibles = new JComboBox();
					ArrayList<PaqueteTareas> paquetes = principal.proyecto.getPaquetes();
					if (paquetes.isEmpty() == false)
					{
						for (PaqueteTareas pt: paquetes)
						{
							paquetesDisponibles.addItem(pt.getNombre());
						}
					}
					Integer input = JOptionPane.showConfirmDialog(null, paquetesDisponibles, "Seleccione un paquete", JOptionPane.DEFAULT_OPTION);
					if (input == JOptionPane.OK_OPTION)
					{
						seleccionado = paquetesDisponibles.getSelectedIndex();
					}
				
				}
				principal.nuevoPaquete(nombrePaquete, descPaquete, seleccionado);
				
			}
		}
		
	}

}
