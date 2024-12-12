plugins {
  id("org.sonarqube") version "6.0.1.5171"
}

sonar {
  properties {
    property("sonar.projectKey", "mahwishriazjamil_medialibrary")
    property("sonar.organization", "mahwishriazjamil")
    property("sonar.host.url", "https://sc-staging.io")
  }
}