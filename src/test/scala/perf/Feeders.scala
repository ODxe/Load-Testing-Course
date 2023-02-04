package perf

import io.gatling.core.Predef._

object Feeders {
  val users = csv("users.csv").circular //также есть перебор по очереди (queque) и случайный (random)
}