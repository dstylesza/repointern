-- name: insert-educationlevel<!
-- inserts educationlevel as first step in intern registration expects :educationlevelid
INSERT INTO intern
(educationlevelid)
VALUES (:educationlevelid);


-- name: delete-intern
-- expects :id
DELETE FROM intern
WHERE id = :id;

-- name: update-intern<!
-- updates the intern table. Expects :id
UPDATE intern
SET
  educationlevelid = :educationlevelid,
  majors = :majors,
  minors = :minors,
  age = :age,
  about = :about,
  provinceid = :provinceid,
  cityid = :cityid
WHERE
  id = :id


