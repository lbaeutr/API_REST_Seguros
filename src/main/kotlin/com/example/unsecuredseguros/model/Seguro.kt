package com.example.unsecuredseguros.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import org.hibernate.annotations.Check
import java.util.Date


@Entity
@Table (name = "seguros")
data class Seguro(


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idSeguro: Int = 0,

    @Column(nullable = false, length = 10)
    val nif: String= "",


    @Column(nullable = false , length = 100)
    val nombre: String="",

    @Column(nullable = false, length = 100)
    val ape1: String ="",

    @Column(length = 100)
    val ape2: String? = null,

    @Check(constraints = "edad >= 0")  // Todo aqui se podria incorporar COLUMNdEFINITION = "INTEGER CHECK (num_hijos >=0)"
    @Column(nullable = false)
    val edad: Int = 0,

    @Check(constraints = "numHijos > 0")// todo aqui se podria hacer  columnDefinition = "INTEGER CHECK (num_hijos >= 0)")
    @Column(name = "num_hijos",nullable = false)
    val numHijos: Int = 0,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion", nullable = false)
    val fechaCreacion: Date = Date() ,


    @Column(nullable = false, length = 10) // todo pensar si hacer un enum para los generos del sexo ver posibilidades
    val sexo: String = "", // Todo enum para hombres y mujeres y  helicopteros de guerras

    @Column(nullable = false)
    val casado: Boolean = false,

    @Column(nullable = false)
    val embarazada: Boolean = false,
)

