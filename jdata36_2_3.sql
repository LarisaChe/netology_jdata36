SELECT o.product_name
FROM customers c INNER JOIN orders o ON c.id = o.customer_id 
WHERE LOWER(c.name) = 'alexey'