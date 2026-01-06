-- Leasing Seeding

INSERT INTO units (property_id, unit_number, monthly_rent, status, days_on_market, pm_id) VALUES
(1, '101', 1200.00, 'Occupied', 0, 1),
(1, '102', 1250.00, 'Available', 14, 1),
(2, '102B', 2100.00, 'Occupied', 0, 2);

INSERT INTO tenants (unit_id, name, email, lease_start, lease_END, rent_status) VALUES
(1, 'Alice Wonderland', 'alice@example.com', '2024-12-25', '2025-12-24', 'Good Standing'), -- Test for expired tenants
(3, 'John Doe', 'bob@example.com', '2025-11-15', '2026-11-14', 'Delinquent'); -- Test for tenant in bad standing

INSERT INTO prospects (unit_applying_for, name, annual_income, credit_score) VALUES
(2, 'Samuel Skinner', 55000.00, 720),
(2, 'Tyler Jones', 99000.00, 580);

