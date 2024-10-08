# Sistema de Facturación

Este proyecto es un sistema de facturación basado en una arquitectura de tres capas, con soporte para operaciones CRUD en todas las entidades principales: Client, Product y Sale. Además, se utiliza DTO para la transferencia de datos y está documentado mediante Swagger/OpenAPI.

## Funcionalidad esperada

Al eliminar un cliente se eliminan sus ventas asociadas.

Al eliminar un producto se eliminan sus ventas asociadas.

Al eliminar una venta solo se elimina la venta.

Luego todos los GET/POST funcionan del mismo modo, guardar, buscar todos o buscar por ID.

### Ejemplos POST
Post /clients
{
  "name": "John",
  "lastName": "Doe",
  "docNumber": "12345678"
}


Post /products
{
  "description": "Laptop",
  "code": "PROD123",
  "stock": 50,
  "price": 1999.99
}


Post /sales 
{
  "clientId": 1,
  "productId": 1,
  "quantity": 5,
  "saleDate": "2023-08-15"
}



## Tecnologías utilizadas
Spring Boot - Framework para la creación de aplicaciones Java.
Hibernate - Implementación JPA para la persistencia de datos.
Swagger / Springdoc OpenAPI - Para la generación de la documentación de la API.
H2 Database.

## Arquitectura
### El sistema está basado en una arquitectura de tres capas:

Capa de Controlador: Expone los endpoints RESTful que permiten interactuar con el web service. Cada entidad tiene su propio controlador con métodos para obtener todos los registros (GET), obtener por ID (GET), crear (POST) y eliminar (DELETE).

Capa de Servicio: Sirve de intermediario entre los controladores y el repositorio. Contiene la lógica de negocio.

Capa de Repositorio: Se encarga de la persistencia y recuperación de datos utilizando JPA/Hibernate.

(Adicionalmente, utilicé DTO (Data Transfer Object) para manejar la transferencia de datos entre el frontend y la API, optimizando el manejo de la información.)

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

La API está documentada mediante Swagger. Para acceder a la documentación interactiva, inicie la aplicación y visite la siguiente URL: http://localhost:8080/swagger-ui/index.html

Para visualizar la base de datos ingresar a http://localhost:8080/h2-console/login.jsp?jsessionid=2d6dce7db7b10ab613b2f1ff7cde8f20 (En application.properties están las credenciales)
