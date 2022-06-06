package administrador.Proyectos.interfazgrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class dialFecha extends JDialog implements ActionListener
{
	private Date fechaIngresada;
	
	private VentanaActividades principal;
	
	private JLabel peticion;
	
	private JButton btnIngresar;
	private JButton btnFechaHoy;
	
	private JTextField txtFechaIngresada;
	
	private static final String CONTINUAR = "CONTINUAR";
	private static final String PAUSAR = "PAUSAR";
	private static final String FINALIZAR = "FINALIZAR";
	private static final String ACTUAL = "ACTUAL";
	
	private String tipo;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public dialFecha(String texto, String tipo, VentanaActividades principal)
	{
		setLayout(new BorderLayout());
		setSize(520, 150);
		setLocationRelativeTo(null);
		setTitle("Nuevo Participante");
		
		fechaIngresada = new Date();
		
		this.tipo = tipo;
		this.principal = principal;
		
		peticion = new JLabel(texto);
		
		btnFechaHoy = new JButton("Usar Fecha de Hoy");
		btnIngresar = new JButton("Usar Fecha Ingresada");
		
		btnIngresar.addActionListener(this);
		
		if (tipo == "continuar")
		{
			btnIngresar.setActionCommand(CONTINUAR);
		}
		else if (tipo == "pausar")
		{
			btnIngresar.setActionCommand(PAUSAR);
		}
		else if (tipo == "finalizar")
		{
			btnIngresar.setActionCommand(FINALIZAR);
		}
		
		btnFechaHoy.addActionListener(this);
		btnFechaHoy.setActionCommand(ACTUAL);
		
		JPanel bonito = new JPanel();
		bonito.setLayout(new FlowLayout());
		bonito.add(btnIngresar);
		bonito.add(btnFechaHoy);
		
		txtFechaIngresada = new JTextField();
		
		
		add(peticion, BorderLayout.NORTH);
		add(txtFechaIngresada, BorderLayout.CENTER);
		add(bonito, BorderLayout.SOUTH);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(CONTINUAR))
		{
			try {
				fechaIngresada = sdf.parse(txtFechaIngresada.getText());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			principal.estadoCronometro(2, fechaIngresada);
			setVisible(false);
			dispose();
		}
		else if (comando.equals(FINALIZAR))
		{
			try {
				fechaIngresada = sdf.parse(txtFechaIngresada.getText());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			principal.finalizarActividad(fechaIngresada);
			setVisible(false);
			dispose();
		}
		else if (comando.equals(PAUSAR))
		{
			try {
				fechaIngresada = sdf.parse(txtFechaIngresada.getText());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			principal.estadoCronometro(1, fechaIngresada);
			setVisible(false);
			dispose();
		}
		else if (comando.equals(ACTUAL))
		{
			if (tipo == "continuar")
			{
				principal.estadoCronometro(2, fechaIngresada);
				setVisible(false);
				dispose();
			}
			else if (tipo == "pausar")
			{
				principal.estadoCronometro(1, fechaIngresada);
				setVisible(false);
				dispose();
			}
			else if (tipo == "finalizar")
			{
				principal.finalizarActividad(fechaIngresada);
				setVisible(false);
				dispose();
			}
		}
	}

}
