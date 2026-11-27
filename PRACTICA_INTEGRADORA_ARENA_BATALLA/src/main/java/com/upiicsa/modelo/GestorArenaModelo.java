package com.upiicsa.modelo;
import com.upiicsa.persistencia.BatallaHibernateDAO;
import com.upiicsa.persistencia.PersonajeDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorArenaModelo { 
    private List<PersonajeBase> personajes = new ArrayList<>();
    private List<HistorialBatalla> batallas = new ArrayList<>();
    private PersonajeBase combatiente1;
    private PersonajeBase combatiente2;
    private final PersonajeDAO personajeDAO = new PersonajeDAO();
    private final BatallaHibernateDAO batallaHibernateDAO = new BatallaHibernateDAO();
 
    public void agregar(PersonajeBase nuevo) throws IllegalArgumentException, SQLException { 
        // TODO: Validar que no exista otro personaje con el mismo ID antes de agregar. 
        // En Unidad V: Invocar aquí a personajeDAO.insertar(nuevo) y sincronizar 'personaje'.
        if(buscarPorId(nuevo.getId()) != null){
            throw new IllegalArgumentException(
                    "Ya existe un combatiente con ID" + nuevo.getId()
            );
        }
        
        personajeDAO.insertar(nuevo);
        
        personajes.add(nuevo);
        
    }
 
    public List<PersonajeBase> obtenerTodos() { 
        return new ArrayList<>(personajes);
    }
    
    public PersonajeBase buscarPorId(String id) {
        for (PersonajeBase personajes : personajes) {
            if (personajes.getId().equals(id)) {
                return personajes;
            }
        }

        return null;
    }
 
    public boolean actualizar(PersonajeBase actualizado) throws SQLException {
            int indice = -1;

            for (int i = 0; i < personajes.size(); i++) {
                if (personajes.get(i).getId().equals(actualizado.getId())) {
                    indice = i;
                    break;
                }
            }

            if (indice == -1) {
                return false;
            }
            
            boolean resultado = personajeDAO.actualizar(actualizado);

            if (resultado) {
                personajes.set(indice, actualizado);
            }

            return resultado;
        }
    public boolean eliminar(String id) throws SQLException {
        int indice = -1;

        for (int i = 0; i < personajes.size(); i++) {
            if (personajes.get(i).getId().equals(id)) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            return false;
        }

        boolean resultado = personajeDAO.eliminar(id);

        if (resultado) {
            personajes.remove(indice);
        }

        return resultado;
    }
    
    public void cargarRoster() throws SQLException {
        List<PersonajeBase> personajess = personajeDAO.listarTodos();

        personajes.clear();
        personajes.addAll(personajess);
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
        
        throw new IllegalArgumentException("Tipo de personajes desconocido");
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
    
    public void cargarCombates() {
        List<HistorialBatalla> historial =
                batallaHibernateDAO.obtenerHistorial();

        batallas.clear();
        batallas.addAll(historial);
    }
    
    public List<HistorialBatalla> obtenerCombates(){
         return new ArrayList<>(batallas);
    }
    
    public void agregarCombate(HistorialBatalla batalla){
        batallaHibernateDAO.registrarBatalla(batalla);
        
        batallas.add(batalla);
    }
    
} 