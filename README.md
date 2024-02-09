# Proyect Integrador Final

Desarrollo de un *Sistema de Gestión Compras* para manejar información de Proveedores, Productos y Órdenes de compra.




## Ejecutar localmente

Pasos necesarios para correr el proyecto localmente

- Crear una base de datos llamada
```sql
  CREATE DATABASE springIntegrador;
```

- Crear la(s) siguiente(s) tabla(s)
```sql
  USE [springIntegrador]
GO
/****** Object:  Table [dbo].[address]    Script Date: 9/2/2024 17:58:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[address](
	[addr_id] [bigint] IDENTITY(1,1) NOT NULL,
	[addr_apart] [varchar](2) NULL,
	[addr_floor] [int] NULL,
	[addr_number] [int] NOT NULL,
	[addr_postcode] [varchar](10) NOT NULL,
	[addr_street] [varchar](255) NOT NULL,
	[city_name] [varchar](50) NOT NULL,
	[created_at] [datetime2](6) NULL,
	[updated_at] [datetime2](6) NULL,
	[address_state] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[addr_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[categories]    Script Date: 9/2/2024 17:58:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[categories](
	[cat_id] [int] IDENTITY(1,1) NOT NULL,
	[cat_detail] [varchar](200) NOT NULL,
	[cat_name] [varchar](40) NOT NULL,
	[created_at] [datetime2](6) NULL,
	[updated_at] [datetime2](6) NULL,
	[field_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cat_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[countries]    Script Date: 9/2/2024 17:58:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[countries](
	[coun_id] [int] IDENTITY(1,1) NOT NULL,
	[coun_name] [varchar](40) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[coun_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[fields]    Script Date: 9/2/2024 17:58:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[fields](
	[field_id] [int] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](6) NULL,
	[field_detail] [varchar](200) NOT NULL,
	[field_name] [varchar](20) NOT NULL,
	[updated_at] [datetime2](6) NULL,
PRIMARY KEY CLUSTERED 
(
	[field_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_status]    Script Date: 9/2/2024 17:58:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_status](
	[ordst_id] [int] IDENTITY(1,1) NOT NULL,
	[ordst_name] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ordst_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[products]    Script Date: 9/2/2024 17:58:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[products](
	[prod_id] [int] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](6) NULL,
	[prod_available] [bit] NOT NULL,
	[prod_description] [text] NOT NULL,
	[prod_image] [varchar](255) NULL,
	[prod_name] [varchar](30) NOT NULL,
	[prod_price] [real] NOT NULL,
	[prod_sku] [varchar](13) NOT NULL,
	[updated_at] [datetime2](6) NULL,
	[cat_id] [int] NOT NULL,
	[sup_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[prod_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[purchase_order_product]    Script Date: 9/2/2024 17:58:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[purchase_order_product](
	[pop_id] [bigint] IDENTITY(1,1) NOT NULL,
	[pop_price] [float] NOT NULL,
	[prod_quantity] [int] NOT NULL,
	[ord_id] [int] NOT NULL,
	[prod_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[pop_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[purchase_orders]    Script Date: 9/2/2024 17:58:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[purchase_orders](
	[ord_id] [int] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](6) NULL,
	[ord_delivery_info] [varchar](255) NOT NULL,
	[ord_exp_deliver_date] [datetime2](6) NOT NULL,
	[ord_issue_date] [datetime2](6) NOT NULL,
	[updated_at] [datetime2](6) NULL,
	[ord_status_id] [int] NOT NULL,
	[sup_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ord_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[states]    Script Date: 9/2/2024 17:58:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[states](
	[state_id] [int] IDENTITY(1,1) NOT NULL,
	[state_name] [varchar](30) NOT NULL,
	[country_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[state_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[suppliers]    Script Date: 9/2/2024 17:58:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[suppliers](
	[sup_id] [int] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](6) NULL,
	[sup_isactive] [bit] NOT NULL,
	[sup_bussiness_name] [varchar](30) NOT NULL,
	[sup_code] [varchar](11) NOT NULL,
	[sup_cuit] [varchar](11) NOT NULL,
	[sup_email] [varchar](40) NOT NULL,
	[sup_image] [varchar](255) NOT NULL,
	[sup_telephone] [varchar](11) NOT NULL,
	[sup_website] [varchar](40) NOT NULL,
	[updated_at] [datetime2](6) NULL,
	[addr_id] [bigint] NOT NULL,
	[field_id] [int] NOT NULL,
	[sup_contact] [int] NOT NULL,
	[tax_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[sup_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_c5vi190p2rqjf5yg8soyxdr1d] UNIQUE NONCLUSTERED 
(
	[sup_contact] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_fd035kirlitbeho8al6n01dhv] UNIQUE NONCLUSTERED 
(
	[addr_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_ip7hhqw230jwkee81qid3lot5] UNIQUE NONCLUSTERED 
(
	[sup_code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[suppliers_contacts]    Script Date: 9/2/2024 17:58:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[suppliers_contacts](
	[sup_contact_id] [int] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](6) NULL,
	[sup_contact_email] [varchar](40) NOT NULL,
	[sup_contact_lastname] [varchar](20) NOT NULL,
	[sup_contact_name] [varchar](20) NOT NULL,
	[sup_contact_role] [varchar](30) NOT NULL,
	[sup_contact_telephone] [varchar](11) NOT NULL,
	[updated_at] [datetime2](6) NULL,
PRIMARY KEY CLUSTERED 
(
	[sup_contact_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tax_conditions]    Script Date: 9/2/2024 17:58:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tax_conditions](
	[tax_id] [int] IDENTITY(1,1) NOT NULL,
	[tax_cond_title] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[tax_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[address]  WITH CHECK ADD  CONSTRAINT [FK5jmghige367hwipuw2oo25bv2] FOREIGN KEY([address_state])
REFERENCES [dbo].[states] ([state_id])
GO
ALTER TABLE [dbo].[address] CHECK CONSTRAINT [FK5jmghige367hwipuw2oo25bv2]
GO
ALTER TABLE [dbo].[categories]  WITH CHECK ADD  CONSTRAINT [FKnvo925ska79u0m54eyeiubsoq] FOREIGN KEY([field_id])
REFERENCES [dbo].[fields] ([field_id])
GO
ALTER TABLE [dbo].[categories] CHECK CONSTRAINT [FKnvo925ska79u0m54eyeiubsoq]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FKmqt5yhcyxbxesx3vpwo6drcfo] FOREIGN KEY([sup_id])
REFERENCES [dbo].[suppliers] ([sup_id])
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FKmqt5yhcyxbxesx3vpwo6drcfo]
GO
ALTER TABLE [dbo].[products]  WITH CHECK ADD  CONSTRAINT [FKst3irn0akhwfaujxx7my2dw7j] FOREIGN KEY([cat_id])
REFERENCES [dbo].[categories] ([cat_id])
GO
ALTER TABLE [dbo].[products] CHECK CONSTRAINT [FKst3irn0akhwfaujxx7my2dw7j]
GO
ALTER TABLE [dbo].[purchase_order_product]  WITH CHECK ADD  CONSTRAINT [FKcih5urog2orqf67wkmeaswmn0] FOREIGN KEY([ord_id])
REFERENCES [dbo].[purchase_orders] ([ord_id])
GO
ALTER TABLE [dbo].[purchase_order_product] CHECK CONSTRAINT [FKcih5urog2orqf67wkmeaswmn0]
GO
ALTER TABLE [dbo].[purchase_order_product]  WITH CHECK ADD  CONSTRAINT [FKoatu63cpx6b0dfhe6qqqepswv] FOREIGN KEY([prod_id])
REFERENCES [dbo].[products] ([prod_id])
GO
ALTER TABLE [dbo].[purchase_order_product] CHECK CONSTRAINT [FKoatu63cpx6b0dfhe6qqqepswv]
GO
ALTER TABLE [dbo].[purchase_orders]  WITH CHECK ADD  CONSTRAINT [FK6p1k4kxqr5sfhthtf7shwsfxp] FOREIGN KEY([ord_status_id])
REFERENCES [dbo].[order_status] ([ordst_id])
GO
ALTER TABLE [dbo].[purchase_orders] CHECK CONSTRAINT [FK6p1k4kxqr5sfhthtf7shwsfxp]
GO
ALTER TABLE [dbo].[purchase_orders]  WITH CHECK ADD  CONSTRAINT [FKgeks6fin5mp1i6lj1yipybgbw] FOREIGN KEY([sup_id])
REFERENCES [dbo].[suppliers] ([sup_id])
GO
ALTER TABLE [dbo].[purchase_orders] CHECK CONSTRAINT [FKgeks6fin5mp1i6lj1yipybgbw]
GO
ALTER TABLE [dbo].[states]  WITH CHECK ADD  CONSTRAINT [FKskkdphjml9vjlrqn4m5hi251y] FOREIGN KEY([country_id])
REFERENCES [dbo].[countries] ([coun_id])
GO
ALTER TABLE [dbo].[states] CHECK CONSTRAINT [FKskkdphjml9vjlrqn4m5hi251y]
GO
ALTER TABLE [dbo].[suppliers]  WITH CHECK ADD  CONSTRAINT [FK199y19h2uj3qkarsm26krecoh] FOREIGN KEY([tax_id])
REFERENCES [dbo].[tax_conditions] ([tax_id])
GO
ALTER TABLE [dbo].[suppliers] CHECK CONSTRAINT [FK199y19h2uj3qkarsm26krecoh]
GO
ALTER TABLE [dbo].[suppliers]  WITH CHECK ADD  CONSTRAINT [FK39ojbpxq5n2vmfiu8odxcs4h1] FOREIGN KEY([field_id])
REFERENCES [dbo].[fields] ([field_id])
GO
ALTER TABLE [dbo].[suppliers] CHECK CONSTRAINT [FK39ojbpxq5n2vmfiu8odxcs4h1]
GO
ALTER TABLE [dbo].[suppliers]  WITH CHECK ADD  CONSTRAINT [FK8jkwyd1iw4ugx9x2er59bgr5e] FOREIGN KEY([addr_id])
REFERENCES [dbo].[address] ([addr_id])
GO
ALTER TABLE [dbo].[suppliers] CHECK CONSTRAINT [FK8jkwyd1iw4ugx9x2er59bgr5e]
GO
ALTER TABLE [dbo].[suppliers]  WITH CHECK ADD  CONSTRAINT [FKmny9c0pib1nsp979qle82q5q2] FOREIGN KEY([sup_contact])
REFERENCES [dbo].[suppliers_contacts] ([sup_contact_id])
GO
ALTER TABLE [dbo].[suppliers] CHECK CONSTRAINT [FKmny9c0pib1nsp979qle82q5q2]
GO

```

- Insertar **Entidades minimas**

```sql
  INSERT INTO tax_conditions (tax_cond_title)
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


INSERT INTO order_status (ordst_name)
VALUES 
('Creada'),
('Enviada'),
('Confirmada'),
('Estimada'),
('Recibida'),
('Control'),
('Aceptada'),
('Rechazada'),
('Completa'),
('Cancelada');

INSERT INTO countries (coun_name)
VALUES 
('Argentina'),
('Brasil'),
('Chile'),
('Uruguay'),
('Paraguay');


INSERT INTO states (country_id, state_name)
VALUES 
(1, 'Buenos Aires'),
(1, 'Córdoba'),
(1, 'Santa Fe'),
(1, 'Entre Ríos'),
(1, 'Mendoza'),

(2, 'São Paulo'),
(2, 'Rio de Janeiro'),
(2, 'Bahia'),
(2, 'Minas Gerais'),
(2, 'Pernambuco'),

(3, 'Santiago'),
(3, 'Valparaíso'),
(3, 'Bío Bío'),
(3, 'Araucanía'),
(3, 'Maule'),

(4, 'Montevideo'),
(4, 'Canelones'),
(4, 'Maldonado'),
(4, 'Rocha'),
(4, 'Tacuarembó'),

(5, 'Asunción'),
(5, 'Alto Paraná'),
(5, 'Central'),
(5, 'Itapúa'),
(5, 'Caaguaz');

INSERT INTO fields (FIELD_NAME, FIELD_DETAIL) 
VALUES 
('Alimentos', 'Especializado en la distribución de alimentos frescos y enlatados'),
('Construcción', 'Ofrece una amplia gama de materiales para la construcción'),
('Tecnología', 'Distribuidor de dispositivos tecnológicos y accesorios'),
('Ropa', 'Distribuidor de ropa de moda y accesorios'),
('Muebles', 'Especializado en muebles de oficina y para el hogar');


```

- Ejecutar el servidor de Angular (*puerto 4300*)

```bash
  ng start -o
```

- Ejecutar el servidor de Java (*puerto 8080*)

- Insertar algunas **Categorías** desde el FRONT

- Insertar algunas **Rubros** desde el FRONT

- Insertar algunas **Proveedores** desde el FRONT

- Insertar algunas **Productos** desde el FRONT

- Insertar algunas **Ordenes de Compra** desde el FRONT
## Aclaraciones sobre algunas partes de su código *(opcional)*

El método o función miMetodo lo hice para que pudiera chequear el estado de la respuesta del servidor ... 

```javascript

function miMetodo() {
  return "Ok";
}
```


## API Reference 

#### Obtener todos los productos

```http
  GET /products
```


#### Obtener un producto

```http
  GET /products/${id}
```

#### Obtener todos los suppliers

```http
  GET /suppliers
```


#### Obtener un supplier

```http
  GET /suppliers/${id}
```

#### Obtener todas las orders

```http
  GET /orders
```


#### Obtener una order

```http
  GET /orders/${id}

| Parámetro | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Obligatorio**. ID del Producto a buscar |


## Desarrollado por *(opcional)*

Este proyecto fue desarrollado por: **Gonzalo Faust**
