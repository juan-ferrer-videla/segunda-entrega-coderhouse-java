# Entrega proyecto final

Para poder correr el proyecto deberas clonar este repositorio.
Crear una base de datos de MySQL llamada "abmcoder".

Asegurarte de modificar la configuración para el usuario y contraseña de la base de datos. 

Actualmente esta utilizando como usuario "root" y contraseña "password", recuerda ponerle los datos de tu usuario y contraseña.

Necesitaras utilizar la version 17 de Java.

# Rutas

##  Clientes

###  Obtener todos los clientes  
GET /api/v1/auth

### Obtener un cliente  
GET /api/v1/auth/{id}

### Crear un cliente  
POST /api/v1/auth/register  
Ejemplo JSON {"name": "juan", "lastname": "perez", "dni": 123}

### Borrar un cliente por ID  
DELETE /api/v1/auth/{id}

### Modificar un cliente por ID  
PATCH /api/v1/auth/me/{id}  
Ejemplo JSON {"name":"pepito"}

## Productos

###  Obtener todos los productos
GET /api/v1/products

### Obtener un producto
GET /api/v1/products/{id}

### Crear un product
POST /api/v1/products  
Ejemplo JSON {"name": "Remera", "stock": 1, "price": 12000}

### Borrar un producto por ID
DELETE /api/v1/products/{id}

### Modificar un producto por ID
PATCH /api/v1/products/{id}  
Ejemplo JSON {"name":"Pantalon"}

## Carrito

### Crear un carrito  
POST /api/v1/carts  
Ejemplo JSON
{
"amount": 4,
"product": {
"id": 1
},
"client": {
"id": 1
}
}  
En caso de que existan productos con ese nombre en el carrito del cliente va a añadir actualizar
el recuso y añadirle una unidad, en caso de que no existan productos con ese nombre va a crear un recurso nuevo.


### Borrar un producto del carrito

PUT /api/v1/carts
Ejemplo JSON {
"clientId":1,
"productId":1
}  
En caso de haber un muchos productos para ese cliente en ese carrito va a descontar una unidad, si solo existe una unidad va a eliminar el recurso.

### Ver todos los carritos de todos los clientes
GET /api/v1/carts

### Borrar todos los productos del carrito de un cliente
DELETE /api/v1/carts/{id}

### Ver el carrito de un cliente por el ID
/api/v1/carts/1

## Invoice

### Crear un invoice
POST  /api/v1/invoices
Ejemplo JSON: 1  
Va a crear un carrito para el clientId 1

### Leer todos los invoices
GET  /api/v1/invoices

### Leer los invoices de un cliente por ID
GET  /api/v1/invoices/client/1

### Leer el ultimo invoice de un cliente por ID
GET  /api/v1/invoices/client/last/1