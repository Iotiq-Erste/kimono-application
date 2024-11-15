ALTER TABLE product ALTER COLUMN flexibility TYPE float;
ALTER TABLE product RENAME COLUMN flexibility TO weight_per_unit_area;

ALTER TABLE product_demand ALTER COLUMN flexibility TYPE float;
ALTER TABLE product_demand RENAME COLUMN flexibility TO weight_per_unit_area;