SELECT p.name person, c.name company
FROM person p
JOIN company c ON p.company_id = c.id
WHERE p.company_id IN (SELECT id FROM company WHERE id != 5);

SELECT company.name, COUNT(*)
FROM company
JOIN person ON company.id = person.company_id
GROUP BY company.name
HAVING
    COUNT(*) =
        (SELECT MAX(person)
            FROM (SELECT COUNT(*) AS person FROM company
                JOIN person ON company.id = person.company_id
                    GROUP BY company.name) AS max)