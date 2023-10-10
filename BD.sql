//в связи с тем, что задание было дано поздно (после 22.00), я не успела нарисовать схему. Сделаю задание 1 в следующей ДР


//2.Вывести название и стоимость товаров от 20 EUR.
SELECT 
ProductName, Price
FROM Products

WHERE
		Price > 20

//3.Вывести страны поставщиков.   
SELECT DISTINCT
Country
FROM Suppliers
//4.  В упорядоченном по стоимости виде вывести название и стоимость товаров от всех поставщиков, кроме поставщика 1.
SELECT 
    ProductName, Price, SupplierID
FROM 
    Products
WHERE 
    SupplierID != 1
ORDER BY 
    Price DESC;
//5.Вывести контактные имена клиентов, кроме тех, что из France и USA.
SELECT 
ContactName,Country
FROM
Customers
WHERE
NOT Country IN ('France', 'USA')
