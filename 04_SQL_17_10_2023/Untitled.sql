CREATE TABLE `Players` (
  `player_id` integer,
  `username` varchar(225),
  `email` varchar(128),
  `password` varchar(20),
  `rating` integer,
  `registration_date` timestamp,
  `is_blocked` bool
);

CREATE TABLE `Matches` (
  `match_id` integer,
  `player1_id` integer,
  `player2_id` integer,
  `start_time` timestamp,
  `end_time` timestamp,
  `winner_id` integer,
  `status` bool
);

CREATE TABLE `Chats` (
  `chat_id` integer,
  `chat_name` varchar(20)
);

CREATE TABLE `Messages` (
  `message_id` integer,
  `chat_id` integer,
  `player_id` integer,
  `message_text` text(1000),
  `timestamp` timestamp
);

CREATE TABLE `Maps` (
  `map_id` integer,
  `match_id` integer,
  `board_state` enum
);

ALTER TABLE `Matches` ADD FOREIGN KEY (`player1_id`) REFERENCES `Players` (`player_id`);

ALTER TABLE `Matches` ADD FOREIGN KEY (`player2_id`) REFERENCES `Players` (`player_id`);

ALTER TABLE `Messages` ADD FOREIGN KEY (`chat_id`) REFERENCES `Chats` (`chat_id`);

ALTER TABLE `Messages` ADD FOREIGN KEY (`player_id`) REFERENCES `Players` (`player_id`);

ALTER TABLE `Maps` ADD FOREIGN KEY (`match_id`) REFERENCES `Matches` (`match_id`);
