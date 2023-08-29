
-- create no 01
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `name_en` varchar(20) NOT NULL DEFAULT 'tmp',
  `behavior_th` varchar(511) CHARACTER SET utf8 NOT NULL DEFAULT 'tmp',
  `behavior_en` varchar(511) NOT NULL DEFAULT 'tmp',
  `name_th` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT 'tmp',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


-- create no 02
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL DEFAULT 'tmp',
  `last_name` varchar(100) NOT NULL DEFAULT 'tmp',
  `email` varchar(100) NOT NULL DEFAULT 'tmp',
  `image` varchar(100) NOT NULL DEFAULT 'tmp',
  `birthdays` date DEFAULT NULL,
  `gender` varchar(50) DEFAULT 'tmp',
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


-- create no 03
CREATE TABLE `choices` (
  `choice_id` int(11) NOT NULL AUTO_INCREMENT,
  `name_th` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT 'tmp',
  `name_en` varchar(255) NOT NULL DEFAULT 'tmp',
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`choice_id`),
  KEY `li4d_id` (`category_id`),
  CONSTRAINT `choices_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


-- create no 04
CREATE TABLE `user_history` (
  `history_id` int(11) NOT NULL AUTO_INCREMENT,
  `likes` json DEFAULT NULL,
  `dislikes` json DEFAULT NULL,
  `dates` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`history_id`),
  KEY `user_id` (`user_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `user_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `user_history_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;