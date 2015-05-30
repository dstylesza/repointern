-- name: insert-intern-user<!
-- inserts a user from the homepage form post - Expects :name :email :password :internid
INSERT INTO users (name, password, email, internid)
VALUES (:name, :password, :email, :internid);


-- name: get-user-by-email
-- Selects a user based on their email address expects :email
SELECT *
FROM users
WHERE email = :email;

-- name: get-intern-by-id
-- gets all info from the intern userview table by internid expects :userid
SELECT *
FROM internuserview
WHERE id = :userid;


-- name: update-user<!
-- updates the user table
UPDATE users
SET
  name = :name,
  email = :email,
  mobileno = :mobileno
WHERE
  id = :id;


-- name: verify-user<!
-- verifies user account expects :id
UPDATE users
SET
  verified = 1
WHERE
  id = :id
