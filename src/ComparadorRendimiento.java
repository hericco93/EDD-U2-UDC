import java.util.Arrays;
import java.util.Random;

public class ComparadorRendimiento {

    public static void bubbleSortOptimizado(int[] arr, String tipoArreglo) {
        long inicio = System.nanoTime();
        long comparaciones = 0;
        long intercambios = 0;
        int n = arr.length;
        boolean intercambiado;

        for (int i = 0; i < n - 1; i++) {
            intercambiado = false;
            for (int j = 0; j < n - 1 - i; j++) {
                comparaciones++;
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    intercambios++;
                    intercambiado = true;
                }
            }
            // Optimización: si no hubo intercambios, el arreglo ya está ordenado
            if (!intercambiado) break;
        }

        long fin = System.nanoTime();
        double tiempoMs = (fin - inicio) / 1_000_000.0; // Convertir a milisegundos

        System.out.printf("Bubble Sort (%s): %.3f ms | Comparaciones: %d | Intercambios: %d%n",
                tipoArreglo, tiempoMs, comparaciones, intercambios);
    }

    public static int[] generarAleatorio(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }

    public static int[] generarInverso(int[] base) {
        int[] arr = base.clone();
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = temp;
        }
        return arr;
    }

    public static int[] generarCasiOrdenado(int[] base) {
        int[] arr = base.clone();
        Arrays.sort(arr);
        Random rand = new Random();
        int intercambios = Math.max(1, arr.length / 20);
        for (int i = 0; i < intercambios; i++) {
            int idx = rand.nextInt(arr.length - 1);
            int temp = arr[idx];
            arr[idx] = arr[idx + 1];
            arr[idx + 1] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        int tamano = 1000;

        int[] base = generarAleatorio(tamano);
        int[] aleatorio = base.clone();
        int[] casiOrdenado = generarCasiOrdenado(base);
        int[] inverso = generarInverso(base);

        System.out.println("Arreglos de " + tamano + " elementos generados con éxito.");
        System.out.println("\n--- RESULTADOS BUBBLE SORT ---");
        bubbleSortOptimizado(aleatorio.clone(), "Aleatorio");
        bubbleSortOptimizado(casiOrdenado.clone(), "Casi Ordenado");
        bubbleSortOptimizado(inverso.clone(), "Inverso");
    }
}