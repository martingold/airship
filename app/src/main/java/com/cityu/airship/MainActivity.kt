package com.cityu.airship

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.SeekBar
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
            flightModel.setDegree(angle, strength / 100.0);
            debugText.text = motorSerializer.serializeString(flightModel.motors)
            ble.sendData(motorSerializer.serializeBytes(flightModel.motors))
        }, 200)

        upButton.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                flightModel.up()
            } else if(motionEvent.action == MotionEvent.ACTION_UP) {
                flightModel.hover()
            }
            debugText.text = motorSerializer.serializeString(flightModel.motors)
            ble.sendData(motorSerializer.serializeBytes(flightModel.motors))
        }

        downButton.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                flightModel.down()
            } else if(motionEvent.action == MotionEvent.ACTION_UP) {
                flightModel.hover()
            }
            debugText.text = motorSerializer.serializeString(flightModel.motors)
            ble.sendData(motorSerializer.serializeBytes(flightModel.motors))
        }

        hoverLevel.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                flightModel.hoverLevel = progress / 100.0
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                debugText.text = motorSerializer.serializeString(flightModel.motors)
                ble.sendData(motorSerializer.serializeBytes(flightModel.motors))
            }
        })

        ble.connect(this, deviceAddress, object: BLEConnection.ConnectionChangedListener {
            override fun onConnectionChanged(connected: Boolean) {

            }
        })
    }
}
