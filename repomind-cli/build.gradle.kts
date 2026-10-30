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
        project(":repomind-generators")
    )

    implementation(
        "info.picocli:picocli:4.7.7"
    )
}

application {

    mainClass.set(
        "pl.piotr.repomind.cli.Main"
    )
}