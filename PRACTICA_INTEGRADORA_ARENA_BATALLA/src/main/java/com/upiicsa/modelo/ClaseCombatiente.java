package com.upiicsa.modelo;

public enum ClaseCombatiente {
    ARQUERO("Arquero","src/main/resources/imagenes/Image_Avatar_Default_Arquero.png" ,
            new String[]{"Rango", "Municion"},false),
    BARBARO("Barbaro", "src/main/resources/imagenes/Image_Avatar_Default_Barbaro.png",
            new String[]{"Estamina", "Resistencia", "Arma Adicional"}, false),
    BARDO("Bardo", "src/main/resources/imagenes/Image_Avatar_Default_Bardo.png",
            new String[]{"Inspiracion"}, false),
    CABALLERO("Caballero", "src/main/resources/imagenes/Image_Avatar_Default_Caballero.png",
            new String[]{"Armadura", "Resistencia", "Criatura de Apoyo"}, true,"Escudo"),
    GOLEM("Golem", "src/main/resources/imagenes/Image_Avatar_Default_Golem.png",
            new String[]{"Resistencia", "Elemento"}, false),
    NINFA("Ninfa", "src/main/resources/imagenes/Image_Avatar_Default_Ninfa.png",
            new String[]{"Elemento", "Nivel de Seduccion", "Experiencia"}, false),
    ORCO("Orco", "src/main/resources/imagenes/Image_Avatar_Default_Orco.png",
            new String[]{"Colmillos", "Tamaño", "Color", "Aditamento"}, false),
    POLINENE("Polinene", "src/main/resources/imagenes/Image_Avatar_Default_Polinene.png",
            new String[]{"Cantidad Polimonedas", "Inteligencia"}, true,"Tamear"),
    SAIYAN("Saiyan", "src/main/resources/imagenes/Image_Avatar_Default_Saiyan.png",
            new String[]{"CelulasZ", "Zenkai", "Ki"}, true,"Cola"),
    STAND("Stand", "src/main/resources/imagenes/Image_Avatar_Default_Stand.png",
            new String[]{"Rango", "Tipo", "Frase", "Usuario"}, false);
    
     private final String nombre;
     private final String rutaAvatarDefault;
     private final String[] atributos;
     private final boolean tieneAtributoBooleano;
     private final String[] atributosBooleanos;
     
    ClaseCombatiente(String nombre,String rutaAvatarDefault,String[] atributos, boolean tieneAtributoBooleano,String... atributosBooleanos) {
        this.nombre = nombre;
        this.rutaAvatarDefault = rutaAvatarDefault;
        this.atributos = atributos;
        this.tieneAtributoBooleano = tieneAtributoBooleano;
        this.atributosBooleanos = atributosBooleanos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRutaImagenAvatarDefault() {
        return rutaAvatarDefault;
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
