-- В рамках БД "онлайн-магазин" напишите след/запросы:

-- Вывести ко-во поставщиков не из UK и не из China

SELECT 
COUNT(*)
FROM Suppliers
WHERE Country NOT IN('UK', 'China')




-- Вывести среднюю/MAX/MIN стоимости и ко-во товаров из категорий 3 и 5

SELECT
    ROUND(AVG(Price), 2) AS AVG_Price,
    ROUND(MIN(Price), 4) AS MIN_Price,
    ROUND(MAX(Price), 4) AS MAX_Price
FROM Products
WHERE CategoryID IN (3, 5);


-- Вывести общую сумму проданных товаров
**с группировкой по ProductID

SELECT 
OrderDetails.ProductID,
ROUND(SUM (OrderDetails.Quantity* Products.Price),2) AS Total_SUM
FROM OrderDetails
JOIN Products ON Products.ProductID=OrderDetails.ProductID
GROUP BY OrderDetails.ProductID

**без группировкой по ProductID

SELECT 
COUNT (*),
ROUND(SUM (OrderDetails.Quantity* Products.Price),2) AS Total_SUM
FROM OrderDetails
JOIN Products ON Products.ProductID=OrderDetails.ProductID




-- Вывести данные о заказах (номер_заказа, имя_клиента, страна_клиента, фамилия_менеджера, название_компании_перевозчика)

SELECT 
Orders.OrderID,Employees.LastName AS Employees ,Customers.CustomerName, Customers.Country,Shippers.ShipperName 
FROM 
OrderDetails
JOIN
Orders ON Orders.OrderID=OrderDetails.OrderID
JOIN
Customers ON Customers.CustomerID= Orders.CustomerID
JOIN
Employees ON Employees.EmployeeID= Orders.EmployeeID
JOIN
Shippers ON Shippers.ShipperID= Orders.ShipperID
GROUP BY Orders.OrderID

-- Вывести сумму, на которую было отправлено товаров клиентам в Germany

**с группировкой по OrderID

SELECT 
OrderDetails.OrderID,
ROUND(SUM (OrderDetails.Quantity* Products.Price),2) AS Total_SUM,Customers.Country
FROM OrderDetails
JOIN 
Products ON Products.ProductID=OrderDetails.ProductID
JOIN
Orders ON Orders.OrderID=OrderDetails.OrderID
JOIN
Customers ON Customers.CustomerID=Orders.CustomerID
WHERE Country='Germany'
GROUP BY 
OrderDetails.OrderID, Customers.Country;

    **без группировкой по OrderID

SELECT 
ROUND(SUM (OrderDetails.Quantity* Products.Price),2) AS Total_SUM,Customers.Country
FROM OrderDetails
JOIN 
Products ON Products.ProductID=OrderDetails.ProductID
JOIN
Orders ON Orders.OrderID=OrderDetails.OrderID
JOIN
Customers ON Customers.CustomerID=Orders.CustomerID
WHERE Country='Germany'
