prompt PL/SQL Developer import file
prompt Created on 2018年10月24日 星期三 by Administrator
set feedback off
set define off
prompt Creating SR_FUNC_LIST...
create table SR_FUNC_LIST
(
  id         NUMBER(26) not null,
  grade      NUMBER,
  parent_id  NUMBER(26),
  sname      VARCHAR2(50) not null,
  url        VARCHAR2(100) not null,
  action     VARCHAR2(30) not null,
  is_usable  NUMBER(1) not null,
  sort_order NUMBER(3),
  memo       VARCHAR2(500),
  icon       VARCHAR2(128)
);

prompt Creating TEST_CESHI...
create table TEST_CESHI
(
  id    VARCHAR2(32) not null,
  name  VARCHAR2(32),
  age   VARCHAR2(32),
  time  DATE,
  state VARCHAR2(32)
);
comment on column TEST_CESHI.id
  is '主键';
comment on column TEST_CESHI.name
  is '名称';
comment on column TEST_CESHI.age
  is '性别';
comment on column TEST_CESHI.time
  is '日期';
comment on column TEST_CESHI.state
  is '状态';
alter table TEST_CESHI
  add constraint PK_TEST_CESHI primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating TEST_CONLLECTION...
create table TEST_CONLLECTION
(
  id          VARCHAR2(32),
  conllection VARCHAR2(32),
  vec_id      VARCHAR2(32)
);

prompt Creating TEST_DEPT...
create table TEST_DEPT
(
  dept  NUMBER,
  id    VARCHAR2(32) default sys_guid() not null,
  state VARCHAR2(32),
  name  VARCHAR2(32)
);
alter table TEST_DEPT
  add constraint PK_TEST_DEPT primary key (ID)
  using index ;

prompt Creating TEST_ITERABLE...
create table TEST_ITERABLE
(
  id       VARCHAR2(32),
  iterable VARCHAR2(32),
  con_id   VARCHAR2(32),
  dates    DATE
);

prompt Creating TEST_MONTH...
create table TEST_MONTH
(
  month VARCHAR2(20),
  dept  INTEGER,
  yj    INTEGER
);

prompt Creating TEST_NUMBER...
create table TEST_NUMBER
(
  name    VARCHAR2(10),
  kecheng VARCHAR2(10),
  fenshu  NUMBER
);

prompt Creating TEST_VECTOR...
create table TEST_VECTOR
(
  id     VARCHAR2(32),
  vector VARCHAR2(32),
  con_id VARCHAR2(32)
);

prompt Creating T_BASE_FIELD...
create table T_BASE_FIELD
(
  id            VARCHAR2(32) not null,
  tablename     VARCHAR2(50),
  field         VARCHAR2(50),
  tname         VARCHAR2(300),
  width         VARCHAR2(10),
  dateformat    VARCHAR2(20),
  datatype      VARCHAR2(20),
  ishidden      VARCHAR2(1),
  sort          VARCHAR2(10),
  create_date   DATE,
  create_userid VARCHAR2(32),
  remark        VARCHAR2(500),
  state         VARCHAR2(1),
  islist        VARCHAR2(1),
  isblank       VARCHAR2(10),
  pk_tablename  VARCHAR2(200),
  outputpath    VARCHAR2(200),
  packagename   VARCHAR2(50),
  beanname      VARCHAR2(20),
  modify_date   DATE,
  modify_userid VARCHAR2(32)
);
comment on table T_BASE_FIELD
  is '页面标签生成，导出获取标题。。基础表';
comment on column T_BASE_FIELD.id
  is '主键id';
comment on column T_BASE_FIELD.tablename
  is '表名';
comment on column T_BASE_FIELD.field
  is '字段名';
comment on column T_BASE_FIELD.tname
  is '标题名称';
comment on column T_BASE_FIELD.width
  is '生成标签设置宽度（可10px或%20）';
comment on column T_BASE_FIELD.dateformat
  is '日期格式';
comment on column T_BASE_FIELD.datatype
  is '数据类型 (optiondata=下拉data类型  optionurl=下拉url类型  text=文本类型  date=时间类型  int=数字 )';
comment on column T_BASE_FIELD.ishidden
  is '是否隐藏 0=隐藏';
comment on column T_BASE_FIELD.sort
  is '排序';
comment on column T_BASE_FIELD.create_date
  is '创建时间';
comment on column T_BASE_FIELD.create_userid
  is '创建人id';
comment on column T_BASE_FIELD.remark
  is '备注';
comment on column T_BASE_FIELD.state
  is '是否有效：1：有效 0：删除';
comment on column T_BASE_FIELD.islist
  is '是否列表展示 1=表头 0字段';
comment on column T_BASE_FIELD.isblank
  is 'no:必填 yes:可空';
comment on column T_BASE_FIELD.pk_tablename
  is '外键表名称';
comment on column T_BASE_FIELD.outputpath
  is '输出路径';
comment on column T_BASE_FIELD.packagename
  is '二级路径——包所在追加路径';
comment on column T_BASE_FIELD.beanname
  is '实体名称';
comment on column T_BASE_FIELD.modify_date
  is '修改时间';
comment on column T_BASE_FIELD.modify_userid
  is '修改人';
alter table T_BASE_FIELD
  add constraint PK_T_BASE_FIELD primary key (ID)
  using index ;

prompt Creating T_BASE_LOADREDIS...
create table T_BASE_LOADREDIS
(
  id            VARCHAR2(32) not null,
  table_name    VARCHAR2(50) not null,
  data_source   VARCHAR2(30),
  database_name VARCHAR2(50),
  remark        VARCHAR2(200),
  create_date   DATE,
  create_name   VARCHAR2(50),
  state         VARCHAR2(1) default '1',
  joint_symbol  VARCHAR2(20)
);
comment on table T_BASE_LOADREDIS
  is '缓存加载表';
comment on column T_BASE_LOADREDIS.id
  is '主键id';
comment on column T_BASE_LOADREDIS.table_name
  is '表名称';
comment on column T_BASE_LOADREDIS.data_source
  is '数据源名称（用户名称）';
comment on column T_BASE_LOADREDIS.database_name
  is '数据库名称';
comment on column T_BASE_LOADREDIS.remark
  is '备注';
comment on column T_BASE_LOADREDIS.create_date
  is '创建时间';
comment on column T_BASE_LOADREDIS.create_name
  is '创建人';
comment on column T_BASE_LOADREDIS.state
  is '是否有效：1：有效 0：无效';
comment on column T_BASE_LOADREDIS.joint_symbol
  is '连接符号';
alter table T_BASE_LOADREDIS
  add constraint PK_T_BASE_LOADREDIS primary key (ID)
  using index ;

prompt Creating T_CODE_INFO...
create table T_CODE_INFO
(
  id               VARCHAR2(32) not null,
  pid              VARCHAR2(32),
  name_            VARCHAR2(200),
  isfolder         VARCHAR2(10),
  type             VARCHAR2(10),
  sort             INTEGER,
  url_             VARCHAR2(500),
  sys_flag         VARCHAR2(5),
  create_date      DATE default sysdate,
  create_user      VARCHAR2(100),
  create_user_name VARCHAR2(100),
  modify_date      DATE,
  modify_user      VARCHAR2(100),
  modify_user_name VARCHAR2(100),
  modify_type      VARCHAR2(20),
  state            VARCHAR2(5),
  remark           VARCHAR2(1000)
);
comment on column T_CODE_INFO.id
  is '主键id';
comment on column T_CODE_INFO.pid
  is '父节点id';
comment on column T_CODE_INFO.name_
  is '代码名称';
comment on column T_CODE_INFO.isfolder
  is '是否目录(1：是 0：不是)';
comment on column T_CODE_INFO.type
  is '菜单、代码';
comment on column T_CODE_INFO.sort
  is '排序';
comment on column T_CODE_INFO.url_
  is '菜单URL';
comment on column T_CODE_INFO.sys_flag
  is '系统标记(1-系统属性)，标记为1为系统属性,不能删除';
comment on column T_CODE_INFO.create_date
  is '创建日期';
comment on column T_CODE_INFO.create_user
  is '创建人';
comment on column T_CODE_INFO.create_user_name
  is '创建人姓名';
comment on column T_CODE_INFO.modify_date
  is '修改日期';
comment on column T_CODE_INFO.modify_user
  is '修改人';
comment on column T_CODE_INFO.modify_user_name
  is '修改人姓名';
comment on column T_CODE_INFO.modify_type
  is '修改类型(create/update/delete)';
comment on column T_CODE_INFO.state
  is '状态(1-可用,0-不可用)';
comment on column T_CODE_INFO.remark
  is '备注';
alter table T_CODE_INFO
  add constraint PK_T_CODE_INFO primary key (ID)
  using index ;

prompt Creating T_PUB_GENDERSERIALNOAUTO...
create table T_PUB_GENDERSERIALNOAUTO
(
  id        VARCHAR2(20) not null,
  todaytime DATE,
  type      VARCHAR2(20),
  subnumber NUMBER,
  remark    VARCHAR2(500),
  newnumber NUMBER
);
comment on column T_PUB_GENDERSERIALNOAUTO.id
  is '主键id';
comment on column T_PUB_GENDERSERIALNOAUTO.todaytime
  is '时间';
comment on column T_PUB_GENDERSERIALNOAUTO.type
  is '类型标识';
comment on column T_PUB_GENDERSERIALNOAUTO.subnumber
  is '20位截取多少位（后至前）';
comment on column T_PUB_GENDERSERIALNOAUTO.remark
  is '备注';
comment on column T_PUB_GENDERSERIALNOAUTO.newnumber
  is '序号，用于升序，查询后立马加1';
alter table T_PUB_GENDERSERIALNOAUTO
  add constraint PK_GENDERSERIALNOAUTO_ID primary key (ID)
  using index ;

prompt Creating T_S_DEPT...
create table T_S_DEPT
(
  id              VARCHAR2(32) not null,
  pid             VARCHAR2(32),
  dept_name       VARCHAR2(100),
  remark          VARCHAR2(200),
  create_date     DATE,
  create_userid   VARCHAR2(32),
  create_username VARCHAR2(20),
  modify_date     DATE,
  modify_userid   VARCHAR2(32),
  state           VARCHAR2(1) default '1'
);
comment on column T_S_DEPT.id
  is '主键id';
comment on column T_S_DEPT.pid
  is '上级部门id';
comment on column T_S_DEPT.dept_name
  is '部门名称';
comment on column T_S_DEPT.remark
  is '备注';
comment on column T_S_DEPT.create_date
  is '创建时间';
comment on column T_S_DEPT.create_userid
  is '创建人id';
comment on column T_S_DEPT.create_username
  is '创建人名称';
comment on column T_S_DEPT.modify_date
  is '修改时间';
comment on column T_S_DEPT.modify_userid
  is '修改人id';
comment on column T_S_DEPT.state
  is '状态0：删除、1：正常';
alter table T_S_DEPT
  add constraint PK_T_S_DEPT primary key (ID)
  using index ;

prompt Creating T_S_MENU...
create table T_S_MENU
(
  id               VARCHAR2(50) not null,
  module           VARCHAR2(20),
  pid              VARCHAR2(50),
  name_            VARCHAR2(300) default '1',
  url_             VARCHAR2(500),
  type             VARCHAR2(10),
  sys_flag         VARCHAR2(1),
  icon             VARCHAR2(200),
  sort             NUMBER(3),
  remark           VARCHAR2(500),
  state            VARCHAR2(1),
  create_date      DATE,
  create_user      VARCHAR2(50),
  create_user_name VARCHAR2(100),
  modify_date      DATE,
  modify_user      VARCHAR2(50),
  modify_user_name VARCHAR2(100),
  modify_type      VARCHAR2(50)
);
comment on table T_S_MENU
  is '菜单功能表';
comment on column T_S_MENU.id
  is '主键id';
comment on column T_S_MENU.module
  is '模块';
comment on column T_S_MENU.pid
  is '上级菜单ID';
comment on column T_S_MENU.name_
  is '显示名称';
comment on column T_S_MENU.url_
  is 'URL的地址';
comment on column T_S_MENU.type
  is '0=功能、1=菜单';
comment on column T_S_MENU.sys_flag
  is '系统标记(0、1)，0= 系统属性(隐藏,页面不展示)    1=系统属性(,不能删除)  ';
comment on column T_S_MENU.icon
  is '显示图片所在路径';
comment on column T_S_MENU.sort
  is '排序';
comment on column T_S_MENU.remark
  is '备注';
comment on column T_S_MENU.state
  is '状态(1-可用,0-不可用)';
comment on column T_S_MENU.create_date
  is '创建日期';
comment on column T_S_MENU.create_user
  is '创建人';
comment on column T_S_MENU.create_user_name
  is '创建人名称';
comment on column T_S_MENU.modify_date
  is '修改日期';
comment on column T_S_MENU.modify_user
  is '修改人';
comment on column T_S_MENU.modify_user_name
  is '修改人姓名';
comment on column T_S_MENU.modify_type
  is '修改类型(create/update/delete)';
alter table T_S_MENU
  add constraint PK_T_S_MENU primary key (ID)
  using index ;

prompt Creating T_S_PERMISS...
create table T_S_PERMISS
(
  id              VARCHAR2(32) not null,
  pid             VARCHAR2(32),
  perm_name       VARCHAR2(100) not null,
  remark          VARCHAR2(200),
  create_date     DATE not null,
  create_userid   VARCHAR2(32),
  create_username VARCHAR2(20),
  modify_date     DATE,
  modify_userid   VARCHAR2(32),
  state           VARCHAR2(1)
);
comment on column T_S_PERMISS.id
  is '主键id';
comment on column T_S_PERMISS.pid
  is '上级权限id';
comment on column T_S_PERMISS.perm_name
  is '权限名称';
comment on column T_S_PERMISS.remark
  is '备注';
comment on column T_S_PERMISS.create_date
  is '创建时间';
comment on column T_S_PERMISS.create_userid
  is '创建人id';
comment on column T_S_PERMISS.create_username
  is '创建人名称';
comment on column T_S_PERMISS.modify_date
  is '修改时间';
comment on column T_S_PERMISS.modify_userid
  is '修改人id';
comment on column T_S_PERMISS.state
  is '状态0：删除、1：正常';
alter table T_S_PERMISS
  add constraint PK_T_S_PERMISS primary key (ID)
  using index ;

prompt Creating T_S_ROLE...
create table T_S_ROLE
(
  id            VARCHAR2(32) not null,
  role_name     VARCHAR2(50) not null,
  description   VARCHAR2(200),
  create_date   DATE not null,
  create_userid VARCHAR2(32),
  create_user   VARCHAR2(20),
  modify_date   DATE,
  modify_userid VARCHAR2(32),
  state         VARCHAR2(1)
);
comment on table T_S_ROLE
  is '存放系统对用户角色的定义';
comment on column T_S_ROLE.id
  is '主键id';
comment on column T_S_ROLE.role_name
  is '角色名';
comment on column T_S_ROLE.description
  is '角色描述';
comment on column T_S_ROLE.create_date
  is '创建时间';
comment on column T_S_ROLE.create_userid
  is '创建人id';
comment on column T_S_ROLE.create_user
  is '创建人名称';
comment on column T_S_ROLE.modify_date
  is '修改时间';
comment on column T_S_ROLE.modify_userid
  is '修改人id';
comment on column T_S_ROLE.state
  is '状态0：删除、1：正常';
alter table T_S_ROLE
  add constraint PK_T_S_ROLE primary key (ID)
  using index ;

prompt Creating T_S_ROLE_PERMREG...
create table T_S_ROLE_PERMREG
(
  id         VARCHAR2(32) not null,
  role_id    VARCHAR2(32) not null,
  perm_id    VARCHAR2(32) not null,
  perm_allow VARCHAR2(1) default '1'
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
comment on table T_S_ROLE_PERMREG
  is '存放对角色的子权限的分配';
comment on column T_S_ROLE_PERMREG.id
  is '主键id';
comment on column T_S_ROLE_PERMREG.role_id
  is '角色ID';
comment on column T_S_ROLE_PERMREG.perm_id
  is '权限id';
comment on column T_S_ROLE_PERMREG.perm_allow
  is '是否有效：1：有效 0：无效';
alter table T_S_ROLE_PERMREG
  add constraint PK_T_S_ROLE_PERMREG primary key (ID)
  using index ;

prompt Creating T_S_USER...
create table T_S_USER
(
  id                   VARCHAR2(32) not null,
  user_id              VARCHAR2(30) not null,
  password             VARCHAR2(100) not null,
  dept_id              VARCHAR2(32),
  role_id              VARCHAR2(32),
  real_name            VARCHAR2(20),
  age                  VARCHAR2(1),
  phone                VARCHAR2(30),
  msisdn               VARCHAR2(50),
  addresses            VARCHAR2(200),
  email                VARCHAR2(50),
  city                 VARCHAR2(50),
  province             VARCHAR2(100),
  country              VARCHAR2(100),
  password_hint        VARCHAR2(200),
  account_enabled      VARCHAR2(1) default '1',
  account_expired      VARCHAR2(1) default '1',
  account_locked       VARCHAR2(1) default '1',
  password_error_times NUMBER(8),
  last_login_time      DATE,
  password_error_lock  VARCHAR2(1) default '1',
  rtx_num              VARCHAR2(50),
  remark               VARCHAR2(500),
  state                VARCHAR2(1) default '1',
  create_date          DATE default SYSDATE,
  create_userid        VARCHAR2(32),
  create_username      VARCHAR2(20),
  modify_date          DATE,
  modify_userid        VARCHAR2(32)
);
comment on table T_S_USER
  is '用户表';
comment on column T_S_USER.id
  is '主键id';
comment on column T_S_USER.user_id
  is '账号';
comment on column T_S_USER.password
  is '密码';
comment on column T_S_USER.dept_id
  is '部门id';
comment on column T_S_USER.role_id
  is '角色id';
comment on column T_S_USER.real_name
  is '姓名';
comment on column T_S_USER.age
  is '性别：1=男、0=女';
comment on column T_S_USER.phone
  is '联系电话';
comment on column T_S_USER.msisdn
  is '移动电话';
comment on column T_S_USER.addresses
  is '住址';
comment on column T_S_USER.email
  is '电子邮箱 ';
comment on column T_S_USER.city
  is '市';
comment on column T_S_USER.province
  is '省';
comment on column T_S_USER.country
  is '国家';
comment on column T_S_USER.password_hint
  is '密码提示';
comment on column T_S_USER.account_enabled
  is '账号是否可用 0-是 1-否  ';
comment on column T_S_USER.account_expired
  is '账号是否过期 0-是 1-否';
comment on column T_S_USER.account_locked
  is '账号是否锁定 0-是 1-否';
comment on column T_S_USER.password_error_times
  is '记录密码输错次数';
comment on column T_S_USER.last_login_time
  is '记录上一次该账号进行登录的时间';
comment on column T_S_USER.password_error_lock
  is '密码输入错误锁，1为正常，0为锁定';
comment on column T_S_USER.rtx_num
  is 'RTX号码';
comment on column T_S_USER.remark
  is '备注';
comment on column T_S_USER.state
  is '状态0：删除、1：正常';
comment on column T_S_USER.create_date
  is '创建时间';
comment on column T_S_USER.create_userid
  is '创建人id';
comment on column T_S_USER.create_username
  is '创建人名称';
comment on column T_S_USER.modify_date
  is '修改时间';
comment on column T_S_USER.modify_userid
  is '修改人id';
create index INDEX_T_S_USER_ID on T_S_USER (USER_ID, PASSWORD);
alter table T_S_USER
  add constraint PK_T_S_USER unique (ID)
  using index ;

prompt Disabling triggers for SR_FUNC_LIST...
alter table SR_FUNC_LIST disable all triggers;
prompt Disabling triggers for TEST_CESHI...
alter table TEST_CESHI disable all triggers;
prompt Disabling triggers for TEST_CONLLECTION...
alter table TEST_CONLLECTION disable all triggers;
prompt Disabling triggers for TEST_DEPT...
alter table TEST_DEPT disable all triggers;
prompt Disabling triggers for TEST_ITERABLE...
alter table TEST_ITERABLE disable all triggers;
prompt Disabling triggers for TEST_MONTH...
alter table TEST_MONTH disable all triggers;
prompt Disabling triggers for TEST_NUMBER...
alter table TEST_NUMBER disable all triggers;
prompt Disabling triggers for TEST_VECTOR...
alter table TEST_VECTOR disable all triggers;
prompt Disabling triggers for T_BASE_FIELD...
alter table T_BASE_FIELD disable all triggers;
prompt Disabling triggers for T_BASE_LOADREDIS...
alter table T_BASE_LOADREDIS disable all triggers;
prompt Disabling triggers for T_CODE_INFO...
alter table T_CODE_INFO disable all triggers;
prompt Disabling triggers for T_PUB_GENDERSERIALNOAUTO...
alter table T_PUB_GENDERSERIALNOAUTO disable all triggers;
prompt Disabling triggers for T_S_DEPT...
alter table T_S_DEPT disable all triggers;
prompt Disabling triggers for T_S_MENU...
alter table T_S_MENU disable all triggers;
prompt Disabling triggers for T_S_PERMISS...
alter table T_S_PERMISS disable all triggers;
prompt Disabling triggers for T_S_ROLE...
alter table T_S_ROLE disable all triggers;
prompt Disabling triggers for T_S_ROLE_PERMREG...
alter table T_S_ROLE_PERMREG disable all triggers;
prompt Disabling triggers for T_S_USER...
alter table T_S_USER disable all triggers;
prompt Deleting T_S_USER...
delete from T_S_USER;
commit;
prompt Deleting T_S_ROLE_PERMREG...
delete from T_S_ROLE_PERMREG;
commit;
prompt Deleting T_S_ROLE...
delete from T_S_ROLE;
commit;
prompt Deleting T_S_PERMISS...
delete from T_S_PERMISS;
commit;
prompt Deleting T_S_MENU...
delete from T_S_MENU;
commit;
prompt Deleting T_S_DEPT...
delete from T_S_DEPT;
commit;
prompt Deleting T_PUB_GENDERSERIALNOAUTO...
delete from T_PUB_GENDERSERIALNOAUTO;
commit;
prompt Deleting T_CODE_INFO...
delete from T_CODE_INFO;
commit;
prompt Deleting T_BASE_LOADREDIS...
delete from T_BASE_LOADREDIS;
commit;
prompt Deleting T_BASE_FIELD...
delete from T_BASE_FIELD;
commit;
prompt Deleting TEST_VECTOR...
delete from TEST_VECTOR;
commit;
prompt Deleting TEST_NUMBER...
delete from TEST_NUMBER;
commit;
prompt Deleting TEST_MONTH...
delete from TEST_MONTH;
commit;
prompt Deleting TEST_ITERABLE...
delete from TEST_ITERABLE;
commit;
prompt Deleting TEST_DEPT...
delete from TEST_DEPT;
commit;
prompt Deleting TEST_CONLLECTION...
delete from TEST_CONLLECTION;
commit;
prompt Deleting TEST_CESHI...
delete from TEST_CESHI;
commit;
prompt Deleting SR_FUNC_LIST...
delete from SR_FUNC_LIST;
commit;
prompt Loading SR_FUNC_LIST...
prompt Table is empty
prompt Loading TEST_CESHI...
insert into TEST_CESHI (id, name, age, time, state)
values ('20171220221249V9623', '我草', '0', to_date('19-12-2017 22:12:44', 'dd-mm-yyyy hh24:mi:ss'), '1');
insert into TEST_CESHI (id, name, age, time, state)
values ('20171220205420V8991', '修改你麻痹', '0', null, '0');
commit;
prompt 2 records loaded
prompt Loading TEST_CONLLECTION...
insert into TEST_CONLLECTION (id, conllection, vec_id)
values ('con_1', 'con_111', 'vec_1');
insert into TEST_CONLLECTION (id, conllection, vec_id)
values ('con_2', 'con_111', 'vec_2');
insert into TEST_CONLLECTION (id, conllection, vec_id)
values ('con_3', 'con_111', 'vec_3');
commit;
prompt 3 records loaded
prompt Loading TEST_DEPT...
insert into TEST_DEPT (dept, id, state, name)
values (1, '3ECA631A0D314117903E68D7C2D6D1AA', '0', null);
insert into TEST_DEPT (dept, id, state, name)
values (2, '3916050FC60D416DBC68188686444B8D', '0', null);
insert into TEST_DEPT (dept, id, state, name)
values (3, '4757E1964B144E08B2A57145B21B5C0B', '0', null);
insert into TEST_DEPT (dept, id, state, name)
values (4, 'F2AF973AA4A74A4CA77F437829D3012D', '0', '我的天');
insert into TEST_DEPT (dept, id, state, name)
values (1, '20171220201149V8742', '1', '1');
insert into TEST_DEPT (dept, id, state, name)
values (1, '20171219232502V2746', '1', '娘的');
insert into TEST_DEPT (dept, id, state, name)
values (null, '20171219230950V9858', '0', null);
insert into TEST_DEPT (dept, id, state, name)
values (9, '20171219231018V2118', '0', null);
commit;
prompt 8 records loaded
prompt Loading TEST_ITERABLE...
insert into TEST_ITERABLE (id, iterable, con_id, dates)
values ('1', '����', 'con_1', to_date('01-09-2017', 'dd-mm-yyyy'));
insert into TEST_ITERABLE (id, iterable, con_id, dates)
values ('2', '����', 'con_2', to_date('01-08-2017', 'dd-mm-yyyy'));
insert into TEST_ITERABLE (id, iterable, con_id, dates)
values ('3', '����', '2', to_date('01-09-2017', 'dd-mm-yyyy'));
commit;
prompt 3 records loaded
prompt Loading TEST_MONTH...
insert into TEST_MONTH (month, dept, yj)
values ('��', 1, 10);
insert into TEST_MONTH (month, dept, yj)
values ('��', 2, 10);
insert into TEST_MONTH (month, dept, yj)
values ('��', 3, 5);
insert into TEST_MONTH (month, dept, yj)
values ('��', 2, 8);
insert into TEST_MONTH (month, dept, yj)
values ('��', 4, 9);
insert into TEST_MONTH (month, dept, yj)
values ('��', 3, 8);
commit;
prompt 6 records loaded
prompt Loading TEST_NUMBER...
prompt Table is empty
prompt Loading TEST_VECTOR...
prompt Table is empty
prompt Loading T_BASE_FIELD...
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221145V8681', 't_s_role', null, null, null, null, null, null, null, to_date('19-12-2017 22:11:45', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', null, null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221145V10705', 't_s_role', 'ID', '主键id', '50px', null, 'text', '1', '1', to_date('19-12-2017 22:11:45', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221145V6510', 't_s_role', 'ROLE_NAME', '角色', '50px', null, 'text', '1', '2', to_date('19-12-2017 22:11:45', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221145V4873', 't_s_role', 'REMARK', '备注', '50px', null, 'text', '1', '3', to_date('19-12-2017 22:11:45', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221145V7546', 't_s_role', 'CREATE_DATE', '创建时间', '50px', null, 'text', '1', '4', to_date('19-12-2017 22:11:45', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221145V6816', 't_s_role', 'CREATE_USERID', '创建人id', '50px', null, 'text', '1', '5', to_date('19-12-2017 22:11:45', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221145V2994', 't_s_role', 'CREATE_USER', '创建人名称', '50px', null, 'text', '1', '6', to_date('19-12-2017 22:11:45', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221145V7468', 't_s_role', 'UPDATE_DATE', '修改时间', '50px', null, 'text', '1', '7', to_date('19-12-2017 22:11:45', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221145V9585', 't_s_role', 'UPDATE_USERID', '修改人id', '50px', null, 'text', '1', '8', to_date('19-12-2017 22:11:45', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221145V6928', 't_s_role', 'STATE', '状态0：删除、1：正常', '50px', null, 'text', '1', '9', to_date('19-12-2017 22:11:45', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505366V14687', 't_s_menu', 'menu', '菜单管理', null, null, null, null, null, to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '1', 'no', null, null, 'author', null, to_date('08-07-2018 02:56:32', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505411V18677', 't_s_menu', 'MODULE', '模块', '50px', null, 'text', '1', '2', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505412V10966', 't_s_menu', 'PID', '上级菜单ID', '50px', null, 'text', '1', '3', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505412V11190', 't_s_menu', 'NAME_', '显示名称', '50px', null, 'optiondata', '1', '4', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505412V18587', 't_s_menu', 'URL_', 'URL的地址', '50px', null, 'text', '1', '5', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505412V16151', 't_s_menu', 'TYPE', '0=功能、1=菜单', '50px', null, 'optiondata', '1', '6', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505412V17271', 't_s_menu', 'SYS_FLAG', '系统标记(0、1)，0= 系统属性(隐藏,页面不展示)    1=系统属性(,不能删除)  ', '50px', null, 'text', '1', '7', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505413V12393', 't_s_menu', 'ICON', '显示图片所在路径', '50px', null, 'text', '1', '8', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505413V17069', 't_s_menu', 'SORT', '排序', '50px', null, 'text', '1', '9', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505413V16467', 't_s_menu', 'REMARK', '备注', '50px', null, 'text', '1', '10', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505413V19170', 't_s_menu', 'STATE', '状态(1-可用,0-不可用)', '50px', null, 'text', '1', '11', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505413V16857', 't_s_menu', 'CREATE_DATE', '创建日期', '50px', null, 'text', '1', '12', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505413V17969', 't_s_menu', 'CREATE_USER', '创建人', '50px', null, 'text', '1', '13', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505414V16625', 't_s_menu', 'CREATE_USER_NAME', '创建人名称', '50px', null, 'text', '1', '14', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505414V19413', 't_s_menu', 'MODIFY_DATE', '修改日期', '50px', null, 'text', '1', '15', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505414V17547', 't_s_menu', 'MODIFY_USER', '修改人', '50px', null, 'text', '1', '16', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505414V10740', 't_s_menu', 'MODIFY_USER_NAME', '修改人姓名', '50px', null, 'text', '1', '17', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707204505414V15023', 't_s_menu', 'MODIFY_TYPE', '修改类型(create/update/delete)', '50px', null, 'text', '1', '18', to_date('07-07-2018 20:45:05', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221114V10873', 't_s_role', null, null, null, null, null, null, null, to_date('19-12-2017 22:11:14', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', null, null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221114V7498', 't_s_role', 'ID', '主键id', '50px', null, 'text', '1', '1', to_date('19-12-2017 22:11:14', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221114V6469', 't_s_role', 'ROLE_NAME', '角色', '50px', null, 'text', '1', '2', to_date('19-12-2017 22:11:14', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221114V10028', 't_s_role', 'REMARK', '备注', '50px', null, 'text', '1', '3', to_date('19-12-2017 22:11:14', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221114V8307', 't_s_role', 'CREATE_DATE', '创建时间', '50px', null, 'text', '1', '4', to_date('19-12-2017 22:11:14', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221114V1111', 't_s_role', 'CREATE_USERID', '创建人id', '50px', null, 'text', '1', '5', to_date('19-12-2017 22:11:14', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221114V7615', 't_s_role', 'CREATE_USER', '创建人名称', '50px', null, 'text', '1', '6', to_date('19-12-2017 22:11:14', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221114V5351', 't_s_role', 'UPDATE_DATE', '修改时间', '50px', null, 'text', '1', '7', to_date('19-12-2017 22:11:14', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221114V4249', 't_s_role', 'UPDATE_USERID', '修改人id', '50px', null, 'text', '1', '8', to_date('19-12-2017 22:11:14', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221114V2676', 't_s_role', 'STATE', '状态0：删除、1：正常', '50px', null, 'text', '1', '9', to_date('19-12-2017 22:11:14', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219230046V5168', 'test_dept', 'ceshiTest', '测试管理', null, null, null, null, null, to_date('19-12-2017 23:00:46', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', 'no', null, null, 'ceshi', null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220201043V6718', 'test_dept', 'ceshi', '测试', null, null, null, null, null, to_date('20-12-2017 20:10:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', 'no', null, null, 'ceshi', null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220201043V10902', 'test_dept', 'DEPT', '1', '50px', null, 'text', '1', '1', to_date('20-12-2017 20:10:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220201043V9611', 'test_dept', 'NAME', '2', '50px', null, 'text', '1', '4', to_date('20-12-2017 20:10:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220204010V4751', 'test_ceshi', 'ceshiTest', '测试生成', null, null, null, null, null, to_date('20-12-2017 20:40:10', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', 'no', null, null, 'ceshiTest', null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220204010V2211', 'test_ceshi', 'ID', '主键', '50px', null, 'text', '1', '1', to_date('20-12-2017 20:40:10', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220204010V1811', 'test_ceshi', 'NAME', '名称', '50px', null, 'text', '1', '2', to_date('20-12-2017 20:40:10', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'no', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220204010V8535', 'test_ceshi', 'AGE', '性别', '50px', null, 'optiondata', '1', '3', to_date('20-12-2017 20:40:10', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220204010V1808', 'test_ceshi', 'TIME', '日期', '50px', 'yyyy-MM-dd HH:mm:ss', 'date', '1', '4', to_date('20-12-2017 20:40:10', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219230046V4923', 'test_dept', 'DEPT', '1', '50px', null, 'text', '1', '1', to_date('19-12-2017 23:00:46', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219230046V8055', 'test_dept', 'ID', '2', '50px', null, 'text', '1', '2', to_date('19-12-2017 23:00:46', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220204943V2405', 'test_ceshi', 'ceshiTestGente', '测试生成', null, null, null, null, null, to_date('20-12-2017 20:49:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '1', 'no', null, null, 'ceshixinzeng', null, to_date('20-12-2017 21:52:53', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220204943V2634', 'test_ceshi', 'NAME', '名称', '50px', null, 'text', '1', '1', to_date('20-12-2017 20:49:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220204943V5921', 'test_ceshi', 'AGE', '性别', '50px', null, 'optiondata', '1', '2', to_date('20-12-2017 20:49:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220204943V10676', 'test_ceshi', 'TIME', '日期', '50px', 'yyyy-MM-dd HH:mm:ss', 'date', '1', '3', to_date('20-12-2017 20:49:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'no', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220213231V2261', 't_s_role', 'role', '角色', null, null, null, null, null, to_date('20-12-2017 21:32:31', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', 'no', null, null, 'rolePa', null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220213231V6736', 't_s_role', 'ID', '主键id', '50px', null, 'text', '1', '1', to_date('20-12-2017 21:32:31', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220213231V1877', 't_s_role', 'ROLE_NAME', '角色', '50px', null, 'text', '1', '2', to_date('20-12-2017 21:32:31', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220213231V2326', 't_s_role', 'REMARK', '备注', '50px', null, 'text', '1', '3', to_date('20-12-2017 21:32:31', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220213231V5448', 't_s_role', 'CREATE_DATE', '创建时间', '50px', null, 'text', '1', '4', to_date('20-12-2017 21:32:31', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220213231V1074', 't_s_role', 'CREATE_USERID', '创建人id', '50px', null, 'text', '1', '5', to_date('20-12-2017 21:32:31', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220213231V6548', 't_s_role', 'CREATE_USER', '创建人名称', '50px', null, 'text', '1', '6', to_date('20-12-2017 21:32:31', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220213231V1945', 't_s_role', 'UPDATE_DATE', '修改时间', '50px', null, 'text', '1', '7', to_date('20-12-2017 21:32:31', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220213231V6440', 't_s_role', 'UPDATE_USERID', '修改人id', '50px', null, 'text', '1', '8', to_date('20-12-2017 21:32:31', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220213231V2601', 't_s_role', 'STATE', '状态0：删除、1：正常', '50px', null, 'text', '1', '9', to_date('20-12-2017 21:32:31', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180228202541V1394', 't_s_role', 'role', '角色管理', null, null, null, null, null, to_date('28-02-2018 20:25:41', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '1', 'no', null, null, 'author', null, to_date('28-02-2018 20:27:40', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180228202541V6774', 't_s_role', 'ID', '主键id', '50px', null, 'text', '0', '1', to_date('28-02-2018 20:25:41', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, to_date('28-02-2018 20:27:40', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180228202541V4025', 't_s_role', 'ROLE_NAME', '角色名', '50px', null, 'text', '1', '2', to_date('28-02-2018 20:25:41', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'no', null, null, null, null, to_date('28-02-2018 20:27:40', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180228202541V9598', 't_s_role', 'DESCRIPTION', '角色描述', '50px', null, 'text', '1', '3', to_date('28-02-2018 20:25:41', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180228202541V8158', 't_s_role', 'CREATE_DATE', '创建时间', '50px', 'yyyy-MM-dd HH:mm:ss', 'date', '1', '4', to_date('28-02-2018 20:25:41', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'no', null, null, null, null, to_date('28-02-2018 20:27:40', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180228202541V10780', 't_s_role', 'CREATE_USERID', '创建人id', '50px', null, 'text', '1', '5', to_date('28-02-2018 20:25:41', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180228202541V7689', 't_s_role', 'CREATE_USER', '创建人名称', '50px', null, 'text', '1', '6', to_date('28-02-2018 20:25:41', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180228202541V3933', 't_s_role', 'MODIFY_DATE', '修改时间', '50px', 'yyyy-MM-dd HH:mm:ss', 'date', '1', '7', to_date('28-02-2018 20:25:41', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'no', null, null, null, null, to_date('28-02-2018 20:27:40', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180228202541V10532', 't_s_role', 'MODIFY_USERID', '修改人id', '50px', null, 'text', '1', '8', to_date('28-02-2018 20:25:41', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180228202541V10463', 't_s_role', 'STATE', '状态0：删除、1：正常', '50px', null, 'text', '0', '9', to_date('28-02-2018 20:25:41', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, to_date('28-02-2018 20:27:40', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220200319V7420', 't_s_role', 'roseTest', '测试角色', null, null, null, null, null, to_date('20-12-2017 20:03:19', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', 'no', null, null, 'roset', null, to_date('20-12-2017 20:06:30', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220195343V3182', 'test_dept', 'ceshiGen', '测试生成', null, null, null, null, null, to_date('20-12-2017 19:53:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', 'no', null, null, 'ceshi', null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220195343V9027', 'test_dept', 'DEPT', '呵呵', '50px', null, 'text', '1', '1', to_date('20-12-2017 19:53:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220195343V6434', 'test_dept', 'NAME', '姓名', '50px', null, 'text', '1', '4', to_date('20-12-2017 19:53:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220200319V1185', 't_s_role', 'ROLE_NAME', '角色', '50px', null, 'text', '1', '2', to_date('20-12-2017 20:03:19', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220200319V10532', 't_s_role', 'REMARK', '备注', '50px', null, 'text', '1', '3', to_date('20-12-2017 20:03:19', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220200319V2436', 't_s_role', 'CREATE_DATE', '创建时间', '50px', null, 'text', '1', '4', to_date('20-12-2017 20:03:19', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220200319V3577', 't_s_role', 'CREATE_USERID', '创建人id', '50px', null, 'text', '1', '5', to_date('20-12-2017 20:03:19', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220200319V8987', 't_s_role', 'CREATE_USER', '创建人名称', '50px', null, 'text', '1', '6', to_date('20-12-2017 20:03:19', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220200319V1628', 't_s_role', 'UPDATE_DATE', '修改时间', '50px', null, 'text', '1', '7', to_date('20-12-2017 20:03:19', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220200319V8383', 't_s_role', 'UPDATE_USERID', '修改人id', '50px', null, 'text', '1', '8', to_date('20-12-2017 20:03:19', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220214422V7082', 't_s_dept', '1', '1', null, null, null, null, null, to_date('20-12-2017 21:44:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '1', 'no', null, null, '1', null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220214422V3704', 't_s_dept', 'ID', '主键id', '50px', null, 'text', '1', '1', to_date('20-12-2017 21:44:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220214422V2276', 't_s_dept', 'PID', '上级部门id', '50px', null, 'text', '1', '2', to_date('20-12-2017 21:44:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220214422V2102', 't_s_dept', 'DEPT_NAME', '部门名称', '50px', null, 'text', '1', '3', to_date('20-12-2017 21:44:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220214422V6490', 't_s_dept', 'REMARK', '备注', '50px', null, 'text', '1', '4', to_date('20-12-2017 21:44:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220214422V3120', 't_s_dept', 'CREATE_DATE', '创建时间', '50px', null, 'text', '1', '5', to_date('20-12-2017 21:44:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220214422V2014', 't_s_dept', 'CREATE_USERID', '创建人id', '50px', null, 'text', '1', '6', to_date('20-12-2017 21:44:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220214422V4333', 't_s_dept', 'CREATE_USERNAME', '创建人名称', '50px', null, 'text', '1', '7', to_date('20-12-2017 21:44:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220214422V5148', 't_s_dept', 'MODIFY_DATE', '修改时间', '50px', null, 'text', '1', '8', to_date('20-12-2017 21:44:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220214422V2428', 't_s_dept', 'MODIFY_USERID', '修改人id', '50px', null, 'text', '1', '9', to_date('20-12-2017 21:44:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171220214422V1098', 't_s_dept', 'STATE', '状态0：删除、1：正常', '50px', null, 'text', '1', '10', to_date('20-12-2017 21:44:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '1', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221749V1613', 't_s_role', null, null, null, null, null, null, null, to_date('19-12-2017 22:17:49', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', null, null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221749V1027', 't_s_role', 'ID', '主键id', '50px', null, 'text', '1', '1', to_date('19-12-2017 22:17:49', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221749V10188', 't_s_role', 'ROLE_NAME', '角色', '50px', null, 'text', '1', '2', to_date('19-12-2017 22:17:49', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221749V8503', 't_s_role', 'REMARK', '备注', '50px', null, 'text', '1', '3', to_date('19-12-2017 22:17:49', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221749V8731', 't_s_role', 'CREATE_DATE', '创建时间', '50px', null, 'text', '1', '4', to_date('19-12-2017 22:17:49', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
commit;
prompt 100 records committed...
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221749V10496', 't_s_role', 'CREATE_USERID', '创建人id', '50px', null, 'text', '1', '5', to_date('19-12-2017 22:17:49', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221749V3830', 't_s_role', 'CREATE_USER', '创建人名称', '50px', null, 'text', '1', '6', to_date('19-12-2017 22:17:49', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221749V6523', 't_s_role', 'UPDATE_DATE', '修改时间', '50px', null, 'text', '1', '7', to_date('19-12-2017 22:17:49', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221749V7453', 't_s_role', 'UPDATE_USERID', '修改人id', '50px', null, 'text', '1', '8', to_date('19-12-2017 22:17:49', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221749V7315', 't_s_role', 'STATE', '状态0：删除、1：正常', '50px', null, 'text', '1', '9', to_date('19-12-2017 22:17:49', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221937V8094', 't_s_role', null, null, null, null, null, null, null, to_date('19-12-2017 22:19:37', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', null, null, null, null, null, to_date('19-12-2017 22:26:15', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221937V6653', 't_s_role', 'ID', '主键id', '50px', null, 'text', '1', '1', to_date('19-12-2017 22:19:37', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221937V5695', 't_s_role', 'ROLE_NAME', '角色', '50px', null, 'text', '1', '2', to_date('19-12-2017 22:19:37', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221937V10344', 't_s_role', 'REMARK', '备注', '50px', null, 'text', '1', '3', to_date('19-12-2017 22:19:37', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221937V1736', 't_s_role', 'CREATE_DATE', '创建时间', '50px', null, 'text', '1', '4', to_date('19-12-2017 22:19:37', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221937V8403', 't_s_role', 'CREATE_USERID', '创建人id', '50px', null, 'text', '1', '5', to_date('19-12-2017 22:19:37', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221937V9699', 't_s_role', 'CREATE_USER', '创建人名称', '50px', null, 'text', '1', '6', to_date('19-12-2017 22:19:37', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221937V5263', 't_s_role', 'UPDATE_DATE', '修改时间', '50px', null, 'text', '1', '7', to_date('19-12-2017 22:19:37', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221937V2229', 't_s_role', 'UPDATE_USERID', '修改人id', '50px', null, 'text', '1', '8', to_date('19-12-2017 22:19:37', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219221937V3778', 't_s_role', 'STATE', '状态0：删除、1：正常', '50px', null, 'text', '1', '9', to_date('19-12-2017 22:19:37', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219223018V9912', null, null, null, null, null, null, null, null, to_date('19-12-2017 22:30:18', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', null, null, null, null, null, to_date('19-12-2017 22:30:25', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219223141V8606', null, null, null, null, null, null, null, null, to_date('19-12-2017 22:31:41', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', null, null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219223242V2099', null, null, null, null, null, null, null, null, to_date('19-12-2017 22:32:42', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', null, null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219223638V9356', null, null, null, null, null, null, null, null, to_date('19-12-2017 22:36:38', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', null, null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219224803V1761', 't_s_role', 'role', '角色管理', null, null, null, null, null, to_date('19-12-2017 22:48:03', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', 'yes', null, null, 'author', null, to_date('19-12-2017 22:48:44', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219224803V10026', 't_s_role', 'ROLE_NAME', '角色', '50px', null, 'text', '1', '2', to_date('19-12-2017 22:48:03', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219224803V10629', 't_s_role', 'REMARK', '备注', '50px', null, 'text', '1', '3', to_date('19-12-2017 22:48:03', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219224803V4556', 't_s_role', 'CREATE_DATE', '创建时间', '50px', 'yyyy-MM-dd HH:mm:ss', 'date', '0', '4', to_date('19-12-2017 22:48:03', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, to_date('19-12-2017 22:48:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219224803V6158', 't_s_role', 'CREATE_USERID', '创建人id', '50px', null, 'text', '0', '5', to_date('19-12-2017 22:48:03', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, to_date('19-12-2017 22:48:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219224803V4307', 't_s_role', 'CREATE_USER', '创建人名称', '50px', null, 'text', '0', '6', to_date('19-12-2017 22:48:03', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, to_date('19-12-2017 22:48:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219224803V5471', 't_s_role', 'UPDATE_DATE', '修改时间', '50px', 'yyyy-MM-dd HH:mm:ss', 'date', '0', '7', to_date('19-12-2017 22:48:03', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, to_date('19-12-2017 22:48:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219224803V9115', 't_s_role', 'UPDATE_USERID', '修改人id', '50px', null, 'text', '0', '8', to_date('19-12-2017 22:48:03', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, to_date('19-12-2017 22:48:22', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219225214V4836', 'test_dept', 'ceshi', '测试', null, null, null, null, null, to_date('19-12-2017 22:52:14', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', 'no', null, null, 'ceshi', null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219225214V1556', 'test_dept', 'DEPT', '呵呵', '50px', null, 'text', '1', '1', to_date('19-12-2017 22:52:14', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219231545V7113', 'test_dept', 'ceshi', '测试', null, null, null, null, null, to_date('19-12-2017 23:15:45', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', 'no', null, null, 'ceshi', null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219231545V2867', 'test_dept', 'DEPT', '数字', '50px', null, 'text', '1', '1', to_date('19-12-2017 23:15:45', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20171219231545V3333', 'test_dept', 'NAME', '名字', '50px', null, 'text', '1', '4', to_date('19-12-2017 23:15:45', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143889V10850', 't_s_menu', 'menu', '菜单管理', null, null, null, null, null, to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '1', 'no', null, null, 'author', null, to_date('07-07-2018 03:28:37', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143895V19435', 't_s_menu', 'ID', '主键id', '50px', null, 'text', '1', '1', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143895V11408', 't_s_menu', 'MODULE', '模块', '50px', null, 'text', '1', '2', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143895V16967', 't_s_menu', 'PID', '上级菜单ID', '50px', null, 'text', '1', '3', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143895V15298', 't_s_menu', 'NAME_', '显示名称', '50px', null, 'optiondata', '1', '4', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, to_date('07-07-2018 03:28:37', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143895V14367', 't_s_menu', 'URL_', 'URL的地址', '50px', null, 'text', '1', '5', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143896V15753', 't_s_menu', 'TYPE', '0=功能、1=菜单', '50px', null, 'optiondata', '1', '6', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, to_date('07-07-2018 03:28:37', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143896V10610', 't_s_menu', 'SYS_FLAG', '系统标记(0、1)，0= 系统属性(隐藏,页面不展示)   ', '50px', null, 'text', '1', '7', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143896V14509', 't_s_menu', 'ICON', '显示图片所在路径', '50px', null, 'text', '1', '8', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143896V15430', 't_s_menu', 'SORT', '排序', '50px', null, 'text', '1', '9', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143896V11934', 't_s_menu', 'REMARK', '备注', '50px', null, 'text', '1', '10', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143896V11873', 't_s_menu', 'STATE', '状态(1-可用,0-不可用)', '50px', null, 'text', '1', '11', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143896V19565', 't_s_menu', 'CREATE_DATE', '创建日期', '50px', null, 'text', '1', '12', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143897V10245', 't_s_menu', 'CREATE_USER', '创建人', '50px', null, 'text', '1', '13', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143897V13109', 't_s_menu', 'CREATE_USER_NAME', '创建人名称', '50px', null, 'text', '1', '14', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143897V10219', 't_s_menu', 'MODIFY_DATE', '修改日期', '50px', null, 'text', '1', '15', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143897V19303', 't_s_menu', 'MODIFY_USER', '修改人', '50px', null, 'text', '1', '16', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143897V14913', 't_s_menu', 'MODIFY_USER_NAME', '修改人姓名', '50px', null, 'text', '1', '17', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
insert into T_BASE_FIELD (id, tablename, field, tname, width, dateformat, datatype, ishidden, sort, create_date, create_userid, remark, state, islist, isblank, pk_tablename, outputpath, packagename, beanname, modify_date, modify_userid)
values ('20180707024143897V15788', 't_s_menu', 'MODIFY_TYPE', '修改类型(create/update/delete)', '50px', null, 'text', '1', '18', to_date('07-07-2018 02:41:43', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, '0', '0', 'yes', null, null, null, null, null, null);
commit;
prompt 151 records loaded
prompt Loading T_BASE_LOADREDIS...
insert into T_BASE_LOADREDIS (id, table_name, data_source, database_name, remark, create_date, create_name, state, joint_symbol)
values ('201809251955001111', 't_s_user', null, null, null, null, 'admin_user', '1', null);
insert into T_BASE_LOADREDIS (id, table_name, data_source, database_name, remark, create_date, create_name, state, joint_symbol)
values ('201809251955001112', 't_s_role', null, null, null, null, 'admin_user', '1', null);
insert into T_BASE_LOADREDIS (id, table_name, data_source, database_name, remark, create_date, create_name, state, joint_symbol)
values ('201809251955001113', 't_s_role_permreg', null, null, null, null, 'admin_user', '1', null);
insert into T_BASE_LOADREDIS (id, table_name, data_source, database_name, remark, create_date, create_name, state, joint_symbol)
values ('201809251955001114', 't_s_permiss', null, null, null, null, 'admin_user', '1', null);
insert into T_BASE_LOADREDIS (id, table_name, data_source, database_name, remark, create_date, create_name, state, joint_symbol)
values ('201809251955001115', 't_s_dept', null, null, null, null, 'admin_user', '1', null);
insert into T_BASE_LOADREDIS (id, table_name, data_source, database_name, remark, create_date, create_name, state, joint_symbol)
values ('201809251955001116', 't_s_menu', null, null, null, null, 'admin_user', '1', null);
insert into T_BASE_LOADREDIS (id, table_name, data_source, database_name, remark, create_date, create_name, state, joint_symbol)
values ('201809251955001117', 't_code_info', null, null, null, null, 'admin_user', '1', null);
insert into T_BASE_LOADREDIS (id, table_name, data_source, database_name, remark, create_date, create_name, state, joint_symbol)
values ('201809251955001118', 't_base_field', null, null, null, null, 'admin_user', '1', null);
commit;
prompt 8 records loaded
prompt Loading T_CODE_INFO...
insert into T_CODE_INFO (id, pid, name_, isfolder, type, sort, url_, sys_flag, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type, state, remark)
values ('20171202023125V8648', '4028282d5875550a01587557b5040001', '测试修改2', '0', '代码', 12, 'abcd2', null, to_date('02-12-2017 02:31:25', 'dd-mm-yyyy hh24:mi:ss'), null, 'u_admin', to_date('14-04-2018 20:22:03', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, '1', null);
insert into T_CODE_INFO (id, pid, name_, isfolder, type, sort, url_, sys_flag, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type, state, remark)
values ('4028282d5875550a01587557b5040001', null, '菜单', '1', null, 1, null, null, to_date('15-11-2017 21:22:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('09-12-2017 18:53:24', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, '1', null);
insert into T_CODE_INFO (id, pid, name_, isfolder, type, sort, url_, sys_flag, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type, state, remark)
values ('a', '4028282d5875550a01587557b5040034', '用户管理', '类型', null, 1, '/pro/common/treeList.do?type=user', null, to_date('15-11-2017 21:22:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('09-12-2017 02:46:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, '1', null);
insert into T_CODE_INFO (id, pid, name_, isfolder, type, sort, url_, sys_flag, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type, state, remark)
values ('b', '4028282d5875550a01587557b5040034', '角色管理', '0', '菜单', 2, '/pro/roleController/treeList.do', null, to_date('15-11-2017 21:22:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('02-03-2018 01:33:59', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, '1', null);
insert into T_CODE_INFO (id, pid, name_, isfolder, type, sort, url_, sys_flag, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type, state, remark)
values ('c', '4028282d5875550a01587557b5040034', '部门管理', '类型', null, 3, '/pro/common/treeList.do?type=dept', null, to_date('15-11-2017 21:22:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('09-12-2017 02:46:10', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, '1', null);
insert into T_CODE_INFO (id, pid, name_, isfolder, type, sort, url_, sys_flag, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type, state, remark)
values ('d', '4028282d5875550a01587557b5040034', '菜单管理', '0', '菜单', 1, '/pro/common/treeList.do?type=codeMenu', null, to_date('15-11-2017 21:22:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('05-01-2018 14:04:23', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, '1', null);
insert into T_CODE_INFO (id, pid, name_, isfolder, type, sort, url_, sys_flag, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type, state, remark)
values ('4028282d5875550a01587557b5040034', '4028282d5875550a01587557b5040001', '权限管理', '1', null, 1, null, null, to_date('15-11-2017 21:22:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('09-12-2017 18:50:46', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, '1', null);
insert into T_CODE_INFO (id, pid, name_, isfolder, type, sort, url_, sys_flag, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type, state, remark)
values ('20171201220308V2902', '4028282d5875550a01587557b5040001', '生成模式', '类型', '类型', 2, '/pro/genertaFieldController/list.do', null, to_date('01-12-2017 22:03:08', 'dd-mm-yyyy hh24:mi:ss'), null, 'u_admin', to_date('19-12-2017 23:05:14', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, '1', null);
insert into T_CODE_INFO (id, pid, name_, isfolder, type, sort, url_, sys_flag, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type, state, remark)
values ('20171219230322V3150', '4028282d5875550a01587557b5040001', '测试生成', '0', '菜单', 6, '/pro/ceshiTestGenteController/list.do', null, to_date('19-12-2017 23:03:22', 'dd-mm-yyyy hh24:mi:ss'), null, 'u_admin', to_date('05-01-2018 12:57:41', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, '1', null);
commit;
prompt 9 records loaded
prompt Loading T_PUB_GENDERSERIALNOAUTO...
prompt Table is empty
prompt Loading T_S_DEPT...
insert into T_S_DEPT (id, pid, dept_name, remark, create_date, create_userid, create_username, modify_date, modify_userid, state)
values ('D167FD414946459094405E0706CF3673', 'D167FD414946459094405E0706CF3672', '子部门', null, to_date('27-08-2017', 'dd-mm-yyyy'), 'u_admin', 'admin', null, null, '1');
insert into T_S_DEPT (id, pid, dept_name, remark, create_date, create_userid, create_username, modify_date, modify_userid, state)
values ('D167FD414946459094405E0706CF3672', null, '部门', null, to_date('27-08-2017', 'dd-mm-yyyy'), 'u_admin', 'admin', null, null, '1');
commit;
prompt 2 records loaded
prompt Loading T_S_MENU...
insert into T_S_MENU (id, module, pid, name_, url_, type, sys_flag, icon, sort, remark, state, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type)
values ('20171202023125V8648', null, '4028282d5875550a01587557b5040001', '测试修改2', 'abcd2', '0', null, null, 12, '3', '1', to_date('02-12-2017 02:31:25', 'dd-mm-yyyy hh24:mi:ss'), null, 'u_admin', to_date('14-04-2018 20:22:03', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into T_S_MENU (id, module, pid, name_, url_, type, sys_flag, icon, sort, remark, state, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type)
values ('4028282d5875550a01587557b5040001', null, null, '菜单', null, '1', null, null, 1, null, '1', to_date('15-11-2017 21:22:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('09-12-2017 18:53:24', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into T_S_MENU (id, module, pid, name_, url_, type, sys_flag, icon, sort, remark, state, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type)
values ('a', null, '4028282d5875550a01587557b5040034', '用户管理', '/pro/common/treeList.do?type=user', '0', null, null, 1, null, '1', to_date('15-11-2017 21:22:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('09-12-2017 02:46:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into T_S_MENU (id, module, pid, name_, url_, type, sys_flag, icon, sort, remark, state, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type)
values ('b', null, '4028282d5875550a01587557b5040034', '角色管理', '/pro/roleController/treeList.do', '0', null, null, 2, null, '1', to_date('15-11-2017 21:22:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('02-03-2018 01:33:59', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into T_S_MENU (id, module, pid, name_, url_, type, sys_flag, icon, sort, remark, state, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type)
values ('c', null, '4028282d5875550a01587557b5040034', '部门管理', '/pro/common/treeList.do?type=dept', '0', null, null, 3, null, '1', to_date('15-11-2017 21:22:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('09-12-2017 02:46:10', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into T_S_MENU (id, module, pid, name_, url_, type, sys_flag, icon, sort, remark, state, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type)
values ('d', null, '4028282d5875550a01587557b5040034', '菜单管理', '/pro/common/treeList.do?type=codeMenu', '0', null, null, 1, null, '1', to_date('15-11-2017 21:22:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('05-01-2018 14:04:23', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into T_S_MENU (id, module, pid, name_, url_, type, sys_flag, icon, sort, remark, state, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type)
values ('4028282d5875550a01587557b5040034', null, '4028282d5875550a01587557b5040001', '权限管理', null, '1', null, null, 1, null, '1', to_date('15-11-2017 21:22:25', 'dd-mm-yyyy hh24:mi:ss'), null, null, to_date('09-12-2017 18:50:46', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into T_S_MENU (id, module, pid, name_, url_, type, sys_flag, icon, sort, remark, state, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type)
values ('20171201220308V2902', null, '4028282d5875550a01587557b5040001', '生成模式', '/pro/genertaFieldController/list.do', '0', null, null, 2, null, '1', to_date('01-12-2017 22:03:08', 'dd-mm-yyyy hh24:mi:ss'), null, 'u_admin', to_date('19-12-2017 23:05:14', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
insert into T_S_MENU (id, module, pid, name_, url_, type, sys_flag, icon, sort, remark, state, create_date, create_user, create_user_name, modify_date, modify_user, modify_user_name, modify_type)
values ('20171219230322V3150', null, '4028282d5875550a01587557b5040001', '测试生成', '/pro/ceshiTestGenteController/list.do', '0', null, null, 6, null, '1', to_date('19-12-2017 23:03:22', 'dd-mm-yyyy hh24:mi:ss'), null, 'u_admin', to_date('05-01-2018 12:57:41', 'dd-mm-yyyy hh24:mi:ss'), null, null, null);
commit;
prompt 9 records loaded
prompt Loading T_S_PERMISS...
prompt Table is empty
prompt Loading T_S_ROLE...
insert into T_S_ROLE (id, role_name, description, create_date, create_userid, create_user, modify_date, modify_userid, state)
values ('20180228210036V2268', 'admin2', '管理员角色2', to_date('28-02-2018 21:00:36', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', null, to_date('28-02-2018 21:01:07', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin', '1');
commit;
prompt 1 records loaded
prompt Loading T_S_ROLE_PERMREG...
prompt Table is empty
prompt Loading T_S_USER...
insert into T_S_USER (id, user_id, password, dept_id, role_id, real_name, age, phone, msisdn, addresses, email, city, province, country, password_hint, account_enabled, account_expired, account_locked, password_error_times, last_login_time, password_error_lock, rtx_num, remark, state, create_date, create_userid, create_username, modify_date, modify_userid)
values ('u_admin', 'admin', '123', 'D167FD414946459094405E0706CF3672', '660241A049E2488BB06C3D5DBD6EE783', '管理员', '1', '123456', null, '广西', null, 'D167FD414946459094405E0706CF3673', null, null, null, '1', '1', '1', null, null, '1', null, null, '1', to_date('27-08-2017', 'dd-mm-yyyy'), 'u_admin', 'admin', to_date('14-04-2018 20:25:16', 'dd-mm-yyyy hh24:mi:ss'), 'u_admin');
commit;
prompt 1 records loaded
prompt Enabling triggers for SR_FUNC_LIST...
alter table SR_FUNC_LIST enable all triggers;
prompt Enabling triggers for TEST_CESHI...
alter table TEST_CESHI enable all triggers;
prompt Enabling triggers for TEST_CONLLECTION...
alter table TEST_CONLLECTION enable all triggers;
prompt Enabling triggers for TEST_DEPT...
alter table TEST_DEPT enable all triggers;
prompt Enabling triggers for TEST_ITERABLE...
alter table TEST_ITERABLE enable all triggers;
prompt Enabling triggers for TEST_MONTH...
alter table TEST_MONTH enable all triggers;
prompt Enabling triggers for TEST_NUMBER...
alter table TEST_NUMBER enable all triggers;
prompt Enabling triggers for TEST_VECTOR...
alter table TEST_VECTOR enable all triggers;
prompt Enabling triggers for T_BASE_FIELD...
alter table T_BASE_FIELD enable all triggers;
prompt Enabling triggers for T_BASE_LOADREDIS...
alter table T_BASE_LOADREDIS enable all triggers;
prompt Enabling triggers for T_CODE_INFO...
alter table T_CODE_INFO enable all triggers;
prompt Enabling triggers for T_PUB_GENDERSERIALNOAUTO...
alter table T_PUB_GENDERSERIALNOAUTO enable all triggers;
prompt Enabling triggers for T_S_DEPT...
alter table T_S_DEPT enable all triggers;
prompt Enabling triggers for T_S_MENU...
alter table T_S_MENU enable all triggers;
prompt Enabling triggers for T_S_PERMISS...
alter table T_S_PERMISS enable all triggers;
prompt Enabling triggers for T_S_ROLE...
alter table T_S_ROLE enable all triggers;
prompt Enabling triggers for T_S_ROLE_PERMREG...
alter table T_S_ROLE_PERMREG enable all triggers;
prompt Enabling triggers for T_S_USER...
alter table T_S_USER enable all triggers;
set feedback on
set define on
prompt Done.
