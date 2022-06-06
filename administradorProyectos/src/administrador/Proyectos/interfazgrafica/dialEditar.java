package administrador.Proyectos.interfazgrafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import administradorProyectos.modelo.Actividad;
import administradorProyectos.modelo.Participante;

public class dialEditar extends JDialog implements ActionListener, ItemListener
{
	private VentanaActividades principal;
	
	private JLabel lblTitulo;
	private JLabel lblFechaInicial;
	private JLabel lblDecripcion;
	private JLabel lblTipo;
	
	private JTextField txtTitulo;
	private JTextField txtDecripcion;
	private JTextField txtFechaInicial;
	
	private JComboBox<String> comboTipo;
	
	private JButton btnEditar;
	
	private JRadioButton radioTitulo;
	private JRadioButton radioFechaInicial;
	private JRadioButton radioDescripcion;
	private JRadioButton radioTipo;
	
	private static final String EDITAR = "EDITAR";
	
	private DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public dialEditar(VentanaActividades principal, VentanaPrincipal vP)
	{
		this.principal = principal;
		
		setSize(520, 510);
		setLocationRelativeTo(null);
		setTitle("Editar Actividad");
		setLayout(new GridLayout(7, 3));
		
		lblTitulo = new JLabel("Título");
		lblFechaInicial = new JLabel("Fecha Inicial(dd-MM-aaaa)");
		lblDecripcion = new JLabel("Descripción");
		lblTipo = new JLabel("Tipo");
		
		txtTitulo = new JTextField();
		txtDecripcion = new JTextField();
		txtFechaInicial = new JTextField();
		
		comboTipo = new JComboBox<String>();
		
		btnEditar = new JButton("Actualizar");
		
		radioDescripcion = new JRadioButton();
		radioFechaInicial = new JRadioButton();
		radioTipo = new JRadioButton();
		radioTitulo = new JRadioButton();
		
		txtTitulo.setEnabled(false);
		txtDecripcion.setEnabled(false);
		txtFechaInicial.setEnabled(false);
		comboTipo.setEnabled(false);
		
		radioDescripcion.addItemListener(this);
		radioFechaInicial.addItemListener(this);
		radioTipo.addItemListener(this);
		radioTitulo.addItemListener(this);
		
		btnEditar.addActionListener(this);
		btnEditar.setActionCommand(EDITAR);
		
		ArrayList<String> tipos = vP.proyecto.getTipos(); 
			
		for(String t: tipos)
		{
			comboTipo.addItem(t);
		}
		
		add(new JLabel("Seleccione y edite"));
		add(new JLabel());
		add(new JLabel());
		add(lblTitulo);
		add(txtTitulo);
		add(radioTitulo);
		add(lblFechaInicial);
		add(txtFechaInicial);
		add(radioFechaInicial);
		add(lblTipo);
		add(comboTipo);
		add(radioTipo);
		add(lblDecripcion);
		add(txtDecripcion);
		add(radioDescripcion);
		add(new JLabel());
		add(btnEditar);
		add(new JLabel());
		
	}
	
	public void actualizar(Actividad currentActivity) 
	{
		txtTitulo.setText(currentActivity.getTitulo());
		txtFechaInicial.setText(sdf.format(currentActivity.getFechaInicial()));
		txtDecripcion.setText(currentActivity.getDescripcion());
		comboTipo.setSelectedItem(currentActivity.getTipo());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(EDITAR))
		{
			if (radioDescripcion.isSelected())
			{
				try {
					principal.editarActividad(3, txtDecripcion.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			if (radioFechaInicial.isSelected())
			{
				try {
					principal.editarActividad(1, txtFechaInicial.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			if (radioTipo.isSelected())
			{
				String t = (String) comboTipo.getSelectedItem();
				try {
					principal.editarActividad(4, t);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			if (radioTitulo.isSelected())
			{
				try {
					principal.editarActividad(2, txtTitulo.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		Object seleccion = e.getSource();
		Integer select = e.getStateChange();
		if (seleccion.equals(radioDescripcion))
		{
			if (select == ItemEvent.SELECTED)
			{
				txtDecripcion.setEnabled(true);
			}
			else if (select == ItemEvent.DESELECTED)
			{
				txtDecripcion.setEnabled(false);
			}
		}
		else if (seleccion.equals(radioFechaInicial))
		{
			if (select == ItemEvent.SELECTED)
			{
				txtFechaInicial.setEnabled(true);
			}
			else if (select == ItemEvent.DESELECTED)
			{
				txtFechaInicial.setEnabled(false);
			}
		}
		else if (seleccion.equals(radioTipo))
		{
			if (select == ItemEvent.SELECTED)
			{
				comboTipo.setEnabled(true);
			}
			else if (select == ItemEvent.DESELECTED)
			{
				comboTipo.setEnabled(false);
			}
		}
		else if (seleccion.equals(radioTitulo))
		{
			if (select == ItemEvent.SELECTED)
			{
				txtTitulo.setEnabled(true);
			}
			else if (select == ItemEvent.DESELECTED)
			{
				txtTitulo.setEnabled(false);
			}
		}
		
	}

	

}
