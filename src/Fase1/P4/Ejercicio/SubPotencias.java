package Fase1.P4.Ejercicio;

import java.util.*;

public class SubPotencias {
    public static boolean canFormSum(int[] nums, int target) {
        // Paso 1: Identificar números que son potencias de 2 (obligatorios)
        int mandatorySum = 0;
        boolean[] isPowerOfTwo = new boolean[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (isPowerOf2(nums[i])) {
                mandatorySum += nums[i];
                isPowerOfTwo[i] = true;
            }
        }

        if (mandatorySum > target) {
            return false;
        }

        int adjustedTarget = target - mandatorySum;
        List<Integer> eligibleNumbers = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            // Saltamos los números que ya son obligatorios
            if (isPowerOfTwo[i]) {
                continue;
            }

            // Verificamos restricción de múltiplos de 5
            if (nums[i] % 5 == 0 && i < nums.length - 1 && nums[i + 1] % 2 != 0) {
                continue; // No podemos elegir este múltiplo de 5
            }

            eligibleNumbers.add(nums[i]);
        }

        // Paso 3: Programación dinámica para el problema de Subset Sum con los números elegibles
        boolean[] dp = new boolean[adjustedTarget + 1];
        dp[0] = true; // Siempre podemos formar una suma de 0

        for (int num : eligibleNumbers) {
            for (int j = adjustedTarget; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[adjustedTarget];
    }


    private static boolean isPowerOf2(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int target = scanner.nextInt();
        boolean result = canFormSum(nums, target);
        System.out.println(result ? "true" : "false");
    }
}