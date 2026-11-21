package modelo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class GestorArenaModelo { 
    private final List<PersonajeBase> roster = new ArrayList<>(); 
 
    public void agregar(JFrame panel,PersonajeBase nuevo) throws IllegalArgumentException { 
        boolean idSinUsar = true;
        // TODO: Validar que no exista otro personaje con el mismo ID antes de agregar. 
        // En Unidad V: Invocar aquí a personajeDAO.insertar(nuevo) y sincronizar 'roster'.
        for(int i = 0; i < roster.size(); i++){
            if(roster.get(i).getId().equalsIgnoreCase(nuevo.getId())){
                idSinUsar = false;
            }
        }
        
        if(idSinUsar){
            roster.add(nuevo); 
        }else{
            javax.swing.JOptionPane.showMessageDialog(panel,
                    "El ID " + nuevo.getId() + " ya esta siendo utilizado",
                    "ID repetido",
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
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
        return roster.removeIf(combatiente -> combatiente.getId().equalsIgnoreCase(id));
    } 
} 