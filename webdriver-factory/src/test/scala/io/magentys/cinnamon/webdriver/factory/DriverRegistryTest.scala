package io.magentys.cinnamon.webdriver.factory

import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.{Platform, WebDriver}
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}

class DriverRegistryTest extends FlatSpec with Matchers with MockitoSugar{

  behavior of "DriverRegistry"

  it should "handle new drivers added by user" in {
    val testCapabilities = new DesiredCapabilities("TEST", "", Platform.ANY)
    DriverRegistry.addDriverProvider(testCapabilities, classOf[ATestDriver].getName)
    val actual: WebDriver = WebDriverFactory().getDriver(testCapabilities, None, None)
    actual shouldBe a [ATestDriver]
  }

  it should "return an instance of local firefox webDriver given firefox desired capabilities" in {
    val firefoxDesiredCapabilities = DesiredCapabilities.firefox
    val actual = DriverRegistry.locals.hasMappingFor(firefoxDesiredCapabilities)
    actual shouldBe true
  }

//  it should "return an instance of xxx" in {
//    val a = mock[DefaultDriverFactory]
//    when(a.newInstance(any[DesiredCapabilities])).thenReturn(ChromeDriver)
//    val b = DriverRegistry.locals.newInstance(DesiredCapabilities.chrome())
//    b shouldBe a [ChromeDriver]
//  }
}
