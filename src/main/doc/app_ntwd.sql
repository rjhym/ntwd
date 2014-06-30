-- phpMyAdmin SQL Dump
-- version 3.3.8.1
-- http://www.phpmyadmin.net
--
-- 主机: w.rdc.sae.sina.com.cn:3307
-- 生成日期: 2014 年 06 月 29 日 20:04
-- 服务器版本: 5.5.23
-- PHP 版本: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `app_ntwd`
--

-- --------------------------------------------------------

--
-- 表的结构 `t_phrase`
--

CREATE TABLE IF NOT EXISTS `t_phrase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phrase` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '成语',
  `phrase_spell` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '成语的拼音',
  `phrase_paraphrase` varchar(3000) COLLATE utf8_bin NOT NULL COMMENT '成语释义',
  `phrase_provenance` varchar(3000) COLLATE utf8_bin NOT NULL COMMENT '成语出处',
  `phrase_demo` varchar(3000) COLLATE utf8_bin NOT NULL COMMENT '成语示例',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `t_phrase`
--

INSERT INTO `t_phrase` (`id`, `phrase`, `phrase_spell`, `phrase_paraphrase`, `phrase_provenance`, `phrase_demo`, `last_update_time`) VALUES
(3, '厚积薄发', 'hòu jī bó fā', '厚积：指大量地、充分地积蓄；薄发：指少量地、慢慢地放出。多多积蓄，慢慢放出。形容只有准备充分才能办好事情。', '', '', '2014-06-29 17:21:38');

-- --------------------------------------------------------

--
-- 表的结构 `t_phrase_page`
--

CREATE TABLE IF NOT EXISTS `t_phrase_page` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `phrase` varchar(500) NOT NULL,
  `phrase_url` varchar(255) NOT NULL,
  `phrase_initial` varchar(100) NOT NULL COMMENT '首字母',
  `phrase_page` int(11) NOT NULL COMMENT '页数',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- 转存表中的数据 `t_phrase_page`
--

