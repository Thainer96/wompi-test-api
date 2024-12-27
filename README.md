# TEST Automation API WOMPI

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](URL_DEL_CI/CD)
[![Coverage Status](https://img.shields.io/badge/coverage-90%25-green)](URL_DEL_REPORTE_DE_COBERTURA)
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)

Este proyecto te permite ejecutar las APIS disponibles, por medio de pruebas REST utilizando java y cucumber con rest assured.

## Tabla de Contenidos

*   [Descripción](#descripción)
*   [Tecnologías Utilizadas](#tecnologías-utilizadas)
*   [Requisitos Previos](#requisitos-previos)
*   [Configuración del Proyecto](#configuración-del-proyecto)
*   [Ejecución de las Pruebas](#ejecución-de-las-pruebas)
    *   [Pruebas Unitarias](#pruebas-unitarias)
    *   [Pruebas de Integración (Rest Assured)](#pruebas-de-integración-rest-assured)
    *   [Pruebas BDD (Cucumber/JBehave)](#pruebas-bdd-cucumberjbehave)
*   [Validaciones](#validaciones)
*   [Context Test (Si aplica)](#context-test-si-aplica)
*   [Ejemplos de Uso (Opcional)](#ejemplos-de-uso-opcional)
*   [Documentación de la API (Opcional)](#documentación-de-la-api-opcional)
*   [Contribución](#contribución)
*   [Licencia](#licencia)
*   [Contacto](#contacto)

## Descripción

Este proyecto realiza el test de APIS en dos de sus metodos GET y PUT, para el manejo de autenticación y registro
de transacciones y sus tipos, basado en el patron de diseño POM con la libreria REST ASSURED, bajo lenguaje de 
programación JAVA.


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
