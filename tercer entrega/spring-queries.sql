USE spring;

-- 1 - Obtener todos los productos, mostrando nombre del producto, categoría, proveedor (razón social y codigo proveedor), precio.

SELECT p.prod_name as 'Producto', cat.cat_name as 'Categoria', s.sup_bussiness_name as 'Proveedor', s.sup_code as 'Codigo Proveedor', p.prod_price as 'Precio'
FROM products p
INNER JOIN categories cat ON cat.cat_id = p.cat_id
INNER JOIN suppliers s ON s.sup_id = p.sup_id

-- 2 -En el listado anterior, además de los datos mostrados, traer el campo imagen aunque el producto NO tenga una. Sino tiene imagen, mostrar "-".

SELECT p.prod_name as 'Producto', cat.cat_name as 'Categoria', s.sup_bussiness_name as 'Proveedor', s.sup_code as 'Codigo Proveedor', p.prod_price as 'Precio', ISNULL(p.prod_image, '-') as 'Imagen'
FROM products p
INNER JOIN categories cat ON cat.cat_id = p.cat_id
INNER JOIN suppliers s ON s.sup_id = p.sup_id

-- 3 - Mostrar los datos que se pueden modificar (en el front) del producto con ID = 2.
SELECT p.prod_sku as 'SKU', cat.cat_name as 'Categoría', p.prod_name as 'Producto', p.prod_image as 'Imagen', p.prod_description as 'Descripción', p.prod_price as 'Precio' FROM products p
INNER JOIN categories cat ON cat.cat_id = p.cat_id
WHERE p.prod_id=2

-- 4 -Listar todo los proveedores cuyo teléfono tenga la característica de Córdoba o que la provincia sea igual a alguna de las 3 con más proveedores.
INSERT INTO suppliers
            (sup_code,
             sup_bussiness_name,
             field_id,
             sup_website,
             sup_email,
             sup_telephone,
             sup_address_name,
             sup_addresss_num,
             city_id,
             sup_cuit,
             tax_id,
             sup_contact_id,
             sup_isdeleted,
             created_at)
VALUES      
	('SUP52', 'El COrdobes', 1,'www.ibm.com','contacto@ibm.com','3514567890','Avenida Paseo Colón', 123, 1,'20301234567', 1, 1, 0, '2024-01-01')


SELECT s.sup_bussiness_name FROM suppliers s
INNER JOIN 
	cities ON s.city_id = cities.city_id
INNER JOIN 
	states st ON cities.state_id = st.state_id
WHERE 
	s.sup_telephone LIKE '351%' OR st.state_id IN (
													SELECT TOP 3 st.state_id FROM states st 
													INNER JOIN 
														cities ON st.state_id = cities.state_id
													INNER JOIN 
														suppliers s ON cities.city_id = s.city_id
													GROUP BY st.state_id
													ORDER BY COUNT(s.sup_id) DESC
													);



-- 5 -Traer un listado de todos los proveedores 
--que no hayan sido eliminados , y ordenados por razon social, codigo proveedor y 
--fecha en que se dió de alta ASC. De este listado mostrar los datos que correspondan con su tabla del front.

SELECT s.sup_code as 'Codigo de Proveedor', s.sup_bussiness_name as 'Razón Social', s.sup_website as 'Web', s.sup_email as 'email', s.sup_telephone as 'Telefono', CONCAT(con.sup_contact_name,', ',con.sup_contact_lastname) as 'Contacto', s.sup_cuit as 'CUIT', tax.tax_cond_title as 'Condicion IVA'
FROM suppliers s  
INNER JOIN suppliers_contacts con ON con.sup_contact_id = s.sup_contact_id
INNER JOIN tax_conditions tax ON tax.tax_id = s.tax_id
WHERE s.sup_isdeleted = 0
ORDER BY s.sup_bussiness_name ASC, s.sup_code ASC, s.created_at ASC


-- 6 -Obtener razon social, codigo proveedor, imagen, web, email, teléfono y los datos del contacto del proveedor con más ordenes de compra cargadas
--todos tienen una orden, inserto una mas para el ejercicio
INSERT INTO purchase_orders (ord_status_id, ord_issue_date, ord_exp_deliver_date, ord_address_name, ord_address_num, city_id, sup_id, created_at) 
VALUES 
(5, '2024-01-05', '2024-01-07', 'Avenida de Mayo', 1314, 21, 10, '2024-01-05');--una orden mas para telecom

SELECT TOP 1
    s.sup_bussiness_name AS 'Razón Social del Proveedor', 
    s.sup_code AS 'Código del Proveedor', 
    ISNULL(s.sup_image, '-') AS 'Imagen',
    s.sup_website AS 'Web', 
    s.sup_email AS 'Email', 
    s.sup_telephone AS 'Teléfono', 
    sc.sup_contact_name AS 'Nombre del Contacto', 
    sc.sup_contact_lastname AS 'Apellido del Contacto', 
    sc.sup_contact_telephone AS 'Teléfono del Contacto', 
    sc.sup_contact_email AS 'Email del Contacto', 
    sc.sup_contact_role AS 'Rol del Contacto'
FROM 
    suppliers s
JOIN 
    suppliers_contacts sc ON s.sup_contact_id = sc.sup_contact_id
JOIN 
    purchase_orders po ON s.sup_id = po.sup_id
GROUP BY 
    s.sup_id, s.sup_bussiness_name, s.sup_code, s.sup_image, s.sup_website, s.sup_email, s.sup_telephone, sc.sup_contact_name, sc.sup_contact_lastname, sc.sup_contact_telephone, sc.sup_contact_email, sc.sup_contact_role
ORDER BY 
    COUNT(po.ord_id) DESC;


-- 7 -Mostrar la fecha emisión, nº de orden, razon social y codigo de proveedor, y la cantidad de productos de cada orden.

SELECT 
    po.ord_issue_date AS 'Fecha de Emisión', 
    po.ord_id AS 'Número de Orden', 
    s.sup_bussiness_name AS 'Razón Social del Proveedor', 
    s.sup_code AS 'Código del Proveedor', 
    SUM(pop.prod_quantity) AS 'Cantidad de Productos'
FROM 
    purchase_orders po
JOIN 
    suppliers s ON po.sup_id = s.sup_id
JOIN 
    purchase_order_product pop ON po.ord_id = pop.ord_id
GROUP BY 
    po.ord_issue_date, po.ord_id, s.sup_bussiness_name, s.sup_code;


-- 8-En el listado anterior, diferenciar cuando una orden está Cancelada o no, y el total de la misma.

SELECT 
    po.ord_issue_date AS 'Fecha de Emisión', 
    po.ord_id AS 'Número de Orden', 
    s.sup_bussiness_name AS 'Razón Social del Proveedor', 
    s.sup_code AS 'Código del Proveedor', 
    SUM(pop.prod_quantity) AS 'Cantidad de Productos',
	(CASE os.ordst_name 
		WHEN 'Cancelado' 
		THEN 'CANCELADO' 
		ELSE 'No Cancelado' 
	END) AS 'Estado de Orden',
	SUM(p.prod_price) 
FROM 
    purchase_orders po
INNER JOIN 
    suppliers s ON po.sup_id = s.sup_id
INNER JOIN 
    purchase_order_product pop ON po.ord_id = pop.ord_id
INNER JOIN
	order_status os ON os.ordst_id = po.ord_status_id
INNER JOIN 
	products p ON p.prod_id = pop.prod_id
GROUP BY 
    po.ord_issue_date, po.ord_id, s.sup_bussiness_name, s.sup_code, os.ordst_name;

-- 9 -Mostrar el detalle de una orden de compra del proveedor 3, trayendo: SKU del producto, nombre producto, cantidad y subtotal.
SELECT 
    p.prod_sku AS 'SKU del Producto', 
    p.prod_name AS 'Nombre del Producto', 
    pop.prod_quantity AS 'Cantidad', 
    p.prod_price * pop.prod_quantity AS 'Subtotal'
FROM 
    purchase_orders po
JOIN 
    purchase_order_product pop ON po.ord_id = pop.ord_id
JOIN 
    products p ON pop.prod_id = p.prod_id
WHERE 
    po.sup_id = 3;

-- 10 -Cambiar el estado a Cancelada y la fecha de modificación a la orden de compra con ID = 4.

UPDATE purchase_orders
SET 
    ord_status_id = (SELECT ordst_id FROM order_status WHERE ordst_name = 'Cancelado'), 
    updated_at = GETDATE()
WHERE 
    ord_id = 4;

-- 11 -Escribir la sentencia para eliminar el producto con id = 1 (NO EJECUTAR, SÓLO MOSTRAR SENTENCIA)

--DELETE FROM products
--WHERE products.prod_id = 1;


