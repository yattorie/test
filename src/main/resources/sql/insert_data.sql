-- Таблица семей
INSERT INTO families (family_name, family_description)
VALUES
('Smith Family', 'A family based in New York'),
('Johnson Family', 'A family based in California'),
('Williams Family', 'A family from Texas'),
('Brown Family', 'A family living in Florida'),
('Davis Family', 'A large family in Oregon'),
('Miller Family', 'A small family based in Nevada'),
('Taylor Family', 'A single-parent family in Ohio');

-- Таблица пользователей
INSERT INTO users (username, email, password)
VALUES
('johnsmith123', 'johnsmith@example.com', 'password123'),
('annajohnson', 'annajohnson@example.com', 'securepassword'),
('markbrown89', 'markbrown@example.com', 'mypassword89'),
('michaelwilliams', 'michaelwilliams@example.com', 'password1234'),
('lisabrown', 'lisabrown@example.com', 'securepass'),
('jamestaylor', 'jamestaylor@example.com', 'mysecurepass'),
('sophiamiller', 'sophiamiller@example.com', 'simplepassword'),
('emmadavis', 'emmadavis@example.com', 'difficultpass');

-- Таблица участников семьи
INSERT INTO family_members (family_id, user_id, role)
VALUES
(1, 1, 'PARENT'), -- John Smith as Parent
(2, 2, 'PARENT'), -- Anna Johnson as Parent
(2, 3, 'CHILD'), -- Mark Brown as Child in Johnson Family
(3, 4, 'PARENT'), -- Michael Williams as Parent in Williams Family
(4, 5, 'CHILD'), -- Lisa Brown as Child in Brown Family
(5, 6, 'PARENT'), -- James Taylor as Parent in Davis Family
(6, 7, 'PARENT'), -- Sophia Miller as Parent in Miller Family
(7, 8, 'CHILD'); -- Emma Davis as Child in Taylor Family

-- Таблица профилей пользователей
INSERT INTO user_profiles (user_id, date_of_birth, family_status, gender)
VALUES
(1, '1980-06-15', 'Married', 'Male'),
(2, '1985-09-20', 'Married', 'Female'),
(3, '2010-11-05', 'Single', 'Male'),
(4, '1975-03-10', 'Married', 'Male'),
(5, '2005-08-25', 'Single', 'Female'),
(6, '1982-12-14', 'Married', 'Male'),
(7, '1990-04-07', 'Single', 'Female'),
(8, '2015-05-18', 'Single', 'Female');

-- Таблица категорий
INSERT INTO categories (category_name, type, created_by)
VALUES
('Groceries', 'Expense', 1),
('Salary', 'Income', 2),
('Utilities', 'Expense', 1),
('Healthcare', 'Expense', 2),
('Investment', 'Income', 1),
('Rent', 'Expense', 3),
('Entertainment', 'Expense', 4),
('Freelance Income', 'Income', 5);

-- Таблица бюджета категорий
INSERT INTO category_budgets (family_id, category_id, budget_amount, spent_amount)
VALUES
(1, 1, 500.00, 150.00), -- Smith Family groceries
(2, 3, 200.00, 50.00),  -- Johnson Family utilities
(3, 4, 300.00, 100.00), -- Williams Family healthcare
(4, 6, 800.00, 600.00), -- Brown Family rent
(5, 7, 500.00, 300.00); -- Davis Family entertainment

-- Таблица тегов
INSERT INTO tags (name)
VALUES
('Essential'),
('Recurring'),
('Luxury');

-- Таблица связи категорий и тегов
INSERT INTO category_tags (category_id, tag_id)
VALUES
(1, 1), -- Groceries tagged as Essential
(3, 2), -- Utilities tagged as Recurring
(7, 3); -- Entertainment tagged as Luxury

-- Таблица целей
INSERT INTO goals (family_id, goal_name, description, target_amount, due_date)
VALUES
(1, 'Vacation Fund', 'Save for a summer vacation', 3000.00, '2024-06-01'),
(2, 'New Car', 'Save for a new family car', 15000.00, '2025-12-31'),
(3, 'Emergency Fund', 'Save for unexpected expenses', 10000.00, '2025-01-01'),
(4, 'Education Fund', 'Save for college tuition', 50000.00, '2028-08-15'),
(5, 'House Renovation', 'Renovate the family house', 20000.00, '2026-03-30');

-- Таблица стратегий для достижения целей
INSERT INTO goal_strategies (goal_id, description, step)
VALUES
(1, 'Set aside $500 monthly from salary', 'Step 1'),
(2, 'Cut down on non-essential expenses', 'Step 2'),
(3, 'Save 10% of income monthly', 'Step 1'),
(4, 'Apply for scholarships to reduce costs', 'Step 1');

-- Таблица отчетов
INSERT INTO reports (family_id, report_name, description)
VALUES
(1, 'Monthly Expenses Report', 'A detailed report of all monthly expenses'),
(2, 'Yearly Income Report', 'A summary of the family yearly income');

-- Таблица транзакций
INSERT INTO transactions (family_id, user_id, category_id, amount, type, description)
VALUES
(1, 1, 1, 200.00, 'Expense', 'Weekly grocery shopping'),
(2, 2, 2, 3000.00, 'Income', 'Monthly salary credited'),
(2, 3, 3, 50.00, 'Expense', 'Electricity bill payment'),
(3, 4, 5, 2500.00, 'Income', 'Freelance project payment'),
(4, 5, 6, 1200.00, 'Expense', 'Monthly rent payment'),
(5, 6, 7, 500.00, 'Expense', 'Weekly movie nights'),
(1, 1, 4, 1000.00, 'Expense', 'Health checkup'),
(2, 2, 5, 5000.00, 'Income', 'Quarterly investment returns');

-- Таблица напоминаний
INSERT INTO reminders (family_id, description, reminder_date, status)
VALUES
(1, 'Pay utility bill', '2024-12-01 10:00:00', 'Pending'),
(2, 'Review monthly budget', '2024-11-30 18:00:00', 'Pending'),
(3, 'Submit annual tax report', '2024-04-15 12:00:00', 'Pending'),
(4, 'Pay house rent', '2024-12-01 09:00:00', 'Pending'),
(5, 'Renew car insurance', '2024-11-15 10:00:00', 'Pending');

-- Таблица семейного бюджета
INSERT INTO family_budgets (family_id, balance)
VALUES
(1, 5000.00),
(2, 10000.00),
(3, 8000.00),
(4, 12000.00),
(5, 15000.00);

