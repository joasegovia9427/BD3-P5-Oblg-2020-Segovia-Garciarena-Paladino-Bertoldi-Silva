package grafica.ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Utilitarios.MensajesPersonalizados;
import Utilitarios.TipoMensaje;
import grafica.controladores.controladorPrincipal;
import logica.excepciones.LogicaException;
import logica.excepciones.ServidorException;
import logica.valueObjects.voJuguetes;
import logica.valueObjects.voNinio;
import persistencia.excepciones.PersistenciaException;

import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.TextField;
import java.awt.SystemColor;
import java.awt.Color;

public class AdministradorJugueteria extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCedulaBorrarNinio;
	private JTextField textFieldNumeroDescripcionJuguete;
	private JTextField textFieldCedulaDescripcionJuguete;
	private JTable tableListarJuguetes;
	private JTable table_ListarNinios;
	private JTextField textFieldDescripcionNuevoJuguete;
	private JTextField textFieldCedulaNuevoJuguete;
	private JTextField textFieldCedulaIngresarNinio;
	private JTextField textFieldNombreIngresarNinio;
	private JTextField textFieldApellidoIngresarNinio;
	private JFrame frame;
	private JTable tableDescripcionJuguete;
	private static controladorPrincipal controller;

	private DefaultTableModel modelDescripcion = new DefaultTableModel(); // agrego para cargar la tabla
	private DefaultTableModel modelNinios = new DefaultTableModel(); // agrego para cargar la tabla
	private DefaultTableModel modelJuguetes = new DefaultTableModel(); // agrego para cargar la tabla
	private JTextField textFieldCedulaListarJuguetes;

	public static MensajesPersonalizados msg = new MensajesPersonalizados();
	public TextField textFieldMsgsOut;
	public TipoMensaje tipoMsg;

	public AdministradorJugueteria() {

		try {
			controller = new controladorPrincipal();
			setVisible(true);
		} catch (FileNotFoundException e2) {

			NuevaVentanaMsgOut(TipoMensaje.ERROR, e2.getMessage() + " 1");
			setVisible(false);
		} catch (NotBoundException e2) {
			NuevaVentanaMsgOut(TipoMensaje.ERROR, e2.getMessage() + " 3");
			setVisible(false);
		} catch (IOException e1) {
			try {
				throw new ServidorException(msg.errorIO);
			} catch (ServidorException e2) {
				JOptionPane.showMessageDialog(frame, e2.getMessage());

//				NuevaVentanaMsgOut(TipoMensaje.ERROR, e2.getMessage());
				setVisible(false);// tiene de estar en false
			}
		}

		setTitle("Administracion Jugueteria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 508, 316);
		contentPane.add(tabbedPane);

		JPanel panelIngresarNinio = new JPanel();
		tabbedPane.addTab("Ingresar Ni\u00F1o", null, panelIngresarNinio, null);
		panelIngresarNinio.setLayout(null);

		JLabel lblCedulaIngresarNinio = new JLabel("Cedula");
		lblCedulaIngresarNinio.setBounds(55, 50, 46, 14);
		panelIngresarNinio.add(lblCedulaIngresarNinio);

		JLabel lblNombreIngresarNinio = new JLabel("Nombre");
		lblNombreIngresarNinio.setBounds(55, 87, 46, 14);
		panelIngresarNinio.add(lblNombreIngresarNinio);

		JLabel lblApellidoingresarNinio = new JLabel("Apellido");
		lblApellidoingresarNinio.setBounds(55, 129, 46, 14);
		panelIngresarNinio.add(lblApellidoingresarNinio);

		textFieldCedulaIngresarNinio = new JTextField();
		textFieldCedulaIngresarNinio.setBounds(155, 47, 86, 20);
		panelIngresarNinio.add(textFieldCedulaIngresarNinio);
		textFieldCedulaIngresarNinio.setColumns(10);

		textFieldNombreIngresarNinio = new JTextField();
		textFieldNombreIngresarNinio.setBounds(155, 84, 256, 20);
		panelIngresarNinio.add(textFieldNombreIngresarNinio);
		textFieldNombreIngresarNinio.setColumns(10);

		textFieldApellidoIngresarNinio = new JTextField();
		textFieldApellidoIngresarNinio.setBounds(155, 126, 256, 20);
		panelIngresarNinio.add(textFieldApellidoIngresarNinio);
		textFieldApellidoIngresarNinio.setColumns(10);

		JButton btnIngresarNio = new JButton("Ingresar Ni\u00F1o");
		btnIngresarNio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ced = textFieldCedulaIngresarNinio.getText();
				String nombre = textFieldNombreIngresarNinio.getText();
				String apellido = textFieldApellidoIngresarNinio.getText();

				if ((NoEsVacio(ced)) && (NoEsVacio(nombre) && (NoEsVacio(apellido)))) {
					if (EsNumero(ced)) {
						if (esCIValida(ced)) {

							if ((!EsNumero(nombre)) && (!EsNumero(apellido))) {
								int cedula;
								cedula = Integer.parseInt(ced);
								try {
									controller.nuevoNinioCP(cedula, nombre, apellido);
									NuevaVentanaMsgOut(TipoMensaje.SUCCESS, msg.infoNinioIngresado);
								} catch (RemoteException e1) {
									NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
								} catch (LogicaException e1) {
									NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
								} catch (PersistenciaException e1) {
									NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
								}
							} else {
								NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaNombreApellido);
							}
						} else {
							NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaCedulaInvalida);
						}
					} else {
						NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaCedulaConLetras);
					}
				} else {
					NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaCamposVacios);
				}
			}
		});
		btnIngresarNio.setBounds(101, 176, 140, 23);
		panelIngresarNinio.add(btnIngresarNio);

		JPanel panelIngresarJuguete = new JPanel();
		tabbedPane.addTab("Ingresar Juguete", null, panelIngresarJuguete, null);
		panelIngresarJuguete.setLayout(null);

		JLabel lblDescripcionNuevoJuguete = new JLabel("Descripcion");
		lblDescripcionNuevoJuguete.setBounds(31, 97, 96, 14);
		panelIngresarJuguete.add(lblDescripcionNuevoJuguete);

		JLabel lblCedulaNuevoJuguete = new JLabel("Cedula");
		lblCedulaNuevoJuguete.setBounds(31, 44, 46, 14);
		panelIngresarJuguete.add(lblCedulaNuevoJuguete);

		textFieldDescripcionNuevoJuguete = new JTextField();
		textFieldDescripcionNuevoJuguete.setBounds(131, 94, 336, 34);
		panelIngresarJuguete.add(textFieldDescripcionNuevoJuguete);
		textFieldDescripcionNuevoJuguete.setColumns(10);

		textFieldCedulaNuevoJuguete = new JTextField();
		textFieldCedulaNuevoJuguete.setBounds(131, 41, 86, 20);
		panelIngresarJuguete.add(textFieldCedulaNuevoJuguete);
		textFieldCedulaNuevoJuguete.setColumns(10);

		JButton btnIngresarJuguete = new JButton("Ingresar Juguete");
		btnIngresarJuguete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String numero = "0";
				String descripcion = textFieldDescripcionNuevoJuguete.getText();
				String ced = textFieldCedulaNuevoJuguete.getText();

				if ((NoEsVacio(ced)) && (NoEsVacio(numero) && (NoEsVacio(descripcion)))) {
					if ((EsNumero(ced)) && (EsNumero(numero))) {
						if (esCIValida(ced)) {
							int cedula, num;
							num = Integer.parseInt(numero);
							cedula = Integer.parseInt(ced);
							try {
								controller.nuevoJugueteCP(num, descripcion, cedula);
								NuevaVentanaMsgOut(TipoMensaje.SUCCESS, msg.infoJugueteIngresado);
							} catch (IOException e1) {
								NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
							} catch (LogicaException e1) {
								NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
							} catch (PersistenciaException e1) {
								NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
							}
						} else {
							NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaCedulaInvalida);
						}
					} else {
						NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaCedulaJuguete);
					}
				} else {
					NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaCamposVacios);
				}
			}
		});
		btnIngresarJuguete.setBounds(131, 149, 152, 23);
		panelIngresarJuguete.add(btnIngresarJuguete);

		JPanel panelListarNinios = new JPanel();
		tabbedPane.addTab("Listar Ni\u00F1os", null, panelListarNinios, null);
		panelListarNinios.setLayout(null);

		JScrollPane scrollPane_ListarNinios = new JScrollPane();
		scrollPane_ListarNinios.setBounds(30, 45, 452, 216);
		panelListarNinios.add(scrollPane_ListarNinios);

		table_ListarNinios = new JTable();
		scrollPane_ListarNinios.setColumnHeaderView(table_ListarNinios);
		crearTablaNinios();
		table_ListarNinios.setModel(modelNinios);

		scrollPane_ListarNinios.setViewportView(table_ListarNinios);

		JButton btnListarNios = new JButton("Listar Ni\u00F1os");
		btnListarNios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					List<voNinio> ninios = controller.listarNinosCP();
					if (ninios == null || ninios.isEmpty() ) {// || ninios.size() == 0) {
						NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaNoHayNinios);
					} else {
						cargaNiniosTabla(ninios);
						NuevaVentanaMsgOut(TipoMensaje.INFO, msg.infoGraficaListadoEnPantalla);
					}

				} catch (IOException e1) {
					NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
				} catch (PersistenciaException e1) {
					NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
				} catch (LogicaException e1) {
					NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
				}
			}
		});
		btnListarNios.setBounds(20, 11, 160, 23);
		panelListarNinios.add(btnListarNios);

		JPanel panelListarJuguetes = new JPanel();
		tabbedPane.addTab("Listar Juguetes", null, panelListarJuguetes, null);
		panelListarJuguetes.setLayout(null);

		JScrollPane scrollPaneListarJuguetes = new JScrollPane();
		scrollPaneListarJuguetes.setBounds(10, 63, 483, 198);
		panelListarJuguetes.add(scrollPaneListarJuguetes);

		crearTablaJuguetes();

		tableListarJuguetes = new JTable();
		scrollPaneListarJuguetes.setColumnHeaderView(tableListarJuguetes);
		tableListarJuguetes.setModel(modelJuguetes);// para setear las tablas
		scrollPaneListarJuguetes.setViewportView(tableListarJuguetes);

		JButton btnListarJuguetes = new JButton("Listar Juguetes");
		btnListarJuguetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String cedu = textFieldCedulaListarJuguetes.getText();

				if ((NoEsVacio(cedu))) {
					if (EsNumero(cedu)) {
						int cedula = Integer.parseInt(cedu);
						try {
							controller = new controladorPrincipal();
							List<voJuguetes> vJ = controller.listarJuguetesCP(cedula);

							if (vJ != null || !vJ.isEmpty() || vJ.size() > 0) {
								cargaJuguetesenTabla(vJ);
								NuevaVentanaMsgOut(TipoMensaje.INFO, msg.infoGraficaListadoEnPantalla);
							} else {
								NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaNoHayJuguetes);
							}
						} catch (IOException e1) {
							NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
						} catch (LogicaException e1) {
							NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
						} catch (PersistenciaException e1) {
							NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
						} catch (NotBoundException e1) {
							NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
						}
					} else {
						NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaSoloNumeros);
					}

				} else {
					NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaCamposVacios);
				}
			}
		});
		btnListarJuguetes.setBounds(275, 29, 157, 23);
		panelListarJuguetes.add(btnListarJuguetes);

		JLabel lblCedulajuguetes = new JLabel("Cedula");
		lblCedulajuguetes.setBounds(32, 33, 63, 14);
		panelListarJuguetes.add(lblCedulajuguetes);

		textFieldCedulaListarJuguetes = new JTextField();
		textFieldCedulaListarJuguetes.setBounds(105, 30, 86, 20);
		panelListarJuguetes.add(textFieldCedulaListarJuguetes);
		textFieldCedulaListarJuguetes.setColumns(10);

		JPanel panelDescripcionJuguetes = new JPanel();
		tabbedPane.addTab("Descripcion de Juguete", null, panelDescripcionJuguetes, null);
		panelDescripcionJuguetes.setLayout(null);

		JLabel lblNumeroDescripcionJuguete = new JLabel("N\u00FAmero:");
		lblNumeroDescripcionJuguete.setBounds(27, 59, 65, 14);
		panelDescripcionJuguetes.add(lblNumeroDescripcionJuguete);

		JLabel lblCedulaDescripcionJuguete = new JLabel("Cedula:");
		lblCedulaDescripcionJuguete.setBounds(27, 31, 65, 14);
		panelDescripcionJuguetes.add(lblCedulaDescripcionJuguete);

		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(27, 135, 99, 14);
		panelDescripcionJuguetes.add(lblDescripcion);

		textFieldNumeroDescripcionJuguete = new JTextField();
		textFieldNumeroDescripcionJuguete.setBounds(142, 56, 86, 20);
		panelDescripcionJuguetes.add(textFieldNumeroDescripcionJuguete);
		textFieldNumeroDescripcionJuguete.setColumns(10);

		textFieldCedulaDescripcionJuguete = new JTextField();
		textFieldCedulaDescripcionJuguete.setBounds(142, 28, 86, 20);
		panelDescripcionJuguetes.add(textFieldCedulaDescripcionJuguete);
		textFieldCedulaDescripcionJuguete.setColumns(10);

		JButton btnBuscarDescripcin = new JButton("Buscar Descripcion");
		btnBuscarDescripcin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String num = textFieldNumeroDescripcionJuguete.getText();
				String ced = textFieldCedulaDescripcionJuguete.getText();
				if ((NoEsVacio(num)) && (NoEsVacio(ced))) {
					if (EsNumero(num) && EsNumero(ced)) {
						int cedula, numero;
						numero = Integer.parseInt(num);
						cedula = Integer.parseInt(ced);
						try {
							String descripcion = controller.darDescripcionCP(cedula, numero);
							cargarTablaDescripcion(descripcion);
							NuevaVentanaMsgOut(TipoMensaje.INFO, msg.infoGraficaDescEnPantalla);
						} catch (IOException e1) {
							NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
						} catch (LogicaException e1) {
							NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
						} catch (PersistenciaException e1) {
							NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
						}
					} else {
						NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaSoloNumeros);
					}
				} else {
					NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaCamposVacios);
				}
			}
		});
		btnBuscarDescripcin.setBounds(82, 91, 146, 23);
		panelDescripcionJuguetes.add(btnBuscarDescripcin);

		JScrollPane scrollPaneDescripcionJuguete = new JScrollPane();
		scrollPaneDescripcionJuguete.setBounds(27, 160, 452, 85);
		panelDescripcionJuguetes.add(scrollPaneDescripcionJuguete);

		crearTablaDescripcion();// creo tabla

		tableDescripcionJuguete = new JTable();
		scrollPaneDescripcionJuguete.setColumnHeaderView(tableDescripcionJuguete);

		tableDescripcionJuguete.setModel(modelDescripcion);// para setear las tablas

		scrollPaneDescripcionJuguete.setViewportView(tableDescripcionJuguete);

		JPanel panel_BorrarNinio = new JPanel();
		tabbedPane.addTab("Borrar Niño", null, panel_BorrarNinio, null);
		panel_BorrarNinio.setLayout(null);

		JLabel lblCedulaBorrarNinio = new JLabel("Cedula");
		lblCedulaBorrarNinio.setBounds(52, 79, 46, 14);
		panel_BorrarNinio.add(lblCedulaBorrarNinio);

		textFieldCedulaBorrarNinio = new JTextField();
		textFieldCedulaBorrarNinio.setBounds(147, 76, 150, 20);
		panel_BorrarNinio.add(textFieldCedulaBorrarNinio);
		textFieldCedulaBorrarNinio.setColumns(10);

		JButton btnBorrarninio = new JButton("Borrar Ni\u00F1o");
		btnBorrarninio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String cedu = textFieldCedulaBorrarNinio.getText();
				if ((NoEsVacio(cedu))) {
					if (EsNumero(cedu)) {
						int cedula = Integer.parseInt(cedu);
						try {
							controller.borrarNinioJuguetesCP(cedula);
							NuevaVentanaMsgOut(TipoMensaje.SUCCESS, msg.infoNinioEliminado);

						} catch (IOException e1) {
							NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
						} catch (LogicaException e1) {
							NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
						} catch (PersistenciaException e1) {
							NuevaVentanaMsgOut(TipoMensaje.ERROR, e1.getMessage());
						}
					} else {
						NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaSoloNumeros);
					}
				} else {
					NuevaVentanaMsgOut(TipoMensaje.ERROR, msg.errorGraficaCamposVacios);
				}
			}
		});
		btnBorrarninio.setBounds(147, 144, 150, 23);
		panel_BorrarNinio.add(btnBorrarninio);

		textFieldMsgsOut = new TextField();
		textFieldMsgsOut.setBackground(new Color(240, 248, 255));
		textFieldMsgsOut.setForeground(Color.BLACK);
		textFieldMsgsOut.setEditable(false);
		textFieldMsgsOut.setText("...los mensajes se desplegarán aquí... ");
		textFieldMsgsOut.setBounds(5, 330, 508, 50);
		contentPane.add(textFieldMsgsOut);
	}

	private Boolean EsNumero(String texto) {
		Boolean es = false;
		int index = 0;
		while (index < texto.length() && (!es)) {
			char aux = texto.charAt(index);

			if (aux >= '0' && aux <= '9') {
				es = true;
			}
			index++;
		}
		return es;
	}

	private Boolean NoEsVacio(String texto) {
		Boolean es = false;
		if (!("".equals(texto))) {
			es = true;
		}
		return es;
	}

	public void NuevaVentanaMsgOut(TipoMensaje in_tipoMsg, String Texto) {
		textFieldMsgsOut.setText(Texto);
		switch (in_tipoMsg) {
		case INFO:
			textFieldMsgsOut.setForeground(new Color(0, 0, 128));
			break;
		case SUCCESS:
			textFieldMsgsOut.setForeground(new Color(34, 139, 34));
			break;
		case ERROR:
			textFieldMsgsOut.setForeground(Color.RED);
			break;
		case WARNING:
			textFieldMsgsOut.setForeground(new Color(255, 215, 0));
			break;
		default:
			break;
		}
//		JOptionPane.showMessageDialog(frame, Texto);
	}

	private void crearTablaDescripcion() {
		modelDescripcion.addColumn("Descipcion");
	}

	private void cargarTablaDescripcion(String Descp) {

		modelDescripcion.setRowCount(0);
		Object Ob[] = null;
		modelDescripcion.addRow(Ob);
		modelDescripcion.setValueAt(Descp, 0, 0);
	}

	private void crearTablaNinios() {
		modelNinios.addColumn("Cedula");
		modelNinios.addColumn("Nombre");
		modelNinios.addColumn("Apellido");
	}

	private void cargarTablaNinios(voNinio VOP, int a) {
		modelNinios.setRowCount(a);
		Object Ob[] = null;
		modelNinios.addRow(Ob);
		modelNinios.setValueAt(VOP.getCedula(), a, 0);
		modelNinios.setValueAt(VOP.getNombre(), a, 1);
		modelNinios.setValueAt(VOP.getApellido(), a, 2);

	}

	private void cargaNiniosTabla(List<voNinio> VOP) {
		for (int i = 0; i < VOP.size(); i++)
			cargarTablaNinios(VOP.get(i), i);

	}

	private void crearTablaJuguetes() {
		modelJuguetes.addColumn("Numero");
		modelJuguetes.addColumn("Descripcion");
		modelJuguetes.addColumn("CI de Ninios");
	}

	private void cargarTablaJuguetes(voJuguetes VOP, int a) {
		modelJuguetes.setRowCount(a);
		Object Ob[] = null;
		modelJuguetes.addRow(Ob);
		modelJuguetes.setValueAt(VOP.getNumero(), a, 0);
		modelJuguetes.setValueAt(VOP.getDescripcion(), a, 1);
		modelJuguetes.setValueAt(VOP.getCedulaNinio(), a, 2);
	}

	private void cargaJuguetesenTabla(List<voJuguetes> VOP) {

		for (int i = 0; i < VOP.size(); i++) {
			cargarTablaJuguetes(VOP.get(i), i);
		}
	}

	private boolean esCIValida(String ci) {
		boolean es = false;
		if (isScapeValidate(ci)) {
			es = true;
		} else {
			if (ci.length() != 8) {
				es = false;
			} else {
				int digVerificador = Integer.parseInt((ci.charAt(ci.length() - 1)) + "");
				int[] factores = new int[] { 2, 9, 8, 7, 6, 3, 4 };
				int suma = 0;
				for (int i = 0; i < 7; i++) {
					suma += Integer.parseInt(ci.charAt(i) + "") * factores[i];
				}
				int resto = suma % 10;
				int checkdigit = 10 - resto;

				if (checkdigit == digVerificador) {
					es = true;
				} else {
					es = false;
				}
			}
		}
		return es;
	}

	private boolean isScapeValidate(String ci) {
		boolean isScapeValidate = true;
		String temp = ci;
		if (temp.length() > 9) {
			isScapeValidate = false;
		}
		if (temp.length() >= 4 && temp.length() < 10) {
			isScapeValidate = false;
			int[] newDigits = new int[temp.length()];
			for (int i = 0; i < temp.length(); i++) {
				newDigits[i] = temp.charAt(i) - '0';
				if (i == 3)
					break;
			}
			if ((newDigits[0] == 9 && newDigits[1] == 9 && newDigits[2] == 9)
					|| (newDigits[0] == 1 && newDigits[1] == 1 && newDigits[2] == 1)
					|| (newDigits[0] == 1 && newDigits[1] == 2 && newDigits[2] == 3)) {
				isScapeValidate = true;
			}
		}
		return isScapeValidate;
	}
}
