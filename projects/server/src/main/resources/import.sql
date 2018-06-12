SELECT setval('ingredient_id_seq', 1, false);
SELECT setval('meal_id_seq', 1, false);
SELECT setval('menu_id_seq', 1, false);
SELECT setval('ordered_meal_id_seq', 1, false);

INSERT INTO place (id) VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10);

INSERT INTO ingredient (name,quantity) VALUES ('salmon',2),('mozzarella',2),('pepper',2),('oshinko',2),('lettuce',2),('avocado',2),('philadelphia cheese',2),('dressing',2),('tobico',2),('pore',2),('parmesan',2),('naughty dressing',2);

INSERT INTO meal (category, details, image, name, possible_to_do, price, proper_time) VALUES ('Sushi recommends',null,'http://cdn.upmenu.com/static/product-images/8ca52eae-4d4a-11e4-ac27-00163edcb8a0/d905d69f-be24-11e7-93f9-525400841de1/2/large/foto_n6.jpg','Sake Tempura',TRUE,29,15),('Sushi recommends',null,'http://cdn.upmenu.com/static/product-images/8ca52eae-4d4a-11e4-ac27-00163edcb8a0/1d7dc623-be25-11e7-93f9-525400841de1/2/large/california_lion_roll.jpg','California Lion Roll',TRUE,39,20),('Lunch',null,'http://cdn.upmenu.com/static/product-images/8ca52eae-4d4a-11e4-ac27-00163edcb8a0/ddb2fc37-8a44-11e4-ac27-00163edcb8a0/3/large/fusion-salmon.jpg','Fusion Salmon',TRUE,21,25);

INSERT INTO meal_ingredients (meal_id, ingredients_id) VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(2,1),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(3,1),(3,9),(3,8);

INSERT INTO menu (current, name) VALUES (TRUE,'Spring'),(FALSE,'Summer'),(FALSE,'Autumn'),(FALSE,'Winter');

INSERT INTO menu_meals (menu_id, meals_id) VALUES (1,1),(1,2),(1,3),(2,1),(3,2),(4,3);

INSERT INTO ordered_meal (amount, meal_id) VALUES (2,1),(1,2),(2,1),(2,2),(2,2),(3,1),(2,2),(1,1),(1,3),(2,1),(3,3),(1,1);

INSERT INTO indent (id, date_end, date_start, status, summary_price, table_id) VALUES (1,null,null,1,97,1),(2,null,null,1,107,1),(3,null,null,2,39,3),(4,null,null,3,39,2),(5,null,null,1,29,5),(6,null,null,2,60,7),(7,null,null,3,50,1),(8,null,null,1,21,4);

INSERT INTO indent_meals (order_id, meals_id) VALUES (1,1),(1,2),(2,3),(2,4),(3,5),(4,6),(5,7),(6,8),(6,9),(7,10),(7,11),(8,12);