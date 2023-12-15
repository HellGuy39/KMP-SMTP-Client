package screen

sealed class Route {

    data object Main: Route()

    data object CreateMail: Route()

}