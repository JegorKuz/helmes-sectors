package com.helmes.sectors.repository;

import com.helmes.sectors.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer> {
    @Query(
            value = """
            WITH RECURSIVE SectorTree (
                id,
                name,
                parent_id,
                sort_path
            ) AS (
                SELECT
                    id,
                    name,
                    parent_id,
                    CAST(id AS VARCHAR(100)) AS sort_path
                FROM
                    sector
                WHERE
                    parent_id IS NULL
                UNION ALL
                SELECT
                    s.id,
                    s.name,
                    s.parent_id,
                    CAST(st.sort_path || '.' || s.id AS VARCHAR(100)) AS sort_path
                FROM
                    sector s
                INNER JOIN
                    SectorTree st ON s.parent_id = st.id
            )
            SELECT
                id,
                name,
                parent_id
            FROM
                SectorTree
            ORDER BY
                sort_path
        """,
            nativeQuery = true
    )
    List<Sector> findAllHierarchicallySorted();
}