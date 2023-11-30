package co.udea.ssmu.api.services;

import co.udea.ssmu.api.dao.IConductorDAO;
import co.udea.ssmu.api.exception.DriverNotFoundException;
import co.udea.ssmu.api.model.Conductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConductorService {
    @Autowired
    private IConductorDAO dao;
    public Conductor guardar(Conductor t) {return dao.save(t);}
    public String borrar(int id) {dao.deleteById(id);  return "Conductor removido";}
    public Iterable<Conductor> list() {return dao.findAll();}
    public Optional<Conductor> listId(int id){ return dao.findById(id);}
    public Conductor actualizar(Conductor t){
        Conductor conductorExistente = dao.findById(t.getIdConductor()).orElse(null);
        conductorExistente.setNombre(t.getNombre());
        conductorExistente.setCelular(t.getCelular());
        conductorExistente.setEmail(t.getEmail());
        conductorExistente.setCedula(t.getCedula());
        conductorExistente.setCiudadActual(t.getCiudadActual());
        conductorExistente.setRol(t.getRol());
        conductorExistente.setNroServicios(t.getNroServicios());
        conductorExistente.setNroFelicitaciones(t.getNroFelicitaciones());
        conductorExistente.setNroAmonestaciones(t.getNroAmonestaciones());
        conductorExistente.setPromedioCalificacion(t.getPromedioCalificacion());
        conductorExistente.setPlaca(t.getPlaca());
        return dao.save(conductorExistente);
    }

    public List<Conductor> clasificarConductoresPorCiudad(String ciudad) throws DriverNotFoundException {
        List<Conductor> conductores = dao.findByCiudad(ciudad);
        if (!conductores.isEmpty()){
            return conductores;
        } else throw new DriverNotFoundException("No hay conductores de " + ciudad);
    }

    public List<Conductor> clasificarConductoresPorPromedio(float promedio) throws DriverNotFoundException {
        List<Conductor> conductores = dao.findByPromedioCalificacionGreaterThan(promedio);
        if (!conductores.isEmpty()) {
            return conductores;
        } else throw new DriverNotFoundException("No hay conductores con promedio igual a " + promedio);
    }

    public List<Conductor> clasificarPorMaxCalificacion(float calificacion) throws DriverNotFoundException {
        List<Conductor> conductores = dao.findByMaxCalificacion(calificacion);
        if (!conductores.isEmpty()) {
            return conductores;
        } else throw new DriverNotFoundException("No hay conductores con menos de " + calificacion + " de calificación");
    }

    public List<Conductor> clasificarPorMinCalificacion(float calificacion) throws DriverNotFoundException {
        List<Conductor> conductores = dao.findByMinCalificacion(calificacion);
        if (!conductores.isEmpty()) {
            return conductores;
        } else throw new DriverNotFoundException("No hay conductores con más de " + calificacion + " de calificación");
    }

    public List<Conductor> clasificarPorMaxAmonestaciones(int amonestaciones) throws DriverNotFoundException {
        List<Conductor> conductores = dao.findByMaxAmonestaciones(amonestaciones);
        if (!conductores.isEmpty()) {
            return conductores;
        } else throw new DriverNotFoundException("No hay conductores con menos de " + amonestaciones + " amonestaciones");
    }

    public List<Conductor> clasificarPorMinAmonestaciones(int amonestaciones) throws DriverNotFoundException {
        List<Conductor> conductores = dao.findByMinAmonestaciones(amonestaciones);
        if (!conductores.isEmpty()) {
            return conductores;
        } else throw new DriverNotFoundException("No hay conductores con más de " + amonestaciones + " amonestaciones");
    }

    public List<Conductor> clasificarPorMaxFelicitaciones(int felicitaciones) throws DriverNotFoundException {
        List<Conductor> conductores = dao.findByMaxFelicitaciones(felicitaciones);
        if (!conductores.isEmpty()) {
            return conductores;
        } else throw new DriverNotFoundException("No hay conductores con menos de " + felicitaciones + " felicitaciones");
    }

    public List<Conductor> clasificarPorMinFelicitaciones(int felicitaciones) throws DriverNotFoundException {
        List<Conductor> conductores = dao.findByMinFelicitaciones(felicitaciones);
        if (!conductores.isEmpty()) {
            return conductores;
        } else throw new DriverNotFoundException("No hay conductores con más de " + felicitaciones + " felicitaciones");
    }
}
