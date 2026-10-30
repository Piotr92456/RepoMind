plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {

    implementation(
        project(":repomind-core")
    )

    implementation(
        "org.openjfx:javafx-controls:25"
    )

    implementation(
        "org.openjfx:javafx-fxml:25"
    )
}

application {

    mainClass.set(
        "pl.piotr.repomind.gui.MainApplication"
    )
}