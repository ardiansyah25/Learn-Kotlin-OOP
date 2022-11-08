package app

fun main(){
   val morningNotification = 51
    val eveningNotification = 135

    printNotificationSummary(morningNotification)
    printNotificationSummary(eveningNotification)
}

fun printNotificationSummary(numberOfMessage:Int){
    if(numberOfMessage < 100){
        println("you have $numberOfMessage notifications.")
    }else{
        println("Your phone is blowing up! you have 99+ notifications.")
    }
}