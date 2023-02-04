package perf

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import perf.Actions._

object CommonScenario{
  def apply(): ScenarioBuilder = new CommonScenario().mainScenario
}

class CommonScenario {

  //группа симуляций
  val open = group("open"){
    exec(welcome)
    .exec(welcomePl)
      .exec(navPl)
  }

  val login = group("login"){
    exec(loginPl)
  }

  val mainScenario = scenario("mainScenario")
    //подключаем feeder к сценарию (подключается ко всем вызовам exec)
    .feed(Feeders.users)
    .exec(open)
    .exec(login)

}
