package modelo;

public enum ClaseCombatiente {
    ARQUERO("Arquero","Rango","Municion"),
    BARBARO("Barbaro","Estamina","Resistencia","Arma Adicional"),
    BARDO("Bardo","Inspiracion"),
    CABALLERO("Caballero","Armadura","Resistencia","Escudo","Criatura de Apoyo"),
    GOLEM("Golem","Resistencia","Elemento"),
    NINFA("Ninfa","Elemento","Nivel de Seduccion","Experiencia"),
    ORCO("Orco","Colmillos","Tamaño","Color","Aditamento"),
    POLINENE("Polinene","Cantidad Polimonedas","Inteligencia","Tamear"),
    SAIYAN("Saiyan","Cola","CelulasZ","Zenkai","Ki"),
    STAND("Stand","Rango","Tipo","Frase","Usuario");
    
     private final String nombre;
     private final String[] atributos;

    ClaseCombatiente(String nombre,String... atributos) {
        this.nombre = nombre;
        this.atributos = atributos;
    }

    public String getNombre() {
        return nombre;
    }

    
    
    public String[] getAtributos() {
        return atributos;
    }
    
}
