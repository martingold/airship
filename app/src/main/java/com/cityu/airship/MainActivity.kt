package com.cityu.airship

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cityu.airship.model.FlightModel
import com.cityu.airship.model.MotorSerializer
import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity() {

    private val deviceAddress: String = "A4:D5:78:13:33:8C"
    private val ble: BLEConnection = BLEConnection()
    private val flightModel = FlightModel()
    private val motorSerializer = MotorSerializer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        joystick.setOnMoveListener({ angle, strength ->
            flightModel.setDegree(angle)
            debugText.text = motorSerializer.serializeString(flightModel.motors)
//            ble.sendData(motorSerializer.serializeBytes(flightModel.motors).dropLast(2).toByteArray())
            ble.sendData(byteArrayOf(flightModel.motors[0].getByte()))
            ble.sendData(byteArrayOf(flightModel.motors[1].getByte()))
            ble.sendData(byteArrayOf(flightModel.motors[2].getByte()))

            Log.i("app", flightModel.motors[0].getByte().toString())
        }, 500)

        ble.connect(this, deviceAddress, object: BLEConnection.ConnectionChangedListener {
            override fun onConnectionChanged(connected: Boolean) {

            }
        })
    }
}
