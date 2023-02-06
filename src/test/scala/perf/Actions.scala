package perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Actions {

  //-------------------GET METHODS-------------------------

  val webtours = http("/webtours/")
    .get("/webtours/")
    .check(status is 200)

  val welcomePl = http("/cgi-bin/welcome.pl?signOff=true")
    .get("/cgi-bin/welcome.pl")
    .queryParam("signOff", "true")
    .check(status is 200)

  val welcomePlSearch = http("/cgi-bin/welcome.pl?page=search")
    .get("/cgi-bin/welcome.pl")
    .queryParam("page", "search")
    .check(status is 200)

  val navPl = http("/cgi-bin/nav.pl?in=home")
    .get("/cgi-bin/nav.pl")
    .queryParam("in", "home")
    .check(status is 200)
    .check(regex("""name="userSession" value="(.+)"""").saveAs("userSession")) //сохраняем значение сессии пользователя в переменной saveAs

  val navPlHome = http("/cgi-bin/nav.pl?page=menu&in=home")
    .get("/cgi-bin/nav.pl")
    .queryParam("page", "menu")
    .queryParam("in", "home")
    .check(status is 200)

  val navPlFlights = http("/cgi-bin/nav.pl?page=menu&in=flights")
    .get("/cgi-bin/nav.pl")
    .queryParam("page", "menu")
    .queryParam("in", "flights")
    .check(status is 200)

  val loginPlIntro = http("/cgi-bin/login.pl?intro=true")
    .get("/cgi-bin/login.pl")
    .queryParam("intro", "true")
    .check(status is 200)

  val reservationsPl = http("/cgi-bin/reservations.pl?page=welcome")
    .get("/cgi-bin/reservations.pl")
    .queryParam("page", "welcome")
    .check(status is 200)

  //------------------------------POST METHODS----------------------------------

  val loginPl = http("/cgi-bin/login.pl")
    .post("/cgi-bin/login.pl")
    .formParam("userSession", "${userSession}")
    .formParam("username", "${login}")
    .formParam("password", "${password}")
    .formParam("login.x", "19")
    .formParam("login.y", "11")
    .formParam("JSFormSubmit", "off")
    .check(status is 200)

  //Заполнение информации о покупке билета
  val reservationsPlFlightInformation = http("/cgi-bin/reservations.pl (заполнение информации о покупке билета)")
    .post("/cgi-bin/reservations.pl")
    .formParam("advanceDiscount", "0")
    //.formParam("depart", "${city}")
    .formParam("depart", "Paris")
    .formParam("departDate", "02/05/2023")
    //.formParam("arrive", "${city}")
    .formParam("arrive", "San Francisco")
    .formParam("returnDate", "02/15/2023")
    .formParam("numPassengers", "1")
    //.formParam("seatPref", "${seatingPreference}")
    //.formParam("seatType", "${typeOfSeat}")
    .formParam("seatPref", "Window")
    .formParam("seatType", "Business")
    .formParam("findFlights.x", "73")
    .formParam("findFlights.y", "3")
    .check(status is 200)
    //.check(regex("""name="seatPref" value="(.+)"""").saveAs("seatPref"))
    //.check(regex("""name="seatType" value="(.+)"""").saveAs("seatType"))
    //.check(regex("""name="outboundFlight" value="(.+)"""").saveAs("outboundFlight"))

  //Выбор билета из списка
  val reservationsPlChooseTicket = http("/cgi-bin/reservations.pl (выбор билета из списка)")
    .post("/cgi-bin/reservations.pl")
    .formParam("outboundFlight", "460;902;02/05/2023")
    .formParam("numPassengers", "1")
    .formParam("advanceDiscount", "0")
    /*.formParam("seatType", "${seatType}")
    .formParam("seatPref", "${seatPref}") */
    .formParam("seatType", "Business")
    .formParam("seatPref", "Window")
    .formParam("findFlights.x", "54")
    .formParam("findFlights.y", "5")
    .check(status is 200)

  //Завершение покупки билета
  val reservationsPlBuyTicket = http("/cgi-bin/reservations.pl (покупка билета)")
    .post("/cgi-bin/reservations.pl")
    .formParam("firstName", "Oleg")
    .formParam("lastName", "Dronov")
    .formParam("address1", "Krasnoznamenshkaya")
    .formParam("address1", "Voronezh")
    .formParam("pass1", "Oleg Dronov")
    .formParam("creditCard", "3456789011121314")
    .formParam("expDate", "")
    .formParam("oldCCOption", "")
    .formParam("numPassengers", "1")
    /*.formParam("seatType", "${seatType}")
    .formParam("seatPref", "${seatPref}")*/
    .formParam("seatType", "Business")
    .formParam("seatPref", "Window")
    //.formParam("outboundFlight", "${outboundFlight}")
    .formParam("outboundFlight", "460;902;02/05/2023")
    .formParam("advanceDiscount", "0")
    .formParam("returnFlight", "")
    .formParam("JSFormSubmit", "off")
    .formParam("buyFlights.x", "63")
    .formParam("buyFlights.y", "8")
}

