CREATE VIEW sector_with_depth AS
WITH RECURSIVE SectorHierarchy (id, name, parent_id, depth) AS (
    SELECT
        s.id,
        s.name,
        s.parent_id,
        0 AS depth
    FROM sector s
    WHERE s.parent_id IS NULL

    UNION ALL

    SELECT
        s.id,
        s.name,
        s.parent_id,
        sh.depth + 1 AS depth
    FROM sector s
    INNER JOIN SectorHierarchy sh ON s.parent_id = sh.id
)
SELECT * FROM SectorHierarchy;