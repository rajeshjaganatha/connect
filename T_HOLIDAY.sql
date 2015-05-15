/*
Navicat SQL Server Data Transfer

Source Server         : local
Source Server Version : 105000
Source Host           : localhost:1433
Source Database       : Connect
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 105000
File Encoding         : 65001

Date: 2015-04-28 13:50:14
*/


-- ----------------------------
-- Table structure for [dbo].[T_HOLIDAY]
-- ----------------------------
DROP TABLE [dbo].[T_HOLIDAY]
GO
CREATE TABLE [dbo].[T_HOLIDAY] (
[HOLIDAY_ID] int NOT NULL IDENTITY(1,1) ,
[HOLIDAY_NAME] varchar(255) NOT NULL ,
[DATE] date NOT NULL ,
[DAY] varchar(50) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[T_HOLIDAY]', RESEED, 29)
GO

-- ----------------------------
-- Records of T_HOLIDAY
-- ----------------------------
SET IDENTITY_INSERT [dbo].[T_HOLIDAY] ON
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'3', N'Milad-un-Nabi', N'2014-01-14', N'Tuesday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'4', N'Makara Samkranti', N'2014-01-14', N'Tuesday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'5', N'Pongal', N'2014-01-14', N'Tuesday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'6', N'Republic Day', N'2014-01-26', N'Sunday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'7', N'Maha Shivaratri', N'2014-02-27', N'Thursday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'8', N'Gudi Padva / Ugadi /', N'2014-03-31', N'Monday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'9', N'Annual Accounts Closing', N'2014-04-01', N'Tuesday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'10', N'Mahavir Jayanti', N'2014-04-13', N'Sunday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'11', N'Dr Ambedkar Jayanti', N'2014-04-14', N'Monday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'12', N'Good Friday', N'2014-04-18', N'Friday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'13', N'May Day', N'2014-05-01', N'Thursday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'14', N'Shiv Jayanthi', N'2014-05-01', N'Thursday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'15', N'Basava Jayanti', N'2014-05-02', N'Friday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'16', N'Ramazan / Idul Fitr', N'2014-07-29', N'Tuesday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'17', N'Independence Day', N'2014-08-15', N'Friday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'18', N'Mahalaya', N'2014-09-24', N'Wednesday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'19', N'Mid-Year Accounts', N'2014-09-30', N'Tuesday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'20', N'Mahatma Gandhis', N'2014-10-02', N'Thursday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'21', N'Dussehra', N'2014-10-03', N'Friday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'22', N'Bakri Id / Idul Zuha', N'2014-10-06', N'Monday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'23', N'Birthday of Maharishi', N'2014-10-08', N'Wednesday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'24', N'Naraka Chaturdashi', N'2014-10-22', N'Wednesday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'25', N'Balipadyami Diwali', N'2014-10-24', N'Friday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'26', N'Kannada Rajyothsava', N'2014-11-01', N'Saturday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'27', N'Muharram', N'2014-11-04', N'Tuesday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'28', N'Kanaka Jayanti', N'2014-11-10', N'Monday');
GO
INSERT INTO [dbo].[T_HOLIDAY] ([HOLIDAY_ID], [HOLIDAY_NAME], [DATE], [DAY]) VALUES (N'29', N'Christmas Day', N'2014-12-25', N'Thursday');
GO
SET IDENTITY_INSERT [dbo].[T_HOLIDAY] OFF
GO

-- ----------------------------
-- Indexes structure for table T_HOLIDAY
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[T_HOLIDAY]
-- ----------------------------
ALTER TABLE [dbo].[T_HOLIDAY] ADD PRIMARY KEY ([HOLIDAY_ID])
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[T_HOLIDAY]
-- ----------------------------
ALTER TABLE [dbo].[T_HOLIDAY] ADD FOREIGN KEY ([HOLIDAY_ID]) REFERENCES [dbo].[T_HOLIDAY] ([HOLIDAY_ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO
