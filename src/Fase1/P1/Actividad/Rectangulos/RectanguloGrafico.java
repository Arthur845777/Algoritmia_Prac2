package Fase1.P1.Actividad.Rectangulos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class RectanguloGrafico extends JPanel {
    private List<Rectangulo> rectangulos;

    // VARIABLES (4.5.1)
    private static final Color[] COLORES = {new Color(102, 204, 255, 180), new Color(255, 153, 153, 180)}; // RECTANGULOS

    private static final Color COLOR_FONDO = new Color(35, 35, 40);
    private static final Color COLOR_EJES = new Color(70, 70, 75);
    private static final Color COLOR_TEXTO = new Color(220, 220, 220);

    private static final int ESCALA = 35;
    private static final int LIMITE_EJE = 35;

    // Arrastrar
    private Point lastPoint;
    private int offsetX = 0;
    private int offsetY = 0;

    // RECTANGULO_GRAFICO (4.5.2)
    public RectanguloGrafico(List<Rectangulo> rectangulos) {
        this.rectangulos = rectangulos;
        setPreferredSize(new Dimension(600, 600));
        setBackground(COLOR_FONDO);
        configurarArrastre();
    }

    // CONFIGURACION ARRASTRE(4.5.3)
    private void configurarArrastre() {
        MouseAdapter arrastreAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastPoint = e.getPoint();
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currentPoint = e.getPoint();
                offsetX += (currentPoint.x - lastPoint.x);
                offsetY += (currentPoint.y - lastPoint.y);
                lastPoint = currentPoint;
                repaint();
            }
        };

        addMouseListener(arrastreAdapter);
        addMouseMotionListener(arrastreAdapter);
    }

    // PAINT_COMPONET(4.5.4)
    @Override
    protected void paintComponent(Graphics g) {
        // Variables (4.5.4.1)
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int centerX = getWidth() / 2 + offsetX;
        int centerY = getHeight() / 2 + offsetY;

        g2d.setColor(COLOR_EJES);
        g2d.drawLine(centerX, 10, centerX, getHeight() - 10); // Eje Y
        g2d.drawLine(10, centerY, getWidth() - 10, centerY); // Eje X

        g2d.setFont(new Font("Monospaced", Font.PLAIN, 10));
        g2d.setColor(COLOR_TEXTO);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        // Marcas en eje X-Y y verificacion de arcas (4.5.4.2)
        for (int i = -LIMITE_EJE; i <= LIMITE_EJE; i++) {
            if (i == 0) continue;
            int x = centerX + i * ESCALA;
            if (x >= 10 && x <= getWidth() - 10) {
                g2d.drawLine(x, centerY - 3, x, centerY + 3);
                g2d.drawString(String.valueOf(i), x - 3, centerY + 15);
            }
        }

        for (int i = -LIMITE_EJE; i <= LIMITE_EJE; i++) {
            if (i == 0) continue;
            int y = centerY - i * ESCALA;
            if (y >= 10 && y <= getHeight() - 10) {
                g2d.drawLine(centerX - 3, y, centerX + 3, y);
                g2d.drawString(String.valueOf(i), centerX - 20, y + 4);
            }
        }

        // Dibujo del Rectangulo (4.5.4.3)
        for (int i = 0; i < rectangulos.size(); i++) {
            Rectangulo rect = rectangulos.get(i);

            double x1 = rect.getEsquina1().getX();
            double y1 = rect.getEsquina1().getY();
            double x2 = rect.getEsquina2().getX();
            double y2 = rect.getEsquina2().getY();

            double xMin = Math.min(x1, x2);
            double yMin = Math.min(y1, y2);
            double xMax = Math.max(x1, x2);
            double yMax = Math.max(y1, y2);

            int drawX = centerX + (int)(xMin * ESCALA);
            int drawY = centerY - (int)(yMax * ESCALA);
            int drawWidth = (int)((xMax - xMin) * ESCALA);
            int drawHeight = (int)((yMax - yMin) * ESCALA);

            // Dibujar rectángulo con color
            g2d.setColor(COLORES[i % COLORES.length]);
            g2d.fillRect(drawX, drawY, drawWidth, drawHeight);

            // Contorno
            g2d.setColor(Color.WHITE);
            g2d.drawRect(drawX, drawY, drawWidth, drawHeight);

        // Etiquetas en el rectangulo (4.5.4.4)
            g2d.setFont(new Font("SansSerif", Font.BOLD, 10));
            g2d.setColor(COLOR_TEXTO);
            g2d.drawString("R" + (i+1), drawX + 10, drawY + 25);

            // coordenadas esquina inferior izquierda y superior derecha
            g2d.setFont(new Font("Monospaced", Font.PLAIN, 10));
            String coordsBottomLeft = String.format("(%.1f,%.1f)", xMin, yMin);
            g2d.drawString(coordsBottomLeft, drawX + 5, drawY + drawHeight - 5);

            String coordsTopRight = String.format("(%.1f,%.1f)", xMax, yMax);
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(coordsTopRight, drawX + drawWidth - fm.stringWidth(coordsTopRight) - 5, drawY + 15);
        }
    }

    // MOSTRAR_GRAFICO (4.5.4.5)
    private static JFrame frame;
    private static RectanguloGrafico panel;

    public static void mostrarGrafico(List<Rectangulo> rectangulos) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Comprueba si ya existe algun frame
            if (frame == null) {
                frame = new JFrame("Visualización de Rectángulos");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(700, 700);
                frame.setLocationRelativeTo(null);

                panel = new RectanguloGrafico(rectangulos);

                JScrollPane scrollPane = new JScrollPane(panel);
                scrollPane.setBorder(BorderFactory.createEmptyBorder());

                frame.add(scrollPane);
                frame.setVisible(true);
            } else {
                panel.rectangulos = rectangulos;
                panel.repaint();

                if (!frame.isVisible()) {
                    frame.setVisible(true);
                }
            }
        });
    }
}