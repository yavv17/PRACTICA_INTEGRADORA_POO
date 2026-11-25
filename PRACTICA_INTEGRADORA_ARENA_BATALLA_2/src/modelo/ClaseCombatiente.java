package modelo;

public enum ClaseCombatiente {
    ARQUERO("Arquero", new String[]{"Rango", "Municion"}, false),
    BARBARO("Barbaro", new String[]{"Estamina", "Resistencia", "Arma Adicional"}, false),
    BARDO("Bardo", new String[]{"Inspiracion"}, false),
    CABALLERO("Caballero", new String[]{"Armadura", "Resistencia", "Criatura de Apoyo"}, true,"Escudo"),
    GOLEM("Golem", new String[]{"Resistencia", "Elemento"}, false),
    NINFA("Ninfa", new String[]{"Elemento", "Nivel de Seduccion", "Experiencia"}, false),
    ORCO("Orco", new String[]{"Colmillos", "Tamaño", "Color", "Aditamento"}, false),
    POLINENE("Polinene", new String[]{"Cantidad Polimonedas", "Inteligencia"}, true,"Tamear"),
    SAIYAN("Saiyan", new String[]{"CelulasZ", "Zenkai", "Ki"}, true,"Cola"),
    STAND("Stand", new String[]{"Rango", "Tipo", "Frase", "Usuario"}, false);
    
     private final String nombre;
     private final String[] atributos;
     private final boolean tieneAtributoBooleano;
     private final String[] atributosBooleanos;
     
    ClaseCombatiente(String nombre, String[] atributos, boolean tieneAtributoBooleano,String... atributosBooleanos) {
        this.nombre = nombre;
        this.atributos = atributos;
        this.tieneAtributoBooleano = tieneAtributoBooleano;
        this.atributosBooleanos = atributosBooleanos;
    }

    public String getNombre() {
        return nombre;
    }

    public String[] getAtributos() {
        return atributos;
    }

    public boolean tieneAtributoBooleano() {
        return tieneAtributoBooleano;
    }

    public String[] getAtributosBooleanos() {
        return atributosBooleanos;
    }    
    
}
