package Fase1.P2.Ejercicio.ConversorUnidadesGenerico;

class Conversor<T extends Unidad<? extends Number>> {

    public static <T extends Unidad<? extends Number>> boolean EsIgual(T longi, String type){
        if (type.equals(longi.getTipo())) {
            System.out.println("Ya se encuentra en esta unidad" +longi.getValor().doubleValue());
            return false;
        }
        return true;
    }
    
    public static <T extends Unidad<? extends Number>> double Longitud(T longi, String type) {

        double valor = longi.getValor().doubleValue();
    
        // Conversiones desde metros
        if (longi.getTipo().equals("metros")) {
            if (type.equals("pies")) {
                return valor * 3.28084;  // 1 metro = 3.28084 pies
            } else {
                return valor * 39.3701;  // 1 metro = 39.3701 pulgadas
            }
        }
    
        // Conversiones desde pies
        if (longi.getTipo().equals("pies")) {
            if (type.equals("metros")) {
                return valor / 3.28084;  // 1 pie = 0.3048 metros
            } else {
                return valor * 12;  // 1 pie = 12 pulgadas
            }
        }
    
        // Conversiones desde pulgadas
        if (longi.getTipo().equals("pulgadas")) {
            if (type.equals("metros")) {
                return valor / 39.3701;  // 1 pulgada = 0.0254 metros
            } else {
                return valor / 12;  // 1 pulgada = 0.08333 pies
            }
        }
    
        return 0;
    }
    
    public static <T extends Unidad<? extends Number>>  double  Masa(T masa, String type) {

        double valor = masa.getValor().doubleValue();
    
        // Conversiones desde kilogramos
        if (masa.getTipo().equals("kilogramos")) {
            if (type.equals("libras")) {
                return valor * 2.20462;  // 1 kg = 2.20462 lb
            } else {
                return valor * 35.274;  // 1 kg = 35.274 oz
            }
        }
    
        // Conversiones desde libras
        if (masa.getTipo().equals("libras")) {
            if (type.equals("kilogramos")) {
                return valor / 2.20462;  // 1 lb = 0.453592 kg
            } else {
                return valor * 16;  // 1 lb = 16 oz
            }
        }
    
        // Conversiones desde onzas
        if (masa.getTipo().equals("onzas")) {
            if (type.equals("kilogramos")) {
                return valor / 35.274;  // 1 oz = 0.0283495 kg
            } else {
                return valor / 16;  // 1 oz = 0.0625 lb
            }
        }
    
        return 1;  // En caso de que la unidad no sea reconocida
    }
    
    public static <T extends Unidad<? extends Number>>  double Temperatura(T temp, String type) {
    
        double valor = temp.getValor().doubleValue();
    
        // Conversiones desde Celsius
        if (temp.getTipo().equals("Celsius")) {
            if (type.equals("Fahrenheit")) {
                return (valor * 9/5) + 32;  // °F = (°C × 9/5) + 32
            } else {
                return valor + 273.15;  // K = °C + 273.15
            }
        }
    
        // Conversiones desde Fahrenheit
        if (temp.getTipo().equals("Fahrenheit")) {
            if (type.equals("Celsius")) {
                return (valor - 32) * 5/9;  // °C = (°F - 32) × 5/9
            } else {
                return (valor - 32) * 5/9 + 273.15;  // K = (°F - 32) × 5/9 + 273.15
            }
        }
    
        // Conversiones desde Kelvin
        if (temp.getTipo().equals("Kelvin")) {
            if (type.equals("Celsius")) {
                return valor - 273.15;  // °C = K - 273.15
            } else {
                return (valor - 273.15) * 9/5 + 32;  // °F = (K - 273.15) × 9/5 + 32
            }
        }
    
        return 1;  // En caso de que la unidad no sea reconocida
    }
    
    public static <T extends Unidad<? extends Number>>  double Tiempo(T tiempo, String type) {

        double valor = tiempo.getValor().doubleValue();
    
        // Conversiones desde segundos
        if (tiempo.getTipo().equals("segundos")) {
            if (type.equals("minutos")) {
                return valor / 60;  // 1 minuto = 60 segundos
            } else {
                return valor / 3600;  // 1 hora = 3600 segundos
            }
        }
    
        // Conversiones desde minutos
        if (tiempo.getTipo().equals("minutos")) {
            if (type.equals("segundos")) {
                return valor * 60;  // 1 minuto = 60 segundos
            } else {
                return valor / 60;  // 1 hora = 60 minutos
            }
        }
    
        // Conversiones desde horas
        if (tiempo.getTipo().equals("horas")) {
            if (type.equals("segundos")) {
                return valor * 3600;  // 1 hora = 3600 segundos
            } else {
                return valor * 60;  // 1 hora = 60 minutos
            }
        }
    
        return 1;  // En caso de que la unidad no sea reconocida
    }
    
}
