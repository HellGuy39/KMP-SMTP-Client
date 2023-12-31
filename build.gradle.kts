import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.hellguy39.kmpsmtpclient"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven {
        url = uri("http://repository.aspose.com/repo/")
        isAllowInsecureProtocol = true
    }
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.macos_arm64)
                implementation("org.slf4j:slf4j-api:1.7.5")
                implementation("org.slf4j:slf4j-log4j12:1.7.5")
                implementation("com.aspose:aspose-email:22.4:jdk16")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "KMP-SMTP-Client"
            packageVersion = "1.0.0"
        }
    }
}
