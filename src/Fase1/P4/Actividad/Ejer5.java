package Fase1.P4.Actividad;

class naiveSolution {
    static int getValue(int[] values, int length)
    { if (length <= 0)
        return 0;
        int tmpMax = -1;
        for (int i = 0; i < length; i++) {
            tmpMax = Math.max(tmpMax, values[i] + getValue(values, length - i - 1));
        }
        return tmpMax;
    }
    public static void main(String[] args)
    { int[] values = new int[]{3, 7, 1, 3, 9}; int rodLength = values.length;
        System.out.println("El valor maximo:" + getValue(values, rodLength));
    }
}

class dpSolution {
    static int getValue(int[] values, int rodLength) {
        int[] subSolutions = new int[rodLength + 1];
        for (int i = 1; i <= rodLength; i++) {
            int tmpMax = -1;
            for (int j = 0; j < i; j++)
                tmpMax = Math.max(tmpMax, values[j] + subSolutions[i - j - 1]);
            subSolutions[i] = tmpMax;
        }
        return subSolutions[rodLength];
    }

    public static void main(String[] args) {
        int[] values = new int[]{3, 7, 1, 3, 9};
        int rodLength = values.length;
        System.out.println("El valor maximo: " + getValue(values, rodLength));
    }
}
