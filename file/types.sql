/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : douban
Target Host     : localhost:3306
Target Database : douban
Date: 2017-06-17 10:54:52
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types` (
  `RID` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`RID`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES ('1', 'http://book.douban.com/tag/小说', '文学', '小说');
INSERT INTO `types` VALUES ('2', 'http://book.douban.com/tag/外国文学', '文学', '外国文学');
INSERT INTO `types` VALUES ('3', 'http://book.douban.com/tag/文学', '文学', '文学');
INSERT INTO `types` VALUES ('4', 'http://book.douban.com/tag/随笔', '文学', '随笔');
INSERT INTO `types` VALUES ('5', 'http://book.douban.com/tag/中国文学', '文学', '中国文学');
INSERT INTO `types` VALUES ('6', 'http://book.douban.com/tag/经典', '文学', '经典');
INSERT INTO `types` VALUES ('7', 'http://book.douban.com/tag/日本文学', '文学', '日本文学');
INSERT INTO `types` VALUES ('8', 'http://book.douban.com/tag/散文', '文学', '散文');
INSERT INTO `types` VALUES ('9', 'http://book.douban.com/tag/村上春树', '文学', '村上春树');
INSERT INTO `types` VALUES ('10', 'http://book.douban.com/tag/诗歌', '文学', '诗歌');
INSERT INTO `types` VALUES ('11', 'http://book.douban.com/tag/童话', '文学', '童话');
INSERT INTO `types` VALUES ('12', 'http://book.douban.com/tag/王小波', '文学', '王小波');
INSERT INTO `types` VALUES ('13', 'http://book.douban.com/tag/杂文', '文学', '杂文');
INSERT INTO `types` VALUES ('14', 'http://book.douban.com/tag/古典文学', '文学', '古典文学');
INSERT INTO `types` VALUES ('15', 'http://book.douban.com/tag/儿童文学', '文学', '儿童文学');
INSERT INTO `types` VALUES ('16', 'http://book.douban.com/tag/名著', '文学', '名著');
INSERT INTO `types` VALUES ('17', 'http://book.douban.com/tag/张爱玲', '文学', '张爱玲');
INSERT INTO `types` VALUES ('18', 'http://book.douban.com/tag/余华', '文学', '余华');
INSERT INTO `types` VALUES ('19', 'http://book.douban.com/tag/当代文学', '文学', '当代文学');
INSERT INTO `types` VALUES ('20', 'http://book.douban.com/tag/钱钟书', '文学', '钱钟书');
INSERT INTO `types` VALUES ('21', 'http://book.douban.com/tag/外国名著', '文学', '外国名著');
INSERT INTO `types` VALUES ('22', 'http://book.douban.com/tag/鲁迅', '文学', '鲁迅');
INSERT INTO `types` VALUES ('23', 'http://book.douban.com/tag/诗词', '文学', '诗词');
INSERT INTO `types` VALUES ('24', 'http://book.douban.com/tag/茨威格', '文学', '茨威格');
INSERT INTO `types` VALUES ('25', 'http://book.douban.com/tag/米兰·昆德拉', '文学', '米兰·昆德拉');
INSERT INTO `types` VALUES ('26', 'http://book.douban.com/tag/杜拉斯', '文学', '杜拉斯');
INSERT INTO `types` VALUES ('27', 'http://book.douban.com/tag/港台', '文学', '港台');
INSERT INTO `types` VALUES ('28', 'http://book.douban.com/tag/漫画', '流行', '漫画');
INSERT INTO `types` VALUES ('29', 'http://book.douban.com/tag/绘本', '流行', '绘本');
INSERT INTO `types` VALUES ('30', 'http://book.douban.com/tag/推理', '流行', '推理');
INSERT INTO `types` VALUES ('31', 'http://book.douban.com/tag/青春', '流行', '青春');
INSERT INTO `types` VALUES ('32', 'http://book.douban.com/tag/东野圭吾', '流行', '东野圭吾');
INSERT INTO `types` VALUES ('33', 'http://book.douban.com/tag/科幻', '流行', '科幻');
INSERT INTO `types` VALUES ('34', 'http://book.douban.com/tag/言情', '流行', '言情');
INSERT INTO `types` VALUES ('35', 'http://book.douban.com/tag/悬疑', '流行', '悬疑');
INSERT INTO `types` VALUES ('36', 'http://book.douban.com/tag/武侠', '流行', '武侠');
INSERT INTO `types` VALUES ('37', 'http://book.douban.com/tag/奇幻', '流行', '奇幻');
INSERT INTO `types` VALUES ('38', 'http://book.douban.com/tag/韩寒', '流行', '韩寒');
INSERT INTO `types` VALUES ('39', 'http://book.douban.com/tag/日本漫画', '流行', '日本漫画');
INSERT INTO `types` VALUES ('40', 'http://book.douban.com/tag/耽美', '流行', '耽美');
INSERT INTO `types` VALUES ('41', 'http://book.douban.com/tag/亦舒', '流行', '亦舒');
INSERT INTO `types` VALUES ('42', 'http://book.douban.com/tag/推理小说', '流行', '推理小说');
INSERT INTO `types` VALUES ('43', 'http://book.douban.com/tag/三毛', '流行', '三毛');
INSERT INTO `types` VALUES ('44', 'http://book.douban.com/tag/网络小说', '流行', '网络小说');
INSERT INTO `types` VALUES ('45', 'http://book.douban.com/tag/安妮宝贝', '流行', '安妮宝贝');
INSERT INTO `types` VALUES ('46', 'http://book.douban.com/tag/郭敬明', '流行', '郭敬明');
INSERT INTO `types` VALUES ('47', 'http://book.douban.com/tag/穿越', '流行', '穿越');
INSERT INTO `types` VALUES ('48', 'http://book.douban.com/tag/金庸', '流行', '金庸');
INSERT INTO `types` VALUES ('49', 'http://book.douban.com/tag/轻小说', '流行', '轻小说');
INSERT INTO `types` VALUES ('50', 'http://book.douban.com/tag/阿加莎·克里斯蒂', '流行', '阿加莎·克里斯蒂');
INSERT INTO `types` VALUES ('51', 'http://book.douban.com/tag/几米', '流行', '几米');
INSERT INTO `types` VALUES ('52', 'http://book.douban.com/tag/科幻小说', '流行', '科幻小说');
INSERT INTO `types` VALUES ('53', 'http://book.douban.com/tag/魔幻', '流行', '魔幻');
INSERT INTO `types` VALUES ('54', 'http://book.douban.com/tag/青春文学', '流行', '青春文学');
INSERT INTO `types` VALUES ('55', 'http://book.douban.com/tag/张小娴', '流行', '张小娴');
INSERT INTO `types` VALUES ('56', 'http://book.douban.com/tag/幾米', '流行', '幾米');
INSERT INTO `types` VALUES ('57', 'http://book.douban.com/tag/J.K.罗琳', '流行', 'J.K.罗琳');
INSERT INTO `types` VALUES ('58', 'http://book.douban.com/tag/高木直子', '流行', '高木直子');
INSERT INTO `types` VALUES ('59', 'http://book.douban.com/tag/古龙', '流行', '古龙');
INSERT INTO `types` VALUES ('60', 'http://book.douban.com/tag/沧月', '流行', '沧月');
INSERT INTO `types` VALUES ('61', 'http://book.douban.com/tag/落落', '流行', '落落');
INSERT INTO `types` VALUES ('62', 'http://book.douban.com/tag/张悦然', '流行', '张悦然');
INSERT INTO `types` VALUES ('63', 'http://book.douban.com/tag/校园', '流行', '校园');
INSERT INTO `types` VALUES ('64', 'http://book.douban.com/tag/历史', '文化', '历史');
INSERT INTO `types` VALUES ('65', 'http://book.douban.com/tag/心理学', '文化', '心理学');
INSERT INTO `types` VALUES ('66', 'http://book.douban.com/tag/哲学', '文化', '哲学');
INSERT INTO `types` VALUES ('67', 'http://book.douban.com/tag/传记', '文化', '传记');
INSERT INTO `types` VALUES ('68', 'http://book.douban.com/tag/文化', '文化', '文化');
INSERT INTO `types` VALUES ('69', 'http://book.douban.com/tag/社会学', '文化', '社会学');
INSERT INTO `types` VALUES ('70', 'http://book.douban.com/tag/艺术', '文化', '艺术');
INSERT INTO `types` VALUES ('71', 'http://book.douban.com/tag/设计', '文化', '设计');
INSERT INTO `types` VALUES ('72', 'http://book.douban.com/tag/社会', '文化', '社会');
INSERT INTO `types` VALUES ('73', 'http://book.douban.com/tag/政治', '文化', '政治');
INSERT INTO `types` VALUES ('74', 'http://book.douban.com/tag/建筑', '文化', '建筑');
INSERT INTO `types` VALUES ('75', 'http://book.douban.com/tag/宗教', '文化', '宗教');
INSERT INTO `types` VALUES ('76', 'http://book.douban.com/tag/电影', '文化', '电影');
INSERT INTO `types` VALUES ('77', 'http://book.douban.com/tag/数学', '文化', '数学');
INSERT INTO `types` VALUES ('78', 'http://book.douban.com/tag/政治学', '文化', '政治学');
INSERT INTO `types` VALUES ('79', 'http://book.douban.com/tag/回忆录', '文化', '回忆录');
INSERT INTO `types` VALUES ('80', 'http://book.douban.com/tag/中国历史', '文化', '中国历史');
INSERT INTO `types` VALUES ('81', 'http://book.douban.com/tag/思想', '文化', '思想');
INSERT INTO `types` VALUES ('82', 'http://book.douban.com/tag/国学', '文化', '国学');
INSERT INTO `types` VALUES ('83', 'http://book.douban.com/tag/音乐', '文化', '音乐');
INSERT INTO `types` VALUES ('84', 'http://book.douban.com/tag/人文', '文化', '人文');
INSERT INTO `types` VALUES ('85', 'http://book.douban.com/tag/人物传记', '文化', '人物传记');
INSERT INTO `types` VALUES ('86', 'http://book.douban.com/tag/绘画', '文化', '绘画');
INSERT INTO `types` VALUES ('87', 'http://book.douban.com/tag/戏剧', '文化', '戏剧');
INSERT INTO `types` VALUES ('88', 'http://book.douban.com/tag/艺术史', '文化', '艺术史');
INSERT INTO `types` VALUES ('89', 'http://book.douban.com/tag/佛教', '文化', '佛教');
INSERT INTO `types` VALUES ('90', 'http://book.douban.com/tag/军事', '文化', '军事');
INSERT INTO `types` VALUES ('91', 'http://book.douban.com/tag/西方哲学', '文化', '西方哲学');
INSERT INTO `types` VALUES ('92', 'http://book.douban.com/tag/二战', '文化', '二战');
INSERT INTO `types` VALUES ('93', 'http://book.douban.com/tag/近代史', '文化', '近代史');
INSERT INTO `types` VALUES ('94', 'http://book.douban.com/tag/考古', '文化', '考古');
INSERT INTO `types` VALUES ('95', 'http://book.douban.com/tag/自由主义', '文化', '自由主义');
INSERT INTO `types` VALUES ('96', 'http://book.douban.com/tag/美术', '文化', '美术');
INSERT INTO `types` VALUES ('97', 'http://book.douban.com/tag/爱情', '生活', '爱情');
INSERT INTO `types` VALUES ('98', 'http://book.douban.com/tag/旅行', '生活', '旅行');
INSERT INTO `types` VALUES ('99', 'http://book.douban.com/tag/生活', '生活', '生活');
INSERT INTO `types` VALUES ('100', 'http://book.douban.com/tag/成长', '生活', '成长');
INSERT INTO `types` VALUES ('101', 'http://book.douban.com/tag/励志', '生活', '励志');
INSERT INTO `types` VALUES ('102', 'http://book.douban.com/tag/心理', '生活', '心理');
INSERT INTO `types` VALUES ('103', 'http://book.douban.com/tag/摄影', '生活', '摄影');
INSERT INTO `types` VALUES ('104', 'http://book.douban.com/tag/女性', '生活', '女性');
INSERT INTO `types` VALUES ('105', 'http://book.douban.com/tag/职场', '生活', '职场');
INSERT INTO `types` VALUES ('106', 'http://book.douban.com/tag/美食', '生活', '美食');
INSERT INTO `types` VALUES ('107', 'http://book.douban.com/tag/教育', '生活', '教育');
INSERT INTO `types` VALUES ('108', 'http://book.douban.com/tag/游记', '生活', '游记');
INSERT INTO `types` VALUES ('109', 'http://book.douban.com/tag/灵修', '生活', '灵修');
INSERT INTO `types` VALUES ('110', 'http://book.douban.com/tag/健康', '生活', '健康');
INSERT INTO `types` VALUES ('111', 'http://book.douban.com/tag/情感', '生活', '情感');
INSERT INTO `types` VALUES ('112', 'http://book.douban.com/tag/手工', '生活', '手工');
INSERT INTO `types` VALUES ('113', 'http://book.douban.com/tag/两性', '生活', '两性');
INSERT INTO `types` VALUES ('114', 'http://book.douban.com/tag/养生', '生活', '养生');
INSERT INTO `types` VALUES ('115', 'http://book.douban.com/tag/人际关系', '生活', '人际关系');
INSERT INTO `types` VALUES ('116', 'http://book.douban.com/tag/家居', '生活', '家居');
INSERT INTO `types` VALUES ('117', 'http://book.douban.com/tag/自助游', '生活', '自助游');
INSERT INTO `types` VALUES ('118', 'http://book.douban.com/tag/经济学', '经管', '经济学');
INSERT INTO `types` VALUES ('119', 'http://book.douban.com/tag/管理', '经管', '管理');
INSERT INTO `types` VALUES ('120', 'http://book.douban.com/tag/经济', '经管', '经济');
INSERT INTO `types` VALUES ('121', 'http://book.douban.com/tag/商业', '经管', '商业');
INSERT INTO `types` VALUES ('122', 'http://book.douban.com/tag/金融', '经管', '金融');
INSERT INTO `types` VALUES ('123', 'http://book.douban.com/tag/投资', '经管', '投资');
INSERT INTO `types` VALUES ('124', 'http://book.douban.com/tag/营销', '经管', '营销');
INSERT INTO `types` VALUES ('125', 'http://book.douban.com/tag/创业', '经管', '创业');
INSERT INTO `types` VALUES ('126', 'http://book.douban.com/tag/理财', '经管', '理财');
INSERT INTO `types` VALUES ('127', 'http://book.douban.com/tag/广告', '经管', '广告');
INSERT INTO `types` VALUES ('128', 'http://book.douban.com/tag/股票', '经管', '股票');
INSERT INTO `types` VALUES ('129', 'http://book.douban.com/tag/企业史', '经管', '企业史');
INSERT INTO `types` VALUES ('130', 'http://book.douban.com/tag/策划', '经管', '策划');
INSERT INTO `types` VALUES ('131', 'http://book.douban.com/tag/科普', '科技', '科普');
INSERT INTO `types` VALUES ('132', 'http://book.douban.com/tag/互联网', '科技', '互联网');
INSERT INTO `types` VALUES ('133', 'http://book.douban.com/tag/编程', '科技', '编程');
INSERT INTO `types` VALUES ('134', 'http://book.douban.com/tag/科学', '科技', '科学');
INSERT INTO `types` VALUES ('135', 'http://book.douban.com/tag/交互设计', '科技', '交互设计');
INSERT INTO `types` VALUES ('136', 'http://book.douban.com/tag/用户体验', '科技', '用户体验');
INSERT INTO `types` VALUES ('137', 'http://book.douban.com/tag/算法', '科技', '算法');
INSERT INTO `types` VALUES ('138', 'http://book.douban.com/tag/web', '科技', 'web');
INSERT INTO `types` VALUES ('139', 'http://book.douban.com/tag/科技', '科技', '科技');
INSERT INTO `types` VALUES ('140', 'http://book.douban.com/tag/UE', '科技', 'UE');
INSERT INTO `types` VALUES ('141', 'http://book.douban.com/tag/通信', '科技', '通信');
INSERT INTO `types` VALUES ('142', 'http://book.douban.com/tag/交互', '科技', '交互');
INSERT INTO `types` VALUES ('143', 'http://book.douban.com/tag/UCD', '科技', 'UCD');
INSERT INTO `types` VALUES ('144', 'http://book.douban.com/tag/神经网络', '科技', '神经网络');
INSERT INTO `types` VALUES ('145', 'http://book.douban.com/tag/程序', '科技', '程序');
