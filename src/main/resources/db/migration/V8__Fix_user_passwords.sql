-- Fix user passwords with correct BCrypt hashes

-- Update admin password (admin123)
UPDATE users
SET password = '$2b$10$bbsGWLtqrnGv8apyrCqb/u5fETWU2Llc6OrZBd9NTvlfpIBupIu8a'
WHERE username = 'admin';

-- Update professor passwords (prof123)
UPDATE users
SET password = '$2b$10$EfPV3iDURLu/NxiN7YEegOwtKwuQbiOtP/lA7qULWxxu.RAhuBL9S'
WHERE username IN ('maria.rodriguez', 'carlos.martinez');

-- Update student passwords (est123)
UPDATE users
SET password = '$2b$10$3uNflsUkmGHHT1mX45ArCeA3mhmdpYVZ8gXFO9sFL76wBgB30IvTa'
WHERE username IN ('ana.gonzalez', 'luis.ramirez', 'carmen.lopez');
