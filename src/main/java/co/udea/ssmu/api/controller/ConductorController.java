package co.udea.ssmu.api.controller;

import co.udea.ssmu.api.exception.InvalidRating;
import co.udea.ssmu.api.exception.ModelNotFoundException;
import co.udea.ssmu.api.model.Conductor;
import co.udea.ssmu.api.services.ConductorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conductor")
@CrossOrigin(origins = "*")
@Api(value = "Sistema de manejo de conductores", description = "Operaciones con los conductores")
public class ConductorController {
    @Autowired
    private ConductorService conductorService;

    @ApiOperation("Agregar conductor a la BD")
    @PostMapping("/guardar")
    public String guardar(
            @RequestBody Conductor conductor) throws InvalidRating {
        if (conductor.getPromedioCalificacion()>5) {
            throw new InvalidRating("El promedio debe ser menor o igual a 5");
        }
        conductorService.guardar(conductor);
        return ("Se guardó el conductor con el ID " + conductor.getIdConductor());
    }

    @ApiOperation("Lista todos los conductores de la BD")
    @GetMapping("/listarTodos")
    public Iterable<Conductor> listarConductores(){return conductorService.list();}

    @ApiOperation("Listar un conductor mediante su id de conductor")
    @GetMapping("/listar/{id}")
    public Conductor verConductor(@PathVariable("id") int id) {
        Optional<Conductor> conductor = conductorService.listId(id);
        if (conductor.isPresent()){
            return conductor.get();
        }
        throw new ModelNotFoundException("Id de conductor invalida");
    }

    @ApiOperation("Modificar datos de un conductor")
    @PutMapping("/actualizar/{id}")
    public Conductor actualizarService(@RequestBody Conductor conductor){
        return conductorService.actualizar(conductor);
    }

    @ApiOperation("Borrar un conductor de la BD")
    @DeleteMapping("/borrar/{id}")
    public String borrarConductor(@PathVariable int id){
        return conductorService.borrar(id);
    }

    @ApiOperation("Mostrar conductores por ciudad")
    @GetMapping("/clasificarPorCiudad/{ciudad}")
    public ResponseEntity<List<Conductor>> clasificarConductoresPorCiudad(@PathVariable("ciudad") String ciudad) {
        List<Conductor> conductores = conductorService.clasificarConductoresPorCiudad(ciudad);
        return new ResponseEntity<List<Conductor>>(conductores, HttpStatus.ACCEPTED);
    }

    @ApiOperation("Mostrar conductores por promedio de calificación")
    @GetMapping("/clasificarPorPromedio/{promedio}")
    public ResponseEntity<List<Conductor>> clasificarConductoresPorPromedio(@PathVariable("promedio") int promedio) {
        List<Conductor> list = conductorService.clasificarConductoresPorPromedio(promedio);
        return new ResponseEntity<List<Conductor>>(list, HttpStatus.ACCEPTED);
    }

    @ApiOperation("Mostrar conductores con un número máximo de amonestaciones")
    @GetMapping("/porMaxAmonestaciones/{amonestaciones}")
    public ResponseEntity<List<Conductor>> clasificarPorMaxAmonestaciones(@PathVariable("amonestaciones") int amonestaciones) {
        List<Conductor> conductores = conductorService.clasificarPorMaxAmonestaciones(amonestaciones);
        return new ResponseEntity<List<Conductor>>(conductores, HttpStatus.ACCEPTED);
    }

    @ApiOperation("Mostrar conductores con un número mínimo de amonestaciones")
    @GetMapping("/porMinAmonestaciones/{amonestaciones}")
    public ResponseEntity<List<Conductor>> clasificarPorMinAmonestaciones(@PathVariable("amonestaciones") int amonestaciones) {
        List<Conductor> conductores = conductorService.clasificarPorMinAmonestaciones(amonestaciones);
        return new ResponseEntity<List<Conductor>>(conductores, HttpStatus.ACCEPTED);
    }
}

