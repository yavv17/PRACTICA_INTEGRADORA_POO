package com.upiicsa.modelo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class GestorArenaModelo { 
    private final List<PersonajeBase> personajes = new ArrayList<>(); 
    private PersonajeBase combatiente1;
    private PersonajeBase combatiente2;
 
    public void agregar(JFrame panel,PersonajeBase nuevo) throws IllegalArgumentException { 
        boolean idSinUsar = true;
        // TODO: Validar que no exista otro personaje con el mismo ID antes de agregar. 
        // En Unidad V: Invocar aquí a personajeDAO.insertar(nuevo) y sincronizar 'roster'.
        for(int i = 0; i < personajes.size(); i++){
            if(personajes.get(i).getId().equalsIgnoreCase(nuevo.getId())){
                idSinUsar = false;
            }
        }
        
        if(idSinUsar){
            personajes.add(nuevo); 
        }else{
            throw new IllegalArgumentException("Ya existe un combatiente con ID " + nuevo.getId()); 
        }
    } 
 
    public List<PersonajeBase> obtenerTodos() { 
        return new ArrayList<>(personajes); 
    }
    
    public PersonajeBase buscarPorId(String id) {
        for (int i = 0; i < personajes.size(); i++) {
            PersonajeBase personaje = personajes.get(i);

            if (personaje.getId().equalsIgnoreCase(id)) {
                return personaje;
            }
        }
        return null;
    }
 
    public boolean actualizar(PersonajeBase actualizado) { 
        for (int i = 0; i < personajes.size(); i++) { 
            if (personajes.get(i).getId().equalsIgnoreCase(actualizado.getId())) { 
                personajes.set(i, actualizado); 
                return true; 
            } 
        } 
        return false; 
    } 
 
    public boolean eliminar(String id) { 
        // TODO: Implementar eliminación usando removeIf() sobre 'roster' comparando el ID.
        return personajes.removeIf(combatiente -> combatiente.getId().equalsIgnoreCase(id));
    } 
    
    public PersonajeBase crearCopia(PersonajeBase original){
        if(original instanceof Arquero){
            return new Arquero((Arquero)original);
        }
        if(original instanceof Barbaro){
            return new Barbaro((Barbaro)original);
        }
        if(original instanceof Bardo){
            return new Bardo((Bardo)original);
        }
        if(original instanceof Caballero){
            return new Caballero((Caballero)original);
        }
        if(original instanceof Golem){
            return new Golem((Golem)original);
        }
        if(original instanceof Ninfa){
            return new Ninfa((Ninfa)original);
        }
        if(original instanceof Orco){
            return new Orco((Orco)original);
        }
        if(original instanceof Polinene){
            return new Polinene((Polinene)original);
        }
        if(original instanceof Saiyan){
            return new Saiyan((Saiyan)original);
        }
        if(original instanceof Stand){
            return new Stand((Stand)original);
        }
        
        throw new IllegalArgumentException("Tipo de personaje desconocido");
    }
    
    public void iniciarCombate(PersonajeBase original1,
            PersonajeBase original2){
        combatiente1 = crearCopia(original1);
        combatiente2 = crearCopia(original2);
    }

    public PersonajeBase getCombatiente1() {
        return combatiente1;
    }

    public PersonajeBase getCombatiente2() {
        return combatiente2;
    }
    
    
} 