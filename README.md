# Sistema de Facturación

Este proyecto es un sistema de facturación basado en una arquitectura de tres capas, con soporte para operaciones CRUD en todas las entidades principales: Client, Product y Sale. Además, se utiliza DTO para la transferencia de datos y está documentado mediante Swagger/OpenAPI.

## Tecnologías utilizadas
Spring Boot - Framework para la creación de aplicaciones Java.
Hibernate - Implementación JPA para la persistencia de datos.
Swagger / Springdoc OpenAPI - Para la generación de la documentación de la API.
H2 Database (o cualquier otra base de datos soportada por JPA).
Arquitectura
### El sistema está basado en una arquitectura de tres capas:

Capa de Controlador: Expone los endpoints RESTful que permiten interactuar con la API. Cada entidad tiene su propio controlador con métodos para obtener todos los registros (GET), obtener por ID (GET), crear (POST) y eliminar (DELETE).

Capa de Servicio: Sirve de intermediario entre los controladores y el repositorio.

Capa de Repositorio: Se encarga de la persistencia y recuperación de datos utilizando JPA/Hibernate.

DTO (Data Transfer Object): Utilizados para manejar la transferencia de datos entre el frontend y la API, optimizando el manejo de la información.

## Entidades
### Client (Cliente)
Representa a un cliente en el sistema.

### Atributos:

id (int): Identificador único del cliente.
name (String): Nombre del cliente.
lastName (String): Apellido del cliente.
docNumber (String): Número de documento del cliente.
sales (List<Sale>): Lista de ventas asociadas al cliente.
Relaciones:

Uno a muchos con Sale.

### Product (Producto)
Representa un producto disponible para la venta en el sistema.

### Atributos:

id (int): Identificador único del producto.
description (String): Descripción del producto.
code (String): Código único del producto.
stock (int): Cantidad de unidades disponibles.
price (double): Precio del producto.
sales (List<Sale>): Lista de ventas asociadas al producto.
Relaciones:

Uno a muchos con Sale.
### Sale (Venta)
Representa una transacción de venta en el sistema.

### Atributos:

id (int): Identificador único de la venta.
client (Client): Cliente que realizó la compra.
product (Product): Producto vendido.
quantity (int): Cantidad del producto vendido.
saleDate (Date): Fecha en la que se realizó la venta.
Relaciones:

Muchos a uno con Client y Product.
## Endpoints
Cada entidad cuenta con los siguientes endpoints:

GET /[entidad]: Obtener todos los registros de la entidad.
GET /[entidad]/{id}: Obtener un registro por su ID.
POST /[entidad]: Crear un nuevo registro.
DELETE /[entidad]/{id}: Eliminar un registro por su ID.
Ejemplos de endpoints:
GET /clients: Obtener todos los clientes.
GET /products/{id}: Obtener un producto por su ID.
POST /sales: Registrar una nueva venta.
DELETE /clients/{id}: Eliminar un cliente y todas sus ventas asociadas.
Configuración
Clonar el repositorio.
Configurar la base de datos en el archivo application.properties (por defecto, está configurado para usar H2).
Iniciar la aplicación.
Documentación de la API
La API está documentada mediante Swagger. Para acceder a la documentación interactiva, inicie la aplicación y visite la siguiente URL: http://localhost:8080/swagger-ui/index.html

Para visualizar la base de datos ingresar a http://localhost:8080/h2-console/login.jsp?jsessionid=2d6dce7db7b10ab613b2f1ff7cde8f20
