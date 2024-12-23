-- Удаление триггеров
DROP TRIGGER IF EXISTS trigger_update_family_budget_on_insert ON transactions CASCADE;
DROP TRIGGER IF EXISTS trigger_update_family_budget_on_delete ON transactions CASCADE;

-- Удаление функций
DROP FUNCTION IF EXISTS update_family_budget_on_insert() CASCADE;
DROP FUNCTION IF EXISTS update_family_budget_on_delete() CASCADE;

-- Удаление индексов
DROP INDEX IF EXISTS idx_transactions_category_id CASCADE;
DROP INDEX IF EXISTS idx_transactions_user_id CASCADE;
DROP INDEX IF EXISTS idx_transactions_family_id CASCADE;
DROP INDEX IF EXISTS idx_reminders_family_id CASCADE;
DROP INDEX IF EXISTS idx_reports_family_id CASCADE;
DROP INDEX IF EXISTS idx_goal_strategies_goal_id CASCADE;
DROP INDEX IF EXISTS idx_goals_family_id CASCADE;
DROP INDEX IF EXISTS idx_category_tags_tag_id CASCADE;
DROP INDEX IF EXISTS idx_category_tags_category_id CASCADE;
DROP INDEX IF EXISTS idx_categories_type CASCADE;
DROP INDEX IF EXISTS idx_categories_created_by CASCADE;
DROP INDEX IF EXISTS idx_category_budgets_category_id CASCADE;
DROP INDEX IF EXISTS idx_category_budgets_family_id CASCADE;
DROP INDEX IF EXISTS idx_user_profiles_user_id CASCADE;
DROP INDEX IF EXISTS idx_family_members_user_id CASCADE;
DROP INDEX IF EXISTS idx_family_members_family_id CASCADE;
DROP INDEX IF EXISTS idx_users_email CASCADE;

--Удаление записей из таблиц
DELETE FROM transactions;
DELETE FROM reminders;
DELETE FROM reports;
DELETE FROM goal_strategies;
DELETE FROM goals;
DELETE FROM category_tags;
DELETE FROM tags;
DELETE FROM category_budgets;
DELETE FROM categories;
DELETE FROM user_profiles;
DELETE FROM family_members;
DELETE FROM users;
DELETE FROM families;
DELETE FROM family_budgets;

-- Удаление таблиц
DROP TABLE IF EXISTS transactions CASCADE;
DROP TABLE IF EXISTS reminders CASCADE;
DROP TABLE IF EXISTS reports CASCADE;
DROP TABLE IF EXISTS goal_strategies CASCADE;
DROP TABLE IF EXISTS goals CASCADE;
DROP TABLE IF EXISTS category_tags CASCADE;
DROP TABLE IF EXISTS tags CASCADE;
DROP TABLE IF EXISTS category_budgets CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS user_profiles CASCADE;
DROP TABLE IF EXISTS family_members CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS families CASCADE;
DROP TABLE IF EXISTS family_budgets CASCADE;