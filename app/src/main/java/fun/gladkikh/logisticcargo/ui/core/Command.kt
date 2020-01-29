package `fun`.gladkikh.logisticcargo.ui.core

sealed class Command {
    object Close : Command()
    object OpenSettings : Command()
    object OpenLogin : Command()
    object OpenMain : Command()

}
