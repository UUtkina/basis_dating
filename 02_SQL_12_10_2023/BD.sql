-- В рамках БД "онлайн-магазин" напишите след/запросы:

-- Вывести название и стоимость в USD одного самого дорогого проданного товара (не используя агрегацию)

    SELECT
        Products.ProductName, 
        Products.Price * 1.05 AS Price_usd 
    FROM Products 
    JOIN OrderDetails ON Products.ProductID = OrderDetails.ProductID
    ORDER BY Products.Price DESC
    LIMIT 1

-- Вывести два самых дорогих товара из категории Beverages из USA

    SELECT * FROM [Products]
    JOIN Categories ON Products.CategoryID = Categories.CategoryID
    JOIN Suppliers ON Products.SupplierID = Suppliers.SupplierID
    WHERE 
        Categories.CategoryName = 'Beverages' 
        AND
        Suppliers.Country = 'USA'   
    ORDER BY Products.Price DESC 
    LIMIT 2

-- Удалить товары с ценой менее 50 EUR

    DELETE FROM Products
    WHERE Price < 50

-- Вывести список стран, которые поставляют морепродукты

    SELECT Suppliers.Country FROM [Categories]
    JOIN Products ON Products.CategoryID = Categories.CategoryID
    JOIN Suppliers  ON Products.SupplierID = Suppliers.SupplierID

    WHERE Categories.CategoryName = 'Seafood'

-- Очистить поле ContactName у всех клиентов не из China

    UPDATE Customers
    SET 
        ContactName = NULL
    WHERE Country != 'China'