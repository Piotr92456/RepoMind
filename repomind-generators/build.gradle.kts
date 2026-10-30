plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(":repomind-core"))
    implementation(project(":repomind-plugin-api"))

    implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.13.4")
}