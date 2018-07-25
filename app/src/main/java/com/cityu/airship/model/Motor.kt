package com.cityu.airship.model

class Motor {

    /**
     * Speed in range <-1.0, 1.0>
     */
    var speed = 0.0

    fun getByte(): Byte {
        return ((speed + 1) * 127).toInt().toByte()
    }

}
