package Fase2.P6.Teo.SparceMatrix;

class SparceMatrix<E> {
    private Node<E> head;
    private int[] dim;

    public SparceMatrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser positivas");
        }

        this.head = new Node<E>();
        this.head.setRight(new Node<E>(-1, m-1, null, this.head, null));
        this.head.getRight().setDown(this.head.getRight());

        for (int i = 1; i < m; i++) {
            this.head.setRight(new Node<E>(-1, m-i-1, null, this.head.getRight(), null));
            this.head.getRight().setDown(this.head.getRight());
        }

        this.head.setDown(new Node<E>(n-1, -1, this.head, null, null));
        this.head.getDown().setRight(this.head.getDown());

        for (int i = 1; i < n; i++) {
            this.head.setDown(new Node<E>(n-i-1, -1, this.head.getDown(), null, null));
            this.head.getDown().setRight(this.head.getDown());
        }

        this.dim = new int[]{n, m};
    }

    public void set(int row, int col, E value) {
        if (row < 0 || row >= dim[0] || col < 0 || col >= dim[1]) {
            throw new IndexOutOfBoundsException("Fuera de los límites de la matriz");
        }

        if (value == null) {
            remove(row, col);
            return;
        }

        // Encontrar el nodo adecuado en las cabezeras
        Node<E> rowHeader = head;
        for (int i = -1; i < row; i++) {
            rowHeader = rowHeader.getDown();
        }

        Node<E> colHeader = head;
        for (int j = -1; j < col; j++) {
            colHeader = colHeader.getRight();
        }

        // Actualizar su valor
        Node<E> prev = rowHeader;
        Node<E> currRow = rowHeader.getRight();

        while (currRow != rowHeader && currRow.getCol() < col) {
            prev = currRow;
            currRow = currRow.getRight();
        }

        if (currRow != rowHeader && currRow.getCol() == col) {
            currRow.setValue(value);
            return;
        }

        // Crear Nodo y poner el dato
        Node<E> prevCol = colHeader;
        Node<E> currCol = colHeader.getDown();

        while (currCol != colHeader && currCol.getRow() < row) {
            prevCol = currCol;
            currCol = currCol.getDown();
        }

        Node<E> newNode = new Node<E>(row, col, null, null, value);


        prev.setRight(newNode);
        newNode.setRight(currRow);

        prevCol.setDown(newNode);
        newNode.setDown(currCol);
    }

    public E get(int row, int col) {
        if (row < 0 || row >= dim[0] || col < 0 || col >= dim[1]) {
            throw new IndexOutOfBoundsException("Fuera de los límites de la matriz");
        }

        // fila
        Node<E> rowHeader = head;
        for (int i = -1; i < row; i++) {
            rowHeader = rowHeader.getDown();
        }

        // columna
        Node<E> curr = rowHeader.getRight();
        while (curr != rowHeader && curr.getCol() < col) {
            curr = curr.getRight();
        }

        // Si se encontro el nodo
        if (curr != rowHeader && curr.getCol() == col) {
            return curr.getValue();
        } else {
            return null;
        }
    }

    public void remove(int row, int col) {
        if (row < 0 || row >= dim[0] || col < 0 || col >= dim[1]) {
            throw new IndexOutOfBoundsException("Coordenadas fuera de los límites de la matriz");
        }

        // fila
        Node<E> rowHeader = head;
        for (int i = -1; i < row; i++) {
            rowHeader = rowHeader.getDown();
        }

        // columna
        Node<E> colHeader = head;
        for (int j = -1; j < col; j++) {
            colHeader = colHeader.getRight();
        }

        // buscar nodo en la fila
        Node<E> prevRow = rowHeader;
        Node<E> currRow = rowHeader.getRight();

        while (currRow != rowHeader && currRow.getCol() < col) {
            prevRow = currRow;
            currRow = currRow.getRight();
        }

        // si no esta
        if (currRow == rowHeader || currRow.getCol() != col) {
            return;
        }

        // Bbuscar nodo en la columna xd, lo mismo nomas
        Node<E> prevCol = colHeader;
        Node<E> currCol = colHeader.getDown();

        while (currCol != colHeader && currCol.getRow() < row) {
            prevCol = currCol;
            currCol = currCol.getDown();
        }

        // cambiamos la referencias de fila y columna respectivamente xd
        prevRow.setRight(currRow.getRight());

        prevCol.setDown(currCol.getDown());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matriz ").append(dim[0]).append("×").append(dim[1]).append(":\n");

        for (int i = 0; i < dim[0]; i++) {
            sb.append("[ ");
            for (int j = 0; j < dim[1]; j++) {
                E value = get(i, j);
                if (value == null) {
                    sb.append("0 ");
                } else {
                    sb.append(value).append(" ");
                }
            }
            sb.append("]\n");
        }

        return sb.toString();
    }


    public int[] getDimensions() {
        return dim.clone();
    }
}

class Main{
    public static void main(String[] args) {
        // Crear una matriz dispersa de 5x5
        System.out.println("Creando matriz dispersa 5x5...");
        SparceMatrix<Integer> matrix = new SparceMatrix<>(5, 5);

        // Mostrar matriz vacía
        System.out.println("Matriz inicial (vacía):");
        System.out.println(matrix);

        // Insertar algunos valores
        System.out.println("Insertando valores en posiciones específicas...");
        matrix.set(0, 0, 5);    // Esquina superior izquierda
        matrix.set(0, 4, 8);    // Esquina superior derecha
        matrix.set(4, 0, 3);    // Esquina inferior izquierda
        matrix.set(4, 4, 9);    // Esquina inferior derecha
        matrix.set(2, 2, 7);    // Centro
        matrix.set(1, 3, 6);    // Posición arbitraria

        // Mostrar matriz con valores
        System.out.println("Matriz después de insertar valores:");
        System.out.println(matrix);

        // Obtener un valor existente
        int valor = matrix.get(2, 2);
        System.out.println("Valor en posición (2,2): " + valor);

        // Intentar obtener un valor que no existe (debe ser null -> se muestra 0)
        Integer valorNulo = matrix.get(3, 3);
        System.out.println("Valor en posición (3,3): " + (valorNulo == null ? "null/0" : valorNulo));

        // Modificar un valor existente
        System.out.println("Modificando el valor en (2,2) de 7 a 15...");
        matrix.set(2, 2, 15);
        System.out.println("Nuevo valor en (2,2): " + matrix.get(2, 2));

        // Eliminar un valor
        System.out.println("Eliminando el valor en posición (0,4)...");
        matrix.remove(0, 4);
        System.out.println("Matriz después de eliminar un valor:");
        System.out.println(matrix);

        // Eliminar usando set con null
        System.out.println("Eliminando con set(null) el valor en posición (4,0)...");
        matrix.set(4, 0, null);
        System.out.println("Matriz final:");
        System.out.println(matrix);
    }
}