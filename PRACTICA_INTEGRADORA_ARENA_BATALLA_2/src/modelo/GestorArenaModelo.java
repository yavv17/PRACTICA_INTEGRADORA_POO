package modelo;
import java.util.ArrayList;
import java.util.List;

public class GestorArenaModelo { 
    private final List<PersonajeBase> roster = new ArrayList<>(); 
 
    public void agregar(PersonajeBase nuevo) throws IllegalArgumentException { 
        // TODO: Validar que no exista otro personaje con el mismo ID antes de agregar. 
        // En Unidad V: Invocar aquí a personajeDAO.insertar(nuevo) y sincronizar 'roster'. 
        roster.add(nuevo); 
    } 
 
    public List<PersonajeBase> obtenerTodos() { 
        return new ArrayList<>(roster); 
    } 
 
    public boolean actualizar(PersonajeBase actualizado) { 
        for (int i = 0; i < roster.size(); i++) { 
            if (roster.get(i).getId().equalsIgnoreCase(actualizado.getId())) { 
                roster.set(i, actualizado); 
                return true; 
            } 
        } 
        return false; 
    } 
 
    public boolean eliminar(String id) { 
        // TODO: Implementar eliminación usando removeIf() sobre 'roster' comparando el ID. 
        return false; 
    } 
} 