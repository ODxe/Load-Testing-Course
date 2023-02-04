package perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Actions {

  //-------------------GET METHODS-------------------------

  val welcome = http("/webtours/")
    .get("/webtours/")
    .check(status is 200)

  val welcomePl = http("/cgi-bin/welcome.pl?signOff=true"/* Название в отчете */)
    .get("/cgi-bin/welcome.pl")
    .queryParam("signOff", "true")
    .check(status is 200)

  val navPl = http("/cgi-bin/nav.pl?in=home")
    .get("/cgi-bin/nav.pl")
    .queryParam("in", "home")
    .check(status is 200)
    .check(regex("""name="userSession" value="(.+)"""").saveAs("userSession")) //сохраняем значение сессии пользователя в переменной saveAs

  //------------------------------POST METHODS----------------------------------

  val loginPl = http("/cgi-bin/login.pl")
    .post("/cgi-bin/login.pl")
    .formParam("userSession", "${userSession}")
    //login и пароль берутся из csv файла фидера и подставляются на место переменных login и password
    .formParam("username", "${login}")
    .formParam("password", "${password}")
    .formParam("login.x", "19")
    .formParam("login.y", "11")
    .formParam("JSFormSubmit", "off")
    .check(status is 200)
}

