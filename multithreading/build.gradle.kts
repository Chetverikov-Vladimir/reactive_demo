plugins {
    java
}

group = "che.vlvl"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.7.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}