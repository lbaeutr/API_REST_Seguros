package com.example.unsecuredseguros.services

import com.example.unsecuredseguros.model.Seguro
import com.example.unsecuredseguros.repository.SeguroRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated


@Service
@Validated // TODO tenemos que preguntar esto a diego
class SeguroService {

    @Autowired
    private lateinit var seguroRepository: SeguroRepository

    fun validarSeguro(seguro: Seguro) {
        if (seguro.edad < 18) {
            throw IllegalArgumentException("La edad no puede ser menor de 18")
        }

        if (seguro.casado == false && seguro.numHijos > 0) {
            throw IllegalArgumentException("Si no está casado, el número de hijos debe ser igual a 0.")
        }

        if (seguro.embarazada == true && seguro.sexo != "Mujer") {
            throw IllegalArgumentException("Si está embarazada, el sexo debe ser mujer.")
        }

    }


    fun  guardarSeguro(seguroNuevo: Seguro): Seguro {
        validarSeguro(seguroNuevo)
        return seguroRepository.save(seguroNuevo)
    }


    fun obtenerTodos(): List<Seguro>{
        return seguroRepository.findAll()
    }

    fun obtenerPorId(id: Int): Seguro? {
        return seguroRepository.findById(id).orElse(null)
    }
    fun eliminarSeguro(id: Int): Boolean {

        val seguro = seguroRepository.findById(id)

        return if (seguro.isPresent) {
            seguroRepository.delete(seguro.get())
            true
        } else {
            false
        }
    }

    // todo mirar esto de aqui abajo
    fun actualizarSeguro(id: Int, seguroActualizado: Seguro): Seguro {
        if (!seguroRepository.existsById(id)) {
            throw IllegalArgumentException("El seguro con id $id no existe.")
        }
        validarSeguro(seguroActualizado)
        seguroRepository.save(seguroActualizado.copy(idSeguro = id))
        return seguroActualizado
    }


}