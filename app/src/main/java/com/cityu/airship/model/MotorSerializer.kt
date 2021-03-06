package com.cityu.airship.model

class MotorSerializer {

    fun serializeBytes(motors: List<Motor>): ByteArray {
        return (listOf(255.toByte()) + motors.map { motor -> motor.getByte() }).toByteArray()
    }

    fun serializeString(motors: List<Motor>): String {
        return motors.joinToString("\n") { motor -> motor.speed.toString()}
    }

}
