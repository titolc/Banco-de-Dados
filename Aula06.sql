Explicando os 2 codigos usados na aula de hoje!

1.
select cat.name AS categoria, COUNT(f.film_id) AS total_filmes -- Renomeia a categoria de filmes como 'categoria' e a contagem de filmes como  'total_filmes'
from film f -- utiliza a tabela de filmes
join film_category fc ON f.film_id = fc.film_id -- Conecta 2 tabelas com o uso do 'join', relacionando 'film_category' e 'f.film_id' com a coluna 'fc.film_id' que é comum nas 2 tabelas
join category cat ON fc.category_id = cat.category_id -- Conecta 2 tabelas com o uso do 'join', relacionando 'category cat' e 'fc.category_id' com a coluna 'cat.category_id' que é comum nas 2 tabelas
Group by cat.name -- Agrupa os resultados pelo nome da categoria 'cat.name'
having COUNT(f.film_id) > 50 -- Having é usado para filtrar os resultados após o agrupamento, definindo '>50' ela filtra categorias com mais de 50 filmes
Order by total_filmes DESC; -- Os resultados são ordenados de forma decrescente 'DESC'



2.
select c.customer_id, c.first_name, c.last_name, SUM(p.amount) AS total_gasto -- Aqui é uma simples consulta definindo as colunas e em uma delas é a soma do valor de todas as transações feitas pelo cliente
from customer c -- Utiliza a tabela de Clientes como consulta
join payment p ON c.customer_id = p.customer_id -- Conecta 2 tabelas com o uso do 'join', relacionando 'payment' e 'c.customer_id' com a coluna 'p.customer_id' que é comum nas 2 tabelas
group by c.customer_id -- Agrupa os resultados pelo nome da categoria 'c.customer_id', cada cliente, será calculada a soma dos pagamentos que ele fez
having SUM(p.amount) > 100 -- Having é usado para filtrar os resultados após o agrupamento, definindo '>100' ela filtra clientes com gastos maiores que 100
order by total_gasto DESC; -- Os resultados são ordenados de forma decrescente 'DESC'
