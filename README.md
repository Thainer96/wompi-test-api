# TEST Automation API WOMPI

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](URL_DEL_CI/CD)
[![Coverage Status](https://img.shields.io/badge/coverage-90%25-green)](URL_DEL_REPORTE_DE_COBERTURA)
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)

Este proyecto te permite ejecutar las APIS disponibles, por medio de pruebas REST utilizando java y cucumber con rest assured.

## Tabla de Contenidos

*   [Descripción](#descripción)
*   [Casos automatizados](#casos-automatizados)
*   [Tecnologías Utilizadas](#tecnologías-utilizadas)
*   [Requisitos Previos](#requisitos-previos)
*   [Configuración del Proyecto](#configuración-del-proyecto)
*   [Contacto](#contacto)

## Descripción

Este proyecto realiza el test de APIS en dos de sus metodos GET y PUT, para el manejo de autenticación y registro
de transacciones y sus tipos, basado en el patron de diseño POM con la libreria REST ASSURED, bajo lenguaje de 
programación JAVA.

## casos-automatizados

se realizo la automatización de los siguientes casos en lenguaje Gherkin:

![img_1.png](img_1.png)

## Tecnologías Utilizadas

*   Java (versión 17.0.10)
*   Maven/Gradle (versión 3.9.6)
*   Rest Assured (versión 5.5.0)
*   Cucumber (versión 7.11.1)
*   JUnit (versión 4.13.2)

## Requisitos Previos

*   JDK instalado
*   Maven instalado
*   Allure Instalado

## Configuración del Proyecto

1.  Clonar el repositorio:

    ```bash
    git clone https://github.com/Thainer96/wompi-test-api.git
    ```

2.  Navegar al directorio del proyecto:

    ```bash
    cd src
    ```

3.  Construir el proyecto con Maven/Gradle:

    ```bash
    mvn clean install  # Maven
    ```

## Ejecución de las Pruebas

### Pruebas API

```bash
mvn test # Maven
```

### Ejecución de reporte de pruebas

```bash
mvn allure:report
mvn allure:serve 
```
