# MiParqueo App: prototipo JDBC/Servlet

## Identificacion

- Repositorio: https://github.com/probandoel44/Mi-parqueo-JDBC-servlet-main
- Rama revisada: `main`
- Commit base revisado: `28d5d7d8f144b0a4f2c602b1638103e572128a07`
- Es un prototipo Java/JDBC/Servlet, no el aplicativo Spring Boot canónico.
- Referencia integrada recomendada: https://github.com/probandoel44/Desarrollar-software-a-partir-de-la-integraci-n-de-sus-m-dulos-componentes

## Arquitectura

El proyecto usa clases Java, DAOs con JDBC, MySQL Connector/J y `javax.servlet.http.HttpServlet`. Contiene modelos y DAOs para usuarios, vehículos, parques, espacios, reservas, movimientos, pagos, ubicación, notificaciones y fidelización.

La conexión observada usa `jdbc:mysql://localhost:3306/miparqueo_db`, usuario `root` y contraseña vacía solo como configuración local de ejemplo. No añadir credenciales reales ni datos personales al repositorio.

## Servlet observado

`VehiculoServlet` expuesto en `/vehiculos`:

- `GET /vehiculos`: obtiene vehículos y construye una página HTML.
- `POST /vehiculos`: crea o reutiliza un usuario de demostración, genera un código QR textual, crea un vehículo y devuelve el resultado HTML.

Entrada `POST`:

- `placa`: obligatoria.
- `marca`: opcional.
- `modelo`: opcional.

Salida:

- Página HTML con mensaje de éxito o error y enlace para volver.
- Registra un vehículo y usa un usuario creado/reutilizado para la demostración.

Esta ruta no demuestra autenticación real, autorización, reconocimiento OCR/QR físico, barrera automática ni concurrencia.

## Evidencia SENA

| Elemento | Estado |
| --- | --- |
| Requerimientos oficiales | Pendiente: informe y `requerimientos.pdf` no están en el repositorio. |
| Acta de aprobación | Pendiente. |
| Control de versiones | Disponible, rama `main`, commit indicado. |
| Archivos ejecutables | No hay `.exe`, JAR ni WAR verificado. La carpeta `lib/` contiene el conector MySQL. |
| URL desplegada | No hay URL pública ni servidor configurado. |
| Datos de entrada/salida | Se documenta el servlet `/vehiculos`; faltan fichas de todos los DAOs y operaciones históricas. |
| Pruebas | No se revisaron pruebas ni reportes asociados al commit indicado. |
| Servidor/base de datos | Servidor Java/Servlet requerido más un motor MySQL; no se verificó el contenedor ni el despliegue. |
| Ambientes | No existe configuración separada de desarrollo/pruebas revisada. |
| Manual técnico | Esta guía es parcial; falta configuración de servidor, dependencias, compilación y despliegue. |

## Compilacion y despliegue

No hay Maven configurado en este snapshot. El código fuente puede estar preparado para IDE Java/Servlet como Eclipse/NetBeans. La evidencia disponible no permite afirmar un nombre de WAR, puerto o aplicación de servidor.

Antes de ejecutar:

1. Tener Java y un servidor compatible con Servlets.
2. Tener MySQL 8 y la base `miparqueo_db` con tablas compatibles.
3. Colocar el conector `lib/mysql-connector-java-8.0.30.jar` según el servidor/IDE.
4. Configurar las propiedades de conexión fuera del código fuente para evitar credenciales reales.

No se ejecutó ni compiló este prototipo al preparar esta guía.

## Pendientes

1. Definir si el prototipo se conserva como antecesor o se entrega como módulo independiente.
2. Validar el esquema y las operaciones DAO contra una base de pruebas aislada.
3. Agregar autenticación y autorización antes de exponer `/vehiculos`.
4. Diferenciar el QR textual simulado de un lector real y documentar hardware pendiente.
5. Adjuntar informe, actas, pruebas, artefacto, URL y manual de servidor real.
