package com.cityu.airship.model

class Motor(val reverse: Boolean) {

    /**
     * Speed in range <-1.0, 1.0>
     */
    var speed = 0.0

    fun getByte(): Byte {
        val motorSpeed = if(reverse) speed else -speed
        return ((motorSpeed + 1.0) * 127).toInt().toByte()
    }

}
