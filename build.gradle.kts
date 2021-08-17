import org.gradle.api.tasks.testing.logging.TestLogEvent.*
plugins {
  java
  application
//  id("com.github.johnrengelman.shadow") version "6.0.0"
//  id("io.dotinc.vertx-codegen-plugin") version "0.1.1"
//  kotlin("kapt") version "1.5.21"
}

group = "service.proxy.issue"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
}

val vertxVersion = "4.1.2"
val junitJupiterVersion = "5.7.0"

val mainVerticleName = "service.proxy.issue.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

//application {
//  mainClass.set(launcherClassName)
//}

dependencies {
  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("io.vertx:vertx-service-proxy")
  compileOnly("io.vertx:vertx-codegen")
  implementation("io.vertx:vertx-web")
  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
//  annotationProcessor("io.vertx:vertx-codegen:")
//  annotationProcessor("io.vertx:vertx-service-proxy:")
//  kapt("io.vertx:vertx-service-proxy:4.1.2:processor")
//  kapt("io.vertx:vertx-codegen:4.1.2:processor")
  annotationProcessor("io.vertx:vertx-codegen:4.1.2:processor")
  annotationProcessor("io.vertx:vertx-service-proxy:4.1.2")
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

//tasks.withType<ShadowJar> {
//  archiveClassifier.set("fat")
//  manifest {
//    attributes(mapOf("Main-Verticle" to mainVerticleName))
//  }
//  mergeServiceFiles()
//}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}

tasks.withType<JavaExec> {
  args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}

