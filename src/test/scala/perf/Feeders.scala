package perf

import io.gatling.core.Predef._

object Feeders {
  val users = csv("users.csv").circular
  //val cities = csv("cities.csv").random
  //val seatingPreference = csv("seatingPreference.csv").random
  //val typeOfSeat = csv("typeOfSeat.csv").random
}
