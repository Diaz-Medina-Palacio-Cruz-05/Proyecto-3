package administrador.Proyectos.interfazgrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class panelDerechaVP extends JPanel implements ActionListener
{
	private VentanaPrincipal principal;
	
	private JPanel panelListas;
	private JPanel panelBotones;
	private JPanel panelPart;
	private JPanel panelAct;
	private JPanel panelPaq;
	
	private JButton btnParticipantes;
	private JButton btnActividades;
	private JButton btnRegistro;
	
	private listaScroll listaActividades;
	private listaScroll listaParticipantes;
	private listaScroll listaPaquetesActividades;
	
	private static final String PARTICIPANTES = "PARTICIPANTES";
	private static final String ACTIVIDADES = "ACTIVIDADES";
	private static final String REGISTRO = "REGISTRO";

	public panelDerechaVP(VentanaPrincipal principal)
	{
		this.principal = principal;
		
		setLayout(new GridLayout(2,1));
		
		panelListas = new JPanel();
		panelBotones = new JPanel();
		panelAct = new JPanel();
		panelPart = new JPanel();
		panelPaq = new JPanel();
		
		btnParticipantes = new JButton("Participantes");
		btnActividades = new JButton("Actividades");
		btnRegistro = new JButton("Resgistro de Actividades");
		
		btnParticipantes.addActionListener(this);
		btnParticipantes.setActionCommand(PARTICIPANTES);
		
		btnActividades.addActionListener(this);
		btnActividades.setActionCommand(ACTIVIDADES);
		
		listaActividades = new listaScroll(principal);
		listaParticipantes = new listaScroll(principal);
		listaPaquetesActividades = new listaScroll(principal);
		
		panelPart.setLayout(new BorderLayout());
		panelPart.add(new JLabel("Participantes"), BorderLayout.NORTH);
		panelPart.add(listaParticipantes, BorderLayout.CENTER);
		
		panelAct.setLayout(new BorderLayout());
		panelAct.add(new JLabel("Actividades"), BorderLayout.NORTH);
		panelAct.add(listaActividades, BorderLayout.CENTER);
		
		panelPaq.setLayout(new BorderLayout());
		panelPaq.add(new JLabel("Paquetes"), BorderLayout.NORTH);
		panelPaq.add(listaPaquetesActividades, BorderLayout.CENTER);
		
		panelListas.setLayout(new BorderLayout());
		panelListas.setBorder(new TitledBorder("Resumen"));
		panelListas.add(panelPart, BorderLayout.WEST);
		panelListas.add(panelAct, BorderLayout.CENTER);
		panelListas.add(panelPaq, BorderLayout.EAST);
		
		panelBotones.setBorder(new TitledBorder("Management"));
		panelBotones.setLayout(new GridLayout(3, 1));
		panelBotones.add(btnParticipantes);
		panelBotones.add(btnRegistro);
		panelBotones.add(btnActividades);
		
		add(panelListas);
		add(panelBotones);
		
	}
	
	public void cargarInfo()
	{
		listaActividades.cargarActividades();
		listaParticipantes.cargarParticipantes();
		listaPaquetesActividades.cargarPaquetes();
	}
	
	public void actualizarParticipantes()
	{
		listaParticipantes.actualizarParticipantes();
	}
	
	public void actualizarActividades()
	{
		listaActividades.actualizarActividades();
	}
	
	public void actualizarPaquetes()
	{
		listaPaquetesActividades.actualizarPaquetes();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(PARTICIPANTES))
		{
			if (principal.proyecto == null)
			{
				dialWarning error = new dialWarning("NO SE HA CARGADO O ABIERTO NINGUN PROYECTO");
				error.setVisible(true);
			}
			else
			{
				VentanaParticipantes nuevo = new VentanaParticipantes(principal);
				nuevo.setVisible(true);
			}
		}
		else if (comando.equals(ACTIVIDADES))
		{
			if (principal.proyecto == null)
			{
				dialWarning error = new dialWarning("NO SE HA CARGADO O ABIERTO NINGUN PROYECTO");
				error.setVisible(true);
			}
			else
			{
				VentanaActividades nuevo = new VentanaActividades(principal);
				nuevo.setVisible(true);
			}
			
		}
		
	}

}
