
-- studyroomdb.`member` definition
CREATE TABLE IF NOT EXISTS `member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nickname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- studyroomdb.reservation definition
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime(6) DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  `room_no` bigint DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `status` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_reservation_member` (`member_id`),
  CONSTRAINT `FK_reservation_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- studyroomdb.subject definition
CREATE TABLE `subject` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint DEFAULT NULL,
  `subject_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `total_study_cnt` bigint DEFAULT NULL,
  `total_study_minute` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_subject_member` (`member_id`),
  CONSTRAINT `FK_subject_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- studyroomdb.reservation_subject definition
CREATE TABLE `reservation_subject` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `reservation_id` bigint DEFAULT NULL,
  `subject_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_reservationsubject_reservation` (`reservation_id`),
  KEY `FK_reservationsubject_subject` (`subject_id`),
  CONSTRAINT `FK_reservationsubject_reservation` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`),
  CONSTRAINT `FK_reservationsubject_subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;