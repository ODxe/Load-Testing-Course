package perf

import io.gatling.core.Predef._

object Feeders {
  val users = csv("users.csv").circular
  val departmentCities = csv("departmentCities.csv").shuffle.random
  val arrivalCities = csv("arrivalCities.csv").shuffle.random
}
