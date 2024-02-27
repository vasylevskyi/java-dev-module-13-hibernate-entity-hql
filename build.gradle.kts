plugins {
    id("java")
}

group = "ua.goit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // https://mvnrepository.com/artifact/com.h2database/h2
    val h2Version = "2.2.224"
    testImplementation("com.h2database:h2:${h2Version}")
    implementation("com.h2database:h2:${h2Version}")

    // https://mvnrepository.com/artifact/org.flywaydb/flyway-core
    val flyWayCore = "10.7.2"
    implementation("org.flywaydb:flyway-core:${flyWayCore}")

    // https://mvnrepository.com/artifact/org.hibernate/hibernate-core
    val hibernateCore = "6.4.4.Final"
    implementation("org.hibernate:hibernate-core:${hibernateCore}")

    // https://mvnrepository.com/artifact/net.n2oapp.criteria/criteria-api
    implementation("net.n2oapp.criteria:criteria-api:7.4.0")

    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    val lombokVersion = "1.18.30"
    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
}

tasks.test {
    useJUnitPlatform()
}