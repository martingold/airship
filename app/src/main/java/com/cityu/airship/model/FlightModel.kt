package com.cityu.airship.model

class FlightModel {

    val motors: List<Motor> = listOf(Motor(true), Motor(false), Motor(false))

    fun setDegree(degree: Int) {
        motors[0].speed = if (degree !in 90..270) 1.0 else Math.abs((180 - degree) / 45.0) - 1
        motors[1].speed = if (degree in 90..270) 1.0 else 3 - Math.abs(-degree + 180.0) / 45.0
    }



}