-- Inserts wallet records with an initial balance
INSERT INTO wallets(wallet_id, user_id, balance, reserved_balance) VALUES (1, 16, 1000, 0), (2, 18, 1000, 0);

-- Reserves money by moving it from available balance to reserved balance before bank processing
UPDATE wallets SET balance = balance - 500,
reserved_balance = reserved_balance + 500
WHERE user_id = 16
AND balance >= 0;

SELECT * FROM wallets;

-- Creates a withdrawal request with an initial status
INSERT INTO withdrawals( user_id, amount, bank_account_id, status, retry_count, idempotency_key) VALUES( 16, 5, 'ACC16', 'FAILED', 0,'WD-1612');

SELECT * FROM withdrawals;

-- Inserts an event into the outbox table for asynchronous bank processing
INSERT INTO outbox_events( event_type, payload, status) VALUES('WITHDRAWAL','10','PROCESSED');

SELECT * FROM outbox_events;

-- Fetches the latest 10 withdrawal requests
SELECT * FROM withdrawals
WHERE user_id = 16
ORDER BY created_at DESC
LIMIT 10;

-- Counts withdrawals grouped by their status
SELECT status, COUNT(*) AS total
FROM withdrawals
GROUP BY status;

-- Retrieves all pending events waiting for processing ordered by created time
SELECT *
FROM outbox_events
WHERE status='PENDING'
ORDER BY created_at;

-- Changes withdrawal status from PENDING to PROCESSING
UPDATE withdrawals SET status='PROCESSING'
WHERE withdrawal_id=2;

-- Marks the withdrawal as SUCCESS
UPDATE withdrawals SET status='SUCCESS'
WHERE withdrawal_id=2;

-- Marks the withdrawal as FAILED after retry limit
UPDATE withdrawals SET status='FAILED', retry_count=3
WHERE withdrawal_id=6;

-- Removes reserved money after successful settlement
UPDATE wallets SET reserved_balance = reserved_balance - 5
WHERE user_id=16;

-- Returns reserved money back to available balance after withdrawal failure
UPDATE wallets SET balance = balance + 5, reserved_balance = reserved_balance - 5
WHERE user_id=16;

SELECT * FROM outbox_events;

-- Marks processed events after successful publishing
UPDATE outbox_events SET status='PROCESSED'
WHERE id=1 or id=5;

-- Finds an existing withdrawal using its idempotency key
SELECT *
FROM withdrawals
WHERE idempotency_key='WD-166';

-- Retrieves wallet data of a specific user
SELECT *
FROM wallets
WHERE user_id=16;

-- Retrieves details of a particular withdrawal request
SELECT *
FROM withdrawals
WHERE withdrawal_id=9;
