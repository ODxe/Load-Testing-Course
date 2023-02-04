package perf

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import perf.Actions._

object CommonScenario{
  def apply(): ScenarioBuilder = new CommonScenario().mainScenario
}

class CommonScenario {

  //открытие сайта
  val open = group("open"){
    exec(webtours)
    .exec(welcomePl)
      .exec(navPl)
  }

  //авторизация на сайте
  val login = group("login"){
    exec(loginPl)
      .exec(navPlHome)
        .exec(loginPlIntro)
  }

  //переход на страницу покупки билета
  val flights = group("flights") {
    exec(welcomePlSearch)
      .exec(navPlFlights)
        .exec(reservationsPl)
  }

  //вызов основного сценария
  val mainScenario = scenario("mainScenario")
    .feed(Feeders.users)
    .exec(open)
    .exec(login)
    .exec(flights)
}
