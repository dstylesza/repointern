-- name: get-cities
-- Selects all cities from table
SELECT *
FROM city;

-- name: get-cities-by-provinceid
-- Select all cities for a specific province. Expects :provinceid
SELECT *
FROM city
WHERE provinceid = :provinceid
ORDER BY name;
