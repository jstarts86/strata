// Root build.gradle.kts
plugins {
    java
}

allprojects {
    group = "com.jstarts.strata"
    version = "1.0-SNAPSHOT"
}
subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.add("--enable-preview")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        jvmArgs("--enable-preview")
    }
    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter:5.12.1")
    }
}
