package Fase2.P8.GUI;

import Fase2.P8.AVL.AVLTree;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class AVLTreeVisualizer extends JFrame {

    private AVLTree tree;
    private final int NODE_DIAMETER = 40;
    private final int VERTICAL_GAP = 60;
    private final int HORIZONTAL_MARGIN = 30;
    private final Color NODE_COLOR = new Color(100, 149, 237); // Azul claro
    private final Color BF_COLOR = new Color(255, 255, 255); // Blanco
    private final Color EDGE_COLOR = new Color(70, 70, 70); // Gris oscuro
    private final Font NODE_FONT = new Font("Arial", Font.BOLD, 14);
    private final Font BF_FONT = new Font("Arial", Font.PLAIN, 10);

    /**
     * Constructor que recibe un árbol AVL para visualizar
     * @param tree Árbol AVL a visualizar
     */

    public AVLTreeVisualizer(AVLTree<?> tree) {
        this.tree = tree;
        initializeFrame();
    }

    /**
     * Configura el frame y el panel de visualización
     */
    private void initializeFrame() {
        setTitle("Visualizador de Árbol AVL");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        TreePanel treePanel = new TreePanel();
        add(treePanel);

        // Panel de controles en la parte inferior
        JPanel controlPanel = new JPanel();
        JButton resizeButton = new JButton("Ajustar ventana al árbol");
        resizeButton.addActionListener(e -> adjustFrameSize(treePanel));
        controlPanel.add(resizeButton);

        JButton zoomInButton = new JButton("Zoom +");
        zoomInButton.addActionListener(e -> {
            treePanel.zoomIn();
            treePanel.repaint();
        });
        controlPanel.add(zoomInButton);

        JButton zoomOutButton = new JButton("Zoom -");
        zoomOutButton.addActionListener(e -> {
            treePanel.zoomOut();
            treePanel.repaint();
        });
        controlPanel.add(zoomOutButton);

        add(controlPanel, BorderLayout.SOUTH);

        // Ajustar tamaño inicial
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                adjustFrameSize(treePanel);
            }
        });
    }

    /**
     * Ajusta el tamaño del frame para que quepa todo el árbol
     */
    private void adjustFrameSize(TreePanel treePanel) {
        try {
            if (tree.isEmpty()) {
                return;
            }


            int height = tree.height(tree.getNode());
            if (height <= 0) return;

            // Estimar el tamaño necesario
            int estimatedWidth = (int)Math.pow(2, height) * (NODE_DIAMETER + 10);
            int estimatedHeight = (height + 1) * (VERTICAL_GAP + NODE_DIAMETER) + 100; // +100 para los controles

            // Aplicar el factor de zoom
            estimatedWidth = (int)(estimatedWidth * treePanel.getZoomFactor());
            estimatedHeight = (int)(estimatedHeight * treePanel.getZoomFactor());

            // Establecer tamaño mínimo
            estimatedWidth = Math.max(estimatedWidth, 400);
            estimatedHeight = Math.max(estimatedHeight, 300);

            setSize(estimatedWidth, estimatedHeight);
            setLocationRelativeTo(null);
        } catch (Exception ex) {
            System.err.println("Error al ajustar el tamaño: " + ex.getMessage());
        }
    }

    private class TreePanel extends JPanel {
        private double zoomFactor = 1.0;
        private Map<Object, Point> nodePositions = new HashMap<>();

        public TreePanel() {
            setBackground(Color.WHITE);
        }

        public void zoomIn() {
            zoomFactor *= 1.2;
        }

        public void zoomOut() {
            zoomFactor *= 0.8;
        }

        public double getZoomFactor() {
            return zoomFactor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Activar antialiasing para mejorar la calidad
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Limpiar el mapa de posiciones
            nodePositions.clear();

            if (!tree.isEmpty()) {
                // Obtenemos la altura para calcular el espaciado horizontal
                int treeHeight = tree.height(tree.getNode());
                int panelWidth = getWidth();

                // Dibujar el árbol
                drawTree(g2d, tree.getNode(), panelWidth / 2, HORIZONTAL_MARGIN + NODE_DIAMETER/2,
                        panelWidth / (Math.pow(2, 1) + 1), treeHeight);
            } else {
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.BOLD, 20));
                g2d.drawString("El árbol está vacío", getWidth()/2 - 100, getHeight()/2);
            }
        }

        private void drawTree(Graphics2D g2d, Object node, int x, int y, double hGap, int height) {
            if (node == null) return;

            // Aplicar zoom
            int zoomedX = (int)(x * zoomFactor);
            int zoomedY = (int)(y * zoomFactor);
            int zoomedDiameter = (int)(NODE_DIAMETER * zoomFactor);

            // Guardar la posición para este nodo
            nodePositions.put(node, new Point(zoomedX, zoomedY));

            // Dibujar las conexiones a los hijos primero (para que queden detrás)
            try {
                // Método para obtener el factor de balance (bf) a través de reflexión
                int balanceFactor = getBalanceFactor(node);

                // Obtener hijos
                Object leftChild = getLeft(node);
                Object rightChild = getRight(node);

                // Calcular posiciones de los hijos
                int nextY = y + VERTICAL_GAP + NODE_DIAMETER;
                int leftX = (int) (x - hGap);
                int rightX = (int) (x + hGap);

                // Dibujar líneas a los hijos
                g2d.setColor(EDGE_COLOR);
                g2d.setStroke(new BasicStroke(2.0f));

                if (leftChild != null) {
                    int zoomedLeftX = (int)(leftX * zoomFactor);
                    int zoomedNextY = (int)(nextY * zoomFactor);
                    g2d.drawLine(zoomedX, zoomedY, zoomedLeftX, zoomedNextY);
                }

                if (rightChild != null) {
                    int zoomedRightX = (int)(rightX * zoomFactor);
                    int zoomedNextY = (int)(nextY * zoomFactor);
                    g2d.drawLine(zoomedX, zoomedY, zoomedRightX, zoomedNextY);
                }

                // Dibujar nodo
                g2d.setColor(NODE_COLOR);
                g2d.fillOval(zoomedX - zoomedDiameter/2, zoomedY - zoomedDiameter/2, zoomedDiameter, zoomedDiameter);

                // Borde del nodo
                g2d.setColor(Color.BLACK);
                g2d.drawOval(zoomedX - zoomedDiameter/2, zoomedY - zoomedDiameter/2, zoomedDiameter, zoomedDiameter);

                // Dibujar el valor del nodo
                g2d.setColor(Color.BLACK);
                g2d.setFont(NODE_FONT.deriveFont((float)(NODE_FONT.getSize() * zoomFactor)));
                String nodeValue = getData(node).toString();
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(nodeValue);
                g2d.drawString(nodeValue, zoomedX - textWidth/2, zoomedY + fm.getAscent()/2);

                // Dibujar factor de balance
                String bfText = "bf:" + balanceFactor;
                g2d.setFont(BF_FONT.deriveFont((float)(BF_FONT.getSize() * zoomFactor)));
                fm = g2d.getFontMetrics();
                textWidth = fm.stringWidth(bfText);

                // Pequeño círculo para el factor de balance
                int bfCircleSize = (int)(20 * zoomFactor);
                g2d.setColor(BF_COLOR);
                g2d.fillOval(zoomedX + zoomedDiameter/2 - bfCircleSize/2,
                        zoomedY - zoomedDiameter/2, bfCircleSize, bfCircleSize);
                g2d.setColor(Color.BLACK);
                g2d.drawOval(zoomedX + zoomedDiameter/2 - bfCircleSize/2,
                        zoomedY - zoomedDiameter/2, bfCircleSize, bfCircleSize);

                // Texto del factor de balance
                g2d.setColor(Color.BLACK);
                g2d.drawString(String.valueOf(balanceFactor),
                        zoomedX + zoomedDiameter/2 - fm.stringWidth(String.valueOf(balanceFactor))/2,
                        zoomedY - zoomedDiameter/2 + fm.getAscent());

                // Recursivamente dibujar subárboles
                double newHGap = hGap / 2;
                if (leftChild != null) {
                    drawTree(g2d, leftChild, leftX, nextY, newHGap, height-1);
                }
                if (rightChild != null) {
                    drawTree(g2d, rightChild, rightX, nextY, newHGap, height-1);
                }

            } catch (Exception e) {
                System.err.println("Error al dibujar el nodo: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Métodos auxiliares para acceder a la información del nodo a través de reflexión
        private Object getData(Object node) {
            try {
                return node.getClass().getMethod("getData").invoke(node);
            } catch (Exception e) {
                return "?";
            }
        }

        private Object getLeft(Object node) {
            try {
                return node.getClass().getMethod("getLeft").invoke(node);
            } catch (Exception e) {
                return null;
            }
        }

        private Object getRight(Object node) {
            try {
                return node.getClass().getMethod("getRight").invoke(node);
            } catch (Exception e) {
                return null;
            }
        }

        private int getBalanceFactor(Object node) {
            try {
                return (int) node.getClass().getField("bf").get(node);
            } catch (Exception e) {
                try {
                    return tree.balanceFactor((AVLTree.NodeAVL)node);
                } catch (Exception e2) {
                    return 0;
                }
            }
        }
    }

    public void updateTree(AVLTree<?> tree) {
        this.tree = tree;
        repaint();
    }

    public static void showTree(AVLTree<?> tree) {
        SwingUtilities.invokeLater(() -> {
            AVLTreeVisualizer visualizer = new AVLTreeVisualizer(tree);
            visualizer.setVisible(true);
        });
    }
}