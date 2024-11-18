package com.example.unsecuredseguros.controller

import com.example.unsecuredseguros.model.Seguro
import com.example.unsecuredseguros.services.SeguroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/seguros")
class SeguroController {



    @Autowired
    private lateinit var seguroService: SeguroService


    @GetMapping("/")
    fun obtenerTodos(): List<Seguro> {
        return seguroService.obtenerTodos()

    }

    @GetMapping("/{id}")
    fun obtenerPorId(
        @PathVariable id: Int
    ): Seguro {
        return seguroService.obtenerPorId(id)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Seguro no encontrado")


        // todo
        //exo por Diego
        // return ResponseEntity<Any>("id ${id}no puede ser null , HttpStatus.BAD_REQUEST")
        //segunda forma que podemos encontrar
        // return ResponseEntity<Any>(Seguro ,HttpStatus.OK) -->  En esta parte esta ok correcto

        //


    }

    @PostMapping( "/")
    @ResponseStatus(HttpStatus.CREATED) // --> 201 cuando la creacion es exitosa
    fun crearSeguro(
        @RequestBody seguro: Seguro
    ): Seguro {
        return seguroService.guardarSeguro(seguro)

    }

    @PutMapping("/{id}")
    fun actualizarSeguro(
        @PathVariable id: Int,
        @RequestBody seguro: Seguro
    ): Seguro {
        return seguroService.actualizarSeguro(id, seguro)
             ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Seguro no encontrado")




    }

    @DeleteMapping("/{id}")
    //@ResponseStatus(HttpStatus.OK) --> 204 cuando la eliminacion es exitosa
    fun eliminarSeguro(
        @PathVariable id: Int
    ){
      if (!seguroService.eliminarSeguro(id)) {
           throw ResponseStatusException(HttpStatus.NOT_FOUND, "Seguro no encontrado")
        }
    }







}


