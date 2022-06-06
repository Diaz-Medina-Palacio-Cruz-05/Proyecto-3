package administrador.Proyectos.interfazgrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import administradorProyectos.modelo.Actividad;

public class panelInfoActividad extends JPanel implements ActionListener
{		
	private VentanaActividades principal;
	
	private JPanel panelInfoActi;
	private JPanel panelBotones;
	
	private JButton btnPausar;
	private JButton btnEditar;
	private JButton btnSiguiente;
	private JButton btnAnterior;
	private JButton btnFinalizar;
	
	private JLabel lblTitulo;
	private JLabel lblTipo;
	private JLabel lblTerminado;
	private JLabel lblFechaInicial;
	private JLabel lblFechaFinal;
	private JLabel lblEncargado;
	private JLabel lblDescripcion;
	
	private JTextField txtTitulo;
	private JTextField txtTipo;
	private JTextField txtTerminado;
	private JTextField txtFechaInicial;
	private JTextField txtFechaFinal;
	private JTextField txtEncargado;
	private JTextArea txtDescripcion;
	
	private static final String PAUSAR = "PAUSAR";
	private static final String ANTERIOR = "ANTERIOR";
	private static final String SIGUIENTE = "SIGUIENTE";
	private static final String FINALIZAR = "FINALIZAR";
	private static final String EDITAR = "EDITAR";
	
	private DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public panelInfoActividad(VentanaActividades principal)
	{
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Actividades Encontradas"));
		
		this.principal = principal;
		
		panelInfoActi = new JPanel();
		panelBotones = new JPanel();
		
		btnPausar = new JButton("Pausar");
		btnEditar = new JButton("Editar");
		btnSiguiente = new JButton("--->");
		btnAnterior = new JButton("<---");
		btnFinalizar = new JButton("Finalizar");
		
		lblTitulo = new JLabel("Titulo");
		lblTipo = new JLabel("Tipo");
		lblTerminado = new JLabel("Estado");
		lblFechaInicial = new JLabel("Fecha de Inicio");
		lblFechaFinal = new JLabel("Fecha Terminado");
		lblEncargado = new JLabel("Encargado");
		lblDescripcion = new JLabel("Descripcion");
		 
		txtTitulo = new JTextField();
		txtTipo = new JTextField();
		txtTerminado = new JTextField();
		txtFechaInicial = new JTextField();
		txtFechaFinal = new JTextField();
		txtEncargado = new JTextField();
		txtDescripcion = new JTextArea();
		
		txtTitulo.setEditable(false);
		txtTipo.setEditable(false);
		txtTerminado.setEditable(false);
		txtFechaInicial.setEditable(false);
		txtFechaFinal.setEditable(false);
		txtEncargado.setEditable(false);
		txtDescripcion.setEditable(false);
		
		btnPausar.addActionListener(this);
		btnPausar.setActionCommand(PAUSAR);
		
		btnAnterior.addActionListener(this);
		btnAnterior.setActionCommand(ANTERIOR);
		
		btnEditar.addActionListener(this);
		btnEditar.setActionCommand(EDITAR);
		
		btnFinalizar.addActionListener(this);
		btnFinalizar.setActionCommand(FINALIZAR);
		
		btnSiguiente.addActionListener(this);
		btnSiguiente.setActionCommand(SIGUIENTE);
		
		panelInfoActi.setLayout(new GridLayout(7,2));
		panelInfoActi.setBorder(new TitledBorder("Info Actividad"));
		panelInfoActi.add(lblTitulo);
		panelInfoActi.add(txtTitulo);
		panelInfoActi.add(lblTerminado);
		panelInfoActi.add(txtTerminado);
		panelInfoActi.add(lblTipo);
		panelInfoActi.add(txtTipo);
		panelInfoActi.add(lblEncargado);
		panelInfoActi.add(txtEncargado);
		panelInfoActi.add(lblFechaInicial);
		panelInfoActi.add(txtFechaInicial);
		panelInfoActi.add(lblFechaFinal);
		panelInfoActi.add(txtFechaFinal);
		panelInfoActi.add(lblDescripcion);
		panelInfoActi.add(txtDescripcion);
		
		panelBotones.setLayout(new GridLayout(1,5));
		panelBotones.setBorder(new TitledBorder("Opciones"));
		panelBotones.add(btnAnterior);
		panelBotones.add(btnEditar);
		panelBotones.add(btnFinalizar);
		panelBotones.add(btnPausar);
		panelBotones.add(btnSiguiente);
		
		add(panelInfoActi, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		
	}
	
	public void actualizar(Actividad actividad)
	{
		txtTitulo.setText(actividad.getTitulo());
		txtTipo.setText(actividad.getTipo());
		if (actividad.getTerminado())
		{
			txtTerminado.setText("Actividad Terminada");
			txtTerminado.setForeground(Color.GREEN);
			btnPausar.setEnabled(false);
		}
		else
		{
			txtTerminado.setForeground(Color.BLACK);
			txtTerminado.setText("Actividad en Proceso");
			btnPausar.setEnabled(true);
		}
		String fInicial = sdf.format(actividad.getFechaInicial());
		txtFechaInicial.setText(fInicial);
		if (actividad.getFechaFinal() != null)
		{
			String fFinal = sdf.format(actividad.getFechaFinal());
			txtFechaFinal.setText(fFinal);
		}
		else
		{
			txtFechaFinal.setText("Actividad aun en proceso");
		}
		txtEncargado.setText(actividad.getEncargado().getNombre());
		txtDescripcion.setText(actividad.getDescripcion());
		
		if (actividad.getEstado() == true)
		{
			btnPausar.setText("Continuar");
		}
		else
		{
			btnPausar.setText("Pausar");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(ANTERIOR))
		{
			principal.actualizarAnterior();
		}
		else if (comando.equals(EDITAR))
		{
			dialEditar edicion = new dialEditar(principal, principal.getPrincipal());
			edicion.actualizar(principal.getCurrentActivity());
			edicion.setVisible(true);
		}
		else if (comando.equals(SIGUIENTE))
		{
			principal.actualizarSiguiente();
		}
		else if (comando.equals(FINALIZAR))
		{
			dialFecha ingresador = new dialFecha("Ingrese la fecha de finalización o utilice la fecha de hoy", "finalizar", principal);
			ingresador.setVisible(true);
		}	
		else if (comando.equals(PAUSAR))
		{
			String estado = btnPausar.getText();
			
			if (estado.equals("Pausar"))
			{
				dialFecha ingresador = new dialFecha("Ingrese la fecha de finalización o utilice la fecha de hoy", "pausar", principal);
				ingresador.setVisible(true);
				btnPausar.setText("Continuar");
			}
			else if (estado.equals("Continuar"))
			{
				dialFecha ingresador = new dialFecha("Ingrese la fecha de finalización o utilice la fecha de hoy", "continuar", principal);
				ingresador.setVisible(true);
				btnPausar.setText("Pausar");
			}
		}
		
	}

}
