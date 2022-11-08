import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice(val name:String, val category:String) {

    var deviceStatus = "online"
        protected set
    open val deviceType = "unknown"

    constructor(name: String,category: String,statusCode:Int):this(name,category){
        deviceStatus = when (statusCode){
            0 -> "offline"
            1 -> "online"
            else -> "unknown"
        }
    }

  open fun turnOn(){
      deviceStatus = "on"
  }
    open fun turnOff(){
        deviceStatus = "off"
    }
}

class SmartTVDevice(deviceName:String,deviceCategory:String):SmartDevice(name = deviceName,category = deviceCategory){

    var speakerVolume by RangeRegulator(0,0,100)
    var channelNumber by RangeRegulator(0,0,200)

    override val deviceType = "Smart TV"

    override fun turnOn() {
       super.turnOn()
        println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                    "set to $channelNumber."
        )
    }

    override fun turnOff() {
      super.turnOff()
        println("$name turned off")
    }

    fun increaseSpeakerVolume(){
        speakerVolume++
        println("Speaker Volume increase to $speakerVolume")
    }

    fun decreaseSpeakerVolume(){
        speakerVolume--
        println("Speaker Volume decrease to $speakerVolume")
    }

     fun nextChannel(){
        channelNumber++
        println("Channel number increased to $channelNumber")
    }

    fun previousChannel(){
        channelNumber--
        println("Channel number decreased to $channelNumber")
    }

    fun printDeviceInfo(){
        println("Device name: $name , category: $category, type: $deviceType")
    }
}

class SmartLightDevice(deviceName: String,deviceCategory: String): SmartDevice(name = deviceName,category = deviceCategory){

    override val deviceType = "Smart Light"

    var brightnessLevel by RangeRegulator(2,0,100)

    override fun turnOn() {
       super.turnOn()
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel.")

    }

    override fun turnOff() {
        super.turnOff()
        deviceStatus = "off"
        brightnessLevel = 0
        println("Smart Light turned off")

    }

    fun increaseBrightness(){
        brightnessLevel++
        println("Brightness increase to $brightnessLevel")
    }

    fun decreaseBrightness(){
        brightnessLevel--
        println("Brightness decrease to $brightnessLevel")
    }
}

class SmartHome(
    val smartTVDevice: SmartTVDevice,
    val smartLightDevice: SmartLightDevice
){
    var deviceTurnOnCount = 0
    private set

    fun turnOnTv(){
        deviceTurnOnCount++
        smartTVDevice.turnOn()
    }
    fun turnOffTv(){
        deviceTurnOnCount--
        smartTVDevice.turnOff()
    }
    fun increaseTvVolume() {
        smartTVDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTVDevice.nextChannel()
    }

    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue:Int,
    private val minValue:Int,
    private val maxValue:Int
):ReadWriteProperty<Any?,Int>{

    var fielData = initialValue
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fielData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if(value in minValue..maxValue){
            fielData = value
        }
    }

}

fun main(){

    var smartDevice: SmartDevice = SmartTVDevice("Android TV","Entertainment")
    smartDevice.turnOn()
    smartDevice = SmartLightDevice("Google Light","Utility")
    smartDevice.turnOn()

//  var smartTvDevice = SmartDevice(name = "Android TV",category = "Entertainment")
//    println("Device name is: ${smartTvDevice.name}")
//    smartTvDevice.turnOn()
//    smartTvDevice.turnOff()
}
