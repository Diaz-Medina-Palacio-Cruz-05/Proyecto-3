package administrador.Proyectos.interfazgrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import administradorProyectos.modelo.Participante;

public class VentanaParticipantes extends JFrame implements ActionListener
{
	private VentanaPrincipal principal;
	
	private panelInfoParticipantes panelInfo;
	private JPanel panelBotones;
	
	private JButton btnAnterior;
	private JButton btnDueno;
	private JButton btnSiguiente;
	
	private Integer contador;
	
	private static final String ATRAS = "ATRAS";
	private static final String ADELANTE = "ADELANTE";
	private static final String DUENO = "DUENO";
	
	public VentanaParticipantes(VentanaPrincipal principal)
	{
		setSize(900, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("PARTICIPANTES DEL PROYECTOS");
		setLayout(new BorderLayout());
		
		this.principal = principal;
		contador = 0;
		
		panelInfo = new panelInfoParticipantes();
		panelInfo.setDueno(principal.proyecto.getParticipantes().get(0));
		panelBotones = new JPanel();
		
		btnAnterior = new JButton("<---");
		btnDueno = new JButton("Dueño");
		btnSiguiente = new JButton("--->");
		
		btnAnterior.addActionListener(this);
		btnAnterior.setActionCommand(ATRAS);
		
		btnSiguiente.addActionListener(this);
		btnSiguiente.setActionCommand(ADELANTE);
		
		btnDueno.addActionListener(this);
		btnDueno.setActionCommand(DUENO);
		
		panelBotones.setLayout(new FlowLayout());
		panelBotones.setBorder(new TitledBorder("Opciones"));
		panelBotones.add(btnAnterior);
		panelBotones.add(btnDueno);
		panelBotones.add(btnSiguiente);
		
		add(panelInfo, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		
	}
	
	public void actualizarAnterior()
	{
		if (contador == 0)
		{
			dialWarning nope = new dialWarning("ESTAS EN EL PRIMER PARTICIPANTE");
			nope.setVisible(true);
		}
		else
		{
			contador--;
			Participante p = principal.proyecto.getParticipantes().get(contador);
			panelInfo.actualizar(p);
		}
	}
	
	public void actualizarSiguiente()
	{
		Integer tamañoParticipantes = principal.proyecto.getParticipantes().size();
		if (contador == (tamañoParticipantes - 1))
		{
			dialWarning nope = new dialWarning("ESTAS EN EL ULTIMO PARTICIPANTE");
			nope.setVisible(true);
		}
		else
		{
			contador++;
			Participante p = principal.proyecto.getParticipantes().get(contador);
			panelInfo.actualizar(p);
		}
	}
	
	public void setDueno()
	{	
		contador = 0;
		panelInfo.setDueno(principal.proyecto.getParticipantes().get(contador));
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(ATRAS))
		{
			actualizarAnterior();
		}
		else if(comando.equals(ADELANTE))
		{
			actualizarSiguiente();
		}
		else if(comando.equals(DUENO))
		{
			setDueno();
		}
	}
	
	
	
	
}
