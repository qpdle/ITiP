import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("java")
    application //для запуска и указания mainClass
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

//настройка приложения
application {
    mainClass = "org.example.Main"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.20.0")
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("ch.qos.logback:logback-classic:1.5.32")
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

//настройка тестов
tasks.test {
    useJUnitPlatform()
}

//настройка run (чтобы работал ввод с клавиатуры)
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.shadowJar {
    manifest {
        attributes["Main-Class"] = "org.example.Main"
    }
}

//задача printInfo
abstract class PrintInfoTask : DefaultTask() {
    @TaskAction
    fun print() {
        println("=============================")
        println("This is my first user task.!")
        println("Project: ${project.name}")
        println("Version Gradle: ${project.gradle.gradleVersion}")
        println("=============================")
    }
}

//экземпляр задачи
tasks.register<PrintInfoTask>("printInfo") {
    group = "Custom" //группа в списке задач
    description = "Displays information about the project"
}

//задача generateBuildPassport
tasks.register("generateBuildPassport") {
    group = "Custom"
    description = "Generate build-passport.properties"

    doLast {
        val outputDir = file("${buildDir}/resources/main")
        outputDir.mkdirs()
        val outputFile = file("${outputDir}/build-passport.properties")
        val username = System.getenv("USERNAME") ?: System.getenv("USER") ?: "unknown"
        val osName = System.getProperty("os.name")
        val javaVersion = System.getProperty("java.version")
        val buildDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val greeting = "Hi from Gradle!"

        outputFile.writeText(
            "username=$username\n" +
                    "os.name=$osName\n" +
                    "java.version=$javaVersion\n" +
                    "build.date=$buildDate\n" +
                    "greeting=$greeting"
        )
        println("Generated file: ${outputFile.absolutePath}")
    }
}

tasks.named("processResources") {
    dependsOn("generateBuildPassport") //запустить перед processResources
}
