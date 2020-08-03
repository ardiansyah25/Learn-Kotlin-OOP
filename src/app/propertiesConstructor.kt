package app

import data.User

fun main(){
    val user1 = User("Ardiansyah","12345")
    val user2 = User("joko","34567")

    println(user1.usernameParam)
    println(user2.usernameParam)
}