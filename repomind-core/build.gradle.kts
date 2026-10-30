plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.github.javaparser:javaparser-core:3.27.1")

    testImplementation("org.junit.jupiter:junit-jupiter:5.13.4")
}