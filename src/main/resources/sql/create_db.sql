CREATE DATABASE family_finances;

-- Таблица семей
CREATE TABLE families (
    family_id SERIAL PRIMARY KEY,
    family_name VARCHAR(255) NOT NULL,
    family_description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Таблица пользователей
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL CHECK (LENGTH(username) >= 8),
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL CHECK (LENGTH(password) >= 8),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
CREATE INDEX idx_users_email ON users(email);

-- Таблица участников семьи
CREATE TABLE family_members (
    family_member_id SERIAL PRIMARY KEY,
    family_id INT NOT NULL,
    user_id INT NOT NULL,
    role VARCHAR(50) NOT NULL,
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (family_id) REFERENCES families(family_id)
);
CREATE INDEX idx_family_members_family_id ON family_members(family_id);
CREATE INDEX idx_family_members_user_id ON family_members(user_id);

-- Таблица профилей пользователей
CREATE TABLE user_profiles (
    user_profile_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    date_of_birth DATE,
    family_status VARCHAR(50) NOT NULL,
    gender VARCHAR(50) NOT NULL CHECK (gender IN ('Male', 'Female')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
CREATE INDEX idx_user_profiles_user_id ON user_profiles(user_id);

-- Таблица категорий
CREATE TABLE categories (
    category_id SERIAL PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL CHECK (type IN ('Income', 'Expense')),
    parent_category_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_by INT NOT NULL,
    FOREIGN KEY (parent_category_id) REFERENCES categories(category_id),
    FOREIGN KEY (created_by) REFERENCES users(user_id)
);
CREATE INDEX idx_categories_created_by ON categories(created_by);
CREATE INDEX idx_categories_type ON categories(type);

-- Таблица бюджета категорий
CREATE TABLE category_budgets (
    category_budget_id SERIAL PRIMARY KEY,
    family_id INT NOT NULL,
    category_id INT NOT NULL,
    budget_amount DECIMAL(15, 2) NOT NULL,
    spent_amount DECIMAL(15, 2) DEFAULT 0 NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (family_id) REFERENCES families(family_id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);
CREATE INDEX idx_category_budgets_family_id ON category_budgets(family_id);
CREATE INDEX idx_category_budgets_category_id ON category_budgets(category_id);

-- Таблица тегов
CREATE TABLE tags (
    tag_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- Таблица связи категорий и тегов
CREATE TABLE category_tags (
    category_tag_id SERIAL PRIMARY KEY,
    category_id INT NOT NULL,
    tag_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (tag_id) REFERENCES tags(tag_id)
);
CREATE INDEX idx_category_tags_category_id ON category_tags(category_id);
CREATE INDEX idx_category_tags_tag_id ON category_tags(tag_id);

-- Таблица целей
CREATE TABLE goals (
    goal_id SERIAL PRIMARY KEY,
    family_id INT NOT NULL,
    goal_name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    target_amount DECIMAL(15, 2) NOT NULL,
    due_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (family_id) REFERENCES families(family_id)
);
CREATE INDEX idx_goals_family_id ON goals(family_id);

-- Таблица стратегий для достижения целей
CREATE TABLE goal_strategies (
    strategy_id SERIAL PRIMARY KEY,
    goal_id INT NOT NULL,
    description TEXT NOT NULL,
    step TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (goal_id) REFERENCES goals(goal_id)
);
CREATE INDEX idx_goal_strategies_goal_id ON goal_strategies(goal_id);

-- Таблица отчетов
CREATE TABLE reports (
    report_id SERIAL PRIMARY KEY,
    family_id INT NOT NULL,
    report_name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (family_id) REFERENCES families(family_id)
);
CREATE INDEX idx_reports_family_id ON reports(family_id);

-- Таблица транзакций
CREATE TABLE transactions (
    transaction_id SERIAL PRIMARY KEY,
    family_id INT NOT NULL,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    type VARCHAR(50) NOT NULL CHECK (type IN ('Income', 'Expense')),
    description TEXT NOT NULL,
    FOREIGN KEY (family_id) REFERENCES families(family_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);
CREATE INDEX idx_transactions_family_id ON transactions(family_id);
CREATE INDEX idx_transactions_user_id ON transactions(user_id);
CREATE INDEX idx_transactions_category_id ON transactions(category_id);

-- Таблица напоминаний
CREATE TABLE reminders (
    reminder_id SERIAL PRIMARY KEY,
    family_id INT NOT NULL,
    description TEXT NOT NULL,
    reminder_date TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL CHECK (status IN ('Pending', 'Completed')) DEFAULT 'Pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (family_id) REFERENCES families(family_id)
);
CREATE INDEX idx_reminders_family_id ON reminders(family_id);

-- Таблица семейного бюджета
CREATE TABLE family_budgets (
    family_id INT PRIMARY KEY,
    balance DECIMAL(15, 2) DEFAULT 0 NOT NULL CHECK (balance >= 0),
    FOREIGN KEY (family_id) REFERENCES families(family_id)
);

CREATE OR REPLACE FUNCTION update_family_budget_on_insert()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.type = 'Income' THEN
        UPDATE family_budgets
        SET balance = balance + NEW.amount
        WHERE family_id = NEW.family_id;
    ELSIF NEW.type = 'Expense' THEN
        UPDATE family_budgets
        SET balance = balance - NEW.amount
        WHERE family_id = NEW.family_id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_update_family_budget_on_insert
AFTER INSERT ON transactions
FOR EACH ROW EXECUTE FUNCTION update_family_budget_on_insert();

CREATE OR REPLACE FUNCTION update_family_budget_on_delete()
RETURNS TRIGGER AS $$
BEGIN
    IF OLD.type = 'Income' THEN
        UPDATE family_budgets
        SET balance = balance - OLD.amount
        WHERE family_id = OLD.family_id;
    ELSIF OLD.type = 'Expense' THEN
        UPDATE family_budgets
        SET balance = balance + OLD.amount
        WHERE family_id = OLD.family_id;
    END IF;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_family_budget_on_delete
AFTER DELETE ON transactions
FOR EACH ROW EXECUTE FUNCTION update_family_budget_on_delete();