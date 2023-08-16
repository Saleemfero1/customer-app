import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.8.22"
}

group = "com.reactive"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	//R2DBC
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	//VALIDATION
	implementation ("org.springframework.boot:spring-boot-starter-validation")

	//TEST
	testImplementation ("org.springframework.boot:spring-boot-starter-test")
	testImplementation ("io.projectreactor:reactor-test")
	testImplementation("org.mockito:mockito-core:5.2.0")
	testImplementation("org.mockito:mockito-inline:5.2.0")

	//MYSQL
	implementation("io.r2dbc:r2dbc-spi:1.0.0.RELEASE")
	runtimeOnly ("mysql:mysql-connector-java")
	implementation( "com.github.jasync-sql:jasync-r2dbc-mysql:2.1.16")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
