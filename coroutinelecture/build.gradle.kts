plugins {
    kotlin("jvm") version "2.0.20"
}

group = "sample"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)

}

//tasks.withType<JavaExec> {
//    jvmArgs("-Dkotlinx.coroutines.debug")
//}
