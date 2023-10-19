CREATE TABLE `Users` (
  `user_id` integer,
  `username` varchar(128),
  `email` varchar(128),
  `password` varchar(20),
  `registration_date` timestamp,
  `is_blocked` bool
);

CREATE TABLE `Tracks` (
  `track_id` integer,
  `title` varchar(100),
  `artist` varchar(100),
  `duration` timestamp,
  `release_date` timestamp,
  `genre` varchar(40),
  `reaction_id` integer
);

CREATE TABLE `Reactions` (
  `reaction_id` integer,
  `user_id` integer,
  `reaction_type` enum,
  `reaction_text` text(1000)
);

CREATE TABLE `Playlists` (
  `playlist_id` integer,
  `user_id` integer,
  `title` varchar(100),
  `creation_date` timestamp
);

CREATE TABLE `PlaylistTracks` (
  `playlisttrack_id` integer,
  `playlist_id` integer,
  `track_id` integer
);

ALTER TABLE `Reactions` ADD FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`);

ALTER TABLE `Playlists` ADD FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`);

ALTER TABLE `PlaylistTracks` ADD FOREIGN KEY (`playlist_id`) REFERENCES `Playlists` (`playlist_id`);

ALTER TABLE `PlaylistTracks` ADD FOREIGN KEY (`track_id`) REFERENCES `Tracks` (`track_id`);

ALTER TABLE `Tracks` ADD FOREIGN KEY (`reaction_id`) REFERENCES `Reactions` (`reaction_id`);
