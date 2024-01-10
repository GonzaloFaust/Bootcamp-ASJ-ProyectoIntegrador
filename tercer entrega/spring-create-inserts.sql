CREATE DATABASE spring;
GO
USE spring;

CREATE TABLE countries (
    coun_id TINYINT NOT NULL PRIMARY KEY IDENTITY(1,1),
    coun_name VARCHAR(20) NOT NULL
);

CREATE TABLE states (
    state_id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    coun_id TINYINT NOT NULL REFERENCES countries(coun_id),
    state_name VARCHAR(30) NOT NULL
);

CREATE TABLE cities (
    city_id BIGINT NOT NULL PRIMARY KEY IDENTITY(1,1),
    state_id INT NOT NULL REFERENCES states(state_id),
    city_name VARCHAR(30) NOT NULL,
    city_postcode VARCHAR(10) NOT NULL
);

CREATE TABLE order_status (
    ordst_id TINYINT NOT NULL PRIMARY KEY IDENTITY(1,1),
    ordst_name VARCHAR(20) NOT NULL
);

CREATE TABLE suppliers_contacts (
    sup_contact_id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    sup_contact_name VARCHAR(20) NOT NULL,
    sup_contact_lastname VARCHAR(20) NOT NULL,
    sup_contact_telephone VARCHAR(11) NOT NULL,
    sup_contact_email VARCHAR(40) NOT NULL,
    sup_contact_role VARCHAR(30) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME
);

CREATE TABLE fields (
    field_id TINYINT NOT NULL PRIMARY KEY IDENTITY(1,1),
    field_name VARCHAR(20) NOT NULL,
    field_detail VARCHAR(200) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME
);

CREATE TABLE tax_conditions (
    tax_id TINYINT NOT NULL PRIMARY KEY IDENTITY(1,1),
    tax_cond_title VARCHAR(50) NOT NULL
);

CREATE TABLE suppliers (
    sup_id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    sup_code VARCHAR(5) NOT NULL,
    sup_bussiness_name VARCHAR(30) NOT NULL,
    field_id TINYINT NOT NULL REFERENCES fields(field_id),
    sup_image VARCHAR,
    sup_website VARCHAR(30) NOT NULL,
    sup_email VARCHAR(40) NOT NULL,
    sup_telephone VARCHAR(11) NOT NULL,
    sup_address_name VARCHAR(40) NOT NULL,
    sup_addresss_num INT NOT NULL,
    city_id BIGINT NOT NULL REFERENCES cities(city_id),
    sup_cuit VARCHAR(11) NOT NULL,
    tax_id TINYINT NOT NULL REFERENCES tax_conditions(tax_id),
    sup_contact_id INT NOT NULL REFERENCES suppliers_contacts(sup_contact_id),
    sup_isdeleted BIT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME
);

CREATE TABLE purchase_orders (
    ord_id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    ord_status_id TINYINT NOT NULL REFERENCES order_status(ordst_id),
    ord_issue_date DATE NOT NULL,
    ord_exp_deliver_date DATE NOT NULL,
    ord_address_name VARCHAR(40) NOT NULL,
    ord_address_num INT NOT NULL,
    city_id BIGINT NOT NULL REFERENCES cities(city_id),
    sup_id INT NOT NULL REFERENCES suppliers(sup_id),
    created_at DATETIME NOT NULL,
    updated_at DATETIME
);

CREATE TABLE categories (
    cat_id TINYINT NOT NULL PRIMARY KEY IDENTITY(1,1),
    cat_name VARCHAR(40) NOT NULL,
    cat_detail VARCHAR(200) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME
);

CREATE TABLE products (
    prod_id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    prod_sku VARCHAR(6) NOT NULL,
    sup_id INT NOT NULL REFERENCES suppliers(sup_id),
    cat_id TINYINT NOT NULL REFERENCES categories(cat_id),
    prod_image VARCHAR,
    prod_name VARCHAR(30) NOT NULL,
    prod_description TEXT NOT NULL,
    prod_price FLOAT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME 
);


CREATE TABLE purchase_order_product (
    pop_id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
    ord_id INT NOT NULL REFERENCES purchase_orders(ord_id),
    prod_id INT NOT NULL REFERENCES products(prod_id),
    prod_quantity INT NOT NULL
);


INSERT INTO countries (coun_name) 
VALUES 
	('Argentina'), 
	('Brasil'), 
	('Chile'), 
	('Uruguay'), 
	('Paraguay');

INSERT INTO states (coun_id, state_name) 
VALUES 
	(1, 'Buenos Aires'), 
	(1, 'Córdoba'), 
	(1, 'Santa Fe'), 
	(1, 'Entre Ríos'), 
	(1, 'Corrientes'),
	(2, 'São Paulo'), 
	(2, 'Rio de Janeiro'), 
	(2, 'Bahia'), 
	(2, 'Pernambuco'), 
	(2, 'Ceará'),
	(3, 'Santiago Metropolitan'), 
	(3, 'Antofagasta'), 
	(3, 'Valparaíso'), 
	(3, 'Bío Bío'), 
	(3, 'Araucanía'),
	(4, 'Montevideo'), 
	(4, 'Canelones'), 
	(4, 'Maldonado'), 
	(4, 'Tacuarembó'), 
	(4, 'Cerro Largo'),
	(5, 'Asunción'), 
	(5, 'Alto Paraná'), 
	(5, 'Central'), 
	(5, 'Itapúa'), 
	(5, 'Cordillera');

INSERT INTO cities (state_id, city_name, city_postcode) 
VALUES 
	(1, 'Buenos Aires', 'C1000'), 
	(1, 'La Plata', 'B1900'), 
	(1, 'Mar del Plata', 'B7600'), 
	(1, 'Bahía Blanca', 'B8000'), 
	(1, 'Quilmes', 'B1878'),
	(2, 'Córdoba', 'X5000'), 
	(2, 'Río Cuarto', 'X5800'), 
	(2, 'Villa María', 'X5900'), 
	(2, 'San Francisco', 'X2400'), 
	(2, 'Villa Carlos Paz', 'X5152'),
	(3, 'Rosario', 'S2000'), 
	(3, 'Santa Fe', 'S3000'), 
	(3, 'Rafaela', 'S2300'), 
	(3, 'Villa Gobernador Gálvez', 'S2124'), 
	(3, 'Reconquista', 'S3560'),
	(4, 'Paraná', 'E3100'), 
	(4, 'Concordia', 'E3200'), 
	(4, 'Gualeguaychú', 'E2820'), 
	(4, 'Concepción del Uruguay', 'E3260'), 
	(4, 'La Paz', 'E3190'),
	(5, 'Corrientes', 'W3400'), 
	(5, 'Iberá', 'H3500'), 
	(5, 'Goya', 'N3300'), 
	(5, 'Esquina', 'P3600'), 
	(5, 'Curuzú Cuatiá', 'K4700'),
	(6, 'São Paulo', '01000-00'), 
	(6, 'Guarulhos', '07000-00'), 
	(6, 'Campinas', '13010-00'), 
	(6, 'São Bernardo do Campo', '09750-00'), 
	(6, 'Osasco', '06010-01'),
	(7, 'Rio de Janeiro', '20031-05'), 
	(7, 'São Gonçalo', '24435-00'), 
	(7, 'Duque de Caxias', '25010-15'), 
	(7, 'Nova Iguaçu', '26210-00'), 
	(7, 'Niterói', '24020-07'),
	(8, 'Salvador', '40026-01'), 
	(8, 'Feira de Santana', '44001-08'), 
	(8, 'Vitória da Conquista', '45000-00'), 
	(8, 'Camaçari', '42800-00'), 
	(8, 'Itabuna', '45600-00'),
	(9, 'Recife', '50010-230'), 
	(9, 'Jaboatão dos Guararapes', '54080-01'), 
	(9, 'Olinda', '53010-23'), 
	(9, 'Caruaru', '55014-50'), 
	(9, 'Petrolina', '56302-10'),
	(10, 'Fortaleza', '60010-00'), 
	(10, 'Caucaia', '61600-00'), 
	(10, 'Juazeiro do Norte', '63010-00'), 
	(10, 'Maracanaú', '61900-00'), 
	(10, 'Sobral', '62010-00'),
	(11, 'Santiago', '832000'), 
	(11, 'Antofagasta', '124000'), 
	(11, 'Calama', '139000'), 
	(11, 'La Serena', '170000'),
	(11, 'Copiapó', '153000'),
	(12, 'Valparaíso', '234000'), 
	(12, 'Viña del Mar', '252000'), 
	(12, 'Rancagua', '282000'), 
	(12, 'Talca', '346000'), 
	(12, 'Arica', '100000')


INSERT INTO order_status (ordst_name) 
VALUES 
	('Pedido realizado'), 
	('En preparación'), 
	('Enviado'), 
	('Entregado'), 
	('Cancelado');

INSERT INTO suppliers_contacts (sup_contact_name, sup_contact_lastname, sup_contact_telephone, sup_contact_email, sup_contact_role, created_at) VALUES 
('Juan', 'Pérez', '1234567890', 'juan.perez@example.com', 'Gerente de ventas', '2024-01-01'), 
('María', 'González', '0987654321', 'maria.gonzalez@example.com', 'Representante de ventas', '2024-01-02'), 
('Carlos', 'Rodríguez', '1122334455', 'carlos.rodriguez@example.com', 'Soporte técnico', '2024-01-03'), 
('Ana', 'Martínez', '5566778899', 'ana.martinez@example.com', 'Servicio al cliente', '2024-01-04'), 
('Pedro', 'García', '2233445566', 'pedro.garcia@example.com', 'Ejecutivo de cuentas', '2024-01-05'),
('Roberto', 'Mendoza', '3344556677', 'roberto.mendoza@example.com', 'Gerente de ventas', '2024-01-06'), 
('Patricia', 'López', '7788990011', 'patricia.lopez@example.com', 'Representante de ventas', '2024-01-07'), 
('Fernando', 'Torres', '9911223344', 'fernando.torres@example.com', 'Soporte técnico', '2024-01-08'), 
('Carmen', 'Ramírez', '5566778899', 'carmen.ramirez@example.com', 'Servicio al cliente', '2024-01-09'), 
('Ricardo', 'Gutiérrez', '2233445566', 'ricardo.gutierrez@example.com', 'Ejecutivo de cuentas', '2024-01-10'),
('Laura', 'Morales', '1122334455', 'laura.morales@example.com', 'Gerente de ventas', '2024-01-11'), 
('Javier', 'Hernández', '6677889900', 'javier.hernandez@example.com', 'Representante de ventas', '2024-01-12'), 
('Sofía', 'Castro', '4455667788', 'sofia.castro@example.com', 'Soporte técnico', '2024-01-13'), 
('Luis', 'Ortiz', '8899001122', 'luis.ortiz@example.com', 'Servicio al cliente', '2024-01-14'), 
('Gabriela', 'Vargas', '7788990011', 'gabriela.vargas@example.com', 'Ejecutivo de cuentas', '2024-01-15');


INSERT INTO fields (
	field_name, 
	field_detail, 
	created_at
	) 
VALUES 
	('Tecnología', 'Empresas dedicadas a la producción de tecnología y servicios relacionados', GETDATE()), 
	('Salud', 'Empresas y proveedores que ofrecen productos y servicios de salud', GETDATE()), 
	('Alimentación', 'Empresas y proveedores que producen y distribuyen alimentos', GETDATE()), 
	('Educación', 'Empresas y proveedores que ofrecen servicios educativos', GETDATE()), 
	('Construcción', 'Empresas y proveedores relacionados con la industria de la construcción', GETDATE() );

INSERT INTO tax_conditions (
	tax_cond_title
	)
VALUES
	('IVA Responsable Inscripto'),
	('IVA Responsable no Inscripto'),
	('IVA no Responsable'),
	('IVA Sujeto Exento'),
	('Consumidor Final'),
	('Responsable Monotributo'),
	('Sujeto no Categorizado'),
	('Proveedor del Exterior'),
	('Cliente del Exterior'),
	('IVA Liberado – Ley Nº 19.640'),
	('IVA Responsable Inscripto – Agente de Percepcion'),
	('Pequeño Contribuyente Eventual'),
	('Monotributista Social'),
	('Pequeño Contribuyente Eventual Social');


INSERT INTO suppliers (sup_code, sup_bussiness_name, field_id, sup_website, sup_email, sup_telephone, sup_address_name, sup_addresss_num, city_id, sup_cuit, tax_id, sup_contact_id, sup_isdeleted, created_at) VALUES 
('SUP1', 'IBM', 1, 'www.ibm.com', 'contacto@ibm.com', '1234567890', 'Avenida Paseo Colón', 123, 1, '20301234567', 1, 1, 0, '2024-01-01'), 
('SUP2', 'Hospital Italiano', 2, 'www.hospitalitaliano.org.ar', 'contacto@hospitalitaliano.org.ar', '0987654321', 'Avenida Corrientes', 456, 6, '20456789012', 2, 2, 0, '2024-01-02'), 
('SUP3', 'Arcor', 3, 'www.arcor.com.ar', 'contacto@arcor.com.ar', '1122334455', 'Avenida del Libertador', 789, 11, '20567890123', 3, 3, 0, '2024-01-03'), 
('SUP4', 'Universidad de Buenos Aires', 4, 'www.uba.ar', 'contacto@uba.ar', '5566778899', 'Avenida Rivadavia', 1011, 16, '20678901234', 4, 4, 0, '2024-01-04'), 
('SUP5', 'Techint', 5, 'www.techint.com', 'contacto@techint.com', '2233445566', 'Avenida Santa Fe', 1314, 21, '20789012345', 5, 5, 0, '2024-01-05'),
('SUP6', 'MercadoLibre', 1, 'www.mercadolibre.com.ar', 'contacto@mercadolibre.com.ar', '1234567890', 'Avenida Leandro N. Alem', 123, 1, '20890123456', 1, 6, 0, '2024-01-06'), 
('SUP7', 'Globant', 2, 'www.globant.com', 'contacto@globant.com', '0987654321', 'Avenida Cabildo', 456, 6, '20901234567', 2, 7, 0, '2024-01-07'), 
('SUP8', 'YPF', 3, 'www.ypf.com', 'contacto@ypf.com', '1122334455', 'Avenida 9 de Julio', 789, 11, '30123456789', 3, 8, 0, '2024-01-08'), 
('SUP9', 'Aerolíneas Argentinas', 4, 'www.aerolineas.com.ar', 'contacto@aerolineas.com.ar', '5566778899', 'Avenida Córdoba', 1011, 16, '30234567890', 4, 9, 0, '2024-01-09'), 
('SUP10', 'Telecom Argentina', 5, 'www.telecom.com.ar', 'contacto@telecom.com.ar', '2233445566', 'Avenida de Mayo', 1314, 21, '30345678901', 5, 10, 0, '2024-01-10'),
('SUP11', 'Banco Galicia', 1, 'www.galicia.com.ar', 'contacto@galicia.com.ar', '3344556677', 'Avenida Callao', 123, 1, '30456789012', 1, 11, 0, '2024-01-11'), 
('SUP12', 'Claro Argentina', 2, 'www.claro.com.ar', 'contacto@claro.com.ar', '7788990011', 'Avenida Alvear', 456, 6, '30567890123', 2, 12, 0, '2024-01-12'), 
('SUP13', 'Quilmes', 3, 'www.quilmes.com.ar', 'contacto@quilmes.com.ar', '1122334455', 'Avenida Figueroa Alcorta', 789, 11, '30678901234', 3, 13, 0, '2024-01-13'), 
('SUP14', 'Farmacity', 4, 'www.farmacity.com.ar', 'contacto@farmacity.com.ar', '5566778899', 'Avenida Las Heras', 1011, 16, '30789012345', 4, 14, 0, '2024-01-14'), 
('SUP15', 'Chevrolet Argentina', 5, 'www.chevrolet.com.ar', 'contacto@chevrolet.com.ar', '2233445566', 'Avenida Pueyrredón', 1314, 21, '30890123456', 5, 15, 0, '2024-01-15');



INSERT INTO categories (cat_name, cat_detail, created_at) VALUES 
('Hardware', 'Productos de hardware y componentes electrónicos', '2024-01-16'), 
('Software', 'Productos de software y aplicaciones', '2024-01-17'), 
('Dispositivos móviles', 'Smartphones, tablets y otros dispositivos móviles', '2024-01-18'), 
('Accesorios', 'Accesorios para dispositivos tecnológicos', '2024-01-19'), 
('Servicios de TI', 'Servicios de tecnología de la información', '2024-01-20'),
('Medicamentos', 'Medicamentos y productos farmacéuticos', '2024-01-21'), 
('Equipamiento médico', 'Equipos y dispositivos médicos', '2024-01-22'), 
('Suplementos', 'Suplementos dietéticos y nutricionales', '2024-01-23'), 
('Servicios de salud', 'Servicios de atención médica y bienestar', '2024-01-24'), 
('Productos de cuidado personal', 'Productos para el cuidado personal y la higiene', '2024-01-25'),
('Comida enlatada', 'Alimentos enlatados y conservas', '2024-01-26'), 
('Bebidas', 'Bebidas alcohólicas y no alcohólicas', '2024-01-27'), 
('Productos lácteos', 'Leche, queso, yogur y otros productos lácteos', '2024-01-28'), 
('Carnes', 'Carnes rojas, blancas y productos cárnicos', '2024-01-29'), 
('Frutas y verduras', 'Frutas, verduras y hortalizas frescas', '2024-01-30'),
('Libros de texto', 'Libros de texto y manuales educativos', '2024-01-31'), 
('Cursos online', 'Cursos y formación online', '2024-02-01'), 
('Material de oficina', 'Material de oficina y suministros escolares', '2024-02-02'), 
('Software educativo', 'Software y aplicaciones educativas', '2024-02-03'), 
('Servicios de tutoría', 'Servicios de tutoría y apoyo educativo', '2024-02-04'),
('Materiales de construcción', 'Materiales de construcción y suministros', '2024-02-05'), 
('Herramientas', 'Herramientas y equipos de construcción', '2024-02-06'), 
('Servicios de construcción', 'Servicios de construcción y contratistas', '2024-02-07'), 
('Maquinaria pesada', 'Maquinaria pesada y vehículos de construcción', '2024-02-08'), 
('Suministros de plomería', 'Suministros de plomería y accesorios', '2024-02-09');

INSERT INTO products (prod_sku, sup_id, cat_id, prod_name, prod_description, prod_price, created_at) VALUES 
('P001', 1, 1, 'Laptop Innovadora', 'Laptop de alta calidad', 1500.00, '2024-01-01'), 
('P002', 2, 6, 'Kit de primeros auxilios', 'Kit de primeros auxilios esencial', 50.00, '2024-01-02'), 
('P003', 3, 11, 'Galletas Gourmet', 'Deliciosas galletas gourmet', 10.00, '2024-01-03'), 
('P004', 4, 16, 'Libro de texto de matemáticas', 'Libro de texto para el estudio de las matemáticas', 30.00, '2024-01-04'), 
('P005', 5, 21, 'Martillo de construcción', 'Martillo resistente para construcción', 25.00, '2024-01-05'),
('P006', 6, 2, 'Camiseta de moda', 'Camiseta moderna y elegante', 20.00, '2024-01-06'), 
('P007', 7, 7, 'Pelota de fútbol', 'Pelota de fútbol duradera', 15.00, '2024-01-07'), 
('P008', 8, 12, 'Sofá de sala de estar', 'Sofá cómodo para la sala de estar', 500.00, '2024-01-08'), 
('P009', 9, 17, 'Juego de mesa', 'Juego de mesa divertido para toda la familia', 35.00, '2024-01-09'), 
('P010', 10, 22, 'Crema hidratante', 'Crema hidratante para el cuidado de la piel', 40.00, '2024-01-10'),
('P011', 11, 3, 'Cuenta de ahorros', 'Servicio de cuenta de ahorros', 12.99, '2024-01-11'), 
('P012', 12, 8, 'Software antivirus', 'Software antivirus para proteger tu computadora', 60.00, '2024-01-12'), 
('P013', 13, 13, 'Teléfono móvil', 'Teléfono móvil con las últimas características', 800.00, '2024-01-13'), 
('P014', 14, 18, 'Pintura al óleo', 'Hermosa pintura al óleo para decorar tu hogar', 120.00, '2024-01-14'), 
('P015', 15, 23, 'Boleto de avión', 'Boleto de avión para viajar', 200.00, '2024-01-15');


INSERT INTO purchase_orders (ord_status_id, ord_issue_date, ord_exp_deliver_date, ord_address_name, ord_address_num, city_id, sup_id, created_at) VALUES 
(1, '2024-01-01', '2024-01-10', 'Avenida Paseo Colón', 123, 1, 1, '2024-01-01'), 
(2, '2024-01-02', '2024-01-11', 'Avenida Corrientes', 456, 6, 2, '2024-01-02'), 
(3, '2024-01-03', '2024-01-12', 'Avenida del Libertador', 789, 11, 3, '2024-01-03'), 
(4, '2024-01-04', '2024-01-13', 'Avenida Rivadavia', 1011, 16, 4, '2024-01-04'), 
(5, '2024-01-05', '2024-01-14', 'Avenida Santa Fe', 1314, 21, 5, '2024-01-05'),
(1, '2024-01-06', '2024-01-15', 'Avenida Leandro N. Alem', 123, 1, 6, '2024-01-06'), 
(2, '2024-01-07', '2024-01-16', 'Avenida Cabildo', 456, 6, 7, '2024-01-07'), 
(3, '2024-01-08', '2024-01-17', 'Avenida 9 de Julio', 789, 11, 8, '2024-01-08'), 
(4, '2024-01-09', '2024-01-18', 'Avenida Córdoba', 1011, 16, 9, '2024-01-09'), 
(5, '2024-01-10', '2024-01-19', 'Avenida de Mayo', 1314, 21, 10, '2024-01-10');

INSERT INTO purchase_order_product (ord_id, prod_id, prod_quantity) VALUES 
(1, 1, 10),
(2, 3, 15), 
(3, 5, 25),
(4, 7, 35), 
(5, 9, 45), 
(6, 11, 55), 
(7, 13, 65), 
(8, 15, 75);
