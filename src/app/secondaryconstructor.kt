package app

import data.Address

fun main(){
    val address1 = Address("Jalan A","Jakarta")
    val address2 = Address("jalan B","Riau","Indonesia")

    println(address1.street)
    println(address2.street)
}