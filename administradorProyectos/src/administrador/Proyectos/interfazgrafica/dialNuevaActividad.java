package administrador.Proyectos.interfazgrafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import administradorProyectos.modelo.Cronometro;
import administradorProyectos.modelo.PaqueteTareas;
import administradorProyectos.modelo.Participante;

public class dialNuevaActividad extends JDialog implements ActionListener, ItemListener
{
	private VentanaPrincipal principal;
	
	private JLabel lblTitulo;
	private JLabel lblEncargado;
	private JLabel lblDecripcion;
	private JLabel lblTipo;
	private JLabel lblPaquete;
	
	private JTextField txtTitulo;
	private JTextField txtDecripcion;
	
	private JComboBox<String> comboTipo;
	private JComboBox<String> comboParticipantes;
	private JComboBox<String> comboPaquetes;
	
	private JButton btnCrear;
	
	private JRadioButton radioPaquete;
	
	private static final String ANADIR = "ANADIR";
	
	
	
	public dialNuevaActividad(VentanaPrincipal principal)
	{
		this.principal = principal;
		
		setSize(520, 510);
		setLocationRelativeTo(null);
		setTitle("Nuevo Participante");
		setLayout(new GridLayout(7, 4));
		
		lblTitulo = new JLabel("Título");
		lblEncargado = new JLabel("Participante Encargado");
		lblDecripcion = new JLabel("Descripción");
		lblTipo = new JLabel("Tipo");
		lblPaquete = new JLabel("Paquete");
		
		txtTitulo = new JTextField();
		txtDecripcion = new JTextField();
		
		comboParticipantes = new JComboBox<String>();
		comboTipo = new JComboBox<String>();
		comboPaquetes = new JComboBox<String>();
		
		radioPaquete = new JRadioButton();
		
		btnCrear = new JButton("Crear Actividad");
		
		ArrayList<Participante> participantes = principal.proyecto.getParticipantes();
		ArrayList<String> tipos = principal.proyecto.getTipos(); 
		ArrayList<PaqueteTareas> paquetes = principal.proyecto.getPaquetes();
		
		for(Participante p: participantes)
		{
			comboParticipantes.addItem(p.getNombre());
		}
			
		for(String t: tipos)
		{
				comboTipo.addItem(t);
		}
		
		if (paquetes.isEmpty() == false)
		{
			for (PaqueteTareas pt: paquetes)
			{
				comboPaquetes.addItem(pt.getNombre());
			}
		}
		
		comboPaquetes.setEnabled(false);
		
		radioPaquete.addItemListener(this);
		
		add(new JLabel("Ingrese los datos de la nueva actividad"));
		add(new JLabel());
		add(new JLabel());
		add(lblTitulo);
		add(txtTitulo);
		add(new JLabel());
		add(lblEncargado);
		add(comboParticipantes);
		add(new JLabel());
		add(lblTipo);
		add(comboTipo);
		add(new JLabel());
		add(lblDecripcion);
		add(txtDecripcion);
		add(new JLabel());
		add(lblPaquete);
		add(radioPaquete);
		add(comboPaquetes);
		add(new JLabel());
		add(btnCrear);
		add(new JLabel());
		
		btnCrear.addActionListener(this);
		btnCrear.setActionCommand(ANADIR);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if(comando.equals(ANADIR))
		{
			String titulo = txtTitulo.getText();
			String descripcion = txtDecripcion.getText();
			Integer tipo = comboTipo.getSelectedIndex();
			Integer encargado = comboParticipantes.getSelectedIndex();
			Integer paquete = comboPaquetes.getSelectedIndex();
			principal.nuevaActividad(titulo, descripcion, tipo, encargado, paquete);
			dispose();
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		Object seleccion = e.getSource();
		Integer select = e.getStateChange();
		if (seleccion.equals(radioPaquete))
		{
			if (select == ItemEvent.SELECTED)
			{
				comboPaquetes.setEnabled(true);
			}
			else if (select == ItemEvent.DESELECTED)
			{
				comboPaquetes.setEnabled(false);
			}
		}
		
	}

}
