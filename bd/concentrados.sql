create database concentrados;
use concentrados;

create table rol(
	id_rol int primary key auto_increment,
    nombre varchar(50) not null unique
);

create table usuario(
	id_usuario int not null primary key auto_increment,
    usuario varchar(50) unique not null unique,
    contrasenia varchar(100) not null,
    id_rol int not null,
    constraint fk_UsuarioRol foreign key (id_rol) references rol(id_rol)
);

create table empleado(
	id_empleado int not null primary key auto_increment,
    cod_empleado int not null unique,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    direccion varchar(250) not null,
    telefono varchar(20) not null,
    id_rol int not null,
    id_usuario int not null,
    constraint fk_EmpleadoUsuario foreign key (id_usuario) references usuario(id_usuario) on delete cascade on update cascade
);

create table contacto(
	id_contacto int primary key auto_increment,
    cod_contacto int not null unique,
    nombre varchar(50) not null,
    direccion varchar(50) not null,
    telefono varchar(50),
    nit varchar(40)
);

create table proveedor(
	id_proveedor int not null primary key auto_increment,
    nombre varchar(50) not null,
    direccion varchar(250) not null,
    telefono varchar(50),
    nit varchar(40) not null unique,
    ncf varchar(50) not null,
    giro varchar(90) not null,
    id_contacto int not null,
    constraint fk_ProveedorContacto foreign key (id_contacto) references contacto(id_contacto) on delete cascade on update cascade
);

create table categoria(
	id_categoria int not null primary key auto_increment,
    nombre varchar(50) not null
);

create table producto(
	id_producto int not null primary key auto_increment,
    cod_producto int not null unique,
    nombre varchar(90) not null,
    descripcion varchar(250) not null,
    precio double not null,
    id_categoria int not null,
    constraint fk_ProductoCategoria foreign key (id_categoria) references categoria(id_categoria) on delete cascade on update cascade
);

create table inventario(
	id_inventario int not null primary key auto_increment,
    id_producto int not null,
    cantidad_stock int not null,
    constraint fk_InventarioProducto foreign key (id_producto) references producto(id_producto) on delete cascade on update cascade
);

create table compra(
	id_compra int not null primary key auto_increment,
    fecha timestamp not null,
    id_proveedor int not null,
    constraint fk_CompraProveedor foreign key (id_proveedor) references proveedor(id_proveedor) on delete cascade on update cascade
);

create table venta(
	id_venta int not null primary key auto_increment,
    fecha timestamp not null,
    id_contacto int not null,
    constraint fk_VentaContacto foreign key (id_contacto) references contacto(id_contacto) on delete cascade on update cascade
);

create table compraProducto(
	id_compraProducto int not null primary key auto_increment,
	id_producto int not null,
    cantidad int not null,
    constraint fk_CompproProducto foreign key (id_producto) references producto(id_producto) on delete cascade on update cascade
);

create table ventaProducto(
	id_ventaProducto int not null primary key auto_increment,
	id_producto int not null,
    cantidad int not null,
    constraint fk_VentproProducto foreign key (id_producto) references producto(id_producto) on delete cascade on update cascade
);
s