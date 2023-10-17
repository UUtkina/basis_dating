-- В рамках БД "онлайн-магазин" напишите след/запросы:

-- Найти мин/стоимость товаров для каждой категории

SELECT 
COUNT(*)
FROM Suppliers
WHERE Country NOT IN('UK', 'China')SELECT
  CategoryID,
  ROUND(MIN(Price), 4) AS MIN_Price
FROM Products
GROUP BY CategoryID;




-- Вывести названия категорий, в которых более 10 товаров

SELECT
  CategoryID,
  COUNT(*) AS COUNT_
FROM Products
GROUP BY CategoryID
HAVING COUNT_>=10



-- Очистить тел/номер поставщикам из USA
UPDATE Suppliers
SET Phone = ''
WHERE Country = 'USA';



-- Вывести имена и фамилии (и ко-во заказов) менеджеров, у которых ко-во заказов менее 15

SELECT 
Employees.LastName, Employees.FirstName,COUNT(*)AS COUNT_
FROM 
Orders
JOIN
Employees ON Employees.EmployeeID= Orders.EmployeeID
GROUP BY Orders.EmployeeID
HAVING COUNT_>15