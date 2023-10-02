INSERT INTO game_character (id, parentid, name, color)
SELECT  character_init_data.id, character_init_data.parentid, character_init_data.name, character_init_data.color FROM (
    SELECT 1 AS id, 0 AS parentid, 'Warrior' AS name, 'red' AS color UNION ALL
    SELECT 2, 0, 'Wizard', 'green' UNION ALL
    SELECT 3, 0, 'Priest', 'white' UNION ALL
    SELECT 4, 0, 'Rogue', 'yellow' UNION ALL
    SELECT 5, 1, 'Fighter', 'blue' UNION ALL
    SELECT 6, 1, 'Paladin', 'lighblue' UNION ALL
    SELECT 7, 1, 'Ranger', 'lighgreen' UNION ALL
    SELECT 8, 2, 'Mage', 'grey' UNION ALL
    SELECT 9, 2, 'Specialist wizard', 'lightgrey' UNION ALL
    SELECT 10, 3, 'Cleric', 'red' UNION ALL
    SELECT 11, 3, 'Druid', 'green' UNION ALL
    SELECT 12, 3, 'Priest of specific mythos', 'white' UNION ALL
    SELECT 13, 4, 'Thief', 'yellow' UNION ALL
    SELECT 14, 4, 'Bard', 'blue' UNION ALL
    SELECT 15, 13, 'Assassin', 'lighblue'
) AS character_init_data
LEFT JOIN (
    SELECT id FROM game_character WHERE id BETWEEN 1 AND 15
) existing_characters
ON character_init_data.id = existing_characters.id
WHERE existing_characters.id IS NULL;
