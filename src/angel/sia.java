package angel;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;

class CajeroATM extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    // Estado
    private String pinIngresado = "";
    private String cuentaSeleccionada = "";
    private String montoSeleccionado = "";
    private String numeroTarjeta = ""; // <-- Agrega esto junto a tus otras variables de estado
    private String[] cuentas = {
            "Cuenta Ahorros Soles ****1234",
            "Cuenta Sueldo Soles ****5678",
            "Cuenta Dólares ****9012"
    };
    private String[] montos = {"S/ 100", "S/ 200", "S/ 300", "S/ 400"};
    private double saldoDisponible = 1200.00; // saldo inicial simulado

    public CajeroATM() {
        setTitle("Cajero Perú");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        setUndecorated(true); // Sin bordes de ventana

        GradientPanel fondo = new GradientPanel();
        fondo.setLayout(new BorderLayout());
        fondo.add(mainPanel, BorderLayout.CENTER);

        setContentPane(fondo);

        mainPanel.add(bienvenidaPanel(), "Bienvenida");
        mainPanel.add(pinPanel(), "PIN");
        mainPanel.add(menuPanel(), "Menu");
        mainPanel.add(seleccionCuentaPanel(), "Cuenta");
        mainPanel.add(seleccionMontoPanel(), "Monto");
        mainPanel.add(confirmacionPanel(), "Confirmacion");
        mainPanel.add(procesandoPanel(), "Procesando");
        mainPanel.add(retireDineroPanel(), "Retire");
        mainPanel.add(comprobantePanel(), "Comprobante");
        mainPanel.add(dniPanel(), "DNI");
        mainPanel.add(pinRetiroPanel(), "PinRetiro");

        cardLayout.show(mainPanel, "Bienvenida");
    }

    private JPanel headerPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(245, 245, 245));
        JPanel idiomasPanel = new JPanel();
        idiomasPanel.setOpaque(false);
        idiomasPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        String[] idiomas = {"ES", "EN", "QU"};
        for (String idioma : idiomas) {
            JLabel lbl = new JLabel(idioma);
            lbl.setFont(new Font("Inter", Font.BOLD, 15));
            lbl.setForeground(new Color(156, 163, 175));
            lbl.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            idiomasPanel.add(lbl);
        }
        header.add(idiomasPanel, BorderLayout.EAST);
        return header;
    }

    private JPanel footerPanel(JButton... botones) {
        JPanel footer = new JPanel();
        footer.setBackground(new Color(245, 245, 245));
        footer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        for (JButton b : botones) {
            b.setFocusPainted(false);
            b.setFont(new Font("Inter", Font.BOLD, 16));
            b.setBorder(BorderFactory.createEmptyBorder(12, 32, 12, 32));
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            b.setBorderPainted(false);
            b.setOpaque(true);
            footer.add(b);
        }
        return footer;
    }

    private JPanel bienvenidaPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(244, 246, 248));
        panel.add(headerPanel(), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(new Color(249, 250, 251));
        center.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("BIENVENIDO");
        titulo.setFont(new Font("Inter", Font.BOLD, 32));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0, 105, 217));
        center.add(titulo);

        center.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel texto = new JLabel("Para comenzar, inserte su tarjeta o acérquela al lector sin contacto");
        texto.setFont(new Font("Inter", Font.PLAIN, 18));
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(texto);

        center.add(Box.createRigidArea(new Dimension(0, 30)));

        // Panel para acercar tarjeta con botón invisible encima
        JPanel acercarPanel = new JPanel(null);
        acercarPanel.setOpaque(false);
        acercarPanel.setMaximumSize(new Dimension(200, 70));
        acercarPanel.setPreferredSize(new Dimension(200, 70));

        JLabel iconoContactless = new JLabel("\uD83D\uDCE1", SwingConstants.CENTER); // wifi_tethering unicode
        iconoContactless.setFont(new Font("Segoe UI Emoji", Font.BOLD, 46));
        iconoContactless.setForeground(new Color(0, 105, 217));
        iconoContactless.setBounds(65, 0, 70, 46);

        JLabel acercar = new JLabel("Acercar tarjeta", SwingConstants.CENTER);
        acercar.setFont(new Font("Inter", Font.BOLD, 16));
        acercar.setForeground(new Color(0, 105, 217));
        acercar.setBounds(40, 46, 120, 24);

        JButton btnAcercar = new JButton();
        btnAcercar.setBounds(40, 0, 120, 70);
        btnAcercar.setOpaque(false);
        btnAcercar.setContentAreaFilled(false);
        btnAcercar.setBorderPainted(false);
        btnAcercar.setFocusPainted(false);
        btnAcercar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAcercar.addActionListener(e -> cardLayout.show(mainPanel, "DNI"));

        acercarPanel.add(iconoContactless);
        acercarPanel.add(acercar);
        acercarPanel.add(btnAcercar);

        center.add(acercarPanel);

        center.add(Box.createRigidArea(new Dimension(0, 40)));

        // Panel para insertar tarjeta con botón invisible encima
        JPanel insertarPanel = new JPanel(null);
        insertarPanel.setOpaque(false);
        insertarPanel.setMaximumSize(new Dimension(200, 70));
        insertarPanel.setPreferredSize(new Dimension(200, 70));

        JPanel rect = new JPanel();
        rect.setBackground(new Color(249, 250, 251));
        rect.setBorder(BorderFactory.createLineBorder(new Color(0, 105, 217), 2, true));
        rect.setBounds(65, 0, 70, 44);

        JLabel insertarTxt = new JLabel("Inserte tarjeta", SwingConstants.CENTER);
        insertarTxt.setFont(new Font("Inter", Font.BOLD, 16));
        insertarTxt.setForeground(new Color(0, 105, 217));
        insertarTxt.setBounds(40, 44, 120, 24);

        JButton btnInsertar = new JButton();
        btnInsertar.setBounds(40, 0, 120, 70);
        btnInsertar.setOpaque(false);
        btnInsertar.setContentAreaFilled(false);
        btnInsertar.setBorderPainted(false);
        btnInsertar.setFocusPainted(false);
        btnInsertar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnInsertar.addActionListener(e -> {
            // Simulación automática de tarjeta válida
            String tarjeta = "1234567812345678";
            JOptionPane.showMessageDialog(this, "Tarjeta insertada: " + tarjeta, "Tarjeta Detectada", JOptionPane.INFORMATION_MESSAGE);
            numeroTarjeta = "1234567812345678"; // O el número real leído
            cardLayout.show(mainPanel, "DNI");
        });

        insertarPanel.add(rect);
        insertarPanel.add(insertarTxt);
        insertarPanel.add(btnInsertar);

        center.add(insertarPanel);

        panel.add(center, BorderLayout.CENTER);

        JButton cancelar = new JButton("Cancelar");
        cancelar.setBackground(new Color(220, 38, 38));
        cancelar.setForeground(Color.WHITE);
        cancelar.addActionListener(e -> System.exit(0));

        JButton ayuda = new JButton("Ayuda");
        ayuda.setBackground(new Color(0, 105, 217));
        ayuda.setForeground(Color.WHITE);
        ayuda.addActionListener(e -> JOptionPane.showMessageDialog(this, "Inserte o acerque su tarjeta para continuar.", "Ayuda", JOptionPane.INFORMATION_MESSAGE));

        panel.add(footerPanel(cancelar, ayuda), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel pinPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(244, 246, 248));
        panel.add(headerPanel(), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(new Color(249, 250, 251));
        center.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("INGRESE SU PIN");
        titulo.setFont(new Font("Inter", Font.BOLD, 32));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0, 105, 217));
        center.add(titulo);

        center.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel texto = new JLabel("Por seguridad, cubra el teclado al ingresar su PIN");
        texto.setFont(new Font("Inter", Font.PLAIN, 18));
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(texto);

        center.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel pinLabel = new JLabel("Ingrese su clave PIN para confirmar");
        pinLabel.setFont(new Font("Inter", Font.PLAIN, 18));
        pinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(pinLabel);

        JPasswordField pinField = new JPasswordField(4);
        pinField.setFont(new Font("Inter", Font.BOLD, 28));
        pinField.setMaximumSize(new Dimension(120, 40));
        pinField.setHorizontalAlignment(JTextField.CENTER);
        center.add(pinField);

        center.add(Box.createRigidArea(new Dimension(0, 16)));

        JButton cancelar = new JButton("Cancelar");
        cancelar.setBackground(new Color(220, 38, 38));
        cancelar.setForeground(Color.WHITE);
        cancelar.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        JButton confirmar = new JButton("Confirmar");
        confirmar.setBackground(new Color(0, 204, 102));
        confirmar.setForeground(Color.WHITE);
        confirmar.addActionListener(e -> {
            String pin = new String(pinField.getPassword());
            if (pin.matches("\\d{4}")) {
                cardLayout.show(mainPanel, "Procesando");
                new Timer(2000, evt -> {
                    ((Timer)evt.getSource()).stop();
                    cardLayout.show(mainPanel, "Retire");
                }).start();
            } else {
                JOptionPane.showMessageDialog(this, "El PIN debe tener 4 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(center, BorderLayout.CENTER);
        panel.add(footerPanel(cancelar, confirmar), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel menuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(244, 246, 248));
        panel.add(headerPanel(), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(new Color(249, 250, 251));
        center.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("SELECCIONE UNA OPERACIÓN");
        titulo.setFont(new Font("Inter", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0, 105, 217));
        center.add(titulo);

        center.add(Box.createRigidArea(new Dimension(0, 20)));

        String[] opciones = {
                "1. Retiro de efectivo",
                "2. Consulta de saldo",
                "3. Transferencias",
                "4. Pago de servicios",
                "5. Otras operaciones"
        };
        ButtonGroup grupo = new ButtonGroup();
        JRadioButton[] radios = new JRadioButton[opciones.length];
        for (int i = 0; i < opciones.length; i++) {
            radios[i] = new JRadioButton(opciones[i]);
            radios[i].setFont(new Font("Inter", Font.BOLD, 18));
            radios[i].setBackground(new Color(249, 250, 251));
            radios[i].setForeground(new Color(55, 65, 81));
            radios[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(209, 213, 219), 2, true),
                    BorderFactory.createEmptyBorder(10, 16, 10, 16)
            ));
            grupo.add(radios[i]);
            center.add(Box.createRigidArea(new Dimension(0, 8)));
            center.add(radios[i]);
        }
        radios[0].setSelected(true);

        JButton cancelar = new JButton("Cancelar");
        cancelar.setBackground(new Color(220, 38, 38));
        cancelar.setForeground(Color.WHITE);
        cancelar.addActionListener(e -> cardLayout.show(mainPanel, "Bienvenida"));

        JButton aceptar = new JButton("Aceptar");
        aceptar.setBackground(new Color(0, 204, 102));
        aceptar.setForeground(Color.WHITE);
        aceptar.addActionListener(e -> {
            if (radios[0].isSelected()) {
                cardLayout.show(mainPanel, "Cuenta");
            } else {
                JOptionPane.showMessageDialog(this, "Solo está habilitado el Retiro de efectivo para esta demo.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panel.add(center, BorderLayout.CENTER);
        panel.add(footerPanel(cancelar, aceptar), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel seleccionCuentaPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(244, 246, 248));
        panel.add(headerPanel(), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(new Color(249, 250, 251));
        center.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("SELECCIONE SU CUENTA");
        titulo.setFont(new Font("Inter", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0, 105, 217));
        center.add(titulo);

        center.add(Box.createRigidArea(new Dimension(0, 20)));

        ButtonGroup grupo = new ButtonGroup();
        JRadioButton[] radios = new JRadioButton[cuentas.length];
        for (int i = 0; i < cuentas.length; i++) {
            radios[i] = new JRadioButton((i+1) + ". " + cuentas[i]);
            radios[i].setFont(new Font("Inter", Font.BOLD, 18));
            radios[i].setBackground(new Color(249, 250, 251));
            radios[i].setForeground(new Color(55, 65, 81));
            radios[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(209, 213, 219), 2, true),
                    BorderFactory.createEmptyBorder(10, 16, 10, 16)
            ));
            grupo.add(radios[i]);
            center.add(Box.createRigidArea(new Dimension(0, 8)));
            center.add(radios[i]);
        }
        radios[0].setSelected(true);

        JButton cancelar = new JButton("Cancelar");
        cancelar.setBackground(new Color(220, 38, 38));
        cancelar.setForeground(Color.WHITE);
        cancelar.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        JButton aceptar = new JButton("Aceptar");
        aceptar.setBackground(new Color(0, 204, 102));
        aceptar.setForeground(Color.WHITE);
        aceptar.addActionListener(e -> {
            for (int i = 0; i < radios.length; i++) {
                if (radios[i].isSelected()) {
                    cuentaSeleccionada = cuentas[i];
                    cardLayout.show(mainPanel, "Monto");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Seleccione una cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
        });

        panel.add(center, BorderLayout.CENTER);
        panel.add(footerPanel(cancelar, aceptar), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel seleccionMontoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(244, 246, 248));
        panel.add(headerPanel(), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(new Color(249, 250, 251));
        center.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("SELECCIONE EL MONTO");
        titulo.setFont(new Font("Inter", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0, 105, 217));
        center.add(titulo);

        JLabel subtitulo = new JLabel("O ingrese un monto personalizado");
        subtitulo.setFont(new Font("Inter", Font.PLAIN, 16));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(subtitulo);

        center.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel montosPanel = new JPanel();
        montosPanel.setBackground(new Color(249, 250, 251));
        for (String m : montos) {
            JButton btn = new JButton(m);
            btn.setFont(new Font("Inter", Font.BOLD, 18));
            btn.setBackground(new Color(0, 105, 217));
            btn.setForeground(Color.WHITE);
            btn.setPreferredSize(new Dimension(100, 44));
            btn.setFocusPainted(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            btn.addActionListener(e -> {
                montoSeleccionado = m;
                cardLayout.show(mainPanel, "PinRetiro");
            });
            montosPanel.add(btn);
        }
        center.add(montosPanel);

        center.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel personalizadoPanel = new JPanel();
        personalizadoPanel.setBackground(new Color(249, 250, 251));
        JLabel label = new JLabel("Ingrese monto: ");
        label.setFont(new Font("Inter", Font.PLAIN, 16));
        JTextField campoMonto = new JTextField(10);
        campoMonto.setFont(new Font("Inter", Font.PLAIN, 16));
        personalizadoPanel.add(label);
        personalizadoPanel.add(campoMonto);
        center.add(personalizadoPanel);

        JButton cancelar = new JButton("Cancelar");
        cancelar.setBackground(new Color(220, 38, 38));
        cancelar.setForeground(Color.WHITE);
        cancelar.addActionListener(e -> cardLayout.show(mainPanel, "Cuenta"));

        JButton continuar = new JButton("Continuar");
        continuar.setBackground(new Color(0, 204, 102));
        continuar.setForeground(Color.WHITE);
        continuar.addActionListener(e -> {
            String texto = campoMonto.getText().trim();
            if (!texto.isEmpty()) {
                try {
                    double valor = Double.parseDouble(texto);
                    if (valor >= 10 && valor <= 2000 && valor % 10 == 0) {
                        montoSeleccionado = "S/ " + String.format("%.2f", valor);
                        cardLayout.show(mainPanel, "PinRetiro");
                    } else {
                        JOptionPane.showMessageDialog(this, "Monto mínimo S/ 10, máximo S/ 2000", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione o ingrese un monto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(center, BorderLayout.CENTER);
        panel.add(footerPanel(cancelar, continuar), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel confirmacionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(244, 246, 248));
        panel.add(headerPanel(), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(new Color(249, 250, 251));
        center.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("CONFIRME SU RETIRO");
        titulo.setFont(new Font("Inter", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0, 105, 217));
        center.add(titulo);

        center.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel monto = new JLabel();
        monto.setFont(new Font("Inter", Font.PLAIN, 20));
        monto.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(monto);

        JLabel cuenta = new JLabel();
        cuenta.setFont(new Font("Inter", Font.PLAIN, 18));
        cuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(cuenta);

        JLabel comision = new JLabel("Comisión: S/ 0.00");
        comision.setFont(new Font("Inter", Font.PLAIN, 18));
        comision.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(comision);

        JLabel total = new JLabel();
        total.setFont(new Font("Inter", Font.BOLD, 20));
        total.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(total);

        center.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel pregunta = new JLabel("¿Desea continuar con la operación?");
        pregunta.setFont(new Font("Inter", Font.PLAIN, 18));
        pregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(pregunta);

        JButton no = new JButton("No");
        no.setBackground(new Color(220, 38, 38));
        no.setForeground(Color.WHITE);
        no.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        JButton si = new JButton("Sí");
        si.setBackground(new Color(0, 204, 102));
        si.setForeground(Color.WHITE);
        si.addActionListener(e -> {
            cardLayout.show(mainPanel, "Procesando");
            // Simula procesamiento
            new Timer(2000, evt -> {
                ((Timer)evt.getSource()).stop();
                cardLayout.show(mainPanel, "Retire");
            }).start();
        });

        panel.add(center, BorderLayout.CENTER);
        panel.add(footerPanel(no, si), BorderLayout.SOUTH);

        // Actualiza los textos cada vez que se muestre este panel
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                monto.setText("Monto a retirar: " + (montoSeleccionado.isEmpty() ? "S/ 0.00" : montoSeleccionado));
                cuenta.setText("Cuenta: " + (cuentaSeleccionada.isEmpty() ? "No seleccionada" : cuentaSeleccionada));
                total.setText("Total: " + (montoSeleccionado.isEmpty() ? "S/ 0.00" : montoSeleccionado));
            }
        });

        return panel;
    }

    private JPanel procesandoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(244, 246, 248));
        panel.add(headerPanel(), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(new Color(249, 250, 251));
        center.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(30, 40, 30, 30)
        ));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("PROCESANDO SU OPERACIÓN");
        titulo.setFont(new Font("Inter", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0, 105, 217));
        center.add(titulo);

        center.add(Box.createRigidArea(new Dimension(0, 100)));

        JLabel reloj = new JLabel("\u23F3"); // Unicode hourglass_top
        reloj.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 58));
        reloj.setAlignmentX(Component.CENTER_ALIGNMENT);
        reloj.setForeground(new Color(0, 105, 217));
        center.add(reloj);

        JLabel espere = new JLabel("Por favor espere...");
        espere.setFont(new Font("Inter", Font.BOLD, 20));
        espere.setAlignmentX(Component.CENTER_ALIGNMENT);
        espere.setForeground(new Color(0, 74, 153));
        center.add(espere);

        JButton cancelar = new JButton("Cancelar");
        cancelar.setBackground(new Color(220, 38, 38));
        cancelar.setForeground(Color.WHITE);
        cancelar.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        panel.add(center, BorderLayout.CENTER);
        panel.add(footerPanel(cancelar), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel retireDineroPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(244, 246, 248));
        panel.add(headerPanel(), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(new Color(249, 250, 251));
        center.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("RETIRE SU DINERO");
        titulo.setFont(new Font("Inter", Font.BOLD, 32));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0, 105, 217));
        center.add(titulo);

        center.add(Box.createRigidArea(new Dimension(0, 30)));

        // Icono grande de dinero (puedes cambiar el icono si quieres)
        JLabel icono = new JLabel("\uD83D\uDCB0"); // Unicode billete
        icono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 56));
        icono.setAlignmentX(Component.CENTER_ALIGNMENT);
        icono.setForeground(new Color(0, 105, 217));
        center.add(icono);

        center.add(Box.createRigidArea(new Dimension(0, 20)));

        // Mensaje de monto a retirar
        JLabel mensaje = new JLabel();
        mensaje.setFont(new Font("Inter", Font.BOLD, 22));
        mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        mensaje.setForeground(new Color(55, 65, 81));
        center.add(mensaje);

        center.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel pregunta = new JLabel("¿Desea un comprobante?");
        pregunta.setFont(new Font("Inter", Font.PLAIN, 18));
        pregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
        pregunta.setForeground(new Color(55, 65, 81));
        center.add(pregunta);

        center.add(Box.createRigidArea(new Dimension(0, 16)));

        JPanel botones = new JPanel();
        botones.setBackground(new Color(249, 250, 251));
        botones.setAlignmentX(Component.CENTER_ALIGNMENT);
        botones.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));

        JButton si = new JButton("Sí");
        si.setFont(new Font("Inter", Font.BOLD, 18));
        si.setBackground(new Color(0, 105, 217));
        si.setForeground(Color.WHITE);
        si.setFocusPainted(false);
        si.setBorder(BorderFactory.createEmptyBorder(14, 40, 14, 40));
        si.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        si.addActionListener(e -> cardLayout.show(mainPanel, "Comprobante"));

        JButton no = new JButton("No");
        no.setFont(new Font("Inter", Font.BOLD, 18));
        no.setBackground(new Color(220, 38, 38));
        no.setForeground(Color.WHITE);
        no.setFocusPainted(false);
        no.setBorder(BorderFactory.createEmptyBorder(14, 40, 14, 40));
        no.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        no.addActionListener(e -> cardLayout.show(mainPanel, "Bienvenida"));

        botones.add(si);
        botones.add(no);
        center.add(botones);

        panel.add(center, BorderLayout.CENTER);

        JButton finalizar = new JButton("Finalizar");
        finalizar.setFont(new Font("Inter", Font.BOLD, 18));
        finalizar.setBackground(new Color(0, 105, 217));
        finalizar.setForeground(Color.WHITE);
        finalizar.setFocusPainted(false);
        finalizar.setBorder(BorderFactory.createEmptyBorder(14, 60, 14, 60));
        finalizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        finalizar.addActionListener(e -> cardLayout.show(mainPanel, "Bienvenida"));

        panel.add(footerPanel(finalizar), BorderLayout.SOUTH);

        // ACTUALIZA EL MONTO CADA VEZ QUE SE MUESTRA EL PANEL
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                mensaje.setText("Por favor retire " +
                        (montoSeleccionado.isEmpty() ? "S/ 0.00" : montoSeleccionado) +
                        " del dispensador");
            }
        });

        return panel;
    }

    private JPanel comprobantePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(244, 246, 248));
        panel.add(headerPanel(), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(new Color(249, 250, 251));
        center.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("COMPROBANTE DE OPERACIÓN");
        titulo.setFont(new Font("Inter", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0, 105, 217));
        center.add(titulo);

        center.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel detalle = new JPanel();
        detalle.setBackground(new Color(249, 250, 251));
        detalle.setLayout(new BoxLayout(detalle, BoxLayout.Y_AXIS));
        detalle.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(16, 20, 16, 20)
        ));

        JLabel cajero = new JLabel("Cajero Automático");
        cajero.setFont(new Font("Inter", Font.PLAIN, 18));
        cajero.setAlignmentX(Component.CENTER_ALIGNMENT);
        detalle.add(cajero);

        JLabel fecha = new JLabel(); // Cambia esto para poder actualizarlo dinámicamente
        fecha.setFont(new Font("Inter", Font.PLAIN, 16));
        fecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        detalle.add(fecha);

        JLabel nroOp = new JLabel("Nro. Operación: 123456789");
        nroOp.setFont(new Font("Inter", Font.PLAIN, 16));
        nroOp.setAlignmentX(Component.CENTER_ALIGNMENT);
        detalle.add(nroOp);

        JLabel tarjeta = new JLabel("Tarjeta: •••• 1234");
        tarjeta.setFont(new Font("Inter", Font.PLAIN, 16));
        tarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);
        detalle.add(tarjeta);

        JLabel retiro = new JLabel();
        retiro.setFont(new Font("Inter", Font.PLAIN, 16));
        retiro.setAlignmentX(Component.CENTER_ALIGNMENT);
        detalle.add(retiro);

        JLabel saldo = new JLabel("Saldo disponible: S/ " + String.format("%,.2f", saldoDisponible));
        saldo.setFont(new Font("Inter", Font.PLAIN, 16));
        saldo.setAlignmentX(Component.CENTER_ALIGNMENT);
        detalle.add(saldo);

        center.add(detalle);

        JButton finalizar = new JButton("Finalizar");
        finalizar.setBackground(new Color(0, 105, 217));
        finalizar.setForeground(Color.WHITE);
        finalizar.setFont(new Font("Inter", Font.BOLD, 18));
        finalizar.setFocusPainted(false);
        finalizar.setBorder(BorderFactory.createEmptyBorder(14, 60, 14, 60));
        finalizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        finalizar.addActionListener(e -> cardLayout.show(mainPanel, "Bienvenida"));

        panel.add(center, BorderLayout.CENTER);
        panel.add(footerPanel(finalizar), BorderLayout.SOUTH);

        // ACTUALIZA EL MONTO Y LA FECHA CADA VEZ QUE SE MUESTRA EL PANEL
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                retiro.setText("Retiro: " + (montoSeleccionado.isEmpty() ? "S/ 0.00" : montoSeleccionado));
                java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
                java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                fecha.setText("Fecha: " + ahora.format(formato));
                saldo.setText("Saldo disponible: S/ " + String.format("%,.2f", saldoDisponible)); // <-- aquí
            }
        });

        return panel;
    }

    private JPanel dniPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(244, 246, 248));
        panel.add(headerPanel(), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(new Color(249, 250, 251));
        center.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("INGRESE SU DNI");
        titulo.setFont(new Font("Inter", Font.BOLD, 32));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0, 105, 217));
        center.add(titulo);

        center.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel texto = new JLabel("Ingrese su número de DNI para continuar");
        texto.setFont(new Font("Inter", Font.PLAIN, 18));
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(texto);

        center.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel dniLabel = new JLabel("");
        dniLabel.setFont(new Font("Inter", Font.BOLD, 32));
        dniLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dniLabel.setHorizontalAlignment(SwingConstants.CENTER);
        center.add(dniLabel);

        center.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel teclado = new JPanel(new GridLayout(4, 3, 12, 12));
        String[] teclas = {"1","2","3","4","5","6","7","8","9","","0","⌫"};
        final StringBuilder dniBuilder = new StringBuilder();
        for (String t : teclas) {
            JButton btn = new JButton(t);
            btn.setFont(new Font("Inter", Font.BOLD, 24));
            btn.setFocusPainted(false);
            btn.setBackground(t.equals("⌫") ? new Color(220, 38, 38) : t.equals("") ? new Color(249, 250, 251) : new Color(0, 105, 217));
            btn.setForeground(t.equals("⌫") ? Color.WHITE : t.equals("") ? new Color(249, 250, 251) : Color.WHITE);
            btn.setEnabled(!t.equals(""));
            btn.setBorder(BorderFactory.createLineBorder(new Color(209, 213, 219), 2, true));
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (t.equals("⌫")) {
                btn.addActionListener(e -> {
                    if (dniBuilder.length() > 0) {
                        dniBuilder.deleteCharAt(dniBuilder.length() - 1);
                        dniLabel.setText(dniBuilder.toString());
                    }
                });
            } else if (!t.equals("")) {
                btn.addActionListener(e -> {
                    if (dniBuilder.length() < 8) {
                        dniBuilder.append(t);
                        dniLabel.setText(dniBuilder.toString());
                    }
                });
            }
            teclado.add(btn);
        }
        center.add(teclado);

        JButton cancelar = new JButton("Cancelar");
        cancelar.setBackground(new Color(220, 38, 38));
        cancelar.setForeground(Color.WHITE);
        cancelar.addActionListener(e -> {
            dniBuilder.setLength(0);
            dniLabel.setText("");
            cardLayout.show(mainPanel, "Bienvenida");
        });

        JButton aceptar = new JButton("Aceptar");
        aceptar.setBackground(new Color(0, 204, 102));
        aceptar.setForeground(Color.WHITE);
        aceptar.addActionListener(e -> {
            if (dniBuilder.length() == 8) {
                cardLayout.show(mainPanel, "Menu");
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese un DNI válido de 8 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(center, BorderLayout.CENTER);
        panel.add(footerPanel(cancelar, aceptar), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel pinRetiroPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(244, 246, 248));
        panel.add(headerPanel(), BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(new Color(249, 250, 251));
        center.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("CONFIRME SU RETIRO");
        titulo.setFont(new Font("Inter", Font.BOLD, 28));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(new Color(0, 105, 217));
        center.add(titulo);

        center.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel monto = new JLabel();
        monto.setFont(new Font("Inter", Font.PLAIN, 20));
        monto.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(monto);

        JLabel cuenta = new JLabel();
        cuenta.setFont(new Font("Inter", Font.PLAIN, 18));
        cuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(cuenta);

        JLabel comision = new JLabel("Comisión: S/ 0.00");
        comision.setFont(new Font("Inter", Font.PLAIN, 18));
        comision.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(comision);

        JLabel total = new JLabel();
        total.setFont(new Font("Inter", Font.BOLD, 20));
        total.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(total);

        center.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel pinLabel = new JLabel("Ingrese su clave PIN para confirmar");
        pinLabel.setFont(new Font("Inter", Font.PLAIN, 18));
        pinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        center.add(pinLabel);

        JLabel pinField = new JLabel("");
        pinField.setFont(new Font("Inter", Font.BOLD, 32));
        pinField.setAlignmentX(Component.CENTER_ALIGNMENT);
        pinField.setHorizontalAlignment(SwingConstants.CENTER);
        center.add(pinField);

        center.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel teclado = new JPanel(new GridLayout(4, 3, 12, 12));
        String[] teclas = {"1","2","3","4","5","6","7","8","9","","0","⌫"};
        final StringBuilder pinBuilder = new StringBuilder();
        for (String t : teclas) {
            JButton btn = new JButton(t);
            btn.setFont(new Font("Inter", Font.BOLD, 24));
            btn.setFocusPainted(false);
            btn.setBackground(t.equals("⌫") ? new Color(220, 38, 38) : t.equals("") ? new Color(249, 250, 251) : new Color(0, 105, 217));
            btn.setForeground(t.equals("⌫") ? Color.WHITE : t.equals("") ? new Color(249, 250, 251) : Color.WHITE);
            btn.setEnabled(!t.equals(""));
            btn.setBorder(BorderFactory.createLineBorder(new Color(209, 213, 219), 2, true));
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (t.equals("⌫")) {
                btn.addActionListener(e -> {
                    if (pinBuilder.length() > 0) {
                        pinBuilder.deleteCharAt(pinBuilder.length() - 1);
                        pinField.setText("•".repeat(pinBuilder.length()));
                    }
                });
            } else if (!t.equals("")) {
                btn.addActionListener(e -> {
                    if (pinBuilder.length() < 4) {
                        pinBuilder.append(t);
                        pinField.setText("•".repeat(pinBuilder.length()));
                    }
                });
            }
            teclado.add(btn);
        }
        center.add(teclado);

        JButton cancelar = new JButton("Cancelar");
        cancelar.setBackground(new Color(220, 38, 38));
        cancelar.setForeground(Color.WHITE);
        cancelar.addActionListener(e -> {
            pinBuilder.setLength(0);
            pinField.setText("");
            cardLayout.show(mainPanel, "Menu");
        });

        JButton confirmar = new JButton("Confirmar");
        confirmar.setBackground(new Color(0, 204, 102));
        confirmar.setForeground(Color.WHITE);
        confirmar.addActionListener(e -> {
            if (pinBuilder.length() == 4) {
                double valor = 0.0;
                if (!montoSeleccionado.isEmpty()) {
                    try {
                        valor = Double.parseDouble(montoSeleccionado.replace("S/", "").replace(",", "").trim());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Monto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if (valor > saldoDisponible) {
                    JOptionPane.showMessageDialog(this, "Saldo insuficiente.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                saldoDisponible -= valor; // <-- Resta el monto al saldo disponible
                cardLayout.show(mainPanel, "Procesando");
                new Timer(2000, evt -> {
                    ((Timer)evt.getSource()).stop();
                    cardLayout.show(mainPanel, "Retire");
                }).start();
            } else {
                JOptionPane.showMessageDialog(this, "El PIN debe tener 4 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(center, BorderLayout.CENTER);
        panel.add(footerPanel(cancelar, confirmar), BorderLayout.SOUTH);

        // Actualiza los textos cada vez que se muestre este panel
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                monto.setText("Monto a retirar: " + (montoSeleccionado.isEmpty() ? "S/ 0.00" : montoSeleccionado));
                cuenta.setText("Cuenta: " + (cuentaSeleccionada.isEmpty() ? "No seleccionada" : cuentaSeleccionada));
                total.setText("Total: " + (montoSeleccionado.isEmpty() ? "S/ 0.00" : montoSeleccionado));
                pinBuilder.setLength(0);
                pinField.setText("");
            }
        });

        return panel;
    }

    private JButton crearBotonModerno(String texto, Color colorFondo) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Inter", Font.BOLD, 20));
        btn.setBackground(colorFondo);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(16, 48, 16, 48));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        btn.setOpaque(true);
        // Efecto hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(colorFondo.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(colorFondo);
            }
        });
        return btn;
    }

    // Panel con fondo gradiente
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth(), h = getHeight();
            Color color1 = new Color(106, 17, 203); // #6a11cb
            Color color2 = new Color(37, 117, 252); // #2575fc
            GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CajeroATM().setVisible(true));
    }
}