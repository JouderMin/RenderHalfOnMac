plugins {
    id("dev.architectury.loom").version("1.9-SNAPSHOT")
}

group = "io.github.joudermin"
version = "1.0-SNAPSHOT"

loom {
    forge {
        mixinConfigs("render-half-on-mac.mixins.json")
    }
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
}
val minecraft_version = "1.20.1"
val yarn_mappings = "1.20.1+build.10"
val forge_version = "1.20.1-47.1.3"
val mixinextras_version = "0.4.1"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:${minecraft_version}")
    mappings("net.fabricmc:yarn:${yarn_mappings}:v2")
    forge("net.minecraftforge:forge:${forge_version}")

    annotationProcessor("io.github.llamalad7:mixinextras-common:${mixinextras_version}")?.let { implementation(it) }
    include("io.github.llamalad7:mixinextras-forge:${mixinextras_version}")?.let { implementation(it) }
//    forge("net.minecraftforge:forge:${forge_version}")
}
