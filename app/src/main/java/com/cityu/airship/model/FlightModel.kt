package com.cityu.airship.model

class FlightModel {

    val motors: List<Motor> = listOf(Motor(true), Motor(false), Motor(false))

    var hoverLevel = 0.4

    init {
        motors[0].speed = 0.0
        motors[1].speed = 0.0
        motors[2].speed = hoverLevel
    }

    /**
     * @param degree in range <0, 360>
     * @param strength in range <0.0, 1.0>
     */
    fun setDegree(degree: Int, strength: Double) {
        motors[0].speed = strength * if (degree !in 90..270) 1.0 else Math.abs((180 - degree) / 45.0) - 1
        motors[1].speed = strength * if (degree in 90..270) 1.0 * strength else 3 - Math.abs(-degree + 180.0) / 45.0
    }

    fun hover() {
        motors[2].speed = hoverLevel
    }

    fun up() {
        motors[2].speed = 1.0
    }

    fun down() {
        motors[2].speed = -1.0
    }

}
