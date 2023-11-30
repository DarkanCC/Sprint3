package co.udea.ssmu.api.dao;

import co.udea.ssmu.api.model.Conductor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConductorDAO extends CrudRepository<Conductor,Integer> {
    @Query("FROM Conductor c WHERE c.ciudadActual = :ciudad")
    public List<Conductor> findByCiudad(@Param("ciudad") String ciudad);

    @Query("FROM Conductor c WHERE c.promedioCalificacion = :promedio")
    public List<Conductor> findByPromedioCalificacionGreaterThan(@Param("promedio") float promedio);

    @Query("FROM Conductor c WHERE c.nroAmonestaciones <= :amonestaciones")
    public List<Conductor> findByMaxAmonestaciones(@Param("amonestaciones") int amonestaciones);

    @Query("FROM Conductor c WHERE c.nroAmonestaciones >= :amonestaciones")
    public List<Conductor> findByMinAmonestaciones(@Param("amonestaciones") int amonestaciones);
}
