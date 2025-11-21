# Sistema de Gestión de Gimnasio

[![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/josue770/proyecto-de-aula)

## Descripción

Sistema de gestión integral para gimnasios desarrollado en Java con interfaz gráfica Swing. Permite administrar clientes, entrenadores, entradas/salidas y generar reportes de ingresos y gastos.

## Características Principales

- Gestión de Clientes: Registro, actualización y seguimiento de membresías
- Gestión de Entrenadores: Administración de personal con especialidades dinámicas
- Control de Entradas/Salidas: Registro de acceso de clientes
- Sistema de Reportes: Análisis de ingresos, gastos y ganancias netas
- Validación de Datos: Validación completa de formularios
- Datos Compartidos Globales: Sincronización de datos entre ventanas

## Estructura del Proyecto

```
demo/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    ├── Main.java
                    ├── model/
                    │   ├── Cliente.java
                    │   ├── Entrada.java
                    │   ├── Entrenador.java
                    │   └── Constantes.java
                    ├── controller/
                    │   ├── ClienteController.java
                    │   ├── EntradaController.java
                    │   ├── EntrenadorController.java
                    │   └── ValidacionController.java
                    ├── view/
                    │   ├── MenuPrincipal.java
                    │   ├── RegistroClienteView.java
                    │   ├── RegistroEntradaView.java
                    │   ├── RegistroSalidaView.java
                    │   ├── ConsultarPreciosView.java
                    │   ├── EntrenadoresView.java
                    │   ├── RecepcionView.java
                    │   └── ReportesView.java
                    └── util/
                        └── DatosGlobales.java
```

## Componentes

### Model

**Cliente.java**
- ID, nombre, teléfono, dirección, correo
- Tipo de membresía (Mensual, Semanal, Diaria)
- Fechas de inicio y vencimiento
- Cálculo de días restantes

**Entrenador.java**
- Información personal
- Especialidad personalizable
- Salario y fecha de contratación
- Estado activo/inactivo

**Entrada.java**
- Registro de entrada de clientes
- Hora y fecha automática
- Tipo de membresía

**Constantes.java**
- Precios de membresías
- Valores de devoluciones
- Tipos de membresía

### Controller

**ClienteController.java**
- Operaciones CRUD de clientes
- Búsqueda por membresía
- Gestión de datos en memoria

**EntrenadorController.java**
- Operaciones CRUD de entrenadores
- Búsqueda por especialidad
- Cálculo de salarios totales
- Gestión de estado

**EntradaController.java**
- Registro y eliminación de entradas
- Búsqueda por tipo de membresía
- Verificación de entradas activas

**ValidacionController.java**
- Validación de ID (8-10 dígitos)
- Validación de email y teléfono
- Validación de fechas (dd/MM/yyyy)
- Mensajes de error descriptivos

**DatosGlobales.java**
- Instancias únicas de controladores
- Patrón Singleton
- Sincronización entre ventanas

### View

**MenuPrincipal.java**
- Menú central de acceso

**RegistroClienteView.java**
- Formulario de registro de clientes
- Cálculo automático de vencimiento

**RegistroEntradaView.java**
- Registro de entrada

**RegistroSalidaView.java**
- Registro de salida
- Visualización de tarifa

**EntrenadoresView.java**
- Gestión de entrenadores
- Especialidades dinámicas
- Tabla con datos en tiempo real

**RecepcionView.java**
- Búsqueda de clientes
- Información de membresía

**ConsultarPreciosView.java**
- Visualización de tarifas

**ReportesView.java**
- Resumen general
- Clientes por membresía
- Entrenadores por especialidad
- Cálculo de ingresos netos

## Validaciones

| Campo | Validación |
|-------|-----------|
| ID | 8-10 dígitos numéricos |
| Nombre | Requerido |
| Email | Formato válido |
| Teléfono | 7-10 dígitos |
| Fecha | Formato dd/MM/yyyy |
| Salario | Número positivo |

## Cálculos de Ingresos

Ingresos Totales:
- Clientes Mensual: cantidad × $70,000
- Clientes Semanal: cantidad × $20,000
- Clientes Diaria: cantidad × $6,000

Ganancia Neta:
```
Ingresos Totales - Total de Salarios
```

## Instalación y Uso

Compilación:
```
mvn clean compile
```

Ejecución:
```
mvn exec:java -Dexec.mainClass="com.example.Main"
```

## Flujo General

El sistema utiliza una arquitectura MVC donde:
- Las vistas comunican con controladores
- Los controladores validan datos
- Los datos se almacenan en memoria mediante ArrayList
- DatosGlobales sincroniza los datos entre todas las ventanas

## Documentación

Documentación técnica completa disponible en:
https://deepwiki.com/josue770/proyecto-de-aula

## Tecnologías

- Java 8+
- Swing (GUI)
- Maven (Build)
- Patrón MVC
- Patrón Singleton

## Autor

Josue Ballesteros Anaya