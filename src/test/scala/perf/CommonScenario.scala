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

  //шаги по оформлению покупки билета
  val buyTicket = group("buyTicket"){
    exec(reservationsPlFlightInformation)
      .exec(reservationsPlChooseTicket)
        .exec(reservationsPlBuyTicket)
  }

  //переход на страницу с купленными билетами
  val allTicketsPage = group("allTicketsPage") {
    exec(welcomePlItinerary)
      .exec(navPlItinerary)
        .exec(itineraryPl)
  }

  //выход из системы
  val signOff = group("signOff") {
    exec(welcomePlSignOff)
  }

  //вызов основного сценария нагрузки
  val mainScenario = scenario("mainScenario")
    .feed(Feeders.users)
    .feed(Feeders.departmentCities)
    .feed(Feeders.arrivalCities)
    .exec(open)
    .exec(login)
    .exec(flights)
    .exec(buyTicket)
    .exec(allTicketsPage)
    .exec(signOff)
}
